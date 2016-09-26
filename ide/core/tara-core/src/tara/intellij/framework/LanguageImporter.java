package tara.intellij.framework;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications.Bus;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.io.FileUtilRt;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.platform.templates.github.ZipUtil;
import org.jetbrains.idea.maven.project.MavenProject;
import org.jetbrains.idea.maven.project.MavenProjectsManager;
import tara.intellij.lang.LanguageManager;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.project.configuration.Configuration;
import tara.intellij.project.configuration.maven.MavenHelper;
import tara.intellij.settings.TaraSettings;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.TreeMap;

import static tara.intellij.framework.LanguageInfo.LATEST_VERSION;
import static tara.intellij.framework.LanguageInfo.PROTEO;

public class LanguageImporter {

	private static final Logger LOG = Logger.getInstance(LanguageImporter.class.getName());
	private static final String MODULE_TAG = "$module";
	private static final String POM_XML = "pom.xml";
	private static final String TEMP_POM_XML = "_pom.xml.itr";

	private Module module;

	public LanguageImporter(Module module) {
		this.module = module;
	}

	public String importLanguage(String dsl, String version) {
		try {
			final String snapshotRepository = new MavenHelper(module).snapshotRepository();
			final String versionCode = getVersion(dsl, version, snapshotRepository);
			final Configuration conf = TaraUtil.configurationOf(module);
			if (!PROTEO.getName().equals(dsl)) doImportLanguage(dsl, downloadLanguage(dsl, versionCode, snapshotRepository));
			if (conf != null) conf.dslVersion(versionCode);
			return versionCode;
		} catch (IOException e) {
			error(e);
			return null;
		}
	}

	private File downloadLanguage(String name, String version, String snapshotRepository) {
		try {
			File dslFile = new File(FileUtil.getTempDirectory(), name + "_" + version + ".dsl");
			new ArtifactoryConnector(TaraSettings.getSafeInstance(module.getProject()), snapshotRepository).get(dslFile, name, version);
			return dslFile;
		} catch (IOException e) {
			error(e);
			return null;
		}
	}

	private String getVersion(String key, String version, String snapshotRepository) throws IOException {
		if (LATEST_VERSION.equals(version)) {
			TreeMap<Long, String> versions = new TreeMap<>();
			new ArtifactoryConnector(TaraSettings.getSafeInstance(module.getProject()), snapshotRepository).versions(key).forEach(v -> versions.put(indexOf(v), v));
			return versions.get(versions.lastKey());
		} else return version;
	}

	private Long indexOf(String version) {
		String value = "";
		String[] split = (version.contains("-") ? version.substring(0, version.indexOf("-")) : version).split("\\.");
		int times = split.length - 1;
		if (times == 0) return Long.parseLong(version);
		for (String s : split) {
			if (s.length() < 2) value += new String(new char[2 - s.length()]).replace("\0", "0");
			value += s;
		}
		return Long.parseLong(value);
	}

	private void doImportLanguage(String name, File file) {
		if (file == null || !file.exists()) {
			errorReading(file);
			return;
		}
		final VirtualFile taraDirectory = LanguageManager.getTaraDirectory();
		boolean success = unzip(file, taraDirectory);
		if (!success) errorReading(file);
		pom(module);
		reload(name, module.getProject());
		if (file.exists()) file.delete();
	}


	private boolean unzip(File file, VirtualFile taraDirectory) {
		try {
			ZipUtil.unzip(null, new File(taraDirectory.getPath()), new File(file.getPath()), null, null, false);
			return true;
		} catch (IOException e) {
			LOG.error(e.getMessage());
			return false;
		}
	}

	private void pom(Module module) {
		try {
			customizePom(LanguageManager.getTaraDirectory(), module);
			syncPom(module, new File(module.getProject().getBaseDir().getPath()));
		} catch (IOException e) {
			LOG.error(e.getMessage());
			error(e);
		}
	}

	private void customizePom(VirtualFile taraDirectory, Module module) throws IOException {
		final File pom = new File(taraDirectory.getPath(), TEMP_POM_XML);
		if (!pom.exists()) return;
		final Path destiny = destiny(new File(module.getModuleFilePath()).getParentFile());
		if (!destiny.toFile().exists())
			Files.write(destiny, new String(Files.readAllBytes(pom.toPath())).replace(MODULE_TAG, module.getName().toLowerCase()).getBytes());
		pom.delete();
	}

	private Path destiny(File moduleDirectory) {
		return new File(moduleDirectory, "pom.xml").toPath();
	}

	private void syncPom(Module module, File projectDirectory) throws IOException {
		final String parentPomPath = FileUtil.findFileInProvidedPath(projectDirectory.getPath(), TEMP_POM_XML);
		if (parentPomPath == null || parentPomPath.isEmpty()) return;
		final File parentPom = new File(parentPomPath);
		final File childPom = new File(new File(module.getModuleFilePath()).getParentFile(), POM_XML);
		if (!childPom.exists()) {
			Files.move(parentPom.toPath(), childPom.toPath(), StandardCopyOption.REPLACE_EXISTING);
			initPom(module, childPom);
		} else {
			if (!FileUtilRt.delete(parentPom)) errorRemoving(parentPom);
			final MavenProject project = MavenProjectsManager.getInstance(module.getProject()).findProject(module);
			MavenProjectsManager.getInstance(module.getProject()).forceUpdateProjects(Collections.singletonList(project));
		}
	}

	private void initPom(Module module, File pomFile) {
		final VirtualFile pomVirtualFile = VfsUtil.findFileByIoFile(pomFile, true);
		MavenProjectsManager manager = MavenProjectsManager.getInstance(module.getProject());
		manager.addManagedFilesOrUnignore(Collections.singletonList(pomVirtualFile));
		manager.importProjects();
		manager.forceUpdateAllProjectsOrFindAllAvailablePomFiles();
	}

	private void reload(String fileName, Project project) {
		LanguageManager.reloadLanguage(project, FileUtil.getNameWithoutExtension(fileName));
	}

	private void errorReading(File file) {
		if (file == null)
			Bus.notify(new Notification("Tara Language", "File is null", "", NotificationType.ERROR));
		else
			Bus.notify(new Notification("Tara Language", "Error reading file.", file.getName(), NotificationType.ERROR));
	}

	private void errorRemoving(File file) {
		Bus.notify(new Notification("Tara Language", "Error removing file.", file.getName(), NotificationType.ERROR));
	}

	private void error(IOException e) {
		Bus.notify(new Notification("Tara Language", "Error connecting with Artifactory.", e.getMessage(), NotificationType.ERROR));
	}
}
