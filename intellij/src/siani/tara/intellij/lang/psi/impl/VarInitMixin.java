package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.TaraTypes;
import siani.tara.intellij.lang.psi.TaraVarInit;

import static siani.tara.lang.Primitives.*;

public class VarInitMixin extends ASTWrapperPsiElement {

	private static final String EMPTY = "empty";

	public VarInitMixin(@NotNull ASTNode node) {
		super(node);
	}


	public String getName() {
		ASTNode childByType = this.getNode().findChildByType(TaraTypes.IDENTIFIER_KEY);
		return childByType != null ? childByType.getText() : null;
	}

	public String getValueType() {
		if (!((TaraVarInit) this).getBooleanValueList().isEmpty()) return BOOLEAN;
		if (!((TaraVarInit) this).getCoordinateValueList().isEmpty()) return COORDINATE;
		if (!((TaraVarInit) this).getDateValueList().isEmpty()) return DATE;
		if (!((TaraVarInit) this).getDoubleValueList().isEmpty()) return DOUBLE;
		if (!((TaraVarInit) this).getIntegerValueList().isEmpty()) return INTEGER;
		if (!((TaraVarInit) this).getNaturalValueList().isEmpty()) return NATURAL;
		if (!((TaraVarInit) this).getLinkValueList().isEmpty()) return REFERENCE;
		if (!((TaraVarInit) this).getStringValueList().isEmpty()) return STRING;
		if (((TaraVarInit) this).getEmptyField() != null) return EMPTY;
		return "null";
	}
}