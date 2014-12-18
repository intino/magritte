package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.*;

import java.util.ArrayList;
import java.util.List;

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
		TaraVarInitValue varInitValue = ((TaraVarInit) this).getVarInitValue();
		if (varInitValue == null) return "null";
		if (!varInitValue.getBooleanValueList().isEmpty()) return BOOLEAN;
		if (!varInitValue.getDateValueList().isEmpty()) return DATE;
		if (!varInitValue.getDoubleValueList().isEmpty()) return DOUBLE;
		if (!varInitValue.getIntegerValueList().isEmpty()) return INTEGER;
		if (!varInitValue.getNaturalValueList().isEmpty()) return NATURAL;
		if (!varInitValue.getLinkValueList().isEmpty()
			|| !varInitValue.getIdentifierReferenceList().isEmpty()) return REFERENCE;
		if (!varInitValue.getStringValueList().isEmpty()) return STRING;
		if (varInitValue.getEmptyField() != null) return EMPTY;
		return "null";
	}

	public String[] getValues() {
		TaraVarInitValue value = getValue();
		if (value == null) return new String[0];
		List<String> values = new ArrayList<>();
		for (PsiElement element : value.getChildren()) {
			if (element instanceof TaraMeasureValue) continue;
			values.add(element.getText());
		}
		return values.toArray(new String[values.size()]);
	}

	public TaraVarInitValue getValue() {
		return ((TaraVarInit) this).getVarInitValue();
	}

	public String getMeasureValue() {
		TaraMeasureValue measureValue = ((TaraVarInit) this).getVarInitValue().getMeasureValue();
		return measureValue == null ? null : measureValue.getText();
	}
}