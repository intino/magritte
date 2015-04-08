package siani.tara.intellij.lang.psi;

import com.intellij.pom.Navigatable;
import org.jetbrains.annotations.NotNull;

public interface Parameter extends Navigatable, TaraPsiElement {

	int getIndexInParent();

	boolean isExplicit();

	Value getValue();

	Object[] getValues();

	TaraMeasureValue getMetric();

	boolean isList();

	int getValuesLength();

	TaraFacetApply isInFacet();

	@NotNull
	String getExplicitName();
}
