package monet.::projectName::.intellij.metamodel.psi;

import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiElement;

public interface Annotations extends Navigatable, PsiElement {

	PsiElement[] getAnnotations();
}
