package tara.dsl;

import java.util.Locale;

public class Monopoly extends Tara {
	public Monopoly() {

	}

	@Override
	public String languageName() {
		return "Monopoly";
	}

	@Override
	public Locale locale() {
		return Locale.ENGLISH;
	}

	@Override
	public boolean isTerminalLanguage() {
		return true;
	}

	@Override
	public String metaLanguage() {
		return "tafat";
	}
}