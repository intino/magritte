package monet.tara.compiler.codegeneration.intellij;

import monet.tara.compiler.codegeneration.PathManager;
import monet.tara.compiler.codegeneration.ResourceManager;
import monet.tara.compiler.core.CompilerConfiguration;
import monet.tara.compiler.core.errorcollection.TaraException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystemException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class PluginPackager extends CodeGenerator {

	private static final String INTELLIJ_LIBS_ZIP = "intellij/libs.zip";
	private static final String SWING_LIB_PATH = "intellij/swing.zip";
	private static final String SEP = PathManager.SEP;

	public static void doPackage(CompilerConfiguration conf) throws TaraException {
		try {
			String buildPath = composeBuildPath(conf);
			FileSystemUtils.createDir(buildPath);
			File libs = ResourceManager.getFile(INTELLIJ_LIBS_ZIP);
			File swing = ResourceManager.getFile(SWING_LIB_PATH);
			writeLibs(libs, buildPath);
			writeLibs(libs, buildPath + conf.getProject() + SEP);
			writeLibs(swing, buildPath + conf.getProject() + SEP);
			addDependenciesAndRes(buildPath + conf.getProject() + SEP, conf.getTempDirectory());
			FileSystemUtils.zipDir(buildPath + SEP + conf.getProject() + ".jar", buildPath + SEP + conf.getProject() + SEP);
			FileSystemUtils.removeDir(buildPath + SEP + conf.getProject() + SEP);
			File toZip = new File(buildPath).getParentFile().getParentFile();
			conf.getTargetDirectory().mkdirs();
			FileSystemUtils.zipDir(conf.getTargetDirectory() + SEP + conf.getProject() + ".zip", toZip.getPath());
			FileSystemUtils.removeDir(toZip);
		} catch (IOException e) {
			throw new TaraException("Error during packaging: " + e.getMessage(), true);
		}
	}

	private static void addDependenciesAndRes(String destiny, File tempDir) throws FileSystemException {
		File resDir = new File(PathManager.getBuildIdeResDir(tempDir));
		File metainf = new File(PathManager.getSrcIdeDir(tempDir) + "META-INF");
		if (metainf.exists()) FileSystemUtils.copyDir(metainf, new File(destiny + "META-INF"));
		if (resDir.listFiles() != null)
			for (File file : resDir.listFiles()) {
				if (file.isDirectory()) FileSystemUtils.copyDir(file.getAbsolutePath(), destiny + file.getName());
				else FileSystemUtils.copyFile(file.getAbsolutePath(), destiny + file.getName());
			}

	}

	private static String composeBuildPath(CompilerConfiguration conf) {
		return PathManager.getBuildIdeDir(conf.getTempDirectory()) + conf.getProject() + SEP + "lib" + SEP;
	}

	public static void writeLibs(File libs, String buildPath) throws IOException {
		ZipFile zipFile = new ZipFile(libs);
		Enumeration entries = zipFile.entries();
		Map<String, InputStream> jars = new HashMap<>();
		while (entries.hasMoreElements()) {
			ZipEntry entry = (ZipEntry) entries.nextElement();
			if (entry.isDirectory()) {
				mkdirs(new File(buildPath), entry.getName());
				continue;
			}
			jars.put(entry.getName(), zipFile.getInputStream(entry));
		}
		for (String file : jars.keySet())
			FileSystemUtils.writeInputStream(jars.get(file), new File(buildPath + file));
		zipFile.close();
	}

	private static void mkdirs(File outdir, String path) {
		File d = new File(outdir, path);
		if (!d.exists())
			d.mkdirs();
	}

}

