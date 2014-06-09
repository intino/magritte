package siani.tara.compiler.codegeneration.intellij;

import siani.tara.compiler.codegeneration.IconFactory;
import siani.tara.compiler.codegeneration.JavaCommandHelper;
import siani.tara.compiler.codegeneration.PathManager;
import siani.tara.compiler.codegeneration.ResourceManager;
import siani.tara.compiler.codegeneration.render.RenderUtils;
import siani.tara.compiler.core.CompilerConfiguration;
import siani.tara.compiler.core.errorcollection.TaraException;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PluginCompiler extends CodeGenerator {
	public static final String LIB = "lib";
	private static final Logger LOG = Logger.getLogger(PluginCompiler.class.getName());
	private static final String SEP = PathManager.SEP;
	private static CompilerConfiguration conf;

	public static void generateClasses(CompilerConfiguration configuration) throws TaraException {
		Runtime rt = Runtime.getRuntime();
		try {
			PluginCompiler.conf = configuration;
			Process compileProcess = rt.exec(makeCompileCommand(getSources()));
			if (compileProcess.waitFor() == -1) throw new TaraException("Plugin Compilation failed");
			String errorMessage = printResult(compileProcess);
			if (isFatal(errorMessage)) throw new TaraException("Plugin Compilation failed");
			addIcons();
		} catch (IOException | InterruptedException e) {
			LOG.severe(e.getLocalizedMessage());
		}
	}

	private static File[] getSources() {
		return FileSystemUtils.listFiles(PathManager.getSrcDir(conf.getTempDirectory()), new FileFilter() {
			@Override
			public boolean accept(File file) {
				return !file.getName().startsWith(".") && (file.getName().endsWith(".java") | file.isDirectory());
			}
		});
	}

	private static String[] makeCompileCommand(File[] sources) throws TaraException {
		try {
			List<String> cmd = JavaCommandHelper.buildJavaCompileCommandLine(sources, getClassPath(),
				new String[]{"-encoding", System.getProperty("file.encoding")},
				getBuildDirectory());
			return cmd.toArray(new String[cmd.size()]);
		} catch (IOException e) {
			LOG.severe(e.getMessage());
			throw new TaraException("Error compiling plugin");
		}
	}

	private static String getBuildDirectory() {
		return PathManager.getBuildIdeDir(conf.getTempDirectory()) + conf.getProject() + SEP + LIB + SEP + conf.getProject();
	}

	private static void addIcons() throws TaraException {
		try {
			for (String icon : IconFactory.getIcons().keySet())
				FileSystemUtils.copyFile(ResourceManager.getStream(IconFactory.getIcon(icon)), new File(getDestinyOf(IconFactory.getIcon(icon))));
			if (conf.getProjectIcon() != null && new File(conf.getProjectIcon()).exists())
				FileSystemUtils.copyFile(conf.getProjectIcon(), getDestinyOf(IconFactory.getIcon("-.png")));
			File iconDefinitions = new File(getBuildDirectory() + SEP + "icons" + SEP + "definitions" + SEP);
			iconDefinitions.mkdir();
			for (String iconDir : conf.getIconDirectories()) {
				for (File icon : new File(iconDir).listFiles(new FileFilter() {
					@Override
					public boolean accept(File pathname) {
						return pathname.getName().endsWith(".png");
					}
				})) {
					File file = new File(iconDefinitions.getPath(), icon.getName().toLowerCase());
					FileSystemUtils.copyFile(icon.getAbsolutePath(), file.getAbsolutePath());
				}
			}
		} catch (FileSystemException e) {
			LOG.severe(e.getMessage());
			throw new TaraException("Error adding icons to the plugin");
		}
	}

	private static String getDestinyOf(String tpl) {
		String template = tpl.substring(tpl.indexOf("res") + 3);
		template = template.replace("_", "Definition").replace("/tara/", '/' + conf.getProject().toLowerCase() + '/');
		template = template.replace("-", RenderUtils.toProperCase(conf.getProject())).replaceAll("/", ("\\".equals(SEP)) ? "\\\\" : SEP);
		return getBuildDirectory() + template;
	}

	private static String[] getClassPath() throws TaraException {
		List<String> jarNames = new ArrayList<>();
		jarNames.add(conf.getIdeaHome() + "*");
		jarNames.add(getJar("markdown4j-2.2.jar"));
		jarNames.add(getJar("commons-email-1.3.2.jar"));
		jarNames.add(getJar("javax.mail.jar"));
		jarNames.add(getJar("gson-2.2.4.jar"));
		return jarNames.toArray(new String[jarNames.size()]);
	}

	private static String getJar(String jar) throws TaraException {
		String path = ResourceManager.get(jar);
		if (path.contains("!")) {
			path = path.substring(0, path.indexOf('!')).replace("file:", "");
			if (path.contains(":")) path = path.substring(0);
			path = path.substring(0, path.lastIndexOf('/') + 1) + jar;
			path = new File(path).getAbsolutePath();
			if (!new File(path).exists()) throw new TaraException("Libs not found");
		}
		return path;
	}

	private static boolean isFatal(String errorMessage) {
		return errorMessage != null && !errorMessage.equals("") && !errorMessage.startsWith("Note");
	}
}