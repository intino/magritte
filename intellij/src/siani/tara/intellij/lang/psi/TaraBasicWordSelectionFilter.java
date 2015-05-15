package siani.tara.intellij.lang.psi;

import com.intellij.openapi.util.Condition;
import com.intellij.psi.PsiElement;

public class TaraBasicWordSelectionFilter implements Condition<PsiElement> {
	@Override
	public boolean value(PsiElement element) {
		return element.getNode().getElementType().equals(TaraTypes.STRING_VALUE);
	}
}
