package siani.tara.intellij.lang.psi;

import com.intellij.pom.Navigatable;

public interface FacetApply extends Navigatable, TaraPsiElement {

	String getFacetName();
}
