package monet.::projectName::.intellij.highlighting;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import monet.::projectName::.intellij.lang.::projectProperName::Icons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class ::projectProperName::ColorSettingPage implements ColorSettingsPage {
	private static final AttributesDescriptor[] DESCRIPTORS =
		new AttributesDescriptor[]{
			new AttributesDescriptor("Keyword", ::projectProperName::SyntaxHighlighter.KEYWORD),
			new AttributesDescriptor("Modifiers", ::projectProperName::SyntaxHighlighter.MODIFIERS),
			new AttributesDescriptor("String", ::projectProperName::SyntaxHighlighter.STRING),
			new AttributesDescriptor("Documentation", ::projectProperName::SyntaxHighlighter.DOCUMENTATION),
			new AttributesDescriptor("Primitive", ::projectProperName::SyntaxHighlighter.PRIMITIVE),
			new AttributesDescriptor("Annotations", ::projectProperName::SyntaxHighlighter.ANNOTATION),
			new AttributesDescriptor("Numbers", ::projectProperName::SyntaxHighlighter.NUMBERS),
			new AttributesDescriptor("Bad Characters", ::projectProperName::SyntaxHighlighter.BAD_CHARACTER)
		};

	\@Nullable
	\@Override
	public javax.swing.Icon getIcon() {
		return ::projectProperName::Icons.getIcon(::projectProperName::Icons.ICON_13);
	}

	\@NotNull
	\@Override
	public com.intellij.openapi.fileTypes.SyntaxHighlighter getHighlighter() {
		return new ::projectProperName::SyntaxHighlighter();
	}

	\@NotNull
	\@Override
	public String getDemoText() {
		return
			"Definition abstract as Source <has-code root> \\n" +
				"\\tDefinition as Ontology<optional>" + "\\n" +
				"\\t\\tvar Alias uid" +
				"\\n" +
				"Definition Source as Thesaurus" + "\\n" +
				"\\tnew Term<multiple>" + "\\n" +
				"\\n" +
				"Definition as Operation" + "\\n" +
				"\\tvar String label" + "\\n" +

				"\\' Una entidad es un objeto en la unidad de negocio que representa un contenido" + "\\n" +
				"Definition abstract as Entity<has-code>" + "\\n" +

				"\\t\\' Incluir para ofrecer al usuario información más detallada de la entidad" + "\\n" +
				"\\tDefinition as Description<optional>" + "\\n" +
				"\\t\\tvar String description" + "\\n" +
				"\\n" +
				"\\t\\' Incluir para ofrecer al usuario una ayuda" + "\\n" +
				"\\t\\' - **resource**. Nombre del fichero incluido en la distribución" + "\\n" +
				"\\tDefinition as Help<optional>" + "\\n" +
				"\\t\\tvar String resource" + "\\n" +
				"\\n" + "====B\@d_Ch\@r\@cter====";
	}

	\@Nullable
	\@Override
	public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
		return null;
	}

	\@NotNull
	\@Override
	public AttributesDescriptor[] getAttributeDescriptors() {
		return DESCRIPTORS;
	}

	\@NotNull
	\@Override
	public ColorDescriptor[] getColorDescriptors() {
		return ColorDescriptor.EMPTY_ARRAY;
	}

	\@NotNull
	\@Override
	public String getDisplayName() {
		return "::projectProperName::";
	}
}