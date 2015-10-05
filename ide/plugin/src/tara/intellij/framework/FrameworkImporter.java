package tara.intellij.framework;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.platform.templates.github.ZipUtil;
import tara.intellij.actions.utils.FileSystemUtils;
import tara.intellij.lang.TaraLanguage;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static java.io.File.separator;

public class FrameworkImporter {
	private static final Logger LOG = Logger.getInstance(FrameworkImporter.class.getName());

	private static final String FRAMEWORK = "framework";
	private static final String DSL = "dsl";

	private final Map<String, File> languages;
	private final String dsl;

	public FrameworkImporter(Map<String, File> languages, String dsl) {
		this.languages = languages;
		this.dsl = dsl;
	}

	void importDslAndFramework(ModifiableRootModel rootModel) {
		if (languages.containsKey(this.dsl)) importDslAndFramework(rootModel.getProject().getBaseDir());
	}

	private File importDslAndFramework(VirtualFile projectDirectory) {
		final File file = languages.get(this.dsl);
		if (!file.exists()) return null;
		File destiny;
		try {
			if (isJar(file)) {
				destiny = new File(projectDirectory.getPath() + separator + FRAMEWORK + separator + dsl, file.getName());
				FileSystemUtils.copyFile(file.getPath(), destiny.getPath());
			} else {
				ZipUtil.unzip(null, new File(projectDirectory.getPath()), new File(file.getPath()), null, null, false);
				destiny = new File(projectDirectory.getPath() + separator + dsl);
			}
			if (!TaraLanguage.PROTEO.equals(dsl)) reload(new File(projectDirectory.getPath(), DSL).getPath());
			return destiny.isDirectory() ? destiny : destiny.getParentFile();
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
	}

	private boolean isJar(File file) {
		return file.getName().endsWith(".jar");
	}

	private void reload(String languagesPath) {
		File reload = new File(languagesPath, dsl + ".reload");
		try {
			reload.createNewFile();
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
	}

}
