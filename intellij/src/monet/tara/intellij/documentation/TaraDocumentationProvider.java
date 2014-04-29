package monet.tara.intellij.documentation;

import com.intellij.lang.documentation.AbstractDocumentationProvider;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import monet.tara.intellij.metamodel.psi.Concept;
import monet.tara.intellij.metamodel.psi.TaraFile;
import monet.tara.intellij.metamodel.psi.impl.TaraPsiImplUtil;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


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
		if (element instanceof Concept)
			return generateDoc(element, originalElement);
		return null;
	}

	@NonNls
	public String generateDoc(final PsiElement element, @Nullable final PsiElement originalElement) {
		if (element instanceof Concept)
			return ((Concept) element).getDocCommentText();
		if (element instanceof TaraFile)
			return renderConceptValue(((TaraFile) element).getConcept());
		return renderConceptValue(TaraPsiImplUtil.getContextOf(element));
	}
}