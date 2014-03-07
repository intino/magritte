package monet.tara.compiler.core;

import monet.tara.compiler.code_generation.render.RendersFactory;
import monet.tara.compiler.core.ast.AST;
import monet.tara.compiler.core.ast.ASTNode;
import monet.tara.compiler.core.error_collection.ErrorCollector;
import monet.tara.compiler.core.error_collection.TaraException;
import monet.tara.compiler.core.error_collection.message.SimpleMessage;
import monet.tara.compiler.parser.Parser;
import org.monet.templation.Render;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SourceUnit extends ProcessingUnit {
	protected FileReaderSource source;
	protected String name;
	private AST ast;
	private Parser parser;

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
			if (parser == null)
				parser = new Parser(source.getFile());
			parser.parse();
		} catch (Exception e) {
			getErrorCollector().addError(new SimpleMessage("Parsing Error", this));
		}
	}

	public void convert() throws TaraException {
		ast = parser.convert();
		writeJavaSource();
	}

	private void writeJavaSource() throws TaraException {
		String path = getDefinitionsPath();
		for (ASTNode node : ast.getAstRootNodes()) {
			try {
				File file = new File(path, node.getIdentifier() + "Definition" + ".java");
				file.createNewFile();
				Render render = RendersFactory.getRender("Definition", configuration.getProject(), node);
				FileWriter writer;
				writer = new FileWriter(file);
				writer.write(render.getOutput());
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new TaraException("Error during java source writing");
			}
		}
	}

	private String getDefinitionsPath() {
		String bar = File.separator;
		configuration.cleanTemp();
		String path = configuration.getTempDirectory().getPath() + bar + configuration.getProject() + bar + "definitions";
		new File(path).mkdirs();
		return path;
	}


}
