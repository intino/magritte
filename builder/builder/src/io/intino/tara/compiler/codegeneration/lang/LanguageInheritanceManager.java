package io.intino.tara.compiler.codegeneration.lang;

import io.intino.itrules.FrameBuilder;
import io.intino.itrules.FrameBuilderContext;
import io.intino.tara.Language;
import io.intino.tara.compiler.codegeneration.magritte.TemplateTags;
import io.intino.tara.compiler.model.Model;
import io.intino.tara.lang.semantics.Assumption;
import io.intino.tara.lang.semantics.Constraint;
import io.intino.tara.lang.semantics.Context;

import java.util.List;

class LanguageInheritanceManager implements TemplateTags {
	private final FrameBuilderContext root;
	private final List<String> instanceConstraints;
	private final Language language;
	private TerminalConstraintManager manager;

	LanguageInheritanceManager(FrameBuilderContext root, List<String> instanceConstraints, Language language, Model model) {
		this.root = root;
		this.instanceConstraints = instanceConstraints;
		this.language = language;
		this.manager = new TerminalConstraintManager(language, model);
	}

	void fill() {
		if (instanceConstraints == null || root == null) return;
		for (String instance : instanceConstraints) {
			FrameBuilder nodeFrame = new FrameBuilder(NODE);
			fillRuleInfo(nodeFrame, instance);
			addConstraints(nodeFrame, language.constraints(instance));
			addAssumptions(nodeFrame, language.assumptions(instance));
			root.add(NODE, nodeFrame.toFrame());
		}
	}

	private void addConstraints(FrameBuilder builder, List<Constraint> constraints) {
		FrameBuilder constraintsBuilder = new FrameBuilder(CONSTRAINTS);
		manager.addConstraints(constraints, constraintsBuilder);
		builder.add(CONSTRAINTS, constraintsBuilder.toFrame());
	}

	private void fillRuleInfo(FrameBuilder frame, String instance) {
		Context rules = language.catalog().get(instance);
		frame.add(NAME, instance);
		addTypes(rules.types(), frame);
	}

	private void addTypes(String[] types, FrameBuilder frame) {
		if (types == null) return;
		FrameBuilder typesBuilder = new FrameBuilder(NODE_TYPE);
		for (String type : types) typesBuilder.add(TemplateTags.TYPE, type);
		if (typesBuilder.slots() != 0) frame.add(NODE_TYPE, typesBuilder.toFrame());
	}

	private void addAssumptions(FrameBuilder frame, List<Assumption> assumptions) {
		FrameBuilder builder = new FrameBuilder(ASSUMPTIONS);
		for (Assumption assumption : assumptions)
			builder.add(ASSUMPTION, getAssumptionValue(assumption));
		if (builder.slots() != 0) frame.add(ASSUMPTIONS, builder.toFrame());
	}

	private Object getAssumptionValue(Assumption assumption) {
		return assumption.getClass().getInterfaces()[0].getName().
				substring(assumption.getClass().getInterfaces()[0].getName().lastIndexOf("$") + 1);
	}
}
