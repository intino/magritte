package tara.dsl;

import java.util.Locale;

public class Analytics extends Tara {
	public Analytics() {
	}


	@Override
	public String languageName() {
		return "Analytics";
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