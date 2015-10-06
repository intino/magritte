package tara.intellij.documentation;

import com.intellij.lang.documentation.AbstractDocumentationProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;
import tara.Language;
import tara.dsl.Proteo;
import tara.intellij.codeinsight.completion.CompletionUtils;
import tara.intellij.lang.TaraLanguage;
import tara.intellij.lang.psi.MetaIdentifier;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.language.model.Node;
import tara.language.semantics.Documentation;


public class TaraDocumentationProvider extends AbstractDocumentationProvider {

	@Nullable
	public String getQuickNavigateInfo(PsiElement element, PsiElement originalElement) {
		return generateDoc(element, originalElement);
	}

	@Override
	public PsiElement getDocumentationElementForLookupItem(PsiManager psiManager, Object object, PsiElement element) {
		return object instanceof PsiElement ? (PsiElement) object : null;
	}

	@NonNls
	public String generateDoc(final PsiElement element, @Nullable final PsiElement originalElement) {
		if (originalElement instanceof MetaIdentifier)
			return TaraDocumentationFormatter.doc2Html(null, findDoc(TaraPsiImplUtil.getContainerNodeOf(originalElement)));
		if (element instanceof Node) return ((Node) element).doc();
		if (element instanceof CompletionUtils.FakeElement)
			return findDoc(((CompletionUtils.FakeElement) element).getType(), originalElement);
		return "";
	}

	private String findDoc(Node node) {
		return findDoc(node.type(), (PsiElement) node);
	}

	private String findDoc(String type, PsiElement anElement) {
		final Language language = TaraLanguage.getLanguage(anElement.getContainingFile());
		if (language == null || language instanceof Proteo) return "";
		final Documentation doc = language.doc(type);
		return doc != null ? doc.description() : "";
	}
}