package tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import tara.Language;
import tara.intellij.lang.psi.*;
import tara.intellij.lang.psi.resolve.ReferenceManager;
import tara.lang.model.EmptyNode;
import tara.lang.model.Node;
import tara.lang.model.Primitive;
import tara.lang.model.Primitive.Reference;
import tara.lang.semantics.DeclarationContext;

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
		else if (element instanceof TaraIntegerValue) return Integer.parseInt(value);
		else if (element instanceof TaraTupleValue) {
			final TaraTupleValue tuple = (TaraTupleValue) element;
			return new AbstractMap.SimpleEntry<>(tuple.getStringValue().getValue(), Double.parseDouble(tuple.getDoubleValue().getText()));
		} else if (element instanceof TaraEmptyField) return new EmptyNode();
		else if (element instanceof TaraExpression)
			return new Primitive.Expression(((TaraExpression) element).getValue());
		else if (element instanceof IdentifierReference) {
			Node node = ReferenceManager.resolveToNode((IdentifierReference) element);
			return node != null ? node : createReference(element);
		} else if (element instanceof TaraClassReference) {
			return createMethodReference((TaraClassReference) element);
		}
		return "";
	}

	private Primitive.MethodReference createMethodReference(TaraClassReference element) {
		return new Primitive.MethodReference(element.getIdentifierReference() != null ? element.getIdentifierReference().getText() : "");
	}

	private Reference createReference(PsiElement element) {
		final Reference reference = new Reference(element.getText());
		final Language language = TaraUtil.getLanguage(element);
		if (language == null) return reference;
		final DeclarationContext instance = language.instances().get(element.getText());
		if (instance == null) return reference;
		reference.setToDeclaration(true);
		reference.declarationTypes(instance.types());
		reference.path(instance.path());
		return reference;
	}
}
