package monet.::projectName::.intellij.codeinsight.completion;

import com.intellij.psi.PsiElement;
import com.intellij.psi.filters.ElementFilter;
import monet.::projectName::.intellij.lang.psi.Definition;
import monet.::projectName::.intellij.lang.psi.Signature;

public class InSignatureFitFilter implements ElementFilter {
	public boolean isAcceptable(Object element, PsiElement context) {
		PsiElement myCtx = context.getParent();
		while (myCtx != null && !(myCtx instanceof Definition)) {
			if (myCtx instanceof Signature)
				return true;
			myCtx = myCtx.getParent();
		}
		return false;
	}

	public boolean isClassAcceptable(Class hintClass) {
		return true;
	}
}
