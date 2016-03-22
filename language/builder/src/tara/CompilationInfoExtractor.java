package tara;

import tara.compiler.constants.TaraBuildConstants;
import tara.compiler.core.CompilerConfiguration;
import tara.compiler.core.errorcollection.message.WarningMessage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static tara.compiler.constants.TaraBuildConstants.*;

public class CompilationInfoExtractor {
	private static final Logger LOG = Logger.getLogger(TaraCompilerRunner.class.getName());

	public static void getInfoFromArgsFile(File argsFile, CompilerConfiguration configuration, List<Map<File, Boolean>> srcFiles) {
		BufferedReader reader = null;
		configuration.setOutput(new PrintWriter(System.err));
		configuration.setWarningLevel(WarningMessage.PARANOIA);
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(argsFile)));
			String line;
			readSrc(srcFiles.get(0), DEF_FILE, reader);
			readSrc(srcFiles.get(1), MODEL_FILE, reader);
			line = readSrc(srcFiles.get(2), TEST_MODEL_FILE, reader);
			processArgs(configuration, reader, line);
		} catch (IOException e) {
			LOG.log(Level.SEVERE, "Error getting Args IO: " + e.getMessage(), e);
		} finally {
			try {
				assert reader != null;
				reader.close();
			} catch (IOException e) {
				LOG.log(Level.SEVERE, "Error getting Args IO2: " + e.getMessage(), e);
			}
		}
	}

	private static String readSrc(Map<File, Boolean> srcFiles, String type, BufferedReader reader) throws IOException {
		String line;
		while (!"".equals(line = reader.readLine())) {
			if (type.equals(line)) continue;
			final String[] split = line.split("#");
			final File file = new File(split[0]);
			srcFiles.put(file, Boolean.valueOf(split[1]));
		}
		return line;
	}

	private static void processArgs(CompilerConfiguration configuration, BufferedReader reader, String line) throws IOException {
		String aLine = line;
		while (aLine != null) {
			processLine(configuration, reader, aLine);
			aLine = reader.readLine();
		}
	}

	private static void processLine(CompilerConfiguration configuration, BufferedReader reader, String aLine) throws IOException {
		switch (aLine) {
			case TaraBuildConstants.ENCODING:
				configuration.setSourceEncoding(reader.readLine());
				break;
			case TaraBuildConstants.OUTPUTPATH:
				configuration.setOutDirectory(new File(reader.readLine()));
				break;
			case TaraBuildConstants.FINAL_OUTPUTPATH:
				configuration.setFinalOutputDirectory(reader.readLine());
				break;
			case TaraBuildConstants.PROJECT:
				configuration.setProject(reader.readLine());
				break;
			case TaraBuildConstants.RESOURCES:
				configuration.setResourcesDirectory(new File(reader.readLine()));
				break;
			case TaraBuildConstants.MODULE:
				configuration.setModule(reader.readLine());
				break;
			case TaraBuildConstants.MODEL_LEVEL:
				configuration.setLevel(Integer.valueOf(reader.readLine()));
				break;
			case TaraBuildConstants.EXCLUDED_PHASES:
				configuration.setExcludedPhases(parseToInt(reader.readLine().split(" ")));
				break;
			case TaraBuildConstants.STASH_GENERATION:
				setStashGeneration(configuration, reader);
				break;
			case TaraBuildConstants.SEMANTIC_LIB:
				configuration.setSemanticRulesLib(new File(reader.readLine()));
				break;
			case TaraBuildConstants.GENERATED_LANG_NAME:
				configuration.setGeneratedLanguage(reader.readLine());
				break;
			case TaraBuildConstants.DYNAMIC_LOAD:
				configuration.setDynamicLoad(Boolean.valueOf(reader.readLine()));
				break;
			case TaraBuildConstants.MAKE:
				configuration.setMake(Boolean.valueOf(reader.readLine()));
				break;
			case TaraBuildConstants.TEST:
				configuration.setTest(Boolean.valueOf(reader.readLine()));
				break;
			case TaraBuildConstants.ONTOLOGY:
				configuration.setOntology(Boolean.valueOf(reader.readLine()));
				break;
			case TaraBuildConstants.PLATFORM_REFACTOR_ID:
				configuration.setEngineRefactorId(Integer.valueOf(reader.readLine()));
				break;
			case TaraBuildConstants.APPLICATION_REFACTOR_ID:
				configuration.setApplicationRefactorId(Integer.valueOf(reader.readLine()));
				break;
			case TaraBuildConstants.SRC_PATH:
				configuration.setSrcPath(new File(reader.readLine()));
				break;
			case TaraBuildConstants.TARA_PATH:
				configuration.setTaraDirectory(new File(reader.readLine()));
				break;
			case TaraBuildConstants.NATIVES_PATH:
				configuration.setNativePath(new File(reader.readLine()));
				break;
			case TaraBuildConstants.NATIVES_LANGUAGE:
				configuration.nativeLanguage(reader.readLine());
				break;
			case TaraBuildConstants.LANGUAGE:
				configuration.setLanguage(reader.readLine());
				break;
			case TaraBuildConstants.MAGRITTE:
				configuration.magritteLibrary(reader.readLine());
				break;
			case TaraBuildConstants.RULES:
				configuration.setRulesDirectory(new File(reader.readLine()));
				break;
			default:
				break;
		}
	}

	private static void setStashGeneration(CompilerConfiguration conf, BufferedReader reader) throws IOException {
		final boolean stashGeneration = Boolean.parseBoolean(reader.readLine());
		conf.setStashGeneration(stashGeneration);
	}

	private static List<Integer> parseToInt(String[] phases) throws IOException {
		List<Integer> list = new ArrayList<>();
		for (String phase : phases) list.add(Integer.parseInt(phase));
		return list;
	}
}
