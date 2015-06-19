package siani.tara.intellij.lang.psi;

import com.intellij.pom.Navigatable;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface Parameter extends Navigatable, TaraPsiElement {

	int getIndexInParent();

	boolean isExplicit();

	Value getValue();

	List<Object> getValues();

	TaraMeasureValue getMetric();

	boolean isList();

	TaraFacetApply isInFacet();

	@NotNull
	String getExplicitName();

	String getContract();

	void setContract(String contract);

	String getInferredType();

	void setInferredType(String type);

	void setInferredName(String name);
}
