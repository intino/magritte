package tara.dsl;

import tara.semantic.model.Tara;

import java.util.Locale;

public class Sumus extends Tara {
	public Sumus() {


	}

	@Override
	public String languageName() {
		return "Sumus";
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
