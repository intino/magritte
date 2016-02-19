package tara.intellij.lang;

public class TaraLanguage extends com.intellij.lang.Language {

	public static final TaraLanguage INSTANCE = new TaraLanguage();
	public static final String PROTEO = "Proteo";
	public static final String PROTEO_ONTOLOGY = "Proteo_Ontology";

	private TaraLanguage() {
		super("Tara");
	}

}