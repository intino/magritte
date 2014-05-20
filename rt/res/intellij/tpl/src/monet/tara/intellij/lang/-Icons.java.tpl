package monet.::projectName::.intellij.lang;

import com.intellij.openapi.util.IconLoader;
import org.jetbrains.annotations.NonNls;

import javax.swing.*;
import java.util.HashMap;

public class ::projectProperName::Icons {


	public static final String ICON_13 = "ICON_13";
	public static final String DEFINITION = "DEFINITION_13";
	public static final String CASE_13 = "CASE_13";
	public static final String BASE_13 = "BASE_13";
	public static final String ICON_100 = "ICON_100";
::iconsVar::
	\@NonNls
	public static HashMap<String, Icon> icons = new HashMap<>();

	static {
		icons.put("ICON_13", IconLoader.getIcon("/icons/::projectProperName::.png"));
		icons.put("CASE_13", IconLoader.getIcon("/icons/case.png"));
		icons.put("BASE_13", IconLoader.getIcon("/icons/base.png"));
		icons.put("ICON_100", IconLoader.getIcon("/icons/::projectProperName::100.png"));
		icons.put("DEFINITION", IconLoader.getIcon("/icons/Definition.png"));
::icons::
	}

	private ::projectProperName::Icons() {
	}

	public static Icon getIcon(String icon) {
		return icons.get(icon);
	}
}