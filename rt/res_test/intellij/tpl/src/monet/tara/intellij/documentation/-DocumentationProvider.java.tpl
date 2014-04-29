package monet.::projectName::.intellij.documentation;

import com.intellij.lang.documentation.AbstractDocumentationProvider;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import monet.::projectName::.intellij.metamodel.psi.Definition;
import monet.::projectName::.intellij.metamodel.psi.::projectProperName::File;
import monet.::projectName::.intellij.metamodel.psi.impl.::projectProperName::PsiImplUtil;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class ::projectProperName::DocumentationProvider extends AbstractDocumentationProvider {


	\@NotNull
	private String renderDefinitionValue(Definition definition) {
		String raw = definition.getText();
		if (raw == null) return "<i>empty</i>";
		return StringUtil.escapeXml(raw);
	}

	\@Nullable
	public String getQuickNavigateInfo(PsiElement element, PsiElement originalElement) {
		if (element instanceof Definition)
			return generateDoc(element, originalElement);
		return null;
	}

	\@NonNls
	public String generateDoc(final PsiElement element, \@Nullable final PsiElement originalElement) {
		if (element instanceof Definition)
			return ((Definition) element).getDocCommentText();
		if (element instanceof ::projectProperName::File)
			return renderDefinitionValue(((::projectProperName::File) element).getDefinition());
		return renderDefinitionValue(::projectProperName::PsiImplUtil.getContextOf(element));
	}
}