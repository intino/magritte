package monet.tara.intellij.documentation;

import com.intellij.lang.documentation.AbstractDocumentationProvider;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.ui.GuiUtils;
import monet.tara.intellij.highlighting.TaraSyntaxHighlighter;
import monet.tara.intellij.psi.IConcept;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;


public class TaraDocumentationProvider extends AbstractDocumentationProvider {
	private static String getLocationString(PsiElement element) {
		PsiFile file = element.getContainingFile();
		return file != null ? " [" + file.getName() + "]" : "";
	}

	@NotNull
	private static String renderConceptValue(IConcept concept) {
		String raw = concept.getText();
		if (raw == null) {
			return "<i>empty</i>";
		}
		return StringUtil.escapeXml(raw);
	}

	@Nullable
	public String getQuickNavigateInfo(PsiElement element, PsiElement originalElement) {
		if (element instanceof IConcept) {
			return generateDoc(element, originalElement);
		}
		return null;
	}

	@NonNls
	public String generateDoc(final PsiElement element, @Nullable final PsiElement originalElement) {
		if (element instanceof IConcept) {
			IConcept concept = (IConcept) element;
			String text = concept.getDocCommentText();
			assert text != null;
			return markDown2Html(element, text);
		}
		return renderConceptValue((IConcept) element);
	}

	private String markDown2Html(PsiElement element, String text) { //TODO
		text = text.replaceAll("\\?", "").replaceAll("\\*", "");
		@NonNls String info = "";
		if (text != null) {
			TextAttributes attributes = EditorColorsManager.getInstance().getGlobalScheme().getAttributes(TaraSyntaxHighlighter.DOCUMENTATION).clone();
			Color background = attributes.getBackgroundColor();
			if (background != null)
				info += "<div bgcolor=#" + GuiUtils.colorToHex(background) + ">";
			String doc = StringUtil.join(StringUtil.split(text, "\n"), "<br>");
			info += "<font color=#" + GuiUtils.colorToHex(attributes.getForegroundColor()) + ">" + doc + "</font>\n<br>";
			if (background != null)
				info += "</div>";
		}
		info += "<b>" + getLocationString(element) + "</b>";
		return info;
	}
}