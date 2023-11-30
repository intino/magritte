package io.intino.magritte.builder.compiler.codegeneration.magritte.layer;

import io.intino.itrules.FrameBuilder;
import io.intino.magritte.builder.compiler.codegeneration.magritte.Generator;
import io.intino.magritte.builder.compiler.codegeneration.magritte.TemplateTags;
import io.intino.magritte.builder.compiler.codegeneration.magritte.layer.templates.GraphLoaderTemplate;
import io.intino.magritte.builder.compiler.codegeneration.magritte.stash.StashCreator;
import io.intino.magritte.io.Stash;
import io.intino.magritte.io.StashSerializer;
import io.intino.tara.Language;
import io.intino.tara.builder.core.CompilerConfiguration;
import io.intino.tara.builder.core.errorcollection.TaraException;
import io.intino.tara.builder.model.Model;
import io.intino.tara.builder.utils.Format;
import io.intino.tara.language.model.Mogram;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.logging.Logger;


public class GraphLoaderCreator extends Generator implements TemplateTags {
	private static final Logger LOG = Logger.getGlobal();
	private final CompilerConfiguration conf;

	public GraphLoaderCreator(Language language, CompilerConfiguration conf) {
		super(language, conf.model().outDsl(), conf.workingPackage(), conf.model().language().generationPackage());
		this.conf = conf;
	}

	public String create(Model model) {
		FrameBuilder builder = new FrameBuilder("graphLoader");
		builder.add(LANGUAGE, conf.model().language());
		builder.add(STASH, createStashes(model));
		return Format.customize(new GraphLoaderTemplate()).render(builder.toFrame());
	}

	private String[] createStashes(Model model) {
		return unpack(model).parallelStream()
				.map(nodes -> {
					try {
						return stashOf(nodes, model.language());
					} catch (TaraException e) {
						LOG.log(java.util.logging.Level.SEVERE, "Error during stash generation: " + e.getMessage(), e);
						return null;
					}
				})
				.filter(Objects::nonNull)
				.map(s -> Base64.getEncoder().encodeToString(StashSerializer.serialize(s)))
				.toArray(String[]::new);
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

	private Stash stashOf(List<Mogram> nodes, Language language) throws TaraException {
		try {
			return new StashCreator(nodes, language, conf.getModule(), conf).create();
		} catch (Throwable e) {
			throw new TaraException("Error creating stashes: " + e.getMessage());
		}
	}


	private void create(File file, String text) {
		try {
			Files.writeString(file.toPath(), text);
		} catch (IOException e) {
			LOG.log(java.util.logging.Level.SEVERE, e.getMessage(), e);
		}
	}

}
