package tara.intellij.lang.semantic;

import com.intellij.psi.PsiElement;
import tara.language.model.Element;

public abstract class LanguageElement implements Element {

	public abstract PsiElement element();
}
