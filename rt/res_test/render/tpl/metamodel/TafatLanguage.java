package siani.tafat.intellij.metamodel;

import com.intellij.lang.Language;

public class TafatLanguage extends Language {
	public static final TafatLanguage INSTANCE = new TafatLanguage();

	private TafatLanguage() {
		super("Tafat");
	}
}