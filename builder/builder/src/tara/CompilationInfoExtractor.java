package tara;

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
	private static final Logger LOG = Logger.getGlobal();

	public static void getInfoFromArgsFile(File argsFile, CompilerConfiguration configuration, Map<File, Boolean> srcFiles) {
		BufferedReader reader = null;
		configuration.setWarningLevel(WarningMessage.PARANOIA);
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(argsFile)));
			processArgs(configuration, reader, readSrc(srcFiles, SRC_FILE, reader));
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
			case ENCODING:
				configuration.sourceEncoding(reader.readLine());
				break;
			case OUTPUTPATH:
				configuration.setOutDirectory(new File(reader.readLine()));
				break;
			case PROJECT:
				configuration.setProject(reader.readLine());
				break;
			case RESOURCES:
				configuration.setResourcesDirectory(new File(reader.readLine()));
				break;
			case MODULE:
				configuration.setModule(reader.readLine());
				break;
			case MODEL_LEVEL:
				configuration.moduleType(CompilerConfiguration.ModuleType.valueOf(reader.readLine()));
				break;
			case EXCLUDED_PHASES:
				configuration.setExcludedPhases(parseToInt(reader.readLine().split(" ")));
				break;
			case STASH_GENERATION:
				setStashGeneration(configuration, reader);
				break;
			case SEMANTIC_LIB:
				configuration.setSemanticRulesLib(new File(reader.readLine()));
				break;
			case PLATFORM_OUT_DSL:
				configuration.platformOutDsl(reader.readLine());
				break;
			case APPLICATION_OUT_DSL:
				configuration.applicationOutDsl(reader.readLine());
				break;
			case PERSISTENT_MODEL:
				configuration.setDynamicLoad(Boolean.valueOf(reader.readLine()));
				break;
			case MAKE:
				configuration.setMake(Boolean.valueOf(reader.readLine()));
				break;
			case TEST:
				configuration.setTest(Boolean.valueOf(reader.readLine()));
				break;
			case ONTOLOGY:
				configuration.setOntology(Boolean.valueOf(reader.readLine()));
				break;
			case PLATFORM_REFACTOR_ID:
				configuration.setEngineRefactorId(Integer.valueOf(reader.readLine()));
				break;
			case APPLICATION_REFACTOR_ID:
				configuration.setApplicationRefactorId(Integer.valueOf(reader.readLine()));
				break;
			case SRC_PATH:
				configuration.sourceDirectory(new File(reader.readLine()));
				break;
			case TARA_PROJECT_PATH:
				configuration.setTaraProjectDirectory(new File(reader.readLine()));
				break;
			case TARA_PATH:
				configuration.setTaraDirectory(new File(reader.readLine()));
				break;
			case NATIVES_LANGUAGE:
				configuration.nativeLanguage(reader.readLine());
				break;
			case APPLICATION_LANGUAGE:
				configuration.applicationLanguage(reader.readLine());
				break;
			case SYSTEM_LANGUAGE:
				configuration.systemLanguage(reader.readLine());
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
