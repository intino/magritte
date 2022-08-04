package io.intino.magritte.builder.compiler.operations;

import io.intino.magritte.Language;
import io.intino.magritte.builder.compiler.codegeneration.magritte.stash.StashCreator;
import io.intino.magritte.builder.core.CompilationUnit;
import io.intino.magritte.builder.core.CompilerConfiguration;
import io.intino.magritte.builder.core.errorcollection.CompilationFailedException;
import io.intino.magritte.builder.core.errorcollection.TaraException;
import io.intino.magritte.builder.core.operation.model.ModelOperation;
import io.intino.magritte.builder.model.Model;
import io.intino.magritte.builder.utils.Format;
import io.intino.magritte.io.Stash;
import io.intino.magritte.io.StashSerializer;
import io.intino.magritte.lang.model.Node;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static io.intino.Configuration.Artifact.Model.Level.Solution;
import static io.intino.magritte.builder.compiler.shared.TaraBuildConstants.PRESENTABLE_MESSAGE;

public class StashGenerationOperation extends ModelOperation {
	public static final String STASH = ".stash";
	private static final Logger LOG = Logger.getLogger(StashGenerationOperation.class.getName());
	private final CompilerConfiguration conf;
	private String outDSL;

	public StashGenerationOperation(CompilationUnit compilationUnit) {
		super(compilationUnit);
		this.conf = compilationUnit.configuration();
	}

	@Override
	public void call(Model model) {
		this.outDSL = conf.model().level().isSolution() ? conf.getModule() : conf.model().outDsl();
		try {
			if (conf.isVerbose())
				conf.out().println(PRESENTABLE_MESSAGE + "[" + conf.getModule() + " - " + conf.model().outDsl() + "]" + " Generating Stashes...");
			if ((conf.isTest() || conf.model().level().equals(Solution))) createSeparatedStashes(model);
			else createFullStash(model);
		} catch (TaraException e) {
			LOG.log(Level.SEVERE, "Error during stash generation: " + e.getMessage(), e);
			throw new CompilationFailedException(compilationUnit.getPhase(), compilationUnit, e);
		}
	}

	private void createSeparatedStashes(Model model) throws TaraException {
		for (List<Node> nodes : pack(model)) {
			if (nodes.isEmpty()) continue;
			writeStashTo(stashDestiny(new File(nodes.get(0).file())), stashOf(nodes, model.language()));
		}
	}

	private void createFullStash(Model model) throws TaraException {
		if (model.components().isEmpty()) return;
		writeStashTo(stashDestiny(new File(model.components().get(0).file())), stashOf(model.components(), model.language()));
	}

	private Stash stashOf(List<Node> nodes, Language language) throws TaraException {
		try {
			return new StashCreator(nodes, language, outDSL, conf).create();
		} catch (Throwable e) {
			throw new TaraException("Error creating stashes: " + e.getMessage());
		}
	}

	private void writeStashTo(File taraFile, Stash stash) {
		final byte[] content = StashSerializer.serialize(stash);
		final File file = stashDestiny(taraFile);
		stash.path = file.getName();
		file.getParentFile().mkdirs();
		try (FileOutputStream stream = new FileOutputStream(file)) {
			stream.write(content);
		} catch (IOException e) {
			LOG.log(Level.SEVERE, "Error writing stashes: " + e.getMessage(), e);
			throw new CompilationFailedException(compilationUnit.getPhase(), compilationUnit, e);
		}
		file.getPath();
	}

	private File stashDestiny(File taraFile) {
		final File destiny = conf.resourcesDirectory();
		destiny.mkdirs();
		return conf.isTest() || conf.model().level().isSolution() ?
				new File(destiny, taraFile.getName().split("\\.")[0] + STASH) :
				new File(destiny, Format.firstUpperCase().format(conf.model().outDsl()).toString() + STASH);
	}

	private List<List<Node>> pack(Model model) {
		Map<String, List<Node>> nodes = new HashMap<>();
		for (Node node : model.components()) {
			if (!nodes.containsKey(node.file()))
				nodes.put(node.file(), new ArrayList<>());
			nodes.get(node.file()).add(node);
		}
		return pack(nodes);
	}

	private List<List<Node>> pack(Map<String, List<Node>> nodes) {
		return new ArrayList<>(nodes.values());
	}
}
