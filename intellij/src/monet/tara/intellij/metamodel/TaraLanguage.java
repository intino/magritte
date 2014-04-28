package monet.tara.intellij.metamodel;

import com.intellij.lang.Language;

public class TaraLanguage extends Language {

	public static Object heritage;

	public static final TaraLanguage INSTANCE = new TaraLanguage();

	private TaraLanguage() {
		super("Tara");
		loadHeritage();
	}

	private void loadHeritage() {
		
	}
}