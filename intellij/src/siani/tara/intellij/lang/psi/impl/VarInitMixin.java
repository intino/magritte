package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.*;

import java.util.ArrayList;
import java.util.List;

import static siani.tara.intellij.lang.lexer.TaraPrimitives.*;

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
		TaraValue value = ((TaraVarInit) this).getValue();
		if (value == null) return "null";
		if (!value.getBooleanValueList().isEmpty()) return BOOLEAN;
		if (!value.getDoubleValueList().isEmpty()) return DOUBLE;
		if (!value.getIntegerValueList().isEmpty()) return INTEGER;
		if (!value.getNaturalValueList().isEmpty()) return NATURAL;
		if (!value.getLinkValueList().isEmpty()
			|| !value.getIdentifierReferenceList().isEmpty()) return REFERENCE;
		if (!value.getStringValueList().isEmpty()) return STRING;
		if (value.getEmptyField() != null) return EMPTY;
		return "null";
	}

	public String[] getValues() {
		TaraValue value = ((TaraVarInit) this).getValue();
		if (value == null) return new String[0];
		List<String> values = new ArrayList<>();
		for (PsiElement element : value.getChildren()) {
			if (element instanceof TaraMeasureValue) continue;
			values.add(element.getText());
		}
		return values.toArray(new String[values.size()]);
	}


	public String getMeasureValue() {
		TaraMeasureValue measureValue = ((TaraVarInit) this).getValue().getMeasureValue();
		return measureValue == null ? null : measureValue.getText();
	}
}