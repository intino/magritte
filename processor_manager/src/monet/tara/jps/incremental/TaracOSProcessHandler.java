package monet.tara.jps.incremental;

import com.intellij.execution.process.BaseOSProcessHandler;
import com.intellij.execution.process.ProcessOutputTypes;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.Consumer;
import com.intellij.util.Function;
import com.intellij.util.containers.ContainerUtil;
import monet.tara.compiler.rt.TaraCompilerMessageCategories;
import monet.tara.compiler.rt.TaraRtConstants;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.jps.incremental.messages.BuildMessage;
import org.jetbrains.jps.incremental.messages.CompilerMessage;

import java.io.*;
import java.util.*;

public class TaracOSProcessHandler extends BaseOSProcessHandler {
	public static final String TARA_COMPILER_IN_OPERATION = "Tara compiler in operation...";
	public static final String GRAPE_ROOT = "grape.root";
	private final List<OutputItem> myCompiledItems = new ArrayList<>();
	private final Set<File> toRecompileFiles = new HashSet<>();
	private final List<CompilerMessage> compilerMessages = new ArrayList<>();
	private final StringBuffer stdErr = new StringBuffer();
	private static final Logger LOG = Logger.getInstance("#monet.tara.jps.incremental.TaracOSProcessHandler");
	private final Consumer<String> myStatusUpdater;

	public TaracOSProcessHandler(Process process, Consumer<String> statusUpdater) {
		super(process, null, null);
		myStatusUpdater = statusUpdater;
	}

	public void notifyTextAvailable(final String text, final Key outputType) {
		super.notifyTextAvailable(text, outputType);

		if (LOG.isDebugEnabled()) {
			LOG.debug("Received from tarac: " + text);
		}

		if (outputType == ProcessOutputTypes.SYSTEM) {
			return;
		}

		if (outputType == ProcessOutputTypes.STDERR) {
			stdErr.append(StringUtil.convertLineSeparators(text));
			return;
		}
		parseOutput(text);
	}

	private final StringBuffer outputBuffer = new StringBuffer();

	protected void updateStatus(@Nullable String status) {
			myStatusUpdater.consume(status == null ? TARA_COMPILER_IN_OPERATION : status);
	}

	private void parseOutput(String text) {
		final String trimmed = text.trim();

		if (trimmed.startsWith(TaraRtConstants.PRESENTABLE_MESSAGE)) {
			updateStatus(trimmed.substring(TaraRtConstants.PRESENTABLE_MESSAGE.length()));
			return;
		}

		if (TaraRtConstants.CLEAR_PRESENTABLE.equals(trimmed)) {
			updateStatus(null);
			return;
		}


		if (StringUtil.isNotEmpty(text)) {
			outputBuffer.append(text);

			//compiled start marker have to be in the beginning on each string
			if (outputBuffer.indexOf(TaraRtConstants.COMPILED_START) != -1) {
				if (outputBuffer.indexOf(TaraRtConstants.COMPILED_END) == -1) {
					return;
				}

				final String compiled = handleOutputBuffer(TaraRtConstants.COMPILED_START, TaraRtConstants.COMPILED_END);
				final List<String> list = splitAndTrim(compiled);
				String outputPath = list.get(0);
				String sourceFile = list.get(1);

				OutputItem item = new OutputItem(outputPath, sourceFile);
				if (LOG.isDebugEnabled()) {
					LOG.debug("Output: " + item);
				}
				myCompiledItems.add(item);

			} else if (outputBuffer.indexOf(TaraRtConstants.TO_RECOMPILE_START) != -1) {
				if (outputBuffer.indexOf(TaraRtConstants.TO_RECOMPILE_END) != -1) {
					String url = handleOutputBuffer(TaraRtConstants.TO_RECOMPILE_START, TaraRtConstants.TO_RECOMPILE_END);
					toRecompileFiles.add(new File(url));
				}
			} else if (outputBuffer.indexOf(TaraRtConstants.MESSAGES_START) != -1) {
				if (outputBuffer.indexOf(TaraRtConstants.MESSAGES_END) == -1) {
					return;
				}

				text = handleOutputBuffer(TaraRtConstants.MESSAGES_START, TaraRtConstants.MESSAGES_END);

				List<String> tokens = splitAndTrim(text);
				LOG.assertTrue(tokens.size() > 4, "Wrong number of output params");

				String category = tokens.get(0);
				String message = tokens.get(1);
				String url = tokens.get(2);
				String lineNum = tokens.get(3);
				String columnNum = tokens.get(4);

				int lineInt;
				int columnInt;

				try {
					lineInt = Integer.parseInt(lineNum);
					columnInt = Integer.parseInt(columnNum);
				} catch (NumberFormatException e) {
					LOG.error(e);
					lineInt = 0;
					columnInt = 0;
				}

				BuildMessage.Kind kind = category.equals(TaraCompilerMessageCategories.ERROR)
					? BuildMessage.Kind.ERROR
					: category.equals(TaraCompilerMessageCategories.WARNING)
					? BuildMessage.Kind.WARNING
					: BuildMessage.Kind.INFO;

				CompilerMessage compilerMessage = new CompilerMessage("Tarac", kind, message, url, -1, -1, -1, lineInt, columnInt);
				if (LOG.isDebugEnabled()) {
					LOG.debug("Message: " + compilerMessage);
				}
				compilerMessages.add(compilerMessage);
			}
		}
	}

	private String handleOutputBuffer(String startMarker, String endMarker) {
		final int start = outputBuffer.indexOf(startMarker);
		final int end = outputBuffer.indexOf(endMarker);
		if (start > end) {
			throw new AssertionError("Malformed Tarac output: " + outputBuffer.toString());
		}

		String text = outputBuffer.substring(start + startMarker.length(), end);

		outputBuffer.delete(start, end + endMarker.length());

		return text.trim();
	}

	private static List<String> splitAndTrim(String compiled) {
		return ContainerUtil.map(StringUtil.split(compiled, TaraRtConstants.SEPARATOR), new Function<String, String>() {
			public String fun(String s) {
				return s.trim();
			}
		});
	}

	public List<OutputItem> getSuccessfullyCompiled() {
		return myCompiledItems;
	}

	public Set<File> getToRecompileFiles() {
		return toRecompileFiles;
	}

	public boolean shouldRetry() {
		if (getProcess().exitValue() != 0) {
			return true;
		}
		for (CompilerMessage message : compilerMessages) {
			if (message.getKind() == BuildMessage.Kind.ERROR) {
				return true;
			}
		}
		if (getStdErr().length() > 0) {
			return true;
		}
		return false;
	}

	public List<CompilerMessage> getCompilerMessages(String moduleName) {
		ArrayList<CompilerMessage> messages = new ArrayList<>(compilerMessages);
		final StringBuffer unparsedBuffer = getStdErr();
		if (unparsedBuffer.length() != 0) {
			String msg = unparsedBuffer.toString();
			if (msg.contains(TaraRtConstants.NO_TARA)) {
				msg = "Cannot compile Tara files: no Tara library is defined for module '" + moduleName + "'";
			}

			messages.add(new CompilerMessage("Tarac", BuildMessage.Kind.INFO, msg));
		}

		final int exitValue = getProcess().exitValue();
		if (exitValue != 0) {
			for (CompilerMessage message : messages) {
				if (message.getKind() == BuildMessage.Kind.ERROR) {
					return messages;
				}
			}
			messages.add(new CompilerMessage("Tarac", BuildMessage.Kind.ERROR, "Internal Tarac error: code " + exitValue));
		}

		return messages;
	}

	public StringBuffer getStdErr() {
		return stdErr;
	}

	public static File fillFileWithTaracParameters(final String outputDir,
	                                               final Collection<String> changedSources,
	                                               String finalOutput,
	                                               Map<String, String> class2Src, @Nullable final String encoding) throws IOException {
		File tempFile = FileUtil.createTempFile("ideaTaraToCompile", ".txt", true);

		final Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tempFile)));
		try {
			for (String file : changedSources) {
				writer.write(TaraRtConstants.SRC_FILE + "\n");
				writer.write(file);
				writer.write("\n");
			}

			writer.write("class2src\n");
			for (Map.Entry<String, String> entry : class2Src.entrySet()) {
				writer.write(entry.getKey() + "\n");
				writer.write(entry.getValue() + "\n");
			}
			writer.write(TaraRtConstants.END + "\n");

			if (encoding != null) {
				writer.write(TaraRtConstants.ENCODING + "\n");
				writer.write(encoding + "\n");
			}
			writer.write(TaraRtConstants.OUTPUTPATH + "\n");
			writer.write(outputDir);
			writer.write("\n");
			writer.write(TaraRtConstants.FINAL_OUTPUTPATH + "\n");
			writer.write(finalOutput);
			writer.write("\n");
		} finally {
			writer.close();
		}
		return tempFile;
	}

	public static TaracOSProcessHandler runTarac(Process process, Consumer<String> updater) {
		TaracOSProcessHandler processHandler = new TaracOSProcessHandler(process, updater);

		processHandler.startNotify();
		processHandler.waitFor();
		return processHandler;
	}

	public static class OutputItem {
		public final String outputPath;
		public final String sourcePath;

		public OutputItem(String outputPath, String sourceFileName) {
			this.outputPath = outputPath;
			sourcePath = sourceFileName;
		}

		@Override
		public String toString() {
			return "OutputItem{" +
				"outputPath='" + outputPath + '\'' +
				", sourcePath='" + sourcePath + '\'' +
				'}';
		}
	}

}