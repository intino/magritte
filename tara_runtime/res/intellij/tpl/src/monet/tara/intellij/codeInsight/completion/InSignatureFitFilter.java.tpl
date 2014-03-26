package monet.::projectName::.intellij.codeinsight.completion;

import com.intellij.psi.PsiElement;
import com.intellij.psi.filters.ElementFilter;
import monet.::projectName::.intellij.metamodel.psi.::projectProperName::Types;

public class InSignatureFitFilter implements ElementFilter {
	public boolean isAcceptable(Object element, PsiElement context) {
		PsiElement myCtx = context;
		if (element instanceof PsiElement)
			while (myCtx.getPrevSibling() != null) {
				myCtx = myCtx.getPrevSibling();
				if (::projectProperName::Types.NEWLINE.equals(myCtx.getNode().getElementType()) ||
					::projectProperName::Types.NEW_LINE_INDENT.equals(myCtx.getNode().getElementType()) ||
					::projectProperName::Types.DEDENT.equals(myCtx.getNode().getElementType()))
					break;
			}
		return false;
	}

	public boolean isClassAcceptable(Class hintClass) {
		return true;
	}
}
