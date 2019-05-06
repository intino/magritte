package io.intino.tara.compiler.codegeneration.magritte.layer;

import io.intino.itrules.Rule;
import io.intino.itrules.RuleSet;
import io.intino.itrules.Template;
import io.intino.tara.compiler.codegeneration.magritte.layer.templates.layer.*;

import java.util.ArrayList;
import java.util.List;

public class LayerTemplate extends Template {

	@Override
	protected RuleSet ruleSet() {
		return new RuleSet()
				.add(getAll(new io.intino.tara.compiler.codegeneration.magritte.layer.templates.layer.LayerTemplate().ruleSet()))
				.add(getAll(new HelpersTemplate().ruleSet()))
				.add(getAll(new DeclarationTemplate().ruleSet()))
				.add(getAll(new ConstructorTemplate().ruleSet()))
				.add(getAll(new GettersTemplate().ruleSet()))
				.add(getAll(new SettersTemplate().ruleSet()))
				.add(getAll(new NewElementTemplate().ruleSet()))
				.add(getAll(new Init_referenceTemplate().ruleSet()))
				.add(getAll(new InitTemplate().ruleSet()))
				.add(getAll(new Set_referenceTemplate().ruleSet()))
				.add(getAll(new SetTemplate().ruleSet()))
				.add(getAll(new ListTemplate().ruleSet()))
				;
	}

	private Rule[] getAll(Iterable<Rule> ruleSet) {
		List<Rule> rules = new ArrayList<>();
		ruleSet.forEach(rules::add);
		return rules.toArray(new Rule[0]);
	}
}
