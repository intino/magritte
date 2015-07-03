package siani.tara.intellij.documentation;

import com.intellij.lang.documentation.AbstractDocumentationProvider;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.Language;
import siani.tara.dsl.Proteo;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.MetaIdentifier;
import siani.tara.intellij.lang.psi.Node;
import siani.tara.intellij.lang.psi.TaraModel;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.semantic.model.Documentation;


public class TaraDocumentationProvider extends AbstractDocumentationProvider {

	@NotNull
	private String renderConceptValue(Node node) {
		if (node == null) return "<i>empty</i>";
		String raw = node.getText();
		if (raw == null) return "<i>empty</i>";
		return StringUtil.escapeXml(raw);
	}

	@Nullable
	public String getQuickNavigateInfo(PsiElement element, PsiElement originalElement) {
		return generateDoc(element, originalElement);
	}

	@NonNls
	public String generateDoc(final PsiElement element, @Nullable final PsiElement originalElement) {
		if (originalElement instanceof MetaIdentifier) {
			String doc = findDoc(TaraPsiImplUtil.getContainerNodeOf(originalElement));
			return TaraDocumentationFormatter.doc2Html(null, doc);
		}
		if (element instanceof Node)
			return ((Node) element).getDocCommentText();
		if (element instanceof TaraModel)
			return "";
		return renderConceptValue(TaraPsiImplUtil.getContainerNodeOf(element));
	}

	private String findDoc(Node node) {
		final Language language = TaraLanguage.getLanguage(node.getContainingFile());
		if (language == null || language instanceof Proteo) return "";
		final Documentation doc = language.doc(node.getType());
		return doc != null ? doc.description() : "";
	}
}