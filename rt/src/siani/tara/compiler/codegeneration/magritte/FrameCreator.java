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

}
