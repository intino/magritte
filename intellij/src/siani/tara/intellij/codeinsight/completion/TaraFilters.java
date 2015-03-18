package siani.tara.intellij.codeinsight.completion;

import com.intellij.patterns.PsiElementPattern;
import com.intellij.psi.PsiElement;
import com.intellij.psi.TokenType;
import com.intellij.psi.filters.ElementFilter;
import com.intellij.psi.filters.position.FilterPattern;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.*;

import static com.intellij.patterns.PlatformPatterns.psiElement;
import static siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil.getContainerNodeOf;


public class TaraFilters {

	protected static final PsiElementPattern.Capture<PsiElement> AfterNewLine = psiElement().withLanguage(TaraLanguage.INSTANCE)
		.and(new FilterPattern(new AfterNewLinePrimalFilter()));
	protected static final PsiElementPattern.Capture<PsiElement> afterNewLineInBody = psiElement().withLanguage(TaraLanguage.INSTANCE)
		.and(new FilterPattern(new AfterNewLineInBodyFilter()));
	protected static final PsiElementPattern.Capture<PsiElement> inFacetBody = psiElement().withLanguage(TaraLanguage.INSTANCE)
		.and(new FilterPattern(new AfterNewLineInBodyFilter())).and(new FilterPattern(new InFacetFilter()));
	protected static final PsiElementPattern.Capture<PsiElement> afterEquals = psiElement().withLanguage(TaraLanguage.INSTANCE)
		.and(new FilterPattern(new AfterEqualsFilter()));
	protected static final PsiElementPattern.Capture<PsiElement> afterNodeIdentifier = psiElement()
		.withLanguage(TaraLanguage.INSTANCE)
		.and(new FilterPattern(new AfterElementTypeFitFilter(TaraTypes.IDENTIFIER_KEY)));

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
			return !isNotAcceptable(element, context) && inBody(context) && afterNewLine(context);
		}

		private boolean afterNewLine(PsiElement context) {
			return context.getPrevSibling() == null || previousNewLineIndent(context) || previousNewLine(context);
		}

		private boolean inBody(PsiElement context) {
			return context.getParent() instanceof MetaIdentifier && TaraFilters.inBody(context) && !inAnnotations(context);
		}

		private boolean isNotAcceptable(Object element, PsiElement context) {
			return !(element instanceof PsiElement) || context == null || context.getParent() == null;
		}

		@Override
		public boolean isClassAcceptable(Class hintClass) {
			return true;
		}
	}

	private static boolean previousNewLine(PsiElement context) {
		return context.getPrevSibling() != null && (is(context, TaraTypes.NEWLINE) || is(context, TaraTypes.IMPORTS));
	}

	private static boolean previousNewLineIndent(PsiElement context) {
		return context.getPrevSibling() != null && is(context, TaraTypes.NEW_LINE_INDENT);
	}

	private static boolean is(PsiElement context, IElementType type) {
		return type.equals(context.getPrevSibling().getNode().getElementType());
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
				Node contextOf = getContainerNodeOf(context);
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
				Node node = getContainerNodeOf(getContainerNodeOf(context));
				if (node == null) return false;
//				Node node = TaraUtil.findNode(concept, TaraLanguage.getLanguage(concept.getFile())); TODO
//				if (isIntention(node)) return true;
			}
			return false;
		}

		private boolean facetInBody(PsiElement context) {
			return context.getParent() instanceof MetaIdentifier && inBody(context) && !inAnnotations(context);
		}

//		private boolean isIntention(Node node) {
//			return node != null && node.getObject().is(Annotation.INTENTION);
//		}

		@Override
		public boolean isClassAcceptable(Class hintClass) {
			return true;
		}
	}
}
