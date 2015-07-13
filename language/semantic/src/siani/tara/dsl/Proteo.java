package siani.tara.dsl;

import siani.tara.semantic.constraints.RuleFactory;
import siani.tara.semantic.model.Tara;

import java.util.Locale;

import static siani.tara.semantic.constraints.RuleFactory.multiple;


public class Proteo extends Tara {

	private static final String CONCEPT = "Concept";

	public Proteo() {
		in(Root).def(context(Root).allow(multiple(CONCEPT)));
		in(CONCEPT).def(context(CONCEPT).require(RuleFactory._name()).allow(multiple(CONCEPT)));
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