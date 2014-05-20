package monet.tara.compiler.codegeneration.intellij;

import com.intellij.xml.actions.xmlbeans.FileUtils;
import monet.tara.compiler.codegeneration.PathManager;
import monet.tara.compiler.core.CompilerConfiguration;

import java.io.File;
import java.io.FileFilter;

public class TemplateGenerator extends CodeGenerator {

	public static void generateTemplates(CompilerConfiguration conf) {
		String iconsPath = PathManager.RES + PathManager.SEP + PathManager.SEP + "icons";
		for (String dir : conf.getIconDirectories()) {
			for (File file : new File(dir).listFiles(new FileFilter() {
				@Override
				public boolean accept(File pathname) {
					return pathname.getName().endsWith(".png");
				}
			})) {
				FileUtils.copyFile(file, new File(new File(iconsPath), file.getName()));
			}
		}

	}
}
