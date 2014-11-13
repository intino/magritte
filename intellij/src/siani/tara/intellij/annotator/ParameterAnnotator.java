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
import siani.tara.lang.Variable;
import siani.tara.lang.Word;

import java.util.List;
import java.util.Map;

import static siani.tara.lang.Primitives.*;

public class ParameterAnnotator extends TaraAnnotator {

	private Model model;
	private Node node;

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		if (!(element instanceof Parameter)) return;
		this.holder = holder;
		Parameter parameter = (Parameter) element;
		model = TaraLanguage.getMetaModel(parameter.getContainingFile());
		if (model == null) return;
		TaraFacetApply inFacet = parameter.isInFacet();
		Concept concept = TaraPsiImplUtil.getConceptContainerOf(parameter);
		node = findNode(concept, model);
		if (node == null) return;
		List<Variable> facetVariables = null;
		if (inFacet != null && (facetVariables = getAllowedFacet(node, inFacet.getFirstChild().getText())) == null)
			return;
		List<Variable> variables = (inFacet != null) ? facetVariables : node.getObject().getVariables();
		int index = parameter.getIndexInParent();
		if (index >= variables.size())
			annotateErroneousParameter(parameter, holder);
		else {
			if (parameter.isExplicit()) processExplicit((TaraExplicitParameter) parameter, variables);
			else processImplicit((TaraImplicitParameter) parameter, variables, index);
		}
	}

	private List<Variable> getAllowedFacet(Node node, String name) {
		for (Map.Entry<String, FacetTarget> entry : node.getObject().getAllowedFacets().entrySet())
			if (entry.getKey().substring(entry.getKey().lastIndexOf(".") + 1).equals(name))
				return entry.getValue().getVariables();
		return null;
	}

	private void processExplicit(TaraExplicitParameter parameter, List<Variable> variables) {
		String name = parameter.getIdentifier().getText();
		Variable variable = getVariableByName(variables, name);
		if (variable == null) annotateErroneousParameter(parameter, holder);
		if (parameter.getValue() == null) annotateErroneousParameter(parameter, holder);
		else if (variable instanceof Word) {
			if (!isCorrectWord((Word) variable, parameter.getValue().getText()))
				annotateErroneousParameter(parameter, holder);
		} else if (!areSameType(variable, parameter) || (parameter.isList() && !variable.isList()))
			annotateErroneousParameter(parameter, holder);

	}

	private void processImplicit(TaraImplicitParameter parameter, List<Variable> variables, int index) {
		Variable variable = variables.get(index);
		if (parameter.getValue().getFirstChild() instanceof TaraMetaWord || parameter.getValue().getFirstChild() instanceof TaraIdentifierReference)
			processAsWordOrReference(parameter.getValue(), variable);
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

	private void processAsWordOrReference(PsiElement parameterValue, Variable variable) {
		if (variable instanceof Word) {
			if (!isCorrectWord(((Word) variable), parameterValue.getFirstChild().getText()))
				holder.createErrorAnnotation(parameterValue, "Parameter type error. Expected " + variable.getType());
		} else if (variable instanceof Reference)
			checkReferences(parameterValue.getChildren(), (Reference) variable);
		else holder.createErrorAnnotation(parameterValue, "Parameter type error. Expected " + variable.getType());
	}

	private boolean isCorrectWord(Word word, String value) {
		return word.contains(value);
	}

	private void checkReferences(PsiElement[] parameterValues, Reference reference) {
		if (parameterValues.length > 1 && !reference.isList())
			holder.createErrorAnnotation(parameterValues[0].getParent(), "Only one item is expected");
		for (PsiElement value : parameterValues) {
			if (value instanceof TaraIdentifierReference &&
				!checkWellReference((TaraIdentifierReference) value, reference)) {
				holder.createErrorAnnotation(value, "Unexpected type. Bad reference");
				continue;
			}
			Concept conceptContextOf = TaraPsiImplUtil.getConceptContainerOf(value);
			if (TaraPsiImplUtil.getConceptContainerOf(conceptContextOf) != null)
				if (value instanceof TaraIdentifierReference && !reference.isUniversal() && !inSameContext((TaraIdentifierReference) value))
					holder.createErrorAnnotation(value, "Bad referenced. The reference has to be in the same context");
		}
	}

	private boolean checkWellReference(TaraIdentifierReference reference, Reference variable) {
		TaraReferenceSolver solver = new TaraReferenceSolver(getLastElementOf(reference), reference.getTextRange(), false);
		Concept destiny = TaraPsiImplUtil.getConceptContainerOf(solver.resolve());
		if (destiny != null) {
			MetaIdentifier metaIdentifier = destiny.getMetaIdentifier();
			if ((metaIdentifier != null))
				return checkInHierarchy(metaIdentifier.getText(), variable.getType()) || asFacet(destiny, variable.getType());
		}
		return false;
	}

	private boolean asFacet(Concept destiny, String reference) {
		String facet = reference.substring(reference.lastIndexOf(".") + 1);
		for (FacetApply facetApply : destiny.getFacetApplies())
			if (facetApply.getFacetName().equals(facet))
				return true;
		return false;
	}

	private boolean inSameContext(TaraIdentifierReference reference) {
		TaraReferenceSolver solver = new TaraReferenceSolver(getLastElementOf(reference), reference.getTextRange(), false);
		Concept resolve = TaraPsiImplUtil.getConceptContainerOf(solver.resolve());
		PsiElement referenceContext = reference;
		PsiElement resolveContext = resolve;
		while ((TaraPsiImplUtil.getConceptContainerOf(referenceContext)) != null)
			referenceContext = TaraPsiImplUtil.getConceptContainerOf(referenceContext);
		while ((TaraPsiImplUtil.getConceptContainerOf(resolveContext)) != null)
			resolveContext = TaraPsiImplUtil.getConceptContainerOf(resolveContext);
		return resolveContext == referenceContext;
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
		if (!Attribute.class.isInstance(variable)) return false;
		String varType = variable.getType();
		if (parameter.getValue() == null) return false;
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
