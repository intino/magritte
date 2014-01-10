package monet.tara.intellij;

import com.intellij.openapi.util.TextRange;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import monet.tara.intellij.psi.TaraTypes;
import org.jetbrains.annotations.NotNull;

public class TaraReferenceContributor extends PsiReferenceContributor {
	@Override
	public void registerReferenceProviders(PsiReferenceRegistrar registrar) {
		registrar.registerReferenceProvider(PlatformPatterns.psiElement(TaraTypes.IDENTIFIER), new PsiReferenceProvider() {
			@NotNull
			@Override
			public PsiReference[] getReferencesByElement(@NotNull PsiElement element, @NotNull com.intellij.util.ProcessingContext context) {
				PsiLiteralExpression literalExpression = (PsiLiteralExpression) element;
				String text = (String) literalExpression.getValue();
				if (text != null && text.startsWith("Tara:"))
					return new PsiReference[]{ new TaraReference(element, new TextRange(6, text.length() + 1)) };
				return new PsiReference[0];
			}
		});
	}
}