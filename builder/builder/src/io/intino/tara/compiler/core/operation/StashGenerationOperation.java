package io.intino.tara.compiler.core.operation;

import io.intino.tara.compiler.codegeneration.magritte.stash.StashCreator;
import io.intino.tara.compiler.core.CompilerConfiguration;
import io.intino.tara.compiler.core.errorcollection.TaraException;
import io.intino.tara.compiler.core.operation.model.ModelOperation;
import io.intino.tara.compiler.model.Model;
import io.intino.tara.compiler.codegeneration.Format;
import io.intino.tara.compiler.core.CompilationUnit;
import io.intino.tara.compiler.core.errorcollection.CompilationFailedException;
import io.intino.tara.compiler.shared.Configuration;
import io.intino.tara.io.Stash;
import io.intino.tara.io.StashSerializer;
import io.intino.tara.lang.model.Node;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static io.intino.tara.compiler.shared.TaraBuildConstants.PRESENTABLE_MESSAGE;

public class StashGenerationOperation extends ModelOperation {
	private static final Logger LOG = Logger.getLogger(StashGenerationOperation.class.getName());
	private static final String STASH = ".stash";
	private final CompilationUnit compilationUnit;
	private final CompilerConfiguration conf;
	private String outDSL;

	public StashGenerationOperation(CompilationUnit compilationUnit) {
		super();
		this.compilationUnit = compilationUnit;
		this.conf = compilationUnit.getConfiguration();
	}

	@Override
	public void call(Model model) {
		this.outDSL = conf.level().equals(Configuration.Level.System) ? conf.getModule() : conf.outDSL();
		try {
			if (conf.isVerbose())
				System.out.println(PRESENTABLE_MESSAGE + "[" + conf.getModule() + " - " + conf.outDSL() + "]" + " Generating Stashes...");
			if (conf.isTest()) createTestStashes(model);
			else createStash(model.components());
		} catch (TaraException e) {
			LOG.log(Level.SEVERE, "Error during stash generation: " + e.getMessage(), e);
			throw new CompilationFailedException(compilationUnit.getPhase(), compilationUnit, e);
		}
	}

	private void createTestStashes(Model model) throws TaraException {
		for (List<Node> nodes : pack(model)) {
			if (nodes.isEmpty()) continue;
			writeStashTo(stashDestiny(new File(nodes.get(0).file())), stashOf(nodes));
		}
	}

	private void createStash(List<Node> nodes) throws TaraException {
		if (nodes.isEmpty()) return;
		writeStashTo(stashDestiny(new File(nodes.get(0).file())), stashOf(nodes));
	}

	private Stash stashOf(List<Node> nodes) throws TaraException {
		return new StashCreator(nodes, conf.language(), outDSL, conf).create();
	}

	private String writeStashTo(File taraFile, Stash stash) {
		final byte[] content = StashSerializer.serialize(stash);
		final File file = stashDestiny(taraFile);
		file.getParentFile().mkdirs();
		try (FileOutputStream stream = new FileOutputStream(file)) {
			stream.write(content);
			stream.close();
		} catch (IOException e) {
			LOG.log(Level.SEVERE, "Error writing stashes: " + e.getMessage(), e);
			throw new CompilationFailedException(compilationUnit.getPhase(), compilationUnit, e);
		}
		return file.getPath();
	}

	private File stashDestiny(File taraFile) {
		final File destiny = conf.resourcesDirectory();
		destiny.mkdirs();
		return !conf.isTest() ?
			new File(destiny, Format.firstUpperCase().format(conf.level().equals(Configuration.Level.System) ? "Model" : conf.outDSL()).toString() + STASH) :
			new File(destiny, taraFile.getName().split("\\.")[0] + STASH);
	}

	private List<List<Node>> pack(Model model) {
		Map<String, List<Node>> nodes = new HashMap();
		for (Node node : model.components()) {
			if (!nodes.containsKey(node.file()))
				nodes.put(node.file(), new ArrayList<>());
			nodes.get(node.file()).add(node);
		}
		return pack(nodes);
	}

	private List<List<Node>> pack(Map<String, List<Node>> nodes) {
		return nodes.values().stream().collect(Collectors.toList());
	}

}
