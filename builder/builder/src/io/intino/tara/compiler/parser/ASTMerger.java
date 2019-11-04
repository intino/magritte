package io.intino.tara.compiler.parser;

import io.intino.tara.Language;
import io.intino.tara.compiler.core.CompilerConfiguration;
import io.intino.tara.compiler.core.SourceUnit;
import io.intino.tara.compiler.model.Model;
import io.intino.tara.compiler.model.NodeImpl;
import io.intino.tara.lang.model.Node;

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
		model.setLevel(conf.model().level());
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
			if (!list.containsKey(source.getModel().language()))
				list.put(source.getModel().language(), new ArrayList<>());
			list.get(source.getModel().language()).add(source);
		}
		return list;

	}

	private String getName() {
		return conf.getProject() != null ? conf.getProject() + "." + conf.model().outLanguage() :
				getPresentableName();
	}

	private String getPresentableName() {
		final String name = new File(sources.iterator().next().getName()).getName();
		return name.substring(0, name.lastIndexOf("."));
	}

}
