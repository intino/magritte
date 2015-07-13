package tara.intellij.codeinsight.completion;

import com.intellij.psi.PsiElement;
import com.intellij.psi.filters.ElementFilter;
import tara.intellij.lang.psi.Parameters;
import tara.intellij.lang.psi.Signature;

public class InParametersFitFilter implements ElementFilter {
	public boolean isAcceptable(Object element, PsiElement context) {
		PsiElement myCtx = context.getParent();
		while (myCtx != null && !(myCtx instanceof Signature)) {
			if (myCtx instanceof Parameters)
				return true;
			myCtx = myCtx.getParent();
		}
		return false;
	}

	public boolean isClassAcceptable(Class hintClass) {
		return true;
	}
}