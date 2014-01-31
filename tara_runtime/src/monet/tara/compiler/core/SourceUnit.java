package monet.tara.compiler.core;

import monet.tara.compiler.core.ast.AST;
import monet.tara.compiler.core.error_collection.ErrorCollector;
import monet.tara.compiler.parser.Parser;

import java.io.File;

public class SourceUnit extends ProcessingUnit {
	protected FileReaderSource source;
	protected String name;
	private AST ast;

	public SourceUnit(String name, FileReaderSource source, CompilerConfiguration flags, ErrorCollector er) {
		super(flags, er);
		this.configuration = flags;
		this.errorCollector = er;
		this.name = name;
		this.source = source;
	}

	public SourceUnit(File source, CompilerConfiguration configuration, ErrorCollector er) {
		this(source.getPath(), new FileReaderSource(source, configuration), configuration, er);
	}

	public String getName() {
		return this.name;
	}

	public FileReaderSource getSource() {
		return source;
	}

	public AST getAST() {
		return ast;
	}

	public void parse() {
		try {
			new Parser(source.getFile()).parse();
		} catch (Exception e) {
			getErrorCollector().addError(null);
		}
	}

	public void convert() {
		//Converter(getAST());
	}
}
