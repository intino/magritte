package tara.dsl;

import java.util.Locale;

public class Monet extends Tara {
	public Monet() {

	}

	@Override
	public String languageName() {
		return "Monet";
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
