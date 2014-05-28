package monet.tara.intellij.lang.psi;

import com.intellij.pom.Navigatable;

public interface Parameters extends Navigatable, TaraPsiElement {

	Parameter[] getParameters();
}
