package siani.tara.intellij.lang.psi;

import com.intellij.pom.Navigatable;

public interface ConceptReference extends Navigatable, TaraPsiElement {

	public boolean isAggregated();

}
