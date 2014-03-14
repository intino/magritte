package monet.::projectName::.intellij;

import com.intellij.openapi.util.TextRange;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import monet.::projectName::.intellij.metamodel.psi.::projectProperName::Types;
import org.jetbrains.annotations.NotNull;

public class ::projectProperName::ReferenceContributor extends PsiReferenceContributor {
	\@Override
	public void registerReferenceProviders(PsiReferenceRegistrar registrar) {
		registrar.registerReferenceProvider(PlatformPatterns.psiElement(::projectProperName::Types.IDENTIFIER), new PsiReferenceProvider() {
			\@NotNull
			\@Override
			public PsiReference[] getReferencesByElement(\@NotNull PsiElement element, \@NotNull com.intellij.util.ProcessingContext context) {
				PsiLiteralExpression literalExpression = (PsiLiteralExpression) element;
				String text = (String) literalExpression.getValue();
				if (text != null && text.startsWith("::projectProperName:::"))
					return new PsiReference[]{ new ::projectProperName::Reference(element, new TextRange(6, text.length() + 1)) };
				return new PsiReference[0];
			}
		});
	}
}