package siani.tara.intellij.documentation;

import com.intellij.lang.documentation.AbstractDocumentationProvider;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.MetaIdentifier;
import siani.tara.intellij.lang.psi.TaraFile;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.lang.DeclaredNode;
import siani.tara.lang.Model;


public class TaraDocumentationProvider extends AbstractDocumentationProvider {

	@NotNull
	private String renderConceptValue(Concept concept) {
		if (concept == null) return "<i>empty</i>";
		String raw = concept.getText();
		if (raw == null) return "<i>empty</i>";
		return StringUtil.escapeXml(raw);
	}

	@Nullable
	public String getQuickNavigateInfo(PsiElement element, PsiElement originalElement) {
		return generateDoc(element, originalElement);
	}

	@NonNls
	public String generateDoc(final PsiElement element, @Nullable final PsiElement originalElement) {
		if (originalElement instanceof MetaIdentifier)
			return TaraDocumentationFormatter.doc2Html(null, extractMetaDocumentation(TaraPsiImplUtil.getContextOf(originalElement)));
		if (element instanceof Concept)
			return ((Concept) element).getDocCommentText();
		if (element instanceof TaraFile)
			return renderConceptValue(((TaraFile) element).getConcepts()[0]); //TODO
		return renderConceptValue(TaraPsiImplUtil.getContextOf(element));
	}

	private String extractMetaDocumentation(Concept element) {
		Model heritage = TaraLanguage.getHeritage(((TaraFile) element.getContainingFile()).getParentModel());
		if (heritage == null) return null;
		DeclaredNode node = heritage.searchNode(TaraUtil.getMetaQualifiedName(element));
		if (node == null) return null;
		return node.getObject().getDoc();
	}
}