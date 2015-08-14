package tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.*;
import tara.intellij.lang.psi.resolve.ReferenceManager;
import tara.language.model.EmptyNode;
import tara.language.model.Primitives;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class ValueMixin extends ASTWrapperPsiElement {

	public ValueMixin(ASTNode node) {
		super(node);
	}

	@NotNull
	public List<Object> values() {
		List<Object> values = new ArrayList<>();
		for (PsiElement element : getChildren())
			if (!(element instanceof TaraMeasureValue)) values.add(cast(element));
		return unmodifiableList(values);
	}

	private Object cast(PsiElement element) {
		String value = element.getText();
		if (element instanceof TaraStringValue) return value;
		else if (element instanceof TaraBooleanValue) return Boolean.parseBoolean(value);
		else if (element instanceof TaraInstanceName) return value;
		else if (element instanceof TaraNaturalValue || element instanceof TaraIntegerValue)
			return Integer.parseInt(value);
		else if (element instanceof TaraDoubleValue) return Double.parseDouble(value);
		else if (element instanceof TaraTupleValue) {
			final TaraTupleValue tuple = (TaraTupleValue) element;
			return new AbstractMap.SimpleEntry<>(tuple.getStringValue().getValue(), Double.parseDouble(tuple.getDoubleValue().getText()));
		} else if (element instanceof TaraEmptyField) return new EmptyNode();
		else if (element instanceof TaraExpression)
			return new Primitives.Expression(value.substring(1, value.length() - 1));
		else if (element instanceof IdentifierReference)
			return ReferenceManager.resolveToNode((IdentifierReference) element);
		return "";
	}
}
