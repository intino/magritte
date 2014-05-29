package monet.::projectName::.intellij.documentation;

import com.intellij.lang.documentation.AbstractDocumentationProvider;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import monet.::projectName::.intellij.lang.::projectProperName::Language;
import monet.::projectName::.intellij.lang.psi.Definition;
import monet.::projectName::.intellij.lang.psi.MetaIdentifier;
import monet.::projectName::.intellij.lang.psi.::projectProperName::File;
import monet.::projectName::.intellij.lang.psi.impl.::projectProperName::PsiImplUtil;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class ::projectProperName::DocumentationProvider extends AbstractDocumentationProvider {

	\@NotNull
	private String renderDefinitionValue(Definition definition) {
		if (definition == null) return "<i>empty</i>";
		String raw = definition.getText();
		if (raw == null) return "<i>empty</i>";
		return StringUtil.escapeXml(raw);
	}

	\@Nullable
	public String getQuickNavigateInfo(PsiElement element, PsiElement originalElement) {
		return generateDoc(element, originalElement);
	}

	\@NonNls
	public String generateDoc(final PsiElement element, \@Nullable final PsiElement originalElement) {
		if (originalElement instanceof MetaIdentifier)
			return ::projectProperName::DocumentationFormatter.doc2Html(null, extractMetaDocumentation(originalElement.getText()));
		if (element instanceof Definition)
			return ((Definition) element).getDocCommentText();
		if (element instanceof ::projectProperName::File)
			return renderDefinitionValue(((::projectProperName::File) element).getDefinition());
		return renderDefinitionValue(::projectProperName::PsiImplUtil.getContextOf(element));
	}

	private String extractMetaDocumentation(String key) {
		return ::projectProperName::Language.getHeritage().getNodeNameLookUpTable().get(key).get(0).getDoc();
	}
}