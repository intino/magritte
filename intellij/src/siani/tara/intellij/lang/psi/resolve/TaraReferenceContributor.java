package siani.tara.intellij.lang.psi.resolve;

import com.intellij.openapi.util.TextRange;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

public class TaraReferenceContributor extends PsiReferenceContributor {
	@Override
	public void registerReferenceProviders(@NotNull PsiReferenceRegistrar registrar) {
		registrar.registerReferenceProvider(PlatformPatterns.psiElement(PsiLiteralExpression.class), new PsiReferenceProvider() {
			@NotNull
			@Override
			public PsiReference[] getReferencesByElement(@NotNull PsiElement element, @NotNull ProcessingContext context) {
				PsiLiteralExpression literalExpression = (PsiLiteralExpression) element;
				String text = (String) literalExpression.getValue();
				if (text != null && text.startsWith("Tara:"))
					return new PsiReference[]{new TaraInternalReferenceSolver(element, new TextRange(6, text.length() + 1), null, null)};
				return new PsiReference[0];
			}
		});

	}
}