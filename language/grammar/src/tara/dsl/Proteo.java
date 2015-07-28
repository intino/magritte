package tara.dsl;

import tara.language.semantics.constraints.RuleFactory;

import java.util.Locale;

import static tara.language.semantics.constraints.RuleFactory.multiple;


public class Proteo extends Tara {

	private static final String CONCEPT = "Concept";

	public Proteo() {
		in(Root).def(context(Root).allow(RuleFactory.multiple(CONCEPT)));
		in(CONCEPT).def(context(CONCEPT).require(RuleFactory._name()).allow(RuleFactory.multiple(CONCEPT)));
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