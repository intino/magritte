package io.intino.magritte.builder.compiler.codegeneration.magritte.layer;

import io.intino.builder.CompilerConfiguration;
import io.intino.itrules.Frame;
import io.intino.itrules.FrameBuilder;
import io.intino.magritte.builder.compiler.codegeneration.magritte.Generator;
import io.intino.magritte.builder.compiler.codegeneration.magritte.TemplateTags;
import io.intino.magritte.builder.compiler.codegeneration.magritte.layer.templates.GraphLoaderTemplate;
import io.intino.magritte.builder.compiler.codegeneration.magritte.stash.StashCreator;
import io.intino.magritte.io.model.Stash;
import io.intino.tara.Language;
import io.intino.tara.builder.core.errorcollection.TaraException;
import io.intino.tara.model.Mogram;
import io.intino.tara.processors.model.Model;

import java.util.*;
import java.util.logging.Logger;


public class GraphLoaderCreator extends Generator implements TemplateTags {
	private static final Logger LOG = Logger.getGlobal();
	private final CompilerConfiguration conf;

	public GraphLoaderCreator(Language language, CompilerConfiguration conf) {
		super(language, conf.dsl().outDsl(), conf.generationPackage(), conf.dsl().generationPackage());
		this.conf = conf;
	}

	public String create(Model model) {
		FrameBuilder builder = new FrameBuilder("graphLoader");
		builder.add(WORKING_PACKAGE, workingPackage);
		builder.add(NAME, outDsl);
		builder.add(LANGUAGE, language.languageName());
		builder.add(LANGUAGE + "_" + WORKING_PACKAGE, conf.dsl().generationPackage());
		builder.add(STASH, createStashes(model));
		return Generator.customize(new GraphLoaderTemplate()).render(builder.toFrame());
	}

	private Frame[] createStashes(Model model) {
		return unpack(model).stream()
				.map(nodes -> {
					try {
						return stashFrame(stashOf(nodes, language));
					} catch (TaraException e) {
						LOG.log(java.util.logging.Level.SEVERE, "Error during stash generation: " + e.getMessage(), e);
						return null;
					}
				})
				.filter(Objects::nonNull)
				.toArray(Frame[]::new);
	}

	private List<List<Mogram>> unpack(Model model) {
		Map<String, List<Mogram>> nodes = new HashMap<>();
		model.components().forEach(node -> {
			if (!nodes.containsKey(node.source().getPath())) nodes.put(node.source().getPath(), new ArrayList<>());
			nodes.get(node.source().getPath()).add(node);
		});
		return unpack(nodes);
	}

	private List<List<Mogram>> unpack(Map<String, List<Mogram>> nodes) {
		return new ArrayList<>(nodes.values());
	}

	private Stash stashOf(List<Mogram> nodes, Language language) throws TaraException {
		try {
			return new StashCreator(nodes, language, conf.module(), conf).create();
		} catch (Throwable e) {
			throw new TaraException("Error creating stashes: " + e.getMessage());
		}
	}
}
