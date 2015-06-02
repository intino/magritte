package siani.tara.dsls;

import siani.tara.semantic.model.Tara;

import java.util.Locale;

public class Tafat extends Tara {

	public Tafat() {
	}

	@Override
	public String languageName() {
		return "Tafat";
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
