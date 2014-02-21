package monet.tara.intellij.metamodel;

import com.intellij.lang.Language;

public class TaraLanguage extends Language {
	public static final TaraLanguage INSTANCE = new TaraLanguage();

	private TaraLanguage() {
		super("Tara");
	}
}