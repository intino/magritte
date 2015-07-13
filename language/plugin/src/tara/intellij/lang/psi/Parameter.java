package tara.intellij.lang.psi;

import com.intellij.pom.Navigatable;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface Parameter extends Navigatable, TaraPsiElement {



	int getIndexInParent();

	boolean isExplicit();

	Value getValue();

	List<Object> getValues();

	TaraMeasureValue getMetric();

	boolean isList();

	FacetApply isInFacet();

	@Nullable
	String getName();

	String getContract();

	void setContract(String contract);

	String getInferredType();

	String getValueType();

	void setInferredType(String type);

	void setInferredName(String name);
}
