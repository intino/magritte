package tara.dsl;

import java.util.Locale;

public class Externa extends Tara {
	public Externa() {
	}

	@Override
	public String languageName() {
		return "Census";
	}

	@Override
	public Locale locale() {
		return new Locale("es", "SPAIN", "es_ES");
	}

	@Override
	public boolean isTerminalLanguage() {
		return false;
	}
}
