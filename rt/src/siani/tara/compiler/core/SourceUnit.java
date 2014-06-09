package siani.tara.compiler.core;

import siani.tara.compiler.core.errorcollection.ErrorCollector;
import siani.tara.compiler.core.errorcollection.SyntaxException;
import siani.tara.compiler.core.errorcollection.TaraException;
import siani.tara.compiler.parser.Parser;
import siani.tara.lang.TreeWrapper;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class SourceUnit extends ProcessingUnit {

	private static final Logger LOG = Logger.getLogger(SourceUnit.class.getName());
	protected FileReaderSource source;
	protected String name;
	private TreeWrapper abstractTree;
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

	public TreeWrapper getAbstractTree() {
		return abstractTree;
	}

	public void parse() throws IOException, SyntaxException {
		if (parser == null) parser = new Parser(source.getFile());
		parser.parse();
	}

	public void convert() throws TaraException {
		abstractTree = parser.convert();
	}
}