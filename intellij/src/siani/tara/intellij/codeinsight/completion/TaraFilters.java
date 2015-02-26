package siani.tara.intellij.codeinsight.completion;

import com.intellij.patterns.ElementPattern;
import com.intellij.patterns.PsiElementPattern;
import com.intellij.psi.PsiElement;
import com.intellij.psi.TokenType;
import com.intellij.psi.filters.ElementFilter;
import com.intellij.psi.filters.position.FilterPattern;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.*;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.lang.Annotation;
import siani.tara.lang.Node;

import static com.intellij.patterns.PlatformPatterns.psiElement;
import static siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil.getConceptContainerOf;


public class TaraFilters {

	protected static final PsiElementPattern.Capture<PsiElement> afterNewLineInBody = psiElement().withLanguage(TaraLanguage.INSTANCE)
		.and(new FilterPattern(new AfterNewLineInBodyFilter()));
	protected static final PsiElementPattern.Capture<PsiElement> inFacetBody = psiElement().withLanguage(TaraLanguage.INSTANCE)
		.and(new FilterPattern(new AfterNewLineInBodyFilter())).and(new FilterPattern(new InFacetFilter()));
	protected static final PsiElementPattern.Capture<PsiElement> afterEquals = psiElement().withLanguage(TaraLanguage.INSTANCE)
		.and(new FilterPattern(new AfterEqualsFilter()));
	protected static final PsiElementPattern.Capture<PsiElement> AfterNewLineNoMetamodel = psiElement().withLanguage(TaraLanguage.INSTANCE)
		.and(new FilterPattern(new AfterNewLinePrimalFilter())).and(new FilterPattern(new NoModelFilter()));
	protected static final PsiElementPattern.Capture<PsiElement> AfterNewLineWithMetamodel = psiElement().withLanguage(TaraLanguage.INSTANCE)
		.and(new FilterPattern(new AfterNewLinePrimalFilter())).and(new FilterPattern(new ModelFilter()));
	protected static final PsiElementPattern.Capture<PsiElement> afterIdentifier = psiElement()
		.withLanguage(TaraLanguage.INSTANCE)
		.and(new FilterPattern(new AfterElementTypeFitFilter(TaraTypes.IDENTIFIER_KEY)));
	protected static final ElementPattern<? extends PsiElement> afterSignature = psiElement()
		.withLanguage(TaraLanguage.INSTANCE)
		.and(new FilterPattern(new AfterElementTypeFitFilter(TaraTypes.SIGNATURE)));

	private TaraFilters() {
	}

	private static boolean inBody(PsiElement context) {
		PsiElement parent = context.getParent();
		while (parent != null) {
			if (parent instanceof Body) return true;
			parent = parent.getParent();
		}
		return false;
	}

	private static boolean inAnnotations(PsiElement context) {
		PsiElement parent = context.getParent();
		while (parent != null) {
			if (parent instanceof TaraAnnotations) return true;
			parent = parent.getParent();
		}
		return false;
	}

	private static class AfterElementTypeFitFilter implements ElementFilter {
		IElementType type;

		private AfterElementTypeFitFilter(IElementType type) {
			this.type = type;
		}

		public boolean isAcceptable(Object element, PsiElement context) {
			PsiElement prevSibling = context.getPrevSibling() != null ? context.getPrevSibling() : context.getParent().getPrevSibling();
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

	private static class AfterNewLineInBodyFilter implements ElementFilter {
		@Override
		public boolean isAcceptable(Object element, @Nullable PsiElement context) {
			if (!(element instanceof PsiElement) || context == null || context.getParent() == null) return false;
			return context.getParent() instanceof MetaIdentifier && inBody(context) && !inAnnotations(context) &&
				context.getPrevSibling() == null || previousNewLineIndent(context) || previousNewLine(context);
		}

		@Override
		public boolean isClassAcceptable(Class hintClass) {
			return true;
		}
	}

	private static boolean previousNewLine(PsiElement context) {
		return context.getPrevSibling().getNode().getElementType().equals(TaraTypes.NEWLINE);
	}

	private static boolean previousNewLineIndent(PsiElement context) {
		return context.getPrevSibling().getNode().getElementType().equals(TaraTypes.NEW_LINE_INDENT);
	}

	private static class NoModelFilter implements ElementFilter {
		@Override
		public boolean isAcceptable(Object element, @Nullable PsiElement context) {
			TaraBoxFile file = (TaraBoxFile) (context != null ? context.getContainingFile() : null);
			return file != null && file.getDSL() == null;
		}

		@Override
		public boolean isClassAcceptable(Class hintClass) {
			return true;
		}
	}

	private static class ModelFilter implements ElementFilter {
		@Override
		public boolean isAcceptable(Object element, @Nullable PsiElement context) {
			TaraBoxFile file = (TaraBoxFile) (context != null ? context.getContainingFile() : null);
			return file != null && file.getDSL() != null;
		}

		@Override
		public boolean isClassAcceptable(Class hintClass) {
			return true;
		}
	}

	private static class AfterEqualsFilter implements ElementFilter {
		@Override
		public boolean isAcceptable(Object element, @Nullable PsiElement context) {
			return element instanceof PsiElement && context != null && context.getPrevSibling() != null &&
				context.getPrevSibling().getPrevSibling() != null && isPreviousEquals(context);
		}

		private boolean isPreviousEquals(PsiElement context) {
			return TaraTypes.EQUALS.equals(context.getPrevSibling().getPrevSibling().getNode().getElementType());
		}

		@Override
		public boolean isClassAcceptable(Class hintClass) {
			return true;
		}
	}

	private static class AfterNewLinePrimalFilter implements ElementFilter {
		@Override
		public boolean isAcceptable(Object element, @Nullable PsiElement context) {
			if (!(element instanceof PsiElement) || context == null || context.getParent() == null) return false;
			if (context.getParent() instanceof MetaIdentifier && !inBody(context) && !inAnnotations(context)) {
				Concept contextOf = getConceptContainerOf(context);
				if (contextOf == null || contextOf.getPrevSibling() == null) return false;
				if (previousNewLine(contextOf) || previousNewLineIndent(contextOf))
					return true;
			}
			return false;
		}

		@Override
		public boolean isClassAcceptable(Class hintClass) {
			return true;
		}
	}

	private static class InFacetFilter implements ElementFilter {
		@Override
		public boolean isAcceptable(Object element, @Nullable PsiElement context) {
			if (!(element instanceof PsiElement) || context == null || context.getParent() == null) return false;
			if (facetInBody(context)) {
				Concept concept = getConceptContainerOf(getConceptContainerOf(context));
				if (concept == null) return false;
				Node node = TaraUtil.findNode(concept, TaraLanguage.getMetaModel(concept.getFile()));
				if (isIntention(node)) return true;
			}
			return false;
		}

		private boolean facetInBody(PsiElement context) {
			return context.getParent() instanceof MetaIdentifier && inBody(context) && !inAnnotations(context);
		}

		private boolean isIntention(Node node) {
			return node != null && node.getObject().is(Annotation.INTENTION);
		}

		@Override
		public boolean isClassAcceptable(Class hintClass) {
			return true;
		}
	}
}
