package monet.tara.compiler.code_generation.intellij;

import monet.tara.compiler.core.CompilerConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class PluginPackager extends CodeGenerator {

	private static final String libPath = "/intellij/libs.zip";

	public static void doPackage(CompilerConfiguration conf) {
		try {
			String buildPath = composeBuildPath(conf);
			FileSystemUtils.createDir(buildPath);
			File libs = new File(PluginPackager.class.getResource(libPath).getPath());
			writeLibs(libs, buildPath);
			writeLibs(libs, buildPath + SEP + conf.getProject() + SEP);
			FileSystemUtils.zipDir(buildPath + SEP + conf.getProject() + ".jar", buildPath + SEP + conf.getProject() + SEP);
			FileSystemUtils.removeDir(buildPath + SEP + conf.getProject() + SEP);
			File toZip = new File(buildPath).getParentFile().getParentFile();
			conf.getTargetDirectory().mkdirs();
			FileSystemUtils.zipDir(conf.getTargetDirectory() + SEP + conf.getProject() + ".zip", toZip.getPath());
//			FileSystemUtils.removeDir(toZip);
		} catch (Exception e) {
			e.printStackTrace();
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
			jars.put(entry.getName(), zipFile.getInputStream(entry));
		}
		for (String file : jars.keySet())
			FileSystemUtils.writeInputStream(jars.get(file), new File(buildPath + file));
		zipFile.close();
	}

}

