package monet.tara.intellij.psi;

import com.intellij.openapi.util.Iconable;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiNamedElement;

public interface IAttribute extends Navigatable, Iconable, PsiNamedElement {

	public String getType();

	public enum TYPE {STRING, INTEGER, WORD, DOUBLE, NATURAL, BOOLEAN, UUID}
}
