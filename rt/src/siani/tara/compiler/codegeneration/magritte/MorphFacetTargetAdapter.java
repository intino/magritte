package siani.tara.compiler.codegeneration.magritte;

import org.siani.itrules.framebuilder.Adapter;
import org.siani.itrules.framebuilder.BuilderContext;
import org.siani.itrules.model.Frame;
import siani.tara.Language;
import siani.tara.compiler.model.impl.NodeImpl;

import java.util.Locale;
import java.util.Set;

public class MorphFacetTargetAdapter implements Adapter<NodeImpl> {
	private final String project;
	private final Language language;
	private final Set<String> imports;
	private final Locale locale;

	public MorphFacetTargetAdapter(String project, Language language, Set<String> imports, Locale locale) {
		this.project = project;
		this.language = language;
		this.imports = imports;
		this.locale = locale;
	}

	@Override
	public void adapt(Frame frame, NodeImpl node, BuilderContext builderContext) {


	}
}
