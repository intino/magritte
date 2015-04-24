package siani.tara.intellij.lang.psi;

import com.intellij.pom.Navigatable;

public interface NativeName extends Navigatable, TaraPsiElement {

	String getFormattedName();
}
