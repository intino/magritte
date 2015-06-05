package siani.tara.intellij.documentation;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.ui.GuiUtils;
import org.jetbrains.annotations.NonNls;
import org.markdown4j.Markdown4jProcessor;
import siani.tara.intellij.highlighting.TaraSyntaxHighlighter;

import java.awt.*;
import java.io.IOException;

public class TaraDocumentationFormatter {

	private static final Logger LOG = Logger.getInstance(TaraDocumentationFormatter.class.getName());

	private TaraDocumentationFormatter() {
	}

	private static String getLocationString(PsiElement element) {
		PsiFile file = element.getContainingFile();
		return file != null ? " [" + file.getName().split("\\.")[0] + "]" : "";
	}


	public static String doc2Html(PsiElement element, String text) {
		String html = markdownToHtml(text);
		@NonNls String info = "";
		TextAttributes attributes = EditorColorsManager.getInstance().getGlobalScheme().getAttributes(TaraSyntaxHighlighter.DOCUMENTATION).clone();
		Color background = attributes.getBackgroundColor();
		if (background != null) info += "<div bgcolor=#" + GuiUtils.colorToHex(background) + ">";
		info += "<font color=#" + GuiUtils.colorToHex(attributes.getForegroundColor()) + ">" + html + "</font>";
		if (background != null) info += "</div>";
		if (element != null)
			info += "<b>" + getLocationString(element) + "</b>";
		return info;
	}

	private static String markdownToHtml(String text) {
		String html = "";
		try {
			if (text == null) return "";
			String cleanText = text.replaceAll("'", "").replaceAll(" \\s+", " ").trim();
			html = new Markdown4jProcessor().process(cleanText);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
		return html;
	}
}
