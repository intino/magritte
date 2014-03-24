package monet.tara.intellij.codeinsight.completion;

import com.intellij.psi.PsiElement;
import com.intellij.psi.filters.ElementFilter;
import monet.tara.intellij.metamodel.psi.TaraTypes;

public class InSignatureFitFilter implements ElementFilter {
	public boolean isAcceptable(Object element, PsiElement context) {
		PsiElement myCtx = context;
		if (element instanceof PsiElement)
			while (myCtx.getPrevSibling() != null) {
				myCtx = myCtx.getPrevSibling();
				if (TaraTypes.CONCEPT_KEY.equals(myCtx.getNode().getElementType()))
					return true;
				if (TaraTypes.NEWLINE.equals(myCtx.getNode().getElementType()) ||
					TaraTypes.NEW_LINE_INDENT.equals(myCtx.getNode().getElementType()) ||
					TaraTypes.DEDENT.equals(myCtx.getNode().getElementType()))
					break;
			}
		return false;
	}

	public boolean isClassAcceptable(Class hintClass) {
		return true;
	}
}
