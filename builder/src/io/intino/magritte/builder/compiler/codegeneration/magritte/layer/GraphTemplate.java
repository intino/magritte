package io.intino.magritte.builder.compiler.codegeneration.magritte.layer;

import io.intino.itrules.template.Rule;
import io.intino.itrules.template.Template;
import io.intino.magritte.builder.compiler.codegeneration.magritte.layer.templates.AbstractGraphTemplate;
import io.intino.magritte.builder.compiler.codegeneration.magritte.layer.templates.layer.HelpersTemplate;
import io.intino.magritte.builder.compiler.codegeneration.magritte.layer.templates.layer.NewElementTemplate;

import java.util.ArrayList;
import java.util.List;

public class GraphTemplate extends Template {

	@Override
	public List<Rule> ruleSet() {
		ArrayList<Rule> rules = new ArrayList<>(new NewElementTemplate().ruleSet());
		rules.addAll(new AbstractGraphTemplate().ruleSet());
		rules.addAll(new HelpersTemplate().ruleSet());
		return rules;
	}
}