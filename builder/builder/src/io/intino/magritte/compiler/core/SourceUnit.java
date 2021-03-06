package io.intino.magritte.compiler.core;

import io.intino.magritte.compiler.core.errorcollection.ErrorCollector;
import io.intino.magritte.compiler.core.errorcollection.TaraException;
import io.intino.magritte.compiler.model.Model;
import io.intino.magritte.compiler.parser.Parser;

import java.io.File;
import java.io.IOException;

public class SourceUnit extends ProcessingUnit {

	protected FileReaderSource source;
	protected String name;
	private Model model;
	private Parser parser;
	private final boolean dirty;

	public SourceUnit(String name, FileReaderSource source, CompilerConfiguration configuration, ErrorCollector er, boolean dirty) {
		super(configuration, er);
		this.configuration = configuration;
		this.errorCollector = er;
		this.name = name;
		this.source = source;
		this.dirty = dirty;
	}

	public SourceUnit(File source, CompilerConfiguration configuration, ErrorCollector er, boolean dirty) {
		this(source.getPath(), new FileReaderSource(source, configuration), configuration, er, dirty);
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
			parser = new Parser(source.getFile(), configuration.model().language(), configuration.sourceEncoding(), configuration.model().outDsl());
			parser.parse();
		} catch (IOException e) {
			throw new TaraException("Error opening source " + source.getFile().getName(), e);
		}
	}

	public void importData() throws TaraException {
		model = parser.convert();
	}

	public boolean isDirty() {
		return dirty;
	}
}