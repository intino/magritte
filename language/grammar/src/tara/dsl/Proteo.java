package tara.dsl;

import tara.lang.model.rules.Size;

import java.util.Locale;

import static tara.lang.semantics.constraints.RuleFactory.component;
import static tara.lang.semantics.constraints.RuleFactory.isTerminal;
import static tara.lang.semantics.constraints.RuleFactory.name;


public class Proteo extends Tara {

	private static final String CONCEPT = "Concept";
	private static final String METACONCEPT = "Metaconcept";

	public Proteo() {
		def(Root).with(context(Root).has(component(CONCEPT, Size.MULTIPLE), component(METACONCEPT, Size.MULTIPLE)));
		def(CONCEPT).with(context(METACONCEPT).has(name(), component(METACONCEPT, Size.MULTIPLE), component(CONCEPT, Size.MULTIPLE)).assume(isTerminal()));
		def(METACONCEPT).with(context(METACONCEPT).has(name(), component(METACONCEPT, Size.MULTIPLE), component(CONCEPT, Size.MULTIPLE)));
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

	@Override
	public String metaLanguage() {
		return "";
	}
}