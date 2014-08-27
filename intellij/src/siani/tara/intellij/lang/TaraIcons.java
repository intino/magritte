package siani.tara.intellij.lang;

import com.intellij.openapi.util.IconLoader;
import org.jetbrains.annotations.NonNls;

import javax.swing.*;
import java.util.HashMap;

public class TaraIcons {


	public static final String ICON_13 = "ICON_13";
	public static final String BOX = "BOX";
	public static final String CONCEPT = "CONCEPT";
	public static final String CASE_13 = "CASE_13";
	public static final String ICON_100 = "ICON_100";
	public static final String ADD_SDK = "ADD_SDK";
	public static final String SDK_CLOSED = "SDK_CLOSED";

	@NonNls
	public static HashMap<String, Icon> icons = new HashMap<>();

	static {
		icons.put("ICON_13", IconLoader.getIcon("/icons/Tara.png"));
		icons.put("CASE_13", IconLoader.getIcon("/icons/case.png"));
		icons.put("BASE_13", IconLoader.getIcon("/icons/base.png"));
		icons.put("ICON_100", IconLoader.getIcon("/icons/Tara100.png"));
		icons.put("CONCEPT", IconLoader.getIcon("/icons/Concept.png"));
		icons.put("BOX", IconLoader.getIcon("/icons/box.png"));
		icons.put(ADD_SDK, IconLoader.getIcon("/icons/add_sdk.png"));
		icons.put(SDK_CLOSED, IconLoader.getIcon("/icons/sdk_closed.png"));
	}

	private TaraIcons() {
	}

	public static Icon getIcon(String icon) {
		return icons.get(icon);
	}
}