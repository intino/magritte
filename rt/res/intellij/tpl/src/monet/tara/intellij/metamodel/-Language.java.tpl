package monet.::projectName::.intellij.metamodel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.intellij.lang.Language;
import monet.::projectName::.lang.ASTNode;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

public class ::projectProperName::Language extends Language {

	public static final String AST_JSON = "/ast/ast.json";
	public static final ::projectProperName::Language INSTANCE = new ::projectProperName::Language();
	public static List<ASTNode> heritage = null;

	private ::projectProperName::Language() {
		super("::projectProperName::");
		loadHeritage();
	}

	public static List<ASTNode> getHeritage() {
		return heritage;
	}

	public static boolean hasHeritage() {
		return heritage != null;
	}

	private void loadHeritage() {
		try {
			InputStream heritageInputStream = this.getClass().getResourceAsStream(AST_JSON);
			Gson gson = new Gson();
			Type collectionType = new TypeToken<Collection<ASTNode>>() {
			}.getType();
			heritage = gson.fromJson(new InputStreamReader(heritageInputStream), collectionType);
		} catch (Exception e) {
			e.printStackTrace();
			heritage = null;
		}
	}
}