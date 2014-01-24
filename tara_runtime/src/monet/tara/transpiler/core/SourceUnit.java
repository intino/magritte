package monet.tara.transpiler.core;

import java.io.File;

public class SourceUnit {
	protected FileReaderSource source;
	protected String name;
	private TranspilerConfiguration configuration;
	private ErrorCollector errorCollector;

	public SourceUnit(String name, FileReaderSource source, TranspilerConfiguration flags, ErrorCollector er) {
		this.configuration = flags;
		this.errorCollector = er;
		this.name = name;
		this.source = source;
	}

	public SourceUnit(File source, TranspilerConfiguration configuration, ErrorCollector er) {
		this(source.getPath(), new FileReaderSource(source, configuration), configuration, er);
	}

	public String getName() {
		return this.name;
	}

	public FileReaderSource getSource() {
		return source;
	}

	public ErrorCollector getErrorCollector() {
		return errorCollector;
	}



	//ENGANCHAR ARBOL ANTLR

}
