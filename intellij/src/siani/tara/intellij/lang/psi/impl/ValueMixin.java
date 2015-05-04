package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import siani.tara.intellij.lang.psi.*;

import java.util.ArrayList;
import java.util.List;

public class ValueMixin extends ASTWrapperPsiElement {

	public ValueMixin(ASTNode node) {
		super(node);
	}

	public Object[] getValues() {
		List<Object> values = new ArrayList<>();
		for (PsiElement element : getChildren()) {
			if (element instanceof TaraMeasureValue) continue;
			values.add(cast(element));
		}
		return values.toArray();
	}

	private Object cast(PsiElement element) {
		String value = element.getText();
		if (element instanceof TaraStringValue) return value;
		else if (element instanceof TaraBooleanValue) return Boolean.parseBoolean(value);
		else if (element instanceof TaraLinkValue) return value;
		else if (element instanceof TaraNaturalValue || element instanceof TaraIntegerValue)
			return Integer.parseInt(value);
		else if (element instanceof TaraDoubleValue) return Double.parseDouble(value);
		else if (element instanceof TaraEmptyField) return "$" + value;
		else if (element instanceof IdentifierReference) {
			Node node = ReferenceManager.resolveToNode((IdentifierReference) element);
			return node != null ? node : null;
		}
		return "";
	}
}