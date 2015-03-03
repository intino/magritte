package siani.tara.intellij.lang;

import com.intellij.openapi.util.IconLoader;
import org.jetbrains.annotations.NonNls;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class TaraIcons {


	public static final String ICON_13 = "ICON_13";
	public static final String MODEL = "MODEL";
	public static final String CONCEPT = "CONCEPT";
	public static final String CASE_13 = "SUB_CONCEPT";
	public static final String ICON_100 = "ICON_100";
	public static final String ADD_SDK = "ADD_SDK";
	public static final String SDK_CLOSED = "SDK_CLOSED";

	@NonNls
	public static Map<String, Icon> icons = new HashMap<>();

	static {
		icons.put("ICON_13", IconLoader.getIcon("/icons/Tara.png"));
		icons.put("SUB_CONCEPT", IconLoader.getIcon("/icons/subConcept.png"));
		icons.put("ICON_100", IconLoader.getIcon("/icons/Tara100.png"));
		icons.put("CONCEPT", IconLoader.getIcon("/icons/Concept.png"));
		icons.put("MODEL", IconLoader.getIcon("/icons/model.png"));
		icons.put(ADD_SDK, IconLoader.getIcon("/icons/add_sdk.png"));
		icons.put(SDK_CLOSED, IconLoader.getIcon("/icons/sdk_closed.png"));
	}

	private TaraIcons() {
	}

	public static Icon getIcon(String icon) {
		return icons.get(icon);
	}
}