package tara.compiler.parser;

import tara.compiler.constants.TaraBuildConstants;
import tara.compiler.core.CompilerConfiguration;
import tara.compiler.core.SourceUnit;
import tara.compiler.core.errorcollection.MergeException;
import tara.compiler.model.Model;
import tara.lang.model.Node;

import java.io.File;
import java.util.Collection;
import java.util.List;

public class ASTMerger {
	private final Collection<SourceUnit> sources;
	private final CompilerConfiguration conf;

	public ASTMerger(Collection<SourceUnit> sources, CompilerConfiguration conf) {
		this.sources = sources;
		this.conf = conf;
	}

	public Model doMerge() throws MergeException {
		Model model = new Model(getName());
		model.setLevel(conf.getLevel());
		for (SourceUnit unit : sources) {
			List<Node> includedNodes = unit.getModel().components();
			model.add(includedNodes.toArray(new Node[includedNodes.size()]));
			if (!includedNodes.isEmpty()) model.language(includedNodes.get(0).language());
		}
		for (Node node : model.components()) node.container(model);
		if (conf.isVerbose())
			System.out.println(TaraBuildConstants.PRESENTABLE_MESSAGE + "Tarac: loading metrics...");
		return model;
	}

	private String getName() {
		return conf.getProject() != null ? conf.getProject() + "." + conf.getGeneratedLanguage() :
			getPresentableName();
	}

	private String getPresentableName() {
		final String name = new File(sources.iterator().next().getName()).getName();
		return name.substring(0, name.lastIndexOf("."));
	}

}
