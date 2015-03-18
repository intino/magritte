package siani.tara.intellij.lang.semantic;

import com.intellij.psi.PsiElement;
import siani.tara.model.Element;

public abstract class LanguageElement implements Element {

	public abstract PsiElement element();
}
