package siani.tara.intellij.highlighting;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import siani.tara.intellij.lang.TaraIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class TaraColorSettingPage implements ColorSettingsPage {
	private static final AttributesDescriptor[] DESCRIPTORS =
		new AttributesDescriptor[]{
			new AttributesDescriptor("Keyword", TaraSyntaxHighlighter.KEYWORD),
			new AttributesDescriptor("String", TaraSyntaxHighlighter.STRING),
			new AttributesDescriptor("Documentation", TaraSyntaxHighlighter.DOCUMENTATION),
			new AttributesDescriptor("Primitive", TaraSyntaxHighlighter.PRIMITIVE),
			new AttributesDescriptor("Annotations", TaraSyntaxHighlighter.ANNOTATION),
			new AttributesDescriptor("Numbers", TaraSyntaxHighlighter.NUMBERS),
			new AttributesDescriptor("Bad Characters", TaraSyntaxHighlighter.BAD_CHARACTER)
		};

	@Nullable
	@Override
	public javax.swing.Icon getIcon() {
		return TaraIcons.getIcon(TaraIcons.ICON_13);
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
			"Concept abstract as Source <has-code root> \n" +
				"\tConcept Ontology<optional>" + "\n" +
				"\t\tvar reference uid" +
				"\n" +
				"Concept Source as Thesaurus" + "\n" +
				"\tnew Term<multiple>" + "\n" +
				"\n" +
				"Concept as Operation" + "\n" +
				"\tvar String label" + "\n" +

				"\' Una entidad es un objeto en la unidad de negocio que representa un contenido" + "\n" +
				"Concept abstract as Entity<has-code>" + "\n" +

				"\t\' Incluir para ofrecer al usuario información más detallada de la entidad" + "\n" +
				"\tConcept as Description<optional>" + "\n" +
				"\t\tvar String description" + "\n" +
				"\n" +
				"\t\' Incluir para ofrecer al usuario una ayuda" + "\n" +
				"\t\' - **resource**. Nombre del fichero incluido en la distribución" + "\n" +
				"\tConcept as Help<optional>" + "\n" +
				"\t\tvar String resource" + "\n" +
				"\n" + "====B@d_Ch@r@cter====";
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