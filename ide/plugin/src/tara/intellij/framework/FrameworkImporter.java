package tara.intellij.framework;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.platform.templates.github.ZipUtil;
import tara.intellij.lang.LanguageManager;
import tara.intellij.lang.TaraLanguage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Map;

import static java.io.File.separator;

public class FrameworkImporter {
	private static final Logger LOG = Logger.getInstance(FrameworkImporter.class.getName());

	private static final String MODULE_TAG = "##ChangeIt##";
	private static final String POM_XML = "pom.xml";
	private static final String TEMP_POM_XML = "_pom.xml";

	private final Map<String, File> languages;
	private final String dsl;

	public FrameworkImporter(Map<String, File> languages, String dsl) {
		this.languages = languages;
		this.dsl = dsl;
	}

	void importLanguage(ModifiableRootModel rootModel) {
		if (languages.containsKey(this.dsl)) {
			VirtualFile tara = LanguageManager.getTaraDirectory(rootModel.getProject());
			importLanguage(tara, rootModel.getModule());
		}
	}

	private File importLanguage(VirtualFile taraDirectory, Module module) {
		final File file = languages.get(this.dsl);
		File destiny = LanguageManager.getProteoLibrary(module.getProject());
		try {
			if (TaraLanguage.PROTEO.equals(this.dsl)) downloadFramework(destiny);
			else {
				ZipUtil.unzip(null, new File(taraDirectory.getPath()), new File(file.getPath()), null, null, false);
				destiny = new File(taraDirectory.getPath() + separator + dsl);
				pom(taraDirectory, module);
				LanguageManager.reloadLanguage(dsl, module.getProject());
			}
			return destiny.isDirectory() ? destiny : destiny.getParentFile();
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
	}

	private void downloadFramework(File destiny) {
		new LanguageNetImporter(LanguageManager.PROTEO_SOURCE).downloadTo(destiny);
	}

	private void pom(VirtualFile projectDirectory, Module module) {
		final File moduleDirectory = new File(module.getModuleFilePath()).getParentFile();
		final File projectDir = new File(projectDirectory.getPath());
		try {
			customizePom(projectDirectory, module.getName());
			movePom(projectDir, moduleDirectory);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void customizePom(VirtualFile projectDirectory, String module) throws IOException {
		File pom = new File(projectDirectory.getPath(), TEMP_POM_XML);
		if (!pom.exists()) return;
		final Path pomPath = pom.toPath();
		String pomContent = new String(Files.readAllBytes(pomPath)).replace(MODULE_TAG, module);
		Files.write(pomPath, pomContent.getBytes());
	}

	private void movePom(File projectDirectory, File moduleDirectory) throws IOException {
		final String child = FileUtil.findFileInProvidedPath(projectDirectory.getPath(), TEMP_POM_XML);
		if (child == null || child.isEmpty()) return;
		Files.move(new File(child).toPath(), new File(moduleDirectory, POM_XML).toPath(), StandardCopyOption.REPLACE_EXISTING);
	}
}
