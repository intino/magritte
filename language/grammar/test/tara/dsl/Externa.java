package tara.dsl;

import java.util.Locale;

public class Externa extends Tara {
	public Externa() {

	}

	@Override
	public String languageName() {
		return "Externa";
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
