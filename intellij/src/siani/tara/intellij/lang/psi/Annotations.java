package siani.tara.intellij.lang.psi;

import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiElement;

public interface Annotations extends Navigatable, TaraPsiElement {

	PsiElement[] getAnnotations();
}
