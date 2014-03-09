package monet.::projectName::.intellij.metamodel.psi;

import com.intellij.openapi.util.Iconable;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiNamedElement;

public interface Attribute extends Navigatable, Iconable, PsiNamedElement {

	public String getType();

}
