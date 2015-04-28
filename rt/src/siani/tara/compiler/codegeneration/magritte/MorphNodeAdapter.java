package siani.tara.compiler.codegeneration.magritte;

import org.siani.itrules.Adapter;
import org.siani.itrules.model.Frame;
import siani.tara.Language;
import siani.tara.compiler.model.*;
import siani.tara.compiler.model.impl.NodeImpl;
import siani.tara.compiler.model.impl.NodeReference;
import siani.tara.compiler.model.impl.VariableReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import static siani.tara.compiler.codegeneration.magritte.MorphCreatorHelper.getNodeContainer;
import static siani.tara.compiler.codegeneration.magritte.MorphCreatorHelper.getTypes;
import static siani.tara.compiler.codegeneration.magritte.NameFormatter.composeMorphPackagePath;

public class MorphNodeAdapter implements Adapter<NodeImpl>, TemplateTags {
	private final String project;
	private final String module;
	private final Language language;
	private final Set<String> imports;
	private final Locale locale;
	private Node initNode;

	public MorphNodeAdapter(String project, String module, Language language, Set<String> imports, Locale locale, Node initNode) {
		this.project = project;
		this.module = module;
		this.language = language;
		this.imports = imports;
		this.locale = locale;
		this.initNode = initNode;
	}

	@Override
	public void execute(Frame frame, NodeImpl node, FrameContext context) {
		frame.addTypes(getTypes(node, language));
		addNodeInfo(node, frame);
		addImport(getNodeContainer(node));
		addInner(node, frame, context);
	}

	private void addImport(Node node) {
		if (node != null) {
			String nodePackage = composeMorphPackagePath(node, locale, module);
			if (!nodePackage.equals(MAGRITTE_MORPHS))
				imports.add(nodePackage + DOT + node.getName());
		}
	}

	private void addNodeInfo(Node node, Frame frame) {
		if (initNode != null && !node.equals(initNode)) frame.addFrame(INNER, "");
		if (node.getDoc() != null) frame.addFrame(DOC, node.getDoc());
		addName(node, frame);
		addParent(node, frame);
		addTargets(node, frame);
		addFacets(node, frame);
		addVariables(node, frame);
		addTargets(node, frame);
		addFacets(node, frame);
		addComponents(node, frame);
	}

	private void addName(Node node, Frame frame) {
		if (node.getName() != null && !node.getName().isEmpty())
			frame.addFrame(NAME, node.isAnonymous() ? node.getType() : node.getName());
		frame.addFrame(QN, node.getQualifiedName()).addFrame(PROJECT, project);
	}

	private void addInner(Node node, Frame frame, FrameContext context) {
		for (Node inner : node.getIncludedNodes()) {
			if (inner instanceof NodeReference || inner.isAnonymous()) continue;
			frame.addFrame("node", context.build(inner));
		}
	}

	private void addParent(Node node, Frame newFrame) {
		if (node.getParent() != null) {
			newFrame.addFrame(PARENT, node.getParent().getName().equals(node.getName()) ?
				MAGRITTE_MORPHS + DOT + node.getParent().getQualifiedName() :
				node.getParent().getQualifiedName());
			addImport(node.getParent());
		} else newFrame.addFrame(PARENT, MORPH);
	}

	private void addFacets(Node node, Frame newFrame) {
		for (final Facet facet : node.getFacets())
			newFrame.addFrame(FACETS, new Frame(null).addTypes(getTypes(facet)).addFrame(NAME, facet.getType()));
	}

	private void addComponents(Node node, Frame frame) {
		for (Node include : node.getIncludedNodes()) {
			if (include.isAnonymous()) continue;
			Frame includeFrame = new Frame(null).addTypes(collectReferenceTypes(include));
			if (include instanceof NodeReference) {
				if (!((NodeReference) include).isHas() || include.isAnonymous()) continue;
				addNodeReferenceName((NodeReference) include, includeFrame);
			}
			if (include instanceof NodeImpl)
				addName(include, includeFrame);
			frame.addFrame("component", includeFrame);
		}
	}

	private void addNodeReferenceName(NodeReference node, Frame frame) {
		NodeImpl reference = node.getDestiny();
		frame.addFrame(NAME, reference.getName());
		frame.addFrame(QN, reference.getQualifiedName()).addFrame(PROJECT, project);
	}

	private String[] collectReferenceTypes(Node node) {
		List<String> types = new ArrayList<>();
		types.add("nodeReference");
		if (node.isSingle()) types.add("single");
		if (node.isAggregated()) types.add("aggregated");
		for (Tag tag : node.getFlags()) types.add(tag.getName());
		return types.toArray(new String[types.size()]);
	}

	private void addTargets(Node node, Frame newFrame) {
		for (final FacetTarget target : node.getFacetTargets())
			newFrame.addFrame(TARGETS, new Frame(null).addTypes(getTypes(target)).addFrame(NAME, target.getTarget()));
	}

	protected void addVariables(Node node, final Frame frame) {
		for (final Variable variable : node.getVariables()) {
			Frame varFrame = createVarFrame(variable);
			frame.addFrame(VARIABLE, varFrame);
			if (variable instanceof VariableReference && !variable.getDefaultValues().isEmpty()) {
				addImport(((VariableReference) variable).getDestiny());
			}
		}
	}


	protected Frame createVarFrame(final Variable variable) {
		Frame frame = new Frame(null) {
			{
				addFrame(NAME, variable.getName());
				addFrame(TYPE, getType());
				if (variable.getType().equals(Variable.WORD))
					addFrame(WORDS, variable.getAllowedValues().toArray(new String[(variable.getAllowedValues().size())]));
				else if (variable.getType().equals(Primitives.MEASURE)) {
//					addFrame(MEASURE_TYPE, ((Attribute) variable).getMeasureType());
//					if (((Attribute) variable).getMeasureValue() != null)
//						addFrame(MEASURE_VALUE, resolveMetric(((Attribute) variable).getMeasureValue()));
				}
				if (variable instanceof VariableReference)
					imports.add(((VariableReference) variable).getDestiny().getQualifiedName());
			}

			private String getType() {
				if (variable.getType().equalsIgnoreCase(Primitives.NATURAL)) return Primitives.INTEGER;
				else return variable.getType();
			}
		};
		return frame.addTypes(MorphCreatorHelper.getTypes(variable));
	}

}