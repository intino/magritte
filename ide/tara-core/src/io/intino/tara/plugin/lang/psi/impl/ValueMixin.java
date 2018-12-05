package io.intino.tara.plugin.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import io.intino.tara.Language;
import io.intino.tara.lang.model.EmptyNode;
import io.intino.tara.lang.model.Node;
import io.intino.tara.lang.model.Primitive;
import io.intino.tara.lang.model.Primitive.Reference;
import io.intino.tara.lang.semantics.InstanceContext;
import io.intino.tara.plugin.lang.psi.*;
import io.intino.tara.plugin.lang.psi.resolve.ReferenceManager;
import org.jetbrains.annotations.NotNull;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class ValueMixin extends ASTWrapperPsiElement {

	ValueMixin(ASTNode node) {
		super(node);
	}

	@NotNull
	public List<Object> values() {
		List<Object> values = new ArrayList<>();
		for (PsiElement element : getChildren())
			if (!(element instanceof TaraMetric)) values.add(cast(element));
		return unmodifiableList(values);
	}

	private Object cast(PsiElement element) {
		String value = element.getText();
		if (element instanceof TaraStringValue) return value;
		else if (element instanceof TaraBooleanValue) return Boolean.parseBoolean(value);
		else if (element instanceof TaraDoubleValue) return Double.parseDouble(value);
		else if (element instanceof TaraIntegerValue) return toInt(value);
		else if (element instanceof TaraTupleValue) {
			final TaraTupleValue tuple = (TaraTupleValue) element;
			return new AbstractMap.SimpleEntry<>(tuple.getStringValue().getValue(), Double.parseDouble(tuple.getDoubleValue().getText()));
		} else if (element instanceof TaraEmptyField) return new EmptyNode();
		else if (element instanceof TaraExpression)
			return new Primitive.Expression(((TaraExpression) element).getValue());
		else if (element instanceof IdentifierReference) {
			Node node = ReferenceManager.resolveToNode((IdentifierReference) element);
			return node != null ? node : createReference(element);
		} else if (element instanceof TaraMethodReference) {
			return createMethodReference((TaraMethodReference) element);
		}
		return "";
	}

	private Object toInt(String value) {
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			try {
				return Long.parseLong(value);
			} catch (NumberFormatException ex) {
				return "";
			}
		}
	}

	private Primitive.MethodReference createMethodReference(TaraMethodReference element) {
		return new Primitive.MethodReference(element.getIdentifierReference() != null ? element.getIdentifierReference().getText() : "");
	}

	private Reference createReference(PsiElement element) {
		final Reference reference = new Reference(element.getText());
		final Language language = TaraUtil.getLanguage(element);
		if (language == null) return reference;
		final InstanceContext instance = language.instances().get(element.getText());
		if (instance == null) return reference;
		reference.setToInstance(true);
		reference.instanceTypes(instance.types());
		reference.path(instance.path());
		return reference;
	}
}
