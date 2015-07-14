package tara.builder;

import tara.compiler.core.CompilationUnit;
import tara.compiler.core.CompilerConfiguration;
import tara.compiler.core.CompilerMessage;
import tara.compiler.core.SourceUnit;
import tara.compiler.core.errorcollection.*;
import tara.compiler.core.errorcollection.message.*;
import tara.compiler.model.Element;

import java.io.File;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StashBuilder {
	private final CompilationUnit compilationUnit;
	private final List<CompilerMessage> compilationMessages;
	private static final String LINE_AT = " @ line ";

	public StashBuilder(File root, String src, Charset charset) {
		this.compilationUnit = root.exists() ? buildCompilationUnit(root, src, charset) : null;
		this.compilationMessages = new ArrayList<>();
	}

	public void build() {
		compile(compilationUnit);
		processErrors(compilationMessages);
	}

	private static CompilationUnit buildCompilationUnit(File root, String src, Charset charset) {
		final Map<String, File> fileMap = buildFileMap(root);
		final CompilationUnit unit = new CompilationUnit(buildConfiguration(root, fileMap, new File(root, src), charset));
		unit.addSource(new SourceUnit(new File(root, src), unit.getConfiguration(), unit.getErrorCollector()));
		return unit;
	}

	private static Map<String, File> buildFileMap(File root) {
		List<File> files = new ArrayList<>();
		getAllFiles(root, files);
		Map<String, File> map = new HashMap();
		files.stream().filter(file -> file.getName().endsWith(".tara")).forEach(f -> map.put(getPresentableName(f.getName()), f));
		return map;
	}

	private static String getPresentableName(String name) {
		return name.substring(0, name.lastIndexOf("."));
	}

	public static void getAllFiles(File dir, List<File> fileList) {
		File[] files = dir.listFiles();
		for (File file : files != null ? files : new File[0]) {
			fileList.add(file);
			if (file.isDirectory())
				getAllFiles(file, fileList);
		}
	}

	private static CompilerConfiguration buildConfiguration(File root, Map<String, File> fileMap, File src, Charset charset) {
		final CompilerConfiguration configuration = new CompilerConfiguration();
		configuration.setOutput(new PrintWriter(System.err));
		configuration.setWarningLevel(WarningMessage.PARANOIA);
		configuration.setSourceEncoding(charset.name());
		configuration.setStoreDirectory(root.getAbsolutePath());
		configuration.setClassPath(fileMap);
		configuration.setOutDirectory(src.getParent());
		return configuration;
	}

	private static void processErrors(List<CompilerMessage> compilerMessages) {
		int errorCount = 0;
		for (CompilerMessage message : compilerMessages) {
			if (message.getCategory().equals(TaraCompilerMessageCategories.ERROR)) {
				if (errorCount > 100) continue;
				errorCount++;
			}
			System.err.println(message);
		}
	}


	void compile(CompilationUnit unit) {
		try {
			if (unit == null) return;
			unit.compile();
		} catch (CompilationFailedException e) {
			processCompilationException(e);
		}
	}

	private void processCompilationException(Exception exception) {
		if (exception instanceof MultipleCompilationErrorsException) {
			MultipleCompilationErrorsException errorsException = (MultipleCompilationErrorsException) exception;
			ErrorCollector errorCollector = errorsException.getErrorCollector();
			for (int i = 0; i < errorCollector.getErrorCount(); i++)
				processException(errorCollector.getError(i));
		} else processException(exception);
	}

	private void processException(Message message) {
		if (message instanceof SyntaxErrorMessage)
			addErrorMessage(((SyntaxErrorMessage) message).getCause());
		else if (message instanceof ExceptionMessage)
			processException(((ExceptionMessage) message).getCause());
		else if (message instanceof SimpleMessage)
			addErrorMessage((SimpleMessage) message, compilationMessages);
		else
			addMessageWithoutLocation(compilationMessages, "An unknown error occurred: " + message, true);
	}

	private void processException(Throwable e) {
		if (e instanceof TaraRuntimeException) {
			addErrorMessage((TaraRuntimeException) e);
			return;
		}
		addMessageWithoutLocation(compilationMessages, e.getMessage(), true);

	}

	private void addMessageWithoutLocation(List compilerMessages, String message, boolean error) {
		compilerMessages.add(new CompilerMessage(error ? CompilerMessage.ERROR : CompilerMessage.WARNING, message, null, -1, -1));
	}

	private void addErrorMessage(SyntaxException exception) {
		String message = exception.getMessage();
		String justMessage = message.substring(0, message.lastIndexOf(LINE_AT));
		compilationMessages.add(new CompilerMessage(TaraCompilerMessageCategories.ERROR, justMessage, exception.getSourceLocator(),
			exception.getLine(), exception.getStartColumn()));
	}

	private void addErrorMessage(TaraRuntimeException exception) {
		Element element = exception.getElement();
		compilationMessages.add(element != null ?
			new CompilerMessage(CompilerMessage.ERROR, exception.getMessageWithoutLocationText(), element.getFile(), element.getLine(), -1) :
			new CompilerMessage(CompilerMessage.ERROR, exception.getMessageWithoutLocationText(), "null", -1, -1));
	}

	private void addErrorMessage(SimpleMessage message, List compilerMessages) {
		addMessageWithoutLocation(compilerMessages, message.getMessage(), true);
	}
}
