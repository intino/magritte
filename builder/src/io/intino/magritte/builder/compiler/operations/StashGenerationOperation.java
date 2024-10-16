package io.intino.magritte.builder.compiler.operations;

import io.intino.builder.CompilerConfiguration;
import io.intino.magritte.builder.compiler.codegeneration.magritte.stash.StashCreator;
import io.intino.magritte.io.StashSerializer;
import io.intino.magritte.io.model.Stash;
import io.intino.tara.Language;
import io.intino.tara.builder.core.CompilationUnit;
import io.intino.tara.builder.core.errorcollection.CompilationFailedException;
import io.intino.tara.builder.core.errorcollection.TaraException;
import io.intino.tara.builder.core.operation.model.ModelOperation;
import io.intino.tara.builder.utils.Format;
import io.intino.tara.model.Mogram;
import io.intino.tara.processors.model.Model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static io.intino.builder.BuildConstants.PRESENTABLE_MESSAGE;


public class StashGenerationOperation extends ModelOperation {
	public static final String STASH = ".stash";
	private static final Logger LOG = Logger.getLogger(StashGenerationOperation.class.getName());
	private final CompilerConfiguration conf;
	private String outDSL;
	private boolean M1;

	public StashGenerationOperation(CompilationUnit compilationUnit) {
		super(compilationUnit);
		this.conf = compilationUnit.configuration();
	}

	@Override
	public void call(Model model) {
		this.outDSL = conf.dsl().outDsl() == null ? conf.module() : conf.dsl().outDsl();
		M1 = model.mograms().stream().anyMatch(m -> m.level() == io.intino.tara.model.Level.M1);
		try {
			if (conf.isVerbose())
				conf.out().println(PRESENTABLE_MESSAGE + "[" + conf.module() + " - " + conf.dsl().outDsl() + "]" + " Generating Stashes...");
			if (conf.test() || M1)
				createSeparatedStashes(model);
			else createFullStash(model);
		} catch (TaraException e) {
			LOG.log(Level.SEVERE, "Error during stash generation: " + e.getMessage(), e);
			throw new CompilationFailedException(unit.getPhase(), unit, e);
		}
	}

	private void createSeparatedStashes(Model model) {
		unpack(model).forEach(mograms -> {
			try {
				writeStashTo(stashDestination(new File(mograms.get(0).source().getPath())), stashOf(mograms, unit.language()));
			} catch (TaraException e) {
				LOG.log(Level.SEVERE, "Error during stash generation: " + e.getMessage(), e);
			}
		});
	}

	private void createFullStash(Model model) throws TaraException {
		if (model.components().isEmpty()) return;
		writeStashTo(stashDestination(new File(model.components().get(0).source().getPath())), stashOf(model.components(), unit.language()));
	}

	private Stash stashOf(List<Mogram> mograms, Language language) throws TaraException {
		try {
			return new StashCreator(mograms, language, outDSL, conf).create();
		} catch (Throwable e) {
			throw new TaraException("Error creating stashes: " + e.getMessage());
		}
	}

	private void writeStashTo(File taraFile, Stash stash) {
		try {
			final byte[] content = StashSerializer.serialize(stash);
			final File file = stashDestination(taraFile);
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

	private File stashDestination(File taraFile) {
		final File target = conf.outDirectory();
		target.mkdirs();
		return conf.test() || M1 ?
				new File(target, taraFile.getName().split("\\.")[0] + STASH) :
				new File(target, Format.firstUpperCase().format(conf.dsl().outDsl()).toString() + STASH);
	}

	private List<List<Mogram>> unpack(Model model) {
		Map<String, List<Mogram>> mograms = new HashMap<>();
		model.components().forEach(m -> {
			if (!mograms.containsKey(m.source().getPath())) mograms.put(m.source().getPath(), new ArrayList<>());
			mograms.get(m.source().getPath()).add(m);
		});
		return unpack(mograms);
	}

	private List<List<Mogram>> unpack(Map<String, List<Mogram>> mograms) {
		return new ArrayList<>(mograms.values());
	}
}
