package monet.tara.compiler.code_generation.intellij;

import monet.tara.compiler.core.CompilerConfiguration;
import monet.tara.compiler.core.error_collection.TaraException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystemException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class PluginPackager extends CodeGenerator {

	private static final String libPath = "/intellij/libs.zip";
	private static final String swingLibPath = "/intellij/swing.zip";


	public static void doPackage(CompilerConfiguration conf) throws TaraException {
		try {
			String buildPath = composeBuildPath(conf);
			FileSystemUtils.createDir(buildPath);
			File libs = new File(PluginPackager.class.getResource(libPath).getPath());
			File swing = new File(PluginPackager.class.getResource(swingLibPath).getPath());
			writeLibs(libs, buildPath);
			writeLibs(libs, buildPath + conf.getProject() + SEP);
			writeLibs(swing, buildPath + conf.getProject() + SEP);
			addDependenciesAndRes(buildPath + conf.getProject() + SEP, conf.getTempDirectory().getAbsolutePath());
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

	private static void addDependenciesAndRes(String destiny, String tempDir) throws FileSystemException {
		String resDir = tempDir + SEP + SRC + SEP + IDE + RES;
		File metainf = new File(tempDir + SEP + SRC + SEP + IDE + SRC + SEP + "META-INF");
		if (metainf.exists()) FileSystemUtils.copyDir(metainf, new File(destiny + "META-INF"));
		for (File file : new File(resDir).listFiles()) {
			if (file.isDirectory()) FileSystemUtils.copyDir(file.getAbsolutePath(), destiny + file.getName());
			else FileSystemUtils.copyFile(file.getAbsolutePath(), destiny + file.getName());
		}

	}

	private static String composeBuildPath(CompilerConfiguration conf) {
		return conf.getTempDirectory() + SEP + BUILD + SEP + IDE + conf.getProject() + SEP + "lib" + SEP;
	}

	public static void writeLibs(File libs, String buildPath) throws IOException {
		ZipFile zipFile = new ZipFile(libs);
		Enumeration entries = zipFile.entries();
		HashMap<String, InputStream> jars = new HashMap<>();
		while (entries.hasMoreElements()) {
			ZipEntry entry = (ZipEntry) entries.nextElement();
			if (entry.isDirectory()) {
				if (!entry.getName().startsWith("__"))
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

