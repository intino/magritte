package monet.tara.intellij.codeInsight.completion;

import com.intellij.psi.PsiElement;
import com.intellij.psi.filters.ElementFilter;

public class StatementFitFilter implements ElementFilter {


	public boolean isAcceptable(Object element, PsiElement context) {
		if (element instanceof PsiElement) {
//			final ASTNode ctxNode = context.getNode();
//			if (ctxNode != null && TaraTypes.STRING_NODES.contains(ctxNode.getElementType()))
//				return false; // no sense inside string
//			PsiElement p = (PsiElement) element;
//			int firstOffset = p.getTextRange().getStartOffset();
//			// we must be a stmt ourselves, not a part of another stmt
//			// try to climb to the stmt level with the same offset
//			while (true) {
//				if (p == null) return false;
//				if (p.getTextRange().getStartOffset() != firstOffset) return false;
//				if (p instanceof PyStatement) break;
//				p = p.getParent();
//			}
//			// so, a stmt begins with us
//			// isn't there an incorrect stmt before us on the same line?
//			PsiElement container = p.getParent();
//			if (!(container instanceof PyElement)) return true;
//			if (container instanceof PyStatementList || container instanceof PsiFile) {
//				PsiElement prev = p.getPrevSibling();
//				while (prev instanceof PsiWhiteSpace) prev = prev.getPrevSibling();
//				if (prev == null) return true; // there was only whitespace before us
//				if (prev instanceof PyStatement || prev instanceof PsiComment) { // a non-stmt would be something strange
//					if (prev.getLastChild() instanceof PsiErrorElement) {
//						// prev stmt ends with an error. are we on the same line?
//						PsiDocumentManager docMgr = PsiDocumentManager.getInstance(p.getProject());
//						Document doc = docMgr.getDocument(p.getContainingFile().getOriginalFile());
//						if (doc != null) {
//							if (doc.getLineNumber(prev.getTextRange().getEndOffset()) == doc.getLineNumber(firstOffset)) {
//								return false; // same line
//							}
//						}
//					}
//					return true; // we follow a well-formed stmt
//				}
//			}
//		}
//		return false;
		}
		return true;
	}

	public boolean isClassAcceptable(Class hintClass) {
		return true;
	}

}
