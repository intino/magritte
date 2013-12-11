package monet.tara.metamodel.highlighting;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import monet.tara.metamodel.TaraIcons;
import monet.tara.metamodel.TaraSyntaxHighlighter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class TaraColorSettingPage implements ColorSettingsPage { //TODO
	private static final AttributesDescriptor[] DESCRIPTORS =
	new AttributesDescriptor[]{
	                          new AttributesDescriptor("Keyword", TaraSyntaxHighlighter.KEYWORD),
	                          new AttributesDescriptor("Modifiers", TaraSyntaxHighlighter.MODIFIERS),
	                          new AttributesDescriptor("String", TaraSyntaxHighlighter.STRING),
	                          new AttributesDescriptor("Comment", TaraSyntaxHighlighter.COMMENT),
	                          new AttributesDescriptor("Primitive", TaraSyntaxHighlighter.PRIMITIVE),
	                          new AttributesDescriptor("Annotation", TaraSyntaxHighlighter.ANNOTATION),
	                          new AttributesDescriptor("Numbers", TaraSyntaxHighlighter.NUMBERS),
	                          new AttributesDescriptor("Bad Characters", TaraSyntaxHighlighter.BAD_CHARACTER)
	};

	@Nullable
	@Override
	public javax.swing.Icon getIcon() {
		return TaraIcons.FILE;
	}

	@NotNull
	@Override
	public com.intellij.openapi.fileTypes.SyntaxHighlighter getHighlighter() {
		return new TaraSyntaxHighlighter();
	}

	@NotNull
	@Override
	public String getDemoText() {
		return "#comentaries about it\n" +
		       "\n" +
		       "concept abstract Building is Entity @root\n" +
		       "\tstring children = \"asdasd\"\n" +
		       "\n" +
		       "concept Radiator is Entity @nameable\n" +
		       "\thas children\n" +
		       "\tref RadiatorReference\n" +
		       "\n" +
		       "\n" +
		       "{{{{{{{Bad_Character}}}}}}";
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