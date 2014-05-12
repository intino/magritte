package monet.::projectName::.intellij;

import com.intellij.openapi.util.TextRange;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

public class ::projectProperName::ReferenceContributor extends PsiReferenceContributor {
	\@Override
	public void registerReferenceProviders(PsiReferenceRegistrar registrar) {
		registrar.registerReferenceProvider(PlatformPatterns.psiElement(PsiLiteralExpression.class), new PsiReferenceProvider() {
			\@NotNull
			\@Override
			public PsiReference[] getReferencesByElement(\@NotNull PsiElement element, \@NotNull ProcessingContext context) {
				PsiLiteralExpression literalExpression = (PsiLiteralExpression) element;
				String text = (String) literalExpression.getValue();
				if (text != null && text.startsWith("::projectProperName::\:"))
					return new PsiReference[]{ new ::projectProperName::ReferenceSolver(element, new TextRange(6, text.length() + 1)) };
				return new PsiReference[0];
			}
		});

	}
}