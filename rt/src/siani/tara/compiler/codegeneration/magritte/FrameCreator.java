package siani.tara.compiler.codegeneration.magritte;

import siani.tara.compiler.model.impl.Model;

public abstract class FrameCreator implements TemplateTags {


	protected final Model model;
	protected final String project;
	protected final boolean terminal;

	public FrameCreator(String project, Model model) {
		this.model = model;
		this.project = project;
		terminal = model.isTerminal();
	}

//	protected String[] getLinkNodeTypes(LinkNode node) {
//		List<String> types = new ArrayList<>();
//		types.add(NODE);
//		types.add(REFERENCE);
//		for (Annotation annotation : node.getAnnotations())
//			types.add(annotation.getName());
//		return types.toArray(new String[types.size()]);
//	}
//

//
//	protected String[] getFacetTypes(Variable variable) {
//		List<String> types = new ArrayList<>();
//		Collections.addAll(types, getTypes(variable));
//		types.add(TARGET);
//		return types.toArray(new String[types.size()]);
//	}
//

//


}
