package siani.tara.intellij.lang.psi;

import com.intellij.pom.Navigatable;

import java.util.List;

public interface VarInit extends Navigatable, TaraPsiElement {

	String getName();

	String getValueType();

	List<Object> getValues();

	Value getValue();

	String getMetric();

	String getContract();

	void setContract(String contract);

	String getInferredType();

	void setInferredType(String type);
}
