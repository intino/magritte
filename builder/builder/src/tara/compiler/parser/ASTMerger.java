package tara.compiler.parser;

import tara.compiler.core.CompilerConfiguration;
import tara.compiler.core.SourceUnit;
import tara.compiler.core.errorcollection.MergeException;
import tara.compiler.model.Model;
import tara.compiler.model.NodeImpl;
import tara.lang.model.Node;

import java.io.File;
import java.util.Collection;
import java.util.List;

import static tara.compiler.constants.TaraBuildConstants.PRESENTABLE_MESSAGE;

public class ASTMerger {
	private final Collection<SourceUnit> sources;
	private final CompilerConfiguration conf;

	public ASTMerger(Collection<SourceUnit> sources, CompilerConfiguration conf) {
		this.sources = sources;
		this.conf = conf;
	}

	public Model doMerge() throws MergeException {
		Model model = new Model(getName(), conf.language());
		model.setResourcesRoot(conf.resourcesDirectory());
		model.setLevel(conf.level());
		for (SourceUnit unit : sources) {
			List<Node> components = unit.getModel().components();
			components.stream().forEach(c -> {
				model.add(c, unit.getModel().ruleOf(c));
				((NodeImpl) c).setDirty(unit.isDirty());
			});
			if (!components.isEmpty()) model.language(components.get(0).language());
		}
		for (Node node : model.components()) node.container(model);
		if (conf.isVerbose()) System.out.println(PRESENTABLE_MESSAGE + "Tarac: Merging fragments...");
		return model;
	}

	private String getName() {
		return conf.getProject() != null ? conf.getProject() + "." + conf.outDSL() :
			getPresentableName();
	}

	private String getPresentableName() {
		final String name = new File(sources.iterator().next().getName()).getName();
		return name.substring(0, name.lastIndexOf("."));
	}

}
