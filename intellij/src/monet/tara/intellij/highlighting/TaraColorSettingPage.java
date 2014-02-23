package monet.tara.intellij.highlighting;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import monet.tara.intellij.metamodel.TaraIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class TaraColorSettingPage implements ColorSettingsPage { //TODO
	private static final AttributesDescriptor[] DESCRIPTORS =
		new AttributesDescriptor[]{
			new AttributesDescriptor("Keyword", TaraSyntaxHighlighter.KEYWORD),
			new AttributesDescriptor("Modifiers", TaraSyntaxHighlighter.MODIFIERS),
			new AttributesDescriptor("String", TaraSyntaxHighlighter.STRING),
			new AttributesDescriptor("Documentation", TaraSyntaxHighlighter.DOCUMENTATION),
			new AttributesDescriptor("Primitive", TaraSyntaxHighlighter.PRIMITIVE),
			new AttributesDescriptor("Annotation", TaraSyntaxHighlighter.ANNOTATION),
			new AttributesDescriptor("Numbers", TaraSyntaxHighlighter.NUMBERS),
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
		return "'Documentation about the concept\n" +
			"Concept abstract Building as Entity <root>\n" +
			"\tString name: \"Rafael Cabrera 20\"\n" +
			"\n" +
			"Concept Radiator is Entity <has-code>\n" +
			"\tConcept as children\n" +
			"\tvar RadiatorReference\n" +
			"\n" +
			"\n" +
			"====B@d_Ch@r@cter====";
	}

	@Nullable
	@Override
	public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
		return null;
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