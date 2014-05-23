package monet.tara.compiler.core;

import monet.tara.compiler.core.errorcollection.ErrorCollector;
import monet.tara.compiler.core.errorcollection.SyntaxException;
import monet.tara.compiler.core.errorcollection.TaraException;
import monet.tara.compiler.parser.Parser;
import monet.tara.lang.ASTWrapper;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class SourceUnit extends ProcessingUnit {

	private static final Logger LOG = Logger.getLogger(SourceUnit.class.getName());
	protected FileReaderSource source;
	protected String name;
	private ASTWrapper ast;
	private Parser parser;

	public SourceUnit(String name, FileReaderSource source, CompilerConfiguration configuration, ErrorCollector er) {
		super(configuration, er);
		this.configuration = configuration;
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

	public ASTWrapper getAST() {
		return ast;
	}

	public void parse() throws IOException, SyntaxException {
		if (parser == null) parser = new Parser(source.getFile());
		parser.parse();
	}

	public void convert() throws TaraException {
		ast = parser.convert();
	}
}
