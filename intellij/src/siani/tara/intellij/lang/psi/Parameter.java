package siani.tara.intellij.lang.psi;

import com.intellij.pom.Navigatable;

public interface Parameter extends Navigatable, TaraPsiElement {

	int getIndexInParent();

	boolean isExplicit();

	TaraParameterValue getValue();

	String[] getValues();

	TaraMeasureValue getMeasure();

	boolean isList();

	int getValuesLength();

	TaraFacetApply isInFacet();

	String getExplicitName();
}
