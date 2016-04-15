package tara.compiler.codegeneration.magritte.layer;

import org.siani.itrules.Template;
import org.siani.itrules.model.Frame;
import tara.Language;
import tara.Resolver;
import tara.compiler.codegeneration.Format;
import tara.compiler.codegeneration.magritte.Generator;
import tara.compiler.codegeneration.magritte.NameFormatter;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.compiler.model.Model;
import tara.dsl.Proteo;
import tara.lang.model.Node;
import tara.lang.model.Variable;
import tara.lang.model.rules.CompositionRule;

import java.util.Collection;
import java.util.stream.Collectors;

import static tara.lang.model.Tag.*;

public class GraphWrapperCreator extends Generator implements TemplateTags {

	private final int modelLevel;
	private final boolean dynamicLoad;

	public GraphWrapperCreator(Language language, String generatedLanguage, int modelLevel, boolean dynamicLoad) {
		super(language, generatedLanguage);
		this.modelLevel = modelLevel;
		this.dynamicLoad = dynamicLoad;
	}

	public String create(Model model) {
		Frame frame = new Frame().addTypes("graph");
		frame.addFrame(GENERATED_LANGUAGE, generatedLanguage);
		frame.addFrame(NAME, generatedLanguage);
		collectMainNodes(model).stream().filter(node -> node.name() != null).
			forEach(node -> frame.addFrame(NODE, createRootNodeFrame(node, model.ruleOf(node))));
		return Format.customize(getTemplate()).format(frame);
	}

	private Template getTemplate() {
		return dynamicLoad ? DynamicGraphWrapperTemplate.create() : GraphWrapperTemplate.create();
	}

	private Frame createRootNodeFrame(Node node, CompositionRule rule) {
		Frame frame = new Frame();
		frame.addTypes(NODE);
		if (rule.isSingle()) frame.addTypes(SINGLE);
		if (node.isTerminal()) frame.addTypes(CONCEPT);
		if (node.is(Instance)) frame.addTypes(INSTANCE);
		frame.addFrame(QN, getQn(node));
		addType(node, rule, frame);
		frame.addFrame(NAME, node.name() + (node.facetTarget() != null ? node.facetTarget().targetNode().name() : ""));
		node.variables().stream().filter(variable -> variable.values().isEmpty()).forEach(variable -> createVariable(frame, variable));
		addTerminalVariables(node, frame);
		return frame;
	}

	private void addType(Node node, CompositionRule rule, Frame frame) {
		if (!(language instanceof Proteo)) frame.addFrame(CONCEPT_LAYER, language.doc(node.type()).layer());
		frame.addFrame(TYPE, nodeType(node, rule));
	}

	private String nodeType(Node node, CompositionRule rule) {
		return Resolver.shortType(node.type()) + (!rule.isSingle() ? "List" : "");
	}

	private void createVariable(Frame frame, Variable variable) {
		Frame variableFrame = new Frame();
		variableFrame.addTypes(VARIABLE, variable.type().getName());
		LayerVariableAdapter adapter = new LayerVariableAdapter(language, generatedLanguage, modelLevel);
		adapter.execute(variableFrame, variable, null);
		frame.addFrame(VARIABLE, variableFrame);
	}

	private String getQn(Node node) {
		return node.facetTarget() != null ?
			NameFormatter.getQn(node.facetTarget(), generatedLanguage.toLowerCase()).replace(":", "") :
			NameFormatter.getQn(node, generatedLanguage.toLowerCase()).replace(":", "");
	}

	private Collection<Node> collectMainNodes(Model model) {
		return model.components().stream().filter(n -> !n.is(Component) && !n.into(Component) && !n.is(Feature) && !n.into(Feature)).collect(Collectors.toList());
	}
}
