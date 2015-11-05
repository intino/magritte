package tara.dsl;

import java.util.Locale;

public class Mobility extends Tara {

	@Override
	public String languageName() {
		return "Mobility";
	}

	@Override
	public Locale locale() {
		return Locale.ENGLISH;
	}

	@Override
	public boolean isTerminalLanguage() {
		return true;
	}
}
