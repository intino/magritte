package monet.::projectName::.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import monet.::projectName::.intellij.lang.psi.Parameter;

import org.jetbrains.annotations.NotNull;

public class ParametersMixin extends ASTWrapperPsiElement {
	public ParametersMixin(\@NotNull ASTNode node) {
		super(node);
	}

	public monet.::projectName::.intellij.lang.psi.Parameter[] getParameters() {
		java.util.List<monet.::projectName::.intellij.lang.psi.::projectProperName::Parameter> parameterList = ((::projectProperName::ParametersImpl) this).getParameterList();
		return parameterList.toArray(new monet.::projectName::.intellij.lang.psi.Parameter[parameterList.size()]);
	}
}
