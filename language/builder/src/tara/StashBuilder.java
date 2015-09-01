package tara;

import tara.compiler.constants.TaraBuildConstants;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class StashBuilder {

	public static final char NL = '\n';

	private StashBuilder() {
	}

	public static void build(String home, String... taraFiles) throws Exception {
		File argsFile = createConfigurationFile(home, taraFiles);
		if (argsFile == null) throw new Exception("Arguments file for Tara compiler not found");
		TaracRunner.main(new String[]{argsFile.getAbsolutePath()});
	}

	public static void buildAll(String home) throws Exception {
		final Set<String> set = buildFileSet(new File(home));
		File argsFile = createConfigurationFile(home, set.toArray(new String[set.size()]));
		if (argsFile == null) throw new Exception("Arguments file for Tara compiler not found");
		TaracRunner.main(new String[]{argsFile.getAbsolutePath()});
	}


	private static Set<String> buildFileSet(File root) {
		return getTaraFiles(root, root);
	}

	private static Set<String> getTaraFiles(File folder, File root) {
		Set<String> files = taraFilesIn(folder, root);
		for (File file : folder.listFiles(File::isDirectory))
			files.addAll(getTaraFiles(file, root));
		return files;
	}

	private static Set<String> taraFilesIn(File folder, File root) {
		File[] files = folder.listFiles(StashBuilder::taraFile);
		Set<String> result = new LinkedHashSet<>(files.length);
		for (File file : files) result.add(getNameSpace(file, root));
		return result;
	}

	private static boolean taraFile(File dir, String name) {
		return name.endsWith(".tara");
	}

	private static String getNameSpace(File file, File root) {
		return file.getAbsolutePath().substring(root.getAbsolutePath().length() + 1).replace(".tara", "").replace(File.separator, ".");
	}


	private static File createConfigurationFile(String home, String[] taraFiles) throws Exception {
		try {
			File argsFile = Files.createTempFile(new File(".").toPath(), "__", "__").toFile();
			argsFile.deleteOnExit();
			fillArgs(argsFile, home, taraFiles);
			return argsFile;
		} catch (IOException e) {
			throw new Exception("Error creating temp file", e);
		}
	}

	private static void fillArgs(File argsFile, String home, String[] taraFiles) throws Exception {
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(argsFile)))) {
			writer.write(TaraBuildConstants.SRC_FILE + NL);
			for (String file : getSources(home, taraFiles))
				writer.write(file + NL);
			writer.write(NL);
			writePaths(home, writer);
			writer.write(TaraBuildConstants.MODEL_LEVEL + NL + 0 + NL);
			writer.write(TaraBuildConstants.EXCLUDED_PHASES + NL + 4 + " " + 6 + " " + 8 + NL);
			writer.write(TaraBuildConstants.STASH_GENERATION + NL + "true" + NL);
			writer.close();
		} catch (IOException e) {
			throw new Exception("Error filling args file", e);
		}
	}

	private static String[] getSources(String home, String[] taraFiles) {
		if (taraFiles.length == 0) {
			List<String> files = new ArrayList<>();
			collectFiles(new File(home), files);
			return files.toArray(new String[files.size()]);
		} else return taraFiles;
	}

	private static void writePaths(String home, Writer writer) throws IOException {
//		writer.write(TaraBuildConstants.SEMANTIC_LIB + NL + semanticLib + NL);
		writer.write(TaraBuildConstants.OUTPUTPATH + NL + home + NL);
		writer.write(TaraBuildConstants.FINAL_OUTPUTPATH + NL + home + NL);
		writer.write(TaraBuildConstants.RESOURCES + NL + home + NL);
	}

	private static void collectFiles(File home, List<String> files) {
		for (File file : home.listFiles((dir, name) -> name.endsWith(".tara")))
			if (file.isDirectory()) collectFiles(file, files);
			else files.add(file.getAbsolutePath());
	}
}