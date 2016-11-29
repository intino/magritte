package org.jetbrains.jps.tara.compiler;

import com.intellij.execution.process.BaseOSProcessHandler;
import com.intellij.execution.process.ProcessOutputTypes;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.Consumer;
import com.intellij.util.containers.ContainerUtil;
import org.apache.log4j.Level;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.jps.incremental.messages.BuildMessage;
import org.jetbrains.jps.incremental.messages.CompilerMessage;
import tara.compiler.shared.TaraBuildConstants;

import java.util.ArrayList;
import java.util.List;

import static tara.compiler.shared.TaraBuildConstants.TARAC;
import static tara.compiler.shared.TaraCompilerMessageCategories.ERROR;
import static tara.compiler.shared.TaraCompilerMessageCategories.WARNING;

class TaracOSProcessHandler extends BaseOSProcessHandler {
	private static final String TARA_COMPILER_IN_OPERATION = "Tara compiler in operation...";
	private static final Logger LOG = Logger.getInstance(TaracOSProcessHandler.class);

	private final List<OutputItem> compiledItems = new ArrayList<>();
	private final List<CompilerMessage> compilerMessages = new ArrayList<>();
	private final StringBuilder stdErr = new StringBuilder();
	private final Consumer<String> statusUpdater;
	private final StringBuilder outputBuffer = new StringBuilder();

	TaracOSProcessHandler(Process process, Consumer<String> statusUpdater) {
		super(process, null, null);
		LOG.setLevel(Level.ALL);
		this.statusUpdater = statusUpdater;
	}

	private List<String> splitAndTrim(String compiled) {
		return ContainerUtil.map(StringUtil.split(compiled, TaraBuildConstants.SEPARATOR), String::trim);
	}

	public void notifyTextAvailable(final String text, final Key outputType) {
		super.notifyTextAvailable(text, outputType);
		if (LOG.isDebugEnabled()) LOG.debug("Received from tarac: " + text);
		if (outputType == ProcessOutputTypes.SYSTEM) return;
		if (outputType == ProcessOutputTypes.STDERR) {
			stdErr.append(StringUtil.convertLineSeparators(text));
			return;
		}
		parseOutput(text);
	}

	private void updateStatus(@Nullable String status) {
		statusUpdater.consume(status == null ? TARA_COMPILER_IN_OPERATION : status);
	}

	private void parseOutput(String text) {
		final String trimmed = text.trim();
		if (trimmed.startsWith(TaraBuildConstants.PRESENTABLE_MESSAGE)) {
			updateStatus(trimmed.substring(TaraBuildConstants.PRESENTABLE_MESSAGE.length()));
			return;
		}
		if (TaraBuildConstants.CLEAR_PRESENTABLE.equals(trimmed)) {
			updateStatus(null);
			return;
		}
		if (StringUtil.isNotEmpty(text)) {
			if (text.startsWith(TaraBuildConstants.COMPILED_START)) {
				outputBuffer.append(text);
				updateStatus("Finishing...");
				processCompiledItems();
			} else if (text.startsWith(TaraBuildConstants.MESSAGES_START)) {
				outputBuffer.append(text);
				processMessage();
			}
		}
	}

	private void processMessage() {
		String text;
		if (outputBuffer.indexOf(TaraBuildConstants.MESSAGES_END) == -1) return;
		text = handleOutputBuffer(TaraBuildConstants.MESSAGES_START, TaraBuildConstants.MESSAGES_END);
		List<String> tokens = splitAndTrim(text);
		LOG.assertTrue(tokens.size() > 4, "Wrong number of output params");
		String category = tokens.get(0);
		String message = tokens.get(1);
		String url = tokens.get(2);
		String lineNum = tokens.get(3);
		String columnNum = tokens.get(4);
		int lineInt, columnInt;
		try {
			lineInt = Integer.parseInt(lineNum);
			columnInt = Integer.parseInt(columnNum);
		} catch (NumberFormatException e) {
			LOG.error(e);
			lineInt = 0;
			columnInt = 0;
		}
		BuildMessage.Kind kind = category.equals(ERROR)
			? BuildMessage.Kind.ERROR
			: category.equals(WARNING)
			? BuildMessage.Kind.WARNING
			: BuildMessage.Kind.INFO;
		CompilerMessage compilerMessage = new CompilerMessage(TARAC, kind, message, url, -1, -1, -1, lineInt, columnInt);
		if (LOG.isDebugEnabled()) LOG.debug("Message: " + compilerMessage);
		compilerMessages.add(compilerMessage);
	}

	private void processCompiledItems() {
		if (outputBuffer.indexOf(TaraBuildConstants.COMPILED_END) == -1) return;
		final String compiled = handleOutputBuffer(TaraBuildConstants.COMPILED_START, TaraBuildConstants.COMPILED_END);
		final List<String> list = splitAndTrim(compiled);
		String outputFile = list.get(0);
		String sourceFile = list.get(1);

		OutputItem item = new OutputItem(outputFile, sourceFile);
		if (LOG.isDebugEnabled()) LOG.debug("Output: " + item);
		compiledItems.add(item);
	}

	private String handleOutputBuffer(String startMarker, String endMarker) {
		final int start = outputBuffer.indexOf(startMarker);
		final int end = outputBuffer.indexOf(endMarker);
		if (start > end)
			throw new AssertionError("Malformed Tarac output: " + outputBuffer.toString());
		String text = outputBuffer.substring(start + startMarker.length(), end);
		outputBuffer.delete(start, end + endMarker.length());
		return text.trim();
	}

	List<OutputItem> getSuccessfullyCompiled() {
		return compiledItems;
	}

	List<CompilerMessage> getCompilerMessages(String moduleName) {
		List<CompilerMessage> messages = new ArrayList<>(compilerMessages);
		final StringBuilder unParsedBuffer = getStdErr();
		if (unParsedBuffer.length() != 0) {
			String msg = unParsedBuffer.toString();
			if (msg.contains(TaraBuildConstants.NO_TARA)) {
				msg = "Cannot compile Tara files: no Tara library is defined for module '" + moduleName + "'";
				messages.add(new CompilerMessage(TARAC, BuildMessage.Kind.INFO, msg));
			}
		}
		final int exitValue = getProcess().exitValue();
		if (exitValue != 0) {
			for (CompilerMessage message : messages)
				if (message.getKind() == BuildMessage.Kind.ERROR)
					return messages;
			messages.add(new CompilerMessage(TARAC, BuildMessage.Kind.ERROR, "Internal Tarac error:" + exitValue));
		}
		return messages;
	}

	boolean shouldRetry() {
		for (CompilerMessage message : compilerMessages) {
			if (message.getKind() == BuildMessage.Kind.ERROR) {
//				LOG.debug("Error message: " + message);
				return true;
			}
			if (message.getMessageText().contains(TaraBuildConstants.TARAC_STUB_GENERATION_FAILED)) {
				LOG.debug("Stub failed message: " + message);
				return true;
			}
		}
		if (getStdErr().length() > 0) {
			LOG.debug("Non-empty stderr: '" + getStdErr() + "'");
			LOG.error(getStdErr().toString());
			return true;
		}
		return false;
	}

	private StringBuilder getStdErr() {
		return stdErr;
	}

	static class OutputItem {
		private final String outputPath;
		private final String sourcePath;

		OutputItem(String outputPath, String sourceFileName) {
			this.outputPath = outputPath;
			sourcePath = sourceFileName;
		}

		String getOutputPath() {
			return outputPath;
		}

		String getSourcePath() {
			return sourcePath;
		}

		@Override
		public String toString() {
			return "OutputItem{" + "outputPath='" + outputPath + '\'' + ", sourcePath='" + sourcePath + '\'' + '}';
		}
	}

}