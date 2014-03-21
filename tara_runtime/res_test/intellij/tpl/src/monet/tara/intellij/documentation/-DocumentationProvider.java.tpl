package monet.::projectName::.intellij.documentation;

import com.intellij.lang.documentation.AbstractDocumentationProvider;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.ui.GuiUtils;
import monet.::projectName::.intellij.highlighting.::projectProperName::SyntaxHighlighter;
import monet.::projectName::.intellij.metamodel.psi.Definition;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.markdown4j.Markdown4jProcessor;

import java.awt.*;
import java.io.IOException;


public class ::projectProperName::DocumentationProvider extends AbstractDocumentationProvider {
	private String getLocationString(PsiElement element) {
		PsiFile file = element.getContainingFile();
		return file != null ? " [" + file.getName().split("\\\\.")[0] + "]" \: "";
	}

	\@NotNull
	private String renderDefinitionValue(Definition definition) {
		String raw = definition.getText();
		if (raw == null) {
			return "<i>empty</i>";
		}
		return StringUtil.escapeXml(raw);
	}

	\@Nullable
	public String getQuickNavigateInfo(PsiElement element, PsiElement originalElement) {
		if (element instanceof Definition) {
			return generateDoc(element, originalElement);
		}
		return null;
	}

	\@NonNls
	public String generateDoc(final PsiElement element, \@Nullable final PsiElement originalElement) {
		if (element instanceof Definition)
			return doc2Html(element, ((Definition) element).getDocCommentText());
		return renderDefinitionValue((Definition) element);
	}

	private String doc2Html(PsiElement element, String text) {
		String html = markdownToHtml(text);
		\@NonNls String info = "";
		TextAttributes attributes = EditorColorsManager.getInstance().getGlobalScheme().getAttributes(::projectProperName::SyntaxHighlighter.DOCUMENTATION).clone();
		Color background = attributes.getBackgroundColor();
		if (background != null) info += "<div bgcolor=\#" + GuiUtils.colorToHex(background) + ">";
		info += "<font color=\#" + GuiUtils.colorToHex(attributes.getForegroundColor()) + ">" + html + "</font>";
		if (background != null) info += "</div>";
		info += "<b>" + getLocationString(element) + "</b>";
		return info;
	}

	private String markdownToHtml(String text) {
		String html = "";
		try {
			html = new Markdown4jProcessor().process(text.replace("'", ""));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return html;
	}
}