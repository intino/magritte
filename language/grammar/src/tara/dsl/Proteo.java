package tara.dsl;

import tara.lang.model.rules.Size;

import java.util.Locale;

import static tara.lang.semantics.constraints.RuleFactory.component;
import static tara.lang.semantics.constraints.RuleFactory.name;


public class Proteo extends Tara {

	private static final String CONCEPT = "Concept";

	public Proteo() {
		def(Root).with(context(Root).has(component(CONCEPT, Size.MULTIPLE)));
		def(CONCEPT).with(context(CONCEPT).has(name(), component(CONCEPT, Size.MULTIPLE)));
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