package siani.tara.intellij.annotator.semanticanalizer;

import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix;
import siani.tara.intellij.lang.psi.*;
import siani.tara.intellij.lang.psi.impl.ReferenceManager;
import siani.tara.intellij.lang.psi.impl.TaraParameterValueImpl;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.intellij.project.module.ModuleProvider;
import siani.tara.lang.*;
import siani.tara.lang.Variable;
import siani.tara.lang.Word;

import java.io.File;
import java.util.List;

import static siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.ERROR;
import static siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.INFO;
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
		if (inFacet != null && (facetVariables = getAllowedFacet(node, inFacet.getFirstChild().getText(), getContextNameOf(inFacet))) == null)
			return;
		List<Variable> variables = (inFacet != null) ? facetVariables : node.getObject().getVariables();
		if (parameter.getIndexInParent() >= variables.size())
			results.put(parameter, new AnnotateAndFix(ERROR, DEFAULT_MESSAGE));
		else analyzeParameter(variables, parameter.getIndexInParent());
	}

	private String getContextNameOf(TaraFacetApply inFacet) {
		PsiElement contextOf = TaraPsiImplUtil.getContextOf(inFacet);
		if (contextOf instanceof TaraFacetApply)
			return contextOf.getFirstChild().getText();
		if (contextOf instanceof Concept) return ((Concept) contextOf).getType();
		return null;
	}

	private void analyzeParameter(List<Variable> variables, int index) {
		if (parameter.isExplicit())
			processExplicit(variables);
		else {
			processImplicit(variables.get(index));
			if (!hasErrors()) addParameterNameInfo(variables.get(index));
		}
	}

	private void addParameterNameInfo(Variable variable) {
		results.put(parameter, new AnnotateAndFix(INFO, variable.getName()));
	}

	private void processExplicit(List<Variable> variables) {
		TaraExplicitParameter parameter = (TaraExplicitParameter) this.parameter;
		String name = parameter.getIdentifier().getText();
		Variable variable = getVariableByName(variables, name);
		if (variable == null) {
			results.put(parameter, new AnnotateAndFix(ERROR, DEFAULT_MESSAGE + ": " + name + " does not exists."));
			return;
		}
		analyzeByType(parameter, variable);
	}

	private void analyzeByType(TaraExplicitParameter parameter, Variable variable) {
		if (parameter.getValue() == null)
			results.put(parameter, new AnnotateAndFix(ERROR, DEFAULT_MESSAGE));
		else if (variable instanceof Resource)
			analyzeAsResource(variable);
		else if (variable instanceof Word && !isCorrectWord((Word) variable, parameter.getValue().getText()))
			results.put(parameter, new AnnotateAndFix(ERROR, DEFAULT_MESSAGE));
		else if (variable instanceof Reference && parameter.getParameterValue() != null)
			checkAsReference(parameter.getParameterValue().getChildren(), (Reference) variable);
		else if (!areCompatibleTypes(variable, parameter) || (parameter.isList() && !variable.isList()) || checkAsTuple(parameter.getValuesLength(), variable))
			results.put(parameter, new AnnotateAndFix(ERROR, DEFAULT_MESSAGE));
		else if (variable.getType().equals(MEASURE))
			analyzeMetric(variable, parameter);
	}

	private void analyzeMetric(Variable variable, Parameter parameter) {
		AnnotateAndFix result = new MetricAnalyzer(model, variable, parameter.getValues(), parameter.getMeasure().getText()).analyze();
		if (result != null) results.put(parameter, result);
	}

	private void analyzeAsResource(Variable variable) {
		List<TaraStringValue> values = parameter.getValue().getStringValueList();
		for (TaraStringValue value : values) {
			if (!existResource(value.getText()))
				results.put(parameter, new AnnotateAndFix(ERROR, "Resource not found"));
			else if (!sameType(value.getText().replace("\"", ""), variable.getType()))
				results.put(parameter, new AnnotateAndFix(ERROR, "Incompatible types. Found " +
					value.getText().substring(value.getText().lastIndexOf(".")) + ". " + variable.getType() + " expected"));
		}
	}

	private boolean existResource(String destiny) {
		return findResource(destiny.replace("\"", "")) != null;
	}

	private File findResource(String destiny) {
		VirtualFile[] parent = ModuleRootManager.getInstance(ModuleProvider.getModuleOf(parameter.getContainingFile())).getSourceRoots();
		for (VirtualFile virtualFile : parent) {
			File file = new File(virtualFile.getPath(), destiny);
			if (file.exists()) return file;
		}
		return null;
	}

	private boolean sameType(String destiny, String type) {
		return type.equals(Resource.ANY) || destiny.substring(destiny.lastIndexOf(".") + 1).equalsIgnoreCase(type);
	}

	private void processImplicit(Variable variable) {
		TaraImplicitParameter parameter = (TaraImplicitParameter) this.parameter;
		if (parameter.getValue().getFirstChild() instanceof TaraMetaWord || parameter.getValue().getFirstChild() instanceof TaraIdentifierReference)
			processAsWordOrReference(parameter.getValue(), variable);
		else if (!areCompatibleTypes(variable, parameter) || (parameter.isList() && !variable.isList()))
			results.put(parameter, new AnnotateAndFix(ERROR, "Incorrect type. Expected " + variable.getType()));
		else if (variable instanceof Resource) analyzeAsResource(variable);
		else if (mustBeMeasured((Attribute) variable) && parameter.getMeasure() == null)
			results.put(parameter, new AnnotateAndFix(ERROR, "Measure missed. Expected " + ((Attribute) variable).getMeasureType()));
	}

	private boolean mustBeMeasured(Attribute attribute) {
		return ((DOUBLE.equals(attribute.getType()) && attribute.getMeasureType() != null) || MEASURE.equals(attribute.getType()));
	}

	private boolean checkAsTuple(int parametersLength, Variable variable) {
		if (!Attribute.class.isInstance(variable) || variable.getType().equals(DOUBLE)) return false;
		Attribute attribute = (Attribute) variable;
		return attribute.getCount() != 0 && parametersLength != attribute.getCount();
	}

	private List<Variable> getAllowedFacet(Node node, String name, String context) {
		FacetTarget target = node.getObject().getAllowedFacetByContext(name, context);
		return target != null ? target.getVariables() : null;
	}

	private Variable getVariableByName(List<Variable> variables, String name) {
		for (Variable variable : variables)
			if (variable.getName().equals(name)) return variable;
		return null;
	}

	private void processAsWordOrReference(PsiElement parameterValue, Variable variable) {
		if (variable instanceof Word) {
			if (!isCorrectWord(((Word) variable), parameterValue.getFirstChild().getText()))
				results.put(parameterValue, new AnnotateAndFix(ERROR, "Parameter type error. Expected " + variable.getType()));
		} else if (variable instanceof Reference)
			checkAsReference(parameterValue.getChildren(), (Reference) variable);
		else
			results.put(parameterValue, new AnnotateAndFix(ERROR, "Parameter type error. Expected " + variable.getType()));
	}

	private boolean isCorrectWord(Word word, String value) {
		return word.contains(value);
	}

	private void checkAsReference(PsiElement[] parameterValues, Reference reference) {
		Concept scope = TaraUtil.findScope(node, concept);
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
				if (value instanceof TaraIdentifierReference && scope != null && !inScope((TaraIdentifierReference) value, scope))
					results.put(value, new AnnotateAndFix(ERROR, "Bad referenced. The reference has to be in the scope: " + scope.getName()));
		}
	}

	private boolean inScope(TaraIdentifierReference reference, Concept scope) {
		Concept conceptReference = ReferenceManager.resolveToConcept(reference);
		return conceptReference.getQualifiedName().startsWith(scope.getQualifiedName() + ".");
	}


	private boolean checkWellReference(TaraIdentifierReference reference, Reference variable) {
		Concept destiny = ReferenceManager.resolveToConcept(reference);
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

	private boolean checkInHierarchy(String name, String type) {
		Node referenceNode = model.get(type);
		return !(name == null || referenceNode == null) && (name.equals(referenceNode.getName())
			|| search(name, referenceNode.getObject()));
	}

	private boolean search(String name, NodeObject referenceNode) {
		List<NodeObject> children = referenceNode.getChildren();
		if (children == null) return false;
		for (NodeObject child : children)
			if (child.getName().equals(name) || search(name, child)) return true;
		return false;
	}

	private boolean areCompatibleTypes(Variable variable, Parameter parameter) {
		String varType = variable.getType();
		if (parameter.getValue() == null) return false;
		Types type = getType(parameter);
		switch (type) {
			case TaraStringValueImpl:
				return asString(variable, varType);
			case TaraBooleanValueImpl:
				return varType.equals(BOOLEAN);
			case TaraNaturalValueImpl:
				return asNatural(parameter, varType);
			case TaraIntegerValueImpl:
				return asInteger(parameter, varType);
			case TaraDoubleValueImpl:
				return asDouble(parameter, varType);
			default:
				return false;
		}
	}

	private boolean asString(Variable variable, String varType) {
		return varType.equals(STRING) || varType.equals(DATE) || variable instanceof Resource;
	}

	private boolean asNatural(Parameter parameter, String varType) {
		return varType.equals(NATURAL) || varType.equals(INTEGER) || varType.equals(DOUBLE) ||
			varType.equals(MEASURE) || (varType.equals(RATIO) && parameter.getMeasure() != null && "%".equals(parameter.getMeasure().getText()));
	}

	private boolean asInteger(Parameter parameter, String varType) {
		return varType.equals(INTEGER) || varType.equals(DOUBLE) || varType.equals(MEASURE) ||
			(varType.equals(RATIO) && parameter.getMeasure() != null && "%".equals(parameter.getMeasure().getText()));
	}

	private boolean asDouble(Parameter parameter, String varType) {
		return varType.equals(DOUBLE) || varType.equals(MEASURE) ||
			(varType.equals(RATIO) && parameter.getMeasure() != null && "%".equals(parameter.getMeasure().getText()));
	}

	private Types getType(Parameter parameter) {
		String parameterType = parameter.getValue().getClass().getSimpleName();
		Types type;
		if (parameterType.equals("TaraParameterValueImpl"))
			type = Types.valueOf(getImplicitValueType((TaraParameterValueImpl) parameter.getValue()));
		else type = Types.valueOf(parameterType);
		return type;
	}

	private String getImplicitValueType(TaraParameterValueImpl value) {
		return value.getFirstChild().getClass().getSimpleName();
	}

	enum Types {
		TaraStringValueImpl, TaraBooleanValueImpl, TaraIntegerValueImpl, TaraDoubleValueImpl, TaraNaturalValueImpl, TaraCodeValueImpl,
		TaraCoordinateValueImpl, TaraNaturalListImpl, TaraDoubleListImpl, TaraIntegerListImpl, TaraBooleanListImpl,
		TaraStringListImpl, TaraDateListImpl, TaraCoordinateListImpl, TaraReferenceListImpl, TaraEmptyImpl, TaraMetaWordImpl, TaraIdentifierReferenceImpl
	}
}
