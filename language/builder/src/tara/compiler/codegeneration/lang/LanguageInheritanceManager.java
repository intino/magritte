package tara.compiler.codegeneration.lang;

import org.siani.itrules.model.Frame;
import tara.Language;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.compiler.model.Model;
import tara.lang.semantics.Assumption;
import tara.lang.semantics.Constraint;
import tara.lang.semantics.Context;

import java.util.List;

class LanguageInheritanceManager implements TemplateTags {
	private final Frame root;
	private final List<String> instances;
	private final Language language;
	private TerminalConstraintManager manager;

	LanguageInheritanceManager(Frame root, List<String> instances, Language language, Model model) {
		this.root = root;
		this.instances = instances;
		this.language = language;
		manager = new TerminalConstraintManager(language, model);
	}

	void fill() {
		if (instances == null || root == null) return;
		for (String instance : instances) {
			Frame nodeFrame = new Frame().addTypes(NODE);
			fillRuleInfo(nodeFrame, instance);
			addConstraints(nodeFrame, language.constraints(instance));
			addAssumptions(nodeFrame, language.assumptions(instance));
			root.addFrame(NODE, nodeFrame);
		}
	}

	private void addConstraints(Frame frame, List<Constraint> constraints) {
		Frame constraintsFrame = new Frame().addTypes(CONSTRAINTS);
		manager.addConstraints(constraints, constraintsFrame);
		frame.addFrame(CONSTRAINTS, constraintsFrame);
	}

	private void fillRuleInfo(Frame frame, String aCase) {
		Context rules = language.catalog().get(aCase);
		frame.addFrame(TemplateTags.NAME, aCase);
		addTypes(rules.types(), frame);
	}

	private void addTypes(String[] types, Frame frame) {
		if (types == null) return;
		Frame typesFrame = new Frame().addTypes(NODE_TYPE);
		for (String type : types) typesFrame.addFrame(TemplateTags.TYPE, type);
		if (typesFrame.slots().length > 0) frame.addFrame(NODE_TYPE, typesFrame);
	}

	private void addAssumptions(Frame frame, List<Assumption> assumptions) {
		Frame assumptionsFrame = new Frame().addTypes(ASSUMPTIONS);
		for (Assumption assumption : assumptions)
			assumptionsFrame.addFrame(ASSUMPTION, getAssumptionValue(assumption));
		if (assumptionsFrame.slots().length != 0)
			frame.addFrame(ASSUMPTIONS, assumptionsFrame);
	}

	private Object getAssumptionValue(Assumption assumption) {
		return assumption.getClass().getInterfaces()[0].getName().
				substring(assumption.getClass().getInterfaces()[0].getName().lastIndexOf("$") + 1);
	}
}
