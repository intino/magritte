package io.intino.magritte.builder.compiler.codegeneration.magritte.layer;

import io.intino.itrules.template.Rule;
import io.intino.itrules.template.Template;
import io.intino.magritte.builder.compiler.codegeneration.magritte.layer.templates.layer.*;

import java.util.ArrayList;
import java.util.List;

public class LayerTemplate extends Template {

	@Override
	public List<Rule> ruleSet() {
		List<Rule> rules = new ArrayList<>();
		rules.addAll(new io.intino.magritte.builder.compiler.codegeneration.magritte.layer.templates.layer.LayerTemplate().ruleSet());
		rules.addAll(new HelpersTemplate().ruleSet());
		rules.addAll(new DeclarationTemplate().ruleSet());
		rules.addAll(new ConstructorTemplate().ruleSet());
		rules.addAll(new GettersTemplate().ruleSet());
		rules.addAll(new SettersTemplate().ruleSet());
		rules.addAll(new NewElementTemplate().ruleSet());
		rules.addAll(new Init_referenceTemplate().ruleSet());
		rules.addAll(new InitTemplate().ruleSet());
		rules.addAll(new Set_referenceTemplate().ruleSet());
		rules.addAll(new SetTemplate().ruleSet());
		rules.addAll(new ListTemplate().ruleSet());
		return rules;
	}

	private Rule[] getAll(Iterable<Rule> ruleSet) {
		List<Rule> rules = new ArrayList<>();
		ruleSet.forEach(rules::add);
		return rules.toArray(new Rule[0]);
	}
}
