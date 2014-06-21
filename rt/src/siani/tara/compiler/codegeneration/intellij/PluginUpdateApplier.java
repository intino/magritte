package siani.tara.compiler.codegeneration.intellij;

import siani.tara.compiler.codegeneration.PathManager;
import siani.tara.compiler.core.CompilerConfiguration;
import siani.tara.compiler.core.errorcollection.TaraException;

import java.io.File;

public class PluginUpdateApplier extends CodeGenerator {

	private static final String SEP = PathManager.SEP;

	public static void update(CompilerConfiguration conf) throws TaraException {
		try {
			File classes = new File(conf.getPluginDirectory(), "classes");
			File outpath = new File(classes, conf.getProject());
			FileSystemUtils.createDir(outpath + SEP + conf.getProject());
			outpath.mkdirs();
			String path = PathManager.getBuildIdeDir(conf.getTempDirectory()) + conf.getProject();
			for (File file : new File(path).listFiles())
				if (file.isDirectory())
					FileSystemUtils.copyDir(file.getAbsolutePath(), outpath.getAbsolutePath() + SEP + file.getName());
			new File(outpath, ".reload").createNewFile();
			new File(outpath, ".model_reload").createNewFile();
		} catch (Exception e) {
			throw new TaraException("Error applying the update: " + e.toString(), true);
		}
	}

}

