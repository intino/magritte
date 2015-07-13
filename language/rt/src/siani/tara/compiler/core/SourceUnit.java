package siani.tara.compiler.core;

import siani.tara.compiler.core.errorcollection.ErrorCollector;
import siani.tara.compiler.core.errorcollection.TaraException;
import siani.tara.compiler.model.impl.Model;
import siani.tara.compiler.parser.Parser;

import java.io.File;
import java.io.IOException;

public class SourceUnit extends ProcessingUnit {

	protected FileReaderSource source;
	protected String name;
	private Model model;
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

	public Model getModel() {
		return model;
	}

	public void parse() throws TaraException {
		if (parser == null) try {
			parser = new Parser(source.getFile(), configuration.getSourceEncoding());
			parser.parse();
		} catch (IOException e) {
			throw new TaraException("Error opening source " + source.getFile().getName(), e);
		}
	}

	public void importData() throws TaraException {
		model = parser.convert();
	}
}