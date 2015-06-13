package siani.tara.compiler.codegeneration.magritte;

import org.siani.itrules.Adapter;
import org.siani.itrules.model.Frame;
import siani.tara.compiler.model.FacetTarget;
import siani.tara.compiler.model.Node;
import siani.tara.compiler.model.NodeContainer;
import siani.tara.compiler.model.Variable;
import siani.tara.compiler.model.impl.NodeImpl;
import siani.tara.compiler.model.impl.NodeReference;
import siani.tara.compiler.model.impl.VariableReference;
import siani.tara.semantic.model.Primitives;

import java.util.ArrayList;
import java.util.List;

import static siani.tara.compiler.codegeneration.magritte.NameFormatter.getQn;
import static siani.tara.compiler.codegeneration.magritte.NameFormatter.getQnOfFacet;
import static siani.tara.semantic.model.Variable.NATIVE_SEPARATOR;

public class MorphFacetTargetAdapter implements Adapter<FacetTarget>, TemplateTags {
	private final String project;
	private final String generatedLanguage;
	private int modelLevel;

	public MorphFacetTargetAdapter(String project, String generatedLanguage, int modelLevel) {
		this.project = project;
		this.generatedLanguage = generatedLanguage;
		this.modelLevel = modelLevel;
	}

	@Override
	public void execute(Frame frame, FacetTarget target, FrameContext<FacetTarget> context) {
		frame.addTypes("nodeimpl");
		addFacetTargetInfo(target, frame);
		addInner(target, frame, context);
	}

	private void addFacetTargetInfo(FacetTarget target, Frame frame) {
		addName(target, frame);
		addParent(target, frame);
		addVariables(target, frame);
		addComponents(target, frame);
	}

	private void addInner(FacetTarget target, Frame frame, FrameContext<FacetTarget> context) {
		for (Node inner : target.getIncludedNodes()) {
			if (inner instanceof NodeReference || inner.isAnonymous()) continue;
			frame.addFrame("node", context.build(inner));
		}
	}

	private void addName(FacetTarget node, Frame frame) {
		frame.addFrame(NAME, ((Node) node.getContainer()).getName() + "_" + node.getTargetNode().getName());
		frame.addFrame(QN, node.getTargetNode().getQualifiedName()).addFrame(PROJECT, project);
	}

	private void addParent(FacetTarget target, Frame newFrame) {
		NodeContainer nodeContainer = target.getContainer();
		newFrame.addFrame(PARENT, generatedLanguage.toLowerCase() + "." + getQnOfFacet((Node) nodeContainer));
	}

	protected void addVariables(FacetTarget target, final Frame frame) {
		target.getVariables().stream().
			filter(variable -> !variable.isInherited()).
			forEach(variable -> frame.addFrame(VARIABLE, createVarFrame(variable)));
	}

	private void addComponents(FacetTarget target, Frame frame) {
		for (Node include : target.getIncludedNodes()) {
			if (include.isAnonymous()) continue;
			Frame includeFrame = new Frame().addTypes(collectReferenceTypes(include));
			if (isDefinition(include) && !isDefinition(target.getTargetNode())) includeFrame.addFrame(DEFINITION, "");
			if (!isDefinition(target.getTargetNode())) includeFrame.addFrame(DEFINITION_AGGREGABLE, "");
			includeFrame.addFrame(GENERATED_LANGUAGE, generatedLanguage.toLowerCase());
			if (include instanceof NodeReference) {
				if (!((NodeReference) include).isHas() || include.isAnonymous()) continue;
				addNodeReferenceName((NodeReference) include, includeFrame);
			}
			if (include instanceof NodeImpl)
				addName(include, includeFrame);
			frame.addFrame(COMPONENT, includeFrame);
		}
	}

	protected Frame createVarFrame(final Variable variable) {
		Frame frame = new Frame() {
			{
				addFrame(NAME, variable.getName());
				addFrame(GENERATED_LANGUAGE, generatedLanguage.toLowerCase());
				addFrame(CONTRACT, format(variable.getContract()));
				addFrame(TYPE, getType());
				if (variable.getType().equals(Variable.WORD))
					addFrame(WORDS, variable.getAllowedValues().toArray(new String[(variable.getAllowedValues().size())]));
				else if (variable.getType().equals(Primitives.MEASURE)) {
//					addFrame(MEASURE_TYPE, ((Attribute) variable).getMeasureType());
//					if (((Attribute) variable).getMeasureValue() != null)
//						addFrame(MEASURE_VALUE, resolveMetric(((Attribute) variable).getMeasureValue()));
				} else if (variable.getType().equals(Primitives.NATIVE)) {
					final NativeExtractor nativeExtractor = new
						NativeExtractor(variable.getContract().substring(0, variable.getContract().indexOf(NATIVE_SEPARATOR)),
						variable.getName(), variable.getContract().substring(variable.getContract().indexOf(NATIVE_SEPARATOR) + 1));
					addFrame("parameters", nativeExtractor.parameters());
					addFrame("interfaceName", nativeExtractor.interfaceName());
					addFrame("methodName", nativeExtractor.methodName());
					addFrame("returnValue", nativeExtractor.returnValue());
					String scope = "";
					if (isDefinition((Node) variable.getContainer())) scope = "scope";
					else if (!variable.isTerminal()) scope = "node";
					addFrame("scope", scope);
				}
			}

			private String format(String contract) {
				if (contract == null) return "";
				final int i = contract.indexOf(siani.tara.semantic.model.Variable.NATIVE_SEPARATOR);
				return (i >= 0) ? contract.substring(0, i) : contract;
			}

			private String getType() {
				if (variable.getType().equalsIgnoreCase(Primitives.NATURAL)) return Primitives.INTEGER;
				if (variable instanceof VariableReference)
					return getQn(((VariableReference) variable).getDestiny(), generatedLanguage);
				else return variable.getType();
			}
		};
		return frame.addTypes(MorphCreatorHelper.getTypes(variable, modelLevel));
	}

	private boolean isDefinition(Node node) {
		return !node.isTerminal() && (node.isFeature() || isInFeature(node.getContainer()));
	}

	private boolean isInFeature(NodeContainer node) {
		NodeContainer nodeContainer = node;
		while (nodeContainer != null && nodeContainer instanceof Node)
			if (((Node) nodeContainer).isFeature() || ((Node) nodeContainer).isTerminal()) return true;
			else nodeContainer = nodeContainer.getContainer();
		return false;
	}

	private void addName(Node node, Frame frame) {
		if (node.getName() != null && !node.getName().isEmpty())
			frame.addFrame(NAME, node.isAnonymous() ? node.getType() : node.getName());
		frame.addFrame(QN, node.getQualifiedName()).addFrame(PROJECT, project);
	}


	private void addNodeReferenceName(NodeReference node, Frame frame) {
		NodeImpl reference = node.getDestiny();
		frame.addFrame(NAME, reference.getName());
		frame.addFrame(QN, getQn(reference, generatedLanguage));
		frame.addFrame(PROJECT, project);
	}

	private String[] collectReferenceTypes(Node node) {
		List<String> types = new ArrayList<>();
		types.add("nodeReference");
		if (node.isSingle()) types.add("single");
		if (node.isRequired()) types.add("required");
		if (node.isFeature()) types.add("feature");
		return types.toArray(new String[types.size()]);
	}


}
