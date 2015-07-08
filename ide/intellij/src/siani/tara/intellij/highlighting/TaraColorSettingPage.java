package siani.tara.intellij.highlighting;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.TaraIcons;

import java.util.HashMap;
import java.util.Map;

public class TaraColorSettingPage implements ColorSettingsPage {
	private static final AttributesDescriptor[] DESCRIPTORS =
		new AttributesDescriptor[]{
			new AttributesDescriptor("Meta Identifier", TaraSyntaxHighlighter.META_IDENTIFIER),
			new AttributesDescriptor("Identifier", TaraSyntaxHighlighter.IDENTIFIER),
			new AttributesDescriptor("String", TaraSyntaxHighlighter.STRING),
			new AttributesDescriptor("Documentation", TaraSyntaxHighlighter.DOCUMENTATION),
			new AttributesDescriptor("Primitive", TaraSyntaxHighlighter.PRIMITIVE),
			new AttributesDescriptor("Annotations", TaraSyntaxHighlighter.ANNOTATION),
			new AttributesDescriptor("Number", TaraSyntaxHighlighter.NUMBER),
			new AttributesDescriptor("Operator", TaraSyntaxHighlighter.OPERATOR),
			new AttributesDescriptor("Bad Characters", TaraSyntaxHighlighter.BAD_CHARACTER)
		};

	@Nullable
	@Override
	public javax.swing.Icon getIcon() {
		return TaraIcons.ICON_13;
	}

	@NotNull
	@Override
	public com.intellij.openapi.fileTypes.SyntaxHighlighter getHighlighter() {
		return new TaraSyntaxHighlighter();
	}

	@NotNull
	@Override
	public String getDemoText() {
		return
			"box millenermetamodel.m0.pruebareunion\n" +
				"use millenermetamodel.m1 as metamodel\n" +
				"\n" +
				"Territory LaReunion is terminal\n" +
				"    var integer value = 0\n" +
				"    var string quantity = \'cantidad\'\n" +
				"    Territory SaintPierre\n" +
				"        Zone C49D420\n" +
				"            Zone I23981\n" +
				"        Zone Z9D421\n" +
				"        Zone Z9D409\n" +
				"        Zone Z9D402\n" +
				" \n" +
				"Building X\n" +
				"   Place(C49D420)\n" +
				"   Area(30.0)\n" +
				"\tHousehold X\n" +
				"\t\tBattery X \n" +
				"\t\t\tis BatteryControl\n" +
				"\t\t\tis Storage\n" +
				"\t\t\tis Electrical\n" +
				"\n" +
				"\n" +
				"Transformer Z97411P1120\n" +
				"    Location((55.47953318,-20.89841472))\n" +
				"    is Electrical\n" +
				"\n" +
				"\n" +
				"Substation S_PAUD\n" +
				"    Location((55.29967083,20.98750276))\n" +
				"    FeederSocket BELLEMENE is Electrical\n" +
				"\n" +
				"Connection(S_PAUD, X.X.X)\n";
	}

	@Nullable
	@Override
	public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
		return new HashMap<>();
	}

	@NotNull
	@Override
	public AttributesDescriptor[] getAttributeDescriptors() {
		return DESCRIPTORS;
	}

	@NotNull
	@Override
	public ColorDescriptor[] getColorDescriptors() {
		return ColorDescriptor.EMPTY_ARRAY;
	}

	@NotNull
	@Override
	public String getDisplayName() {
		return "Tara";
	}
}