package siani.tara.intellij.lang.psi;

import com.intellij.pom.Navigatable;

public interface Parameter extends Navigatable, TaraPsiElement {

	public int getIndexInParent();

	public boolean isExplicit();

	public TaraParameterValue getValue();

	public String[] getValues();

	public TaraMeasureValue getMeasure();

	public boolean isList();

	public int getValuesLength();

	public TaraFacetApply isInFacet();
}
