package io.intino.tara.compiler.codegeneration.lang;

import io.intino.itrules.model.Frame;
import io.intino.tara.Language;
import io.intino.tara.compiler.codegeneration.magritte.TemplateTags;
import io.intino.tara.compiler.model.Model;
import io.intino.tara.lang.semantics.Assumption;
import io.intino.tara.lang.semantics.Constraint;
import io.intino.tara.lang.semantics.Context;

import java.util.List;

class LanguageInheritanceManager implements TemplateTags {
	private final Frame root;
	private final List<String> instanceConstraints;
	private final Language language;
	private TerminalConstraintManager manager;

	LanguageInheritanceManager(Frame root, List<String> instanceConstraints, Language language, Model model) {
		this.root = root;
		this.instanceConstraints = instanceConstraints;
		this.language = language;
		this.manager = new TerminalConstraintManager(language, model);
	}

	void fill() {
		if (instanceConstraints == null || root == null) return;
		for (String instance : instanceConstraints) {
			Frame nodeFrame = new Frame().addTypes(NODE);
			fillRuleInfo(nodeFrame, instance);
			addConstraints(nodeFrame, language.constraints(instance));
			addAssumptions(nodeFrame, language.assumptions(instance));
			root.addSlot(NODE, nodeFrame);
		}
	}

	private void addConstraints(Frame frame, List<Constraint> constraints) {
		Frame constraintsFrame = new Frame().addTypes(CONSTRAINTS);
		manager.addConstraints(constraints, constraintsFrame);
		frame.addSlot(CONSTRAINTS, constraintsFrame);
	}

	private void fillRuleInfo(Frame frame, String instance) {
		Context rules = language.catalog().get(instance);
		frame.addSlot(NAME, instance);
		addTypes(rules.types(), frame);
	}

	private void addTypes(String[] types, Frame frame) {
		if (types == null) return;
		Frame typesFrame = new Frame().addTypes(NODE_TYPE);
		for (String type : types) typesFrame.addSlot(TemplateTags.TYPE, type);
		if (typesFrame.slots().length > 0) frame.addSlot(NODE_TYPE, typesFrame);
	}

	private void addAssumptions(Frame frame, List<Assumption> assumptions) {
		Frame assumptionsFrame = new Frame().addTypes(ASSUMPTIONS);
		for (Assumption assumption : assumptions)
			assumptionsFrame.addSlot(ASSUMPTION, getAssumptionValue(assumption));
		if (assumptionsFrame.slots().length != 0)
			frame.addSlot(ASSUMPTIONS, assumptionsFrame);
	}

	private Object getAssumptionValue(Assumption assumption) {
		return assumption.getClass().getInterfaces()[0].getName().
			substring(assumption.getClass().getInterfaces()[0].getName().lastIndexOf("$") + 1);
	}
}
