package io.intino.magritte.builder.compiler.codegeneration.magritte.layer;

import io.intino.itrules.Rule;
import io.intino.itrules.RuleSet;
import io.intino.itrules.Template;
import io.intino.magritte.builder.compiler.codegeneration.magritte.layer.templates.AbstractGraphTemplate;
import io.intino.magritte.builder.compiler.codegeneration.magritte.layer.templates.layer.HelpersTemplate;
import io.intino.magritte.builder.compiler.codegeneration.magritte.layer.templates.layer.NewElementTemplate;

import java.util.ArrayList;
import java.util.List;

public class GraphTemplate extends Template {

	@Override
	protected RuleSet ruleSet() {
		return new RuleSet()
				.add(getAll(new NewElementTemplate().ruleSet()))
				.add(getAll(new AbstractGraphTemplate().ruleSet()))
				.add(getAll(new HelpersTemplate().ruleSet()));

	}

	private Rule[] getAll(Iterable<Rule> ruleSet) {
		List<Rule> rules = new ArrayList<>();
		ruleSet.forEach(rules::add);
		return rules.toArray(new Rule[0]);
	}
}