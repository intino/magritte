package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.*;
import siani.tara.intellij.lang.psi.impl.TaraParameterValueImpl;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.intellij.lang.psi.resolve.TaraReferenceSolver;
import siani.tara.lang.*;

import java.util.List;
import java.util.Map;

public class ParameterAnnotator extends TaraAnnotator {

	protected static final String INTEGER = "integer";
	protected static final String NATURAL = "natural";
	protected static final String BOOLEAN = "boolean";
	protected static final String STRING = "string";
	protected static final String DOUBLE = "double";
	protected static final String REFERENCE = "reference";
	protected static final String COORDINATE = "coordinate";
	protected static final String DATE = "date";

	Model model;
	Node node;

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		if (!(element instanceof Parameter)) return;
		Parameter parameter = (Parameter) element;
		model = TaraLanguage.getMetaModel(parameter.getContainingFile());
		if (model == null) return;
		TaraFacetApply inFacet = parameter.isInFacet();
		Concept concept = TaraPsiImplUtil.getContextOf(parameter);
		node = findNode(concept, model);
		if (node == null) return;
		List<Variable> facetVariables = null;
		if (inFacet != null && (facetVariables = getAllowedFacet(node, inFacet.getFirstChild().getText())) == null)
			return;
		List<Variable> variables = (inFacet != null) ? facetVariables : node.getObject().getVariables();
		int index = parameter.getIndexInParent();
		if (index >= variables.size()) annotateErroneousParameter(parameter, holder);
		else {
			if (parameter.isExplicit()) processExplicit(holder, (TaraExplicitParameter) parameter, variables);
			else processImplicit(holder, (TaraImplicitParameter) parameter, variables, index);
		}
	}

	private List<Variable> getAllowedFacet(Node node, String name) {
		for (Map.Entry<String, List<Variable>> entry : node.getObject().getAllowedFacets().entrySet())
			if (entry.getKey().substring(entry.getKey().lastIndexOf(".") + 1).equals(name)) return entry.getValue();
		return null;
	}

	private void processExplicit(AnnotationHolder holder, TaraExplicitParameter parameter, List<Variable> variables) {
		String name = parameter.getIdentifier().getText();
		Variable variable = getVariableByName(variables, name);
		if (variable == null) annotateErroneousParameter(parameter, holder);
		else if (variable instanceof NodeWord) {
			if (!isCorrectWord((NodeWord) variable, parameter.getValue().getText()))
				annotateErroneousParameter(parameter, holder);
		} else if (!areSameType(variable, parameter) || (parameter.isList() && !variable.isList()))
			annotateErroneousParameter(parameter, holder);

	}

	private boolean isCorrectWord(NodeWord word, String value) {
		return word.contains(value);
	}

	private void processImplicit(AnnotationHolder holder, TaraImplicitParameter parameter, List<Variable> variables, int index) {
		Variable variable = variables.get(index);
		if (parameter.getValue().getFirstChild() instanceof TaraMetaWord || parameter.getValue().getFirstChild() instanceof TaraIdentifierReference)
			processAsWordOrReference(parameter.getValue(), holder, variable);
		else if (!areSameType(variable, parameter) || (parameter.isList() && !variable.isList()))
			holder.createErrorAnnotation(parameter, "Incorrect type. Expected " + variable.getType() + " " + variable.getName());
	}

	private Variable getVariableByName(List<Variable> variables, String name) {
		for (Variable variable : variables) if (variable.getName().equals(name)) return variable;
		return null;
	}

	private void annotateErroneousParameter(PsiElement element, AnnotationHolder holder) {
		holder.createErrorAnnotation(element, "erroneous parameter");
	}

	private void processAsWordOrReference(PsiElement element, AnnotationHolder holder, Variable actualVariable) {
		if (actualVariable instanceof NodeWord)
			isCorrectWord(((NodeWord) actualVariable), element.getFirstChild().getText());
		else if (!Reference.class.isInstance(actualVariable))
			holder.createErrorAnnotation(element, "Parameter type error. Expected " + actualVariable.getType());
		else if (element.getFirstChild() instanceof TaraIdentifierReference &&
			!checkWellReference((TaraIdentifierReference) element.getFirstChild(), (Reference) actualVariable))
			holder.createErrorAnnotation(element, "Unexpected type. Bad reference");
	}

	private boolean checkWellReference(TaraIdentifierReference reference, Reference variable) {
		TaraReferenceSolver solver = new TaraReferenceSolver(getLastElementOf(reference), reference.getTextRange(), false);
		Concept resolve = TaraPsiImplUtil.getContextOf(solver.resolve());
		if (resolve != null) {
			MetaIdentifier metaIdentifier = resolve.getMetaIdentifier();
			if ((metaIdentifier != null))
				return checkInHierarchy(metaIdentifier.getText(), variable.getType());
		}
		return false;
	}

	private boolean checkInHierarchy(String name, String type) {
		Node referenceNode = model.get(type);
		if (name.equals(referenceNode.getName())) return true;
		List<NodeObject> children;
		while ((children = referenceNode.getObject().getChildren()) != null)
			for (NodeObject child : children)
				if (child.getName().equals(name)) return true;

		return false;
	}

	private boolean areSameType(Variable variable, Parameter parameter) {
		if (!NodeAttribute.class.isInstance(variable)) return false;
		String varType = variable.getType();
		String parameterType = parameter.getValue().getClass().getSimpleName();
		Types type;
		if (parameterType.equals("TaraParameterValueImpl"))
			type = Types.valueOf(getImplicitValueType((TaraParameterValueImpl) parameter.getValue()));
		else type = Types.valueOf(parameterType);
		switch (type) {
			case TaraStringValueImpl:
				return varType.equals(STRING);
			case TaraBooleanValueImpl:
				return varType.equals(BOOLEAN);
			case TaraNaturalValueImpl:
				return varType.equals(NATURAL) || varType.equals(INTEGER) || varType.equals(DOUBLE);
			case TaraIntegerValueImpl:
				return varType.equals(INTEGER) || varType.equals(DOUBLE);
			case TaraDoubleValueImpl:
				return varType.equals(DOUBLE);
			case TaraCodeValueImpl:
				return varType.equals(REFERENCE);
			case TaraDateValueImpl:
				return varType.equals(DATE);
			case TaraCoordinateValueImpl:
				return varType.equals(COORDINATE);
			default:
				return false;
		}
	}

	private String getImplicitValueType(TaraParameterValueImpl value) {
		return value.getFirstChild().getClass().getSimpleName();
	}

	private TaraIdentifier getLastElementOf(TaraIdentifierReference reference) {
		return reference.getIdentifierList().get(reference.getIdentifierList().size() - 1);
	}

	enum Types {
		TaraStringValueImpl, TaraBooleanValueImpl, TaraIntegerValueImpl, TaraDoubleValueImpl, TaraNaturalValueImpl, TaraCodeValueImpl,
		TaraDateValueImpl, TaraCoordinateValueImpl, TaraNaturalListImpl, TaraDoubleListImpl, TaraIntegerListImpl, TaraBooleanListImpl,
		TaraStringListImpl, TaraDateListImpl, TaraCoordinateListImpl, TaraReferenceListImpl, TaraEmptyImpl, TaraMetaWordImpl, TaraIdentifierReferenceImpl
	}


}
