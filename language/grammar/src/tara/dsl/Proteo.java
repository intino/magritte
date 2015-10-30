package tara.dsl;

import tara.lang.model.rules.Size;
import tara.lang.semantics.constraints.RuleFactory;

import java.util.Locale;


public class Proteo extends Tara {

	private static final String CONCEPT = "Concept";

	public Proteo() {
		in(Root).def(context(Root).allow(RuleFactory.component(CONCEPT, Size.MULTIPLE)));
		in(CONCEPT).def(context(CONCEPT).require(RuleFactory.name()).allow(RuleFactory.component(CONCEPT, Size.MULTIPLE)));
	}

	@Override
	public String languageName() {
		return "Proteo";
	}

	@Override
	public Locale locale() {
		return Locale.ENGLISH;
	}

	@Override
	public boolean isTerminalLanguage() {
		return false;
	}
}