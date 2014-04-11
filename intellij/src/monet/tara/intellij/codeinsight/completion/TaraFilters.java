package monet.tara.intellij.codeinsight.completion;

import com.intellij.patterns.PsiElementPattern;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiErrorElement;
import com.intellij.psi.TokenType;
import com.intellij.psi.filters.ElementFilter;
import com.intellij.psi.filters.position.FilterPattern;
import com.intellij.psi.tree.IElementType;
import monet.tara.intellij.metamodel.TaraLanguage;
import monet.tara.intellij.metamodel.psi.TaraTypes;
import org.jetbrains.annotations.Nullable;

import static com.intellij.patterns.PlatformPatterns.psiElement;

public class TaraFilters {

	protected static PsiElementPattern.Capture<PsiElement> afterNewLine = psiElement().withLanguage(TaraLanguage.INSTANCE)
		.and(new FilterPattern(new InErrorFilter()));
	protected static PsiElementPattern.Capture<PsiElement> afterConceptKey = psiElement()
		.withLanguage(TaraLanguage.INSTANCE)
		.and(new FilterPattern(new InSignatureFitFilter()))
		.and(new FilterPattern(new AfterElementFitFilter(TaraTypes.CONCEPT_KEY)));
	protected static PsiElementPattern.Capture<PsiElement> inheritance = psiElement()
		.withLanguage(TaraLanguage.INSTANCE)
		.and(new FilterPattern(new InSignatureFitFilter()))
		.andOr(new FilterPattern(new AfterElementFitFilter(TaraTypes.COLON)), new FilterPattern(new AfterElementFitFilter(TaraTypes.DOT)));
	protected static PsiElementPattern.Capture<PsiElement> afterPolymorphicOrMorphKey = psiElement()
		.withLanguage(TaraLanguage.INSTANCE)
		.and(new FilterPattern(new InSignatureFitFilter()))
		.andOr(new FilterPattern(new AfterElementFitFilter(TaraTypes.POLYMORPHIC_KEY)),
			new FilterPattern(new AfterElementFitFilter(TaraTypes.MORPH_KEY)));
	protected static PsiElementPattern.Capture<PsiElement> afterModifierKey = psiElement()
		.withLanguage(TaraLanguage.INSTANCE)
		.and(new FilterPattern(new InSignatureFitFilter()))
		.andOr(new FilterPattern(new AfterElementFitFilter(TaraTypes.FINAL)),
			new FilterPattern(new AfterElementFitFilter(TaraTypes.ABSTRACT)));


	private TaraFilters() {
	}

	private static class AfterElementFitFilter implements ElementFilter {
		IElementType type;

		private AfterElementFitFilter(IElementType type) {
			this.type = type;
		}

		public boolean isAcceptable(Object element, PsiElement context) {
			PsiElement prevSibling = context.getPrevSibling();
			PsiElement prevPrevSibling = prevSibling.getPrevSibling();
			if (element instanceof PsiElement && prevSibling != null && prevPrevSibling != null) {
				if (prevSibling.getNode().getElementType() == TokenType.WHITE_SPACE && type.equals(prevPrevSibling.getNode().getElementType()))
					return true;
				else if (type.equals(prevSibling.getNode().getElementType())) return true;
			}
			return false;
		}

		public boolean isClassAcceptable(Class hintClass) {
			return true;
		}
	}

	private static class InErrorFilter implements ElementFilter {
		@Override
		public boolean isAcceptable(Object element, @Nullable PsiElement context) {
			if (element instanceof PsiElement) {
				assert context != null;
				if (((PsiElement) element).getParent() instanceof PsiErrorElement) return true;
			}
			return false;
		}

		@Override
		public boolean isClassAcceptable(Class hintClass) {
			return true;
		}
	}

}
