package tara.intellij.lang.psi;

import com.intellij.pom.Navigatable;

public interface Contract extends Navigatable, TaraPsiElement {

	String getFormattedName();
}
