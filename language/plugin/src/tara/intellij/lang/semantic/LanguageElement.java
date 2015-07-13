package tara.intellij.lang.semantic;

import com.intellij.psi.PsiElement;
import tara.semantic.model.Element;

public abstract class LanguageElement implements Element {

	public abstract PsiElement element();
}
