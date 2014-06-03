package monet.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import monet.tara.intellij.lang.TaraLanguage;
import monet.tara.intellij.lang.psi.*;
import monet.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import monet.tara.intellij.lang.psi.resolve.TaraReferenceSolver;
import monet.tara.lang.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParameterAnnotator extends TaraAnnotator {
	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		if (!(element instanceof Parameter)) return;
		MetaIdentifier metaIdentifier = TaraPsiImplUtil.getContextOf(element).getMetaIdentifier();
		int index = getIndexOf((Parameters) element.getParent(), (Parameter) element);
		AbstractNode node = TaraLanguage.getHeritage().getNodeNameLookUpTable().get(metaIdentifier.getText()).get(0);
		List<Variable> variables = node.getVariables();
		if (index >= variables.size()) annotateInsufficientParameters(element, holder);
		else {
			Variable actualVariable = variables.get(index);
			if (element.getFirstChild() instanceof TaraIdentifierReference /*|| element.getFirstChild() instanceof TaraIdentifierList*/)
				processAsWordOrReference(element, holder, actualVariable);
			else if (!areSameType(actualVariable, element))
				holder.createErrorAnnotation(element, "parameter type error");
		}
	}

	private void annotateInsufficientParameters(PsiElement element, AnnotationHolder holder) {
		holder.createErrorAnnotation(element, "parameter missed");
	}

	private void processAsWordOrReference(PsiElement element, AnnotationHolder holder, Variable actualVariable) {
		if (actualVariable instanceof NodeWord)
			processAsWord((TaraIdentifierReference) element.getFirstChild(), ((NodeWord) actualVariable));
		else if (element instanceof TaraIdentifierReference &&
			checkReference((TaraIdentifierReference) element.getFirstChild(), (Reference) actualVariable))
			holder.createErrorAnnotation(element, "Parameter type error");
	}

	private boolean processAsWord(TaraIdentifierReference reference, NodeWord word) {
		return word.contains(getLastElementOf(reference).getText());
	}

	private boolean checkReference(TaraIdentifierReference reference, Reference variable) {
		TaraReferenceSolver solver = new TaraReferenceSolver(getLastElementOf(reference), reference.getTextRange(), false);
		PsiElement resolve = solver.resolve();
		if (resolve instanceof Concept) {
			MetaIdentifier metaIdentifier = ((Concept) resolve).getMetaIdentifier();
			if ((metaIdentifier != null) && metaIdentifier.getText().equals(variable.getNode())) return true;
		}
		return false;
	}

	private boolean areSameType(Variable variable, PsiElement element) {
		if (!NodeAttribute.class.isInstance(variable)) return false;
		String varType = ((NodeAttribute) variable).getPrimitiveType();
		Types type = Types.valueOf(element.getFirstChild().getClass().getSimpleName());
		switch (type) {
			case TaraStringValueImpl:
				return varType.equals("String") | varType.equals("Alias");
			case TaraBooleanValueImpl:
				return varType.equals("Boolean");
			case TaraNaturalValueImpl:
				return varType.equals("Natural") || varType.equals("Integer") || varType.equals("Double");
			case TaraIntegerValueImpl:
				return varType.equals("Integer") || varType.equals("Double");
			case TaraDoubleValueImpl:
				return varType.equals("Double");
			case TaraStringListImpl:
				return varType.equals("String") && variable.isList();
			case TaraBooleanListImpl:
				return varType.equals("Boolean") && variable.isList();
			case TaraIntegerListImpl:
				return varType.equals("Integer") && variable.isList();
			case TaraDoubleListImpl:
				return varType.equals("Double") && variable.isList();
			case TaraNaturalListImpl:
				return varType.equals("Natural") && variable.isList();
			default:
				return false;
		}
	}

	private int getIndexOf(Parameters parent, Parameter p) {
		List<Parameter> parameters = new ArrayList<>();
		Collections.addAll(parameters, parent.getParameters());
		return parameters.indexOf(p);
	}

	private TaraIdentifier getLastElementOf(TaraIdentifierReference reference) {
		return reference.getIdentifierList().get(reference.getIdentifierList().size() - 1);
	}

	enum Types {
		TaraStringValueImpl, TaraBooleanValueImpl, TaraIntegerValueImpl, TaraDoubleValueImpl, TaraNaturalValueImpl,
		TaraStringListImpl, TaraBooleanListImpl, TaraIntegerListImpl, TaraDoubleListImpl, TaraNaturalListImpl, GorosEmptyImpl
	}


}
