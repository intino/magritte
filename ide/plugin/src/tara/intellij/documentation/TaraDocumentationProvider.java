package tara.intellij.documentation;

import com.intellij.lang.documentation.AbstractDocumentationProvider;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;
import tara.Language;
import tara.dsl.Proteo;
import tara.intellij.lang.TaraLanguage;
import tara.intellij.lang.psi.MetaIdentifier;
import tara.intellij.lang.psi.TaraNode;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.language.model.Node;
import tara.language.semantics.Documentation;


public class TaraDocumentationProvider extends AbstractDocumentationProvider {

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
		if (element instanceof Node) return ((Node) element).doc();
		return "";
	}

	private String findDoc(Node node) {
		final Language language = TaraLanguage.getLanguage(((TaraNode) node).getContainingFile());
		if (language == null || language instanceof Proteo) return "";
		final Documentation doc = language.doc(node.type());
		return doc != null ? doc.description() : "";
	}
}