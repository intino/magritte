package tara.builder;

import tara.compiler.core.CompilationUnit;
import tara.compiler.core.CompilerConfiguration;
import tara.compiler.core.CompilerMessage;
import tara.compiler.core.errorcollection.*;
import tara.compiler.core.errorcollection.message.*;
import tara.language.model.Element;

import java.io.File;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StashBuilder {
	private static final String LINE_AT = " @ line ";
	private final File root;
	private final Set<String> files;
	private final List<CompilerMessage> compilationMessages;

	public StashBuilder(File root) {
		this.root = root;
		this.files = buildFileSet(root);
		this.compilationMessages = new ArrayList<>();
	}

	public void buildAll(Charset charset) {
		for (String src : files)
			build(src, charset);
	}

	public void build(String unit, Charset charset) {
		CompilationUnit compilationUnit = buildCompilationUnit(unit, charset);
		compile(compilationUnit);
		processErrors(compilationMessages);
	}

	private CompilationUnit buildCompilationUnit(String src, Charset charset) {
		CompilationUnit unit = new CompilationUnit(buildConfiguration(fileOf(src).getParentFile(), charset));
		unit.addSource(fileOf(src));
		return unit;
	}

    private File fileOf(String src) {
        return new File(root, src.replace(".",File.separator) + ".tara");
    }

    private Set<String> buildFileSet(File root) {
		return getTaraFiles(root);
	}

	private String getNameSpace(File file) {
		return file.getAbsolutePath().substring(root.getAbsolutePath().length() + 1).replace(".tara", "").replace(File.separator,".");
	}

	private Set<String> getTaraFiles(File folder) {
		Set<String> files = taraFilesIn(folder);
		for (File file : folder.listFiles(File::isDirectory))
			files.addAll(getTaraFiles(file));
		return files;
	}

	private Set<String> taraFilesIn(File folder) {
		File[] files = folder.listFiles(this::taraFile);
		Set<String> result = new HashSet<>(files.length);
        for (File file : files) {
            result.add(getNameSpace(file));
        }
        return result;
	}

	private boolean taraFile(File dir, String name) {
		return name.endsWith(".tara");
	}

	private CompilerConfiguration buildConfiguration(File out, Charset charset) {
		final CompilerConfiguration configuration = new CompilerConfiguration();
		configuration.setClassPath(files);
		configuration.setOutput(new PrintWriter(System.err));
		configuration.setWarningLevel(WarningMessage.PARANOIA);
		configuration.setSourceEncoding(charset.name());
		configuration.setRootFolder(root.getAbsolutePath());
		configuration.setOutDirectory(out.getAbsolutePath());
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

	private void compile(CompilationUnit unit) {
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
			new CompilerMessage(CompilerMessage.ERROR, exception.getMessageWithoutLocationText(), element.file(), element.line(), -1) :
			new CompilerMessage(CompilerMessage.ERROR, exception.getMessageWithoutLocationText(), "null", -1, -1));
	}

	private void addErrorMessage(SimpleMessage message, List compilerMessages) {
		addMessageWithoutLocation(compilerMessages, message.getMessage(), true);
	}
}
