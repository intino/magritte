package tara.intellij.framework;

import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.libraries.Library;
import com.intellij.openapi.vfs.VirtualFile;
import tara.intellij.TaraRuntimeException;
import tara.intellij.actions.utils.FileSystemUtils;
import tara.intellij.lang.TaraLanguage;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.util.List;

import static java.io.File.separator;
import static tara.intellij.lang.TaraLanguage.DSL;
import static tara.intellij.lang.TaraLanguage.FRAMEWORK;
import static tara.intellij.lang.TaraLanguage.TARA;

public class FrameworkLanguageExtensionSupport {
	private static final String LEVEL = "level";
	private static final String TARA_PREFIX = "Tara -> ";
	private final File moduleDir;
	private final File confFile;

	private String dsl;
	private Integer level;

	public FrameworkLanguageExtensionSupport(File moduleDir, File confFile) {
		this.moduleDir = moduleDir;
		this.confFile = confFile;
		getConfiguration();
	}

	private void getConfiguration() {
		try {
			final List<String> lines = Files.readAllLines(confFile.toPath());
			for (String line : lines) {
				if (line.trim().startsWith("<" + DSL + ">"))
					dsl = line.trim().replace("<" + DSL + ">", "").replace("</" + DSL + ">", "");
				if (line.trim().startsWith("<" + LEVEL + ">"))
					level = Integer.parseInt(line.trim().replace("<" + LEVEL + ">", "").replace("</" + LEVEL + ">", ""));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (dsl == null) dsl = TaraLanguage.PROTEO;
		if (level == null) level = 2;

	}

	public void importLanguageDependency(ModifiableRootModel rootModel) {
		final VirtualFile taraDirectory = getDirectory(rootModel);
		createFrameworkDirectory(taraDirectory);
		importFolder(taraDirectory.findChild(DSL), DSL);
		importFolder(taraDirectory.findChild(FRAMEWORK), FRAMEWORK);
		addDslFrameworkLibToProject(rootModel);
		addPomToProject(new File(rootModel.getModule().getModuleFilePath()).getParent());
	}

	public VirtualFile getDirectory(ModifiableRootModel rootModel) {
		final VirtualFile baseDir = rootModel.getProject().getBaseDir();
		final VirtualFile child = baseDir.findChild(TARA);
		if (child == null) try {
			return baseDir.createChildDirectory(null, TARA);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return child;
	}

	public String getDsl() {
		return dsl;
	}

	public Integer getLevel() {
		return level;
	}

	private void addPomToProject(String path) {
		if (!new File(path, "pom.xml").exists()) return;
		FileSystemUtils.copyFile(path, new File(moduleDir.getAbsolutePath(), "pom.xml").getAbsolutePath());
	}

	private void importFolder(VirtualFile destiny, String folder) {
		if (destiny == null) return;
		File sourceFolder = new File(moduleDir, folder).exists() ? new File(moduleDir, folder) : new File(moduleDir.getParent(), folder);
		final File source = new File(sourceFolder, dsl);
		if (!source.exists()) return;
		try {
			FileSystemUtils.copyDir(source.getPath(), new File(destiny.getPath(), dsl).getAbsolutePath(), false);
		} catch (FileSystemException e) {
			throw new TaraRuntimeException("Impossible to import " + folder);
		}
	}

	private void addDslFrameworkLibToProject(ModifiableRootModel rootModel) {
		if (!new File(moduleDir, FRAMEWORK).exists()) return;
		final Library library = FrameworkSetupHelper.addProjectLibrary(rootModel.getModule(), TARA_PREFIX + dsl, createFrameworkDirectory(rootModel.getProject().getBaseDir()), VirtualFile.EMPTY_ARRAY);
		rootModel.addLibraryEntry(library);
	}

	private File createFrameworkDirectory(VirtualFile baseDir) {
		final File framework = new File(baseDir.getPath() + separator + FRAMEWORK, dsl);
		framework.mkdir();
		return framework;
	}
}
