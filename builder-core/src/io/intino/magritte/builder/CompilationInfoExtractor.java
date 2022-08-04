package io.intino.magritte.builder;

import io.intino.Configuration;
import io.intino.magritte.builder.core.CompilerConfiguration;
import io.intino.magritte.builder.core.errorcollection.message.WarningMessage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static io.intino.magritte.builder.compiler.shared.TaraBuildConstants.*;

public class CompilationInfoExtractor {
	private static final Logger LOG = Logger.getGlobal();

	public static void getInfoFromArgsFile(File argsFile, CompilerConfiguration configuration, Map<File, Boolean> srcFiles) {
		BufferedReader reader = null;
		configuration.setWarningLevel(WarningMessage.PARANOIA);
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(argsFile)));
			processArgs(configuration, reader, readSrc(srcFiles, SRC_FILE, reader));
		} catch (IOException e) {
			LOG.log(java.util.logging.Level.SEVERE, "Error getting Args IO: " + e.getMessage(), e);
		} finally {
			try {
				assert reader != null;
				reader.close();
			} catch (IOException e) {
				LOG.log(java.util.logging.Level.SEVERE, "Error getting Args IO2: " + e.getMessage(), e);
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
			case LEVEL:
				configuration.model().level(Configuration.Artifact.Model.Level.valueOf(reader.readLine()));
				break;
			case EXCLUDED_PHASES:
				configuration.setExcludedPhases(parseToInt(reader.readLine().split(" ")));
				break;
			case SEMANTIC_LIB:
				configuration.setSemanticRulesLib(new File(reader.readLine()));
				break;
			case OUT_DSL:
				configuration.model().outDsl(reader.readLine());
				break;
			case GROUP_ID:
				configuration.groupId(reader.readLine());
				break;
			case ARTIFACT_ID:
				configuration.artifactId(reader.readLine());
				break;
			case VERSION:
				configuration.version(reader.readLine());
				break;
			case MAKE:
				configuration.setMake(Boolean.parseBoolean(reader.readLine()));
				break;
			case TEST:
				configuration.setTest(Boolean.parseBoolean(reader.readLine()));
				break;
			case GENERATION_PACKAGE:
				configuration.workingPackage(reader.readLine());
				break;
			case SRC_PATH:
				readSrcPaths(configuration.sourceDirectories(), reader);
				break;
			case INTINO_PROJECT_PATH:
				configuration.intinoProjectDirectory(new File(reader.readLine()));
				break;
			case TARA_PATH:
				configuration.setTaraDirectory(new File(reader.readLine()));
				break;
			case LANGUAGE:
				final String[] dsl = reader.readLine().trim().split(":");
				configuration.addLanguage(dsl[0], dsl[1]);
				break;
			default:
				break;
		}
	}

	private static void readSrcPaths(List<File> srcPaths, BufferedReader reader) throws IOException {
		String line;
		while (!"".equals(line = reader.readLine()))
			srcPaths.add(new File(line));
	}

	private static List<Integer> parseToInt(String[] phases) {
		List<Integer> list = new ArrayList<>();
		for (String phase : phases) list.add(Integer.parseInt(phase));
		return list;
	}
}
