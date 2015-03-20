package siani.tara.compiler.semantic.wrappers;

import com.intellij.psi.PsiElement;
import siani.tara.semantic.model.Element;

public abstract class LanguageElement implements Element {

	public abstract PsiElement element();
}
