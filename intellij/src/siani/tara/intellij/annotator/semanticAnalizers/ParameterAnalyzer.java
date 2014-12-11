package siani.tara.intellij.annotator.semanticAnalizers;

import com.intellij.psi.PsiElement;
import siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix;
import siani.tara.intellij.lang.psi.*;
import siani.tara.intellij.lang.psi.impl.TaraParameterValueImpl;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.intellij.lang.psi.resolve.TaraInternalReferenceSolver;
import siani.tara.lang.*;
import siani.tara.lang.Variable;

import java.util.List;
import java.util.Map;

import static siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.ERROR;
import static siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil.getConceptContainerOf;
import static siani.tara.lang.Primitives.*;

public class ParameterAnalyzer extends TaraAnalyzer {
	private final Parameter parameter;

	private final String DEFAULT_MESSAGE = "erroneous parameter";
	private final Node node;
	private final Concept concept;
	private final Model model;

	public ParameterAnalyzer(Parameter parameter) {
		this.parameter = parameter;
		concept = getConceptContainerOf(parameter);
		model = getMetamodel(concept);
		node = model != null ? TaraUtil.findNode(concept, model) : null;
	}

	@Override
	public void analyze() {
		if (node == null) return;
		TaraFacetApply inFacet = parameter.isInFacet();
		List<Variable> facetVariables = null;
		if (inFacet != null && (facetVariables = getAllowedFacet(node, inFacet.getFirstChild().getText())) == null)
			return;
		List<Variable> variables = (inFacet != null) ? facetVariables : node.getObject().getVariables();
		int index = parameter.getIndexInParent();
		if (index >= variables.size())
			results.put(parameter, new AnnotateAndFix(ERROR, DEFAULT_MESSAGE));
		else {
			if (parameter.isExplicit()) processExplicit((TaraExplicitParameter) parameter, variables);
			else processImplicit((TaraImplicitParameter) parameter, variables, index);
		}
	}

	private void processExplicit(TaraExplicitParameter parameter, List<Variable> variables) {
		String name = parameter.getIdentifier().getText();
		Variable variable = getVariableByName(variables, name);
		if (variable == null) {
			results.put(parameter, new AnnotateAndFix(ERROR, DEFAULT_MESSAGE));
			return;
		}
		if (parameter.getValue() == null) results.put(parameter, new AnnotateAndFix(ERROR, DEFAULT_MESSAGE));
		else if (variable instanceof siani.tara.lang.Word) {
			if (!isCorrectWord((siani.tara.lang.Word) variable, parameter.getValue().getText()))
				results.put(parameter, new AnnotateAndFix(ERROR, DEFAULT_MESSAGE));
		} else if (!areSameType(variable, parameter) || (parameter.isList() && !variable.isList()) || checkAsTuple(parameter.getValuesLength(), variable))
			results.put(parameter, new AnnotateAndFix(ERROR, DEFAULT_MESSAGE));

	}

	private void processImplicit(TaraImplicitParameter parameter, List<Variable> variables, int index) {
		Variable variable = variables.get(index);
		if (parameter.getValue().getFirstChild() instanceof TaraMetaWord || parameter.getValue().getFirstChild() instanceof TaraIdentifierReference)
			processAsWordOrReference(parameter.getValue(), variable);
		else if (!areSameType(variable, parameter) || (parameter.isList() && !variable.isList()))
			results.put(parameter, new AnnotateAndFix(ERROR, "Incorrect type. Expected " + variable.getType() + " " + variable.getName()));
	}

	private boolean checkAsTuple(int parametersLength, Variable variable) {
		return (variable instanceof Attribute && variable.getType().equals(DOUBLE) && parametersLength != ((Attribute) variable).getCount());
	}

	private List<Variable> getAllowedFacet(Node node, String name) {
		for (Map.Entry<String, FacetTarget> entry : node.getObject().getAllowedFacets().entrySet())
			if (entry.getKey().substring(entry.getKey().lastIndexOf(".") + 1).equals(name))
				return entry.getValue().getVariables();
		return null;
	}

	private Variable getVariableByName(List<Variable> variables, String name) {
		for (Variable variable : variables)
			if (variable.getName().equals(name)) return variable;
		return null;
	}


	private void processAsWordOrReference(PsiElement parameterValue, Variable variable) {
		if (variable instanceof siani.tara.lang.Word) {
			if (!isCorrectWord(((siani.tara.lang.Word) variable), parameterValue.getFirstChild().getText()))
				results.put(parameterValue, new AnnotateAndFix(ERROR, "Parameter type error. Expected " + variable.getType()));
		} else if (variable instanceof Reference)
			checkReferences(parameterValue.getChildren(), (Reference) variable);
		else
			results.put(parameterValue, new AnnotateAndFix(ERROR, "Parameter type error. Expected " + variable.getType()));
	}

	private boolean isCorrectWord(siani.tara.lang.Word word, String value) {
		return word.contains(value);
	}

	private void checkReferences(PsiElement[] parameterValues, Reference reference) {
		if (parameterValues.length > 1 && !reference.isList())
			results.put(parameterValues[0].getParent(), new AnnotateAndFix(ERROR, "Only one item is expected"));
		for (PsiElement value : parameterValues) {
			if (value instanceof TaraIdentifierReference &&
				!checkWellReference((TaraIdentifierReference) value, reference)) {
				results.put(value, new AnnotateAndFix(ERROR, "Unexpected type. Bad reference"));
				continue;
			}
			Concept conceptContextOf = getConceptContainerOf(value);
			if (getConceptContainerOf(conceptContextOf) != null)
				if (value instanceof TaraIdentifierReference && reference.isLocal() && !inSameContext((TaraIdentifierReference) value))
					results.put(value, new AnnotateAndFix(ERROR, "Bad referenced. The reference has to be in the same context"));
		}
	}

	private boolean checkWellReference(TaraIdentifierReference reference, Reference variable) {
		TaraInternalReferenceSolver solver = new TaraInternalReferenceSolver(getLastElementOf(reference), reference.getTextRange());
		Concept destiny = getConceptContainerOf(solver.resolve());
		if (destiny != null) {
			MetaIdentifier metaIdentifier = destiny.getMetaIdentifier();
			if ((metaIdentifier != null))
				return checkInHierarchy(metaIdentifier.getText(), variable.getType())
					|| asFacet(destiny, variable.getType())
					|| checkTypeAsTerminal(destiny, variable);
		}
		return false;
	}

	private boolean checkTypeAsTerminal(Concept destiny, Reference variable) {
		return variable.getInheritedTypes().contains(destiny.getType());
	}

	private boolean asFacet(Concept destiny, String reference) {
		String facet = reference.substring(reference.lastIndexOf(".") + 1);
		for (FacetApply facetApply : destiny.getFacetApplies())
			if (facetApply.getFacetName().equals(facet))
				return true;
		return false;
	}

	private boolean inSameContext(TaraIdentifierReference reference) {
		TaraInternalReferenceSolver solver = new TaraInternalReferenceSolver(getLastElementOf(reference), reference.getTextRange());
		Concept resolve = getConceptContainerOf(solver.resolve());
		PsiElement referenceContext = reference;
		PsiElement resolveContext = resolve;
		while ((getConceptContainerOf(referenceContext)) != null)
			referenceContext = getConceptContainerOf(referenceContext);
		while ((getConceptContainerOf(resolveContext)) != null)
			resolveContext = getConceptContainerOf(resolveContext);
		return resolveContext == referenceContext;
	}

	private boolean checkInHierarchy(String name, String type) {
		Node referenceNode = model.get(type);
		if (name == null || referenceNode == null) return false;
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
				return varType.equals(STRING) || varType.equals(RESOURCE);
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
