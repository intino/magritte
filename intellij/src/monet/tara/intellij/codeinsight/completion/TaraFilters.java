package monet.tara.intellij.codeinsight.completion;

import com.intellij.patterns.PsiElementPattern;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiErrorElement;
import com.intellij.psi.TokenType;
import com.intellij.psi.filters.ElementFilter;
import com.intellij.psi.filters.position.FilterPattern;
import com.intellij.psi.tree.IElementType;
import monet.tara.intellij.lang.TaraLanguage;
import monet.tara.intellij.lang.psi.TaraTypes;
import org.jetbrains.annotations.Nullable;

import static com.intellij.patterns.PlatformPatterns.psiElement;

public class TaraFilters {

	//%extension%
	protected static PsiElementPattern.Capture<PsiElement> afterNewLine = psiElement().withLanguage(TaraLanguage.INSTANCE)
		.and(new FilterPattern(new InErrorFilter()));
	protected static PsiElementPattern.Capture<PsiElement> afterConceptKey = psiElement()
		.withLanguage(TaraLanguage.INSTANCE)
		.and(new FilterPattern(new InSignatureFitFilter()))
		//gen %empty%.andOr(new FilterPattern(new AfterElementFitFilter(monet.\:\:projectName\:\:.intellij.lang.psi.MetaIdentifier.class)), new FilterPattern(new AfterElementTypeFitFilter(\:\:projectProperName\:\:Types.CASE_KEY)));%
		.and(new FilterPattern(new AfterElementTypeFitFilter(TaraTypes.CONCEPT_KEY)));
		//end
	protected static PsiElementPattern.Capture<PsiElement> afterModifierKey = psiElement()
		.withLanguage(TaraLanguage.INSTANCE)
		.and(new FilterPattern(new InSignatureFitFilter()))
		.and(new FilterPattern(new AfterElementTypeFitFilter(TaraTypes.MODIFIER)));

	private TaraFilters() {
	}

	private static class AfterElementTypeFitFilter implements ElementFilter {
		IElementType type;

		private AfterElementTypeFitFilter(IElementType type) {
			this.type = type;
		}

		public boolean isAcceptable(Object element, PsiElement context) {
			PsiElement prevSibling = context.getParent().getPrevSibling();
			if (prevSibling != null && prevSibling.getPrevSibling() != null) {
				PsiElement prevPrevSibling = prevSibling.getPrevSibling();
				if (element instanceof PsiElement) {
					if (prevSibling.getNode().getElementType() == TokenType.WHITE_SPACE && type.equals(prevPrevSibling.getNode().getElementType()))
						return true;
					else if (type.equals(prevSibling.getNode().getElementType())) return true;
				}
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
	//end_extension
}
