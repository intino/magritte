package tara.intellij.lang;

public class TaraLanguage extends com.intellij.lang.Language {

	public static final TaraLanguage INSTANCE = new TaraLanguage();

	private TaraLanguage() {
		super("Tara");
	}

}