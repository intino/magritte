package io.intino.magritte.builder.parser;

import io.intino.magritte.Language;
import io.intino.magritte.builder.core.CompilerConfiguration;
import io.intino.magritte.builder.core.SourceUnit;
import io.intino.magritte.builder.model.Model;
import io.intino.magritte.builder.model.NodeImpl;
import io.intino.magritte.lang.model.Node;

import java.io.File;
import java.util.*;

public class ASTMerger {
	private final Collection<SourceUnit> sources;
	private final CompilerConfiguration conf;

	public ASTMerger(Collection<SourceUnit> sources, CompilerConfiguration conf) {
		this.sources = sources;
		this.conf = conf;
	}

	public Map<Language, Model> doMerge() {
		final Map<Language, List<SourceUnit>> languageListMap = groupByLanguage();
		Map<Language, Model> models = new HashMap<>();
		for (Language language : languageListMap.keySet())
			models.put(language, processByLanguage(language, languageListMap.get(language)));
		return models;
	}

	private Model processByLanguage(Language language, List<SourceUnit> sourceUnits) {
		Model model = new Model(getName());
		model.setLanguage(language);
		model.setResourcesRoot(conf.resourcesDirectory());
		model.level(conf.model().level());
		for (SourceUnit unit : sourceUnits) {
			List<Node> components = unit.getModel().components();
			components.forEach(c -> {
				model.add(c, unit.getModel().rulesOf(c));
				((NodeImpl) c).setDirty(unit.isDirty());
			});
			if (!components.isEmpty()) model.languageName(components.get(0).languageName());
		}
		for (Node node : model.components()) node.container(model);
		return model;
	}

	private Map<Language, List<SourceUnit>> groupByLanguage() {
		Map<Language, List<SourceUnit>> list = new HashMap<>();
		for (SourceUnit source : sources) {
			if (source.getModel().components().isEmpty()) continue;
			if (!list.containsKey(source.getModel().language()))
				list.put(source.getModel().language(), new ArrayList<>());
			list.get(source.getModel().language()).add(source);
		}
		return list;

	}

	private String getName() {
		return conf.getProject() != null ? conf.getProject() + "." + conf.model().outDsl() :
				getPresentableName();
	}

	private String getPresentableName() {
		final String name = new File(sources.iterator().next().getName()).getName();
		return name.substring(0, name.lastIndexOf("."));
	}

}
