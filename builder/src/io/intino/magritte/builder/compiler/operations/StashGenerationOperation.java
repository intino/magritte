package io.intino.magritte.builder.compiler.operations;

import io.intino.magritte.builder.compiler.codegeneration.magritte.stash.StashCreator;
import io.intino.magritte.io.Stash;
import io.intino.magritte.io.StashSerializer;
import io.intino.tara.Language;
import io.intino.tara.builder.core.CompilationUnit;
import io.intino.tara.builder.core.CompilerConfiguration;
import io.intino.tara.builder.core.errorcollection.CompilationFailedException;
import io.intino.tara.builder.core.errorcollection.TaraException;
import io.intino.tara.builder.core.operation.model.ModelOperation;
import io.intino.tara.builder.model.Model;
import io.intino.tara.builder.utils.Format;
import io.intino.tara.language.model.Mogram;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static io.intino.tara.builder.shared.TaraBuildConstants.PRESENTABLE_MESSAGE;


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
		this.outDSL = conf.model().level() == CompilerConfiguration.Level.Model ? conf.getModule() : conf.model().outDsl();
		try {
			if (conf.isVerbose())
				conf.out().println(PRESENTABLE_MESSAGE + "[" + conf.getModule() + " - " + conf.model().outDsl() + "]" + " Generating Stashes...");
			if ((conf.isTest() || conf.model().level().equals(CompilerConfiguration.Level.Model)))
				createSeparatedStashes(model);
			else createFullStash(model);
		} catch (TaraException e) {
			LOG.log(Level.SEVERE, "Error during stash generation: " + e.getMessage(), e);
			throw new CompilationFailedException(unit.getPhase(), unit, e);
		}
	}

	private void createSeparatedStashes(Model model) {
		unpack(model).forEach(nodes -> {
			try {
				writeStashTo(stashDestiny(new File(nodes.get(0).file())), stashOf(nodes, model.language()));
			} catch (TaraException e) {
				LOG.log(Level.SEVERE, "Error during stash generation: " + e.getMessage(), e);
			}
		});
	}

	private void createFullStash(Model model) throws TaraException {
		if (model.components().isEmpty()) return;
		writeStashTo(stashDestiny(new File(model.components().get(0).file())), stashOf(model.components(), model.language()));
	}

	private Stash stashOf(List<Mogram> nodes, Language language) throws TaraException {
		try {
			return new StashCreator(nodes, language, outDSL, conf).create();
		} catch (Throwable e) {
			throw new TaraException("Error creating stashes: " + e.getMessage());
		}
	}

	private void writeStashTo(File taraFile, Stash stash) {
		try {
			final byte[] content = StashSerializer.serialize(stash);
			final File file = stashDestiny(taraFile);
			stash.path = file.getName();
			file.getParentFile().mkdirs();
			try (FileOutputStream stream = new FileOutputStream(file)) {
				stream.write(content);
			} catch (IOException e) {
				LOG.log(Level.SEVERE, "Error writing stashes: " + e.getMessage(), e);
				throw new CompilationFailedException(unit.getPhase(), unit, e);
			}
		} catch (Throwable e) {
			LOG.log(Level.SEVERE, "Error writing stashes: " + e.getMessage(), e);
			throw new CompilationFailedException(unit.getPhase(), unit, e);
		}
	}

	private File stashDestiny(File taraFile) {
		final File destiny = conf.resourcesDirectory();
		destiny.mkdirs();
		return conf.isTest() || conf.model().level() == CompilerConfiguration.Level.Model ?
				new File(destiny, taraFile.getName().split("\\.")[0] + STASH) :
				new File(destiny, Format.firstUpperCase().format(conf.model().outDsl()).toString() + STASH);
	}

	private List<List<Mogram>> unpack(Model model) {
		Map<String, List<Mogram>> nodes = new HashMap<>();
		model.components().forEach(node -> {
			if (!nodes.containsKey(node.file())) nodes.put(node.file(), new ArrayList<>());
			nodes.get(node.file()).add(node);
		});
		return unpack(nodes);
	}

	private List<List<Mogram>> unpack(Map<String, List<Mogram>> nodes) {
		return new ArrayList<>(nodes.values());
	}
}
