package siani.tara.intellij.lang.psi;

import com.intellij.pom.Navigatable;

public interface VarInit extends Navigatable, TaraPsiElement {

	String getName();

	String getValueType();

	String[] getValues();

	TaraVarInitValue getValue();

	String getMeasureValue();
}
