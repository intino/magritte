package tara.intellij.codeinsight.languageinjection;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import org.siani.itrules.model.Frame;
import tara.Language;
import tara.dsl.Proteo;
import tara.intellij.codeinsight.languageinjection.helpers.Format;
import tara.intellij.codeinsight.languageinjection.helpers.NameFormatter;
import tara.intellij.codeinsight.languageinjection.helpers.TemplateTags;
import tara.intellij.lang.psi.TaraRuleContainer;
import tara.intellij.lang.psi.TaraVariable;
import tara.lang.model.*;
import tara.lang.model.rules.variable.NativeRule;

import java.util.*;

import static tara.dsl.Proteo.FACET;
import static tara.dsl.Proteo.METAFACET;
import static tara.intellij.lang.psi.resolve.ReferenceManager.resolveRule;
import static tara.lang.model.Tag.Feature;
import static tara.lang.model.Tag.Instance;

@SuppressWarnings("Duplicates")
public class NativeFormatter implements TemplateTags {

	private Set<String> imports = new HashSet<>();

	private final String generatedLanguage;
	private final Language language;
	private final boolean m0;

	public NativeFormatter(String generatedLanguage, Language language, boolean m0) {
		this.generatedLanguage = generatedLanguage;
		this.language = language;
		this.m0 = m0;
		imports.add("import tara.magritte.Layer;");
	}

	public void fillFrameForNativeVariable(Frame frame, Variable variable) {
		final TaraRuleContainer ruleContainer = ((TaraVariable) variable).getRuleContainer();
		if (ruleContainer == null || ruleContainer.getRule() == null) return;
		PsiElement nativeInterface = resolveRule(ruleContainer.getRule());
		if (nativeInterface == null) return;
		imports.addAll(collectImports((PsiClass) nativeInterface));
		frame.addFrame(IMPORTS, imports.toArray(new String[imports.size()]));
		frame.addFrame(NAME, variable.name());
		frame.addFrame(SIGNATURE, NativeFormatter.getSignature((PsiClass) nativeInterface));
		frame.addFrame(GENERATED_LANGUAGE, generatedLanguage.toLowerCase());
		frame.addFrame(NATIVE_CONTAINER, cleanQn(NativeFormatter.buildContainerPath((NativeRule) variable.rule(), variable.container(), language, generatedLanguage)));
		if (!(language instanceof Proteo)) frame.addFrame(LANGUAGE, language.languageName());
		if (ruleContainer.getRule() != null) frame.addFrame(RULE, ruleContainer.getRule().getText());
		frame.addFrame(RETURN, NativeFormatter.getReturn((PsiClass) nativeInterface, variable.values().get(0).toString()));
	}

	private Set<String> collectImports(PsiClass nativeInterface) {
		if (nativeInterface.getDocComment() == null) return Collections.emptySet();
		final String[] lines = nativeInterface.getDocComment().getText().split("\n");
		Set<String> set = new HashSet<>();
		for (String line : lines)
			if (line.contains("import ")) set.add(line.trim().startsWith("*") ? line.trim().substring(1).trim() : line.trim());
		return set;
	}

	public void fillFrameForNativeParameter(Frame frame, Parameter parameter, String body) {
		final String signature = NativeFormatter.getSignature(parameter);
		final List<String> imports = ((NativeRule) parameter.rule()).imports();
		frame.addFrame(IMPORTS, imports.toArray(new String[imports.size()]));
		frame.addFrame(NAME, parameter.name());
		frame.addFrame(GENERATED_LANGUAGE, generatedLanguage.toLowerCase());
		frame.addFrame(NATIVE_CONTAINER, cleanQn(NativeFormatter.buildContainerPath((NativeRule) parameter.rule(), parameter.container(), language, generatedLanguage)));
		if (!(language instanceof Proteo)) frame.addFrame(LANGUAGE, NativeFormatter.getLanguageScope(parameter, language));
		if (signature != null) frame.addFrame(SIGNATURE, signature);
		final String anInterface = NativeFormatter.getInterface(parameter);
		if (anInterface != null) frame.addFrame(RULE, cleanQn(anInterface));
		if (signature != null) frame.addFrame(RETURN, NativeFormatter.getReturn(body, signature));
	}

	public void fillFrameExpressionVariable(Frame frame, Variable variable, String body) {
		frame.addFrame(NAME, variable.name());
		frame.addFrame(GENERATED_LANGUAGE, generatedLanguage).addFrame("varName", variable.name());
		frame.addFrame(NATIVE_CONTAINER, buildContainerPathOfExpression(variable.container(), generatedLanguage, m0));
		frame.addFrame(TYPE, variable.type().javaName());
		frame.addFrame(RETURN, NativeFormatter.getReturn(body));
	}


	public void fillFrameExpressionParameter(Frame frame, Parameter parameter, String body) {
		final String type = parameter.inferredType().javaName();
		final String signature = "public " + type + " value()";
		final String aPackage = calculatePackage(parameter.container()).toLowerCase();
		frame.addFrame(NAME, parameter.name());
		Frame nativeFrame = new Frame().addTypes(NATIVE).addFrame("body", formatBody(body, signature));
		nativeFrame.addFrame(GENERATED_LANGUAGE, generatedLanguage).addFrame("varName", parameter.name());
		nativeFrame.addFrame(CONTAINER, NameFormatter.cleanQn(buildExpressionContainerPath(parameter.container(), language, generatedLanguage)));
		if (!aPackage.isEmpty()) nativeFrame.addFrame(PACKAGE, aPackage);
		nativeFrame.addFrame(INTERFACE, "magritte.Expression<" + type + ">");
		nativeFrame.addFrame(SIGNATURE, signature);
		nativeFrame.addFrame(CLASS_NAME, parameter.name() + "_" + parameter.getUID());
		frame.addFrame(NATIVE, nativeFrame);
	}

	public static String getLanguageScope(Parameter parameter, Language language) {
		final NativeRule rule = (NativeRule) parameter.rule();
		if (rule != null) return rule.getLanguage();
		else return language.languageName();
	}

	private static String getQn(Node owner, String language, boolean m0) {
		return asNode(owner, language, m0, null);
	}

	private static String getQn(Node owner, Node node, String language, boolean m0) {
		final FacetTarget facetTarget = facetTargetContainer(node);
		if ((owner.type().equals(FACET) || owner.metaTypes().contains(METAFACET)) && facetTarget != null)
			return asFacetTarget(owner, language, facetTarget);
		else return asNode(owner, language, m0, facetTarget);
	}

	private static String cleanQn(String qualifiedName) {
		return qualifiedName.replace(Node.ANNONYMOUS, "").replace("[", "").replace("]", "");
	}

	private static String asNode(Node node, String language, boolean m0, FacetTarget facetTarget) {
		return !m0 ? language.toLowerCase() + DOT + (facetTarget == null ? node.qualifiedName() : NameFormatter.composeInFacetTargetQN(node, facetTarget)) :
			language.toLowerCase() + DOT + node.type();
	}

	private static String asFacetTarget(Node owner, String language, FacetTarget facetTarget) {
		return language.toLowerCase() + DOT + owner.name().toLowerCase() + DOT +
			Format.reference().format(owner.name()) + Format.reference().format(facetTarget.target());
	}

	public static String getSignature(Parameter parameter) {
		final NativeRule rule = (NativeRule) parameter.rule();
		return rule != null ? rule.signature() : null;
	}

	public static String getInterface(Parameter parameter) {
		final NativeRule rule = (NativeRule) parameter.rule();
		if (rule == null)
			return null;//throw new SemanticException(new SemanticError("reject.native.signature.notfound", new LanguageParameter(parameter)));
		return rule.interfaceClass();
	}


	public static String buildContainerPathOfExpression(NodeContainer owner, String generatedLanguage, boolean m0) {
		if (owner instanceof Node)
			return getQn(firstNoFeatureAndNamed(owner), (Node) owner, generatedLanguage, m0);
		return NameFormatter.getQn((FacetTarget) owner, generatedLanguage);
	}

	public static String formatBody(String body, String signature) {
		final String returnText = "return ";
		String formattedBody = body.endsWith(";") || body.endsWith("}") ? body : body + ";";
		if (!signature.contains(" void ") && !formattedBody.contains("\n") && !formattedBody.startsWith(returnText))
			return returnText + formattedBody;
		return formattedBody;
	}

	public static String getSignature(Variable variable) {
		return ((NativeRule) variable.rule()).signature();
	}

	public static String buildContainerPath(NativeRule rule, NodeContainer owner, Language language, String generatedLanguage) {
		final String ruleLanguage = extractLanguageScope(rule, generatedLanguage);
		if (owner instanceof Node && ((Node) owner).facetTarget() == null) {
			final Node scope = ((Node) owner).is(Instance) ? firstNoFeature(owner) : firstNoFeatureAndNamed(owner);
			if (scope == null) return "";
			if (scope.is(Instance)) return getTypeAsScope(scope, ruleLanguage);
			if (scope.facetTarget() != null) return NameFormatter.getQn(scope.facetTarget(), scope, generatedLanguage);
			return getQn(scope, (Node) owner, generatedLanguage, false);
		} else if (owner instanceof Node) return NameFormatter.getQn(((Node) owner).facetTarget(), (Node) owner, generatedLanguage);
		else if (owner instanceof Facet) {
			final Node parent = firstNoFeatureAndNamed(owner);
			if (parent == null) return "";
			return parent.is(Instance) ? getTypeAsScope(parent, language.languageName()) : getQn(parent, ruleLanguage, false);
		} else return "";
	}

	public static String buildExpressionContainerPath(NodeContainer owner, Language language, String generatedLanguage) {
		if (owner instanceof Node) {
			final Node scope = ((Node) owner).is(Instance) ? firstNoFeature(owner) : firstNoFeatureAndNamed(owner);
			if (scope == null) return "";
			if (scope.is(Instance))
				return getTypeAsScope(scope, language instanceof Proteo ? generatedLanguage : language.languageName());
			else return getQn(scope, (Node) owner, generatedLanguage, false);
		} else if (owner instanceof FacetTarget)
			return NameFormatter.getQn((FacetTarget) owner, generatedLanguage);
		else if (owner instanceof Facet) {
			final Node parent = firstNoFeatureAndNamed(owner);
			if (parent == null) return "";
			return parent.is(Instance) ? getTypeAsScope(parent, language.languageName()) : getQn(parent, generatedLanguage, false);
		} else return "";
	}

	private static String getTypeAsScope(Node scope, String language) {
		return language.toLowerCase() + NameFormatter.DOT + NameFormatter.cleanQn(scope.type());
	}

	private static String extractLanguageScope(NativeRule rule, String language) {
		return rule != null ? rule.getLanguage() : language;
	}

	private static Node firstNoFeature(NodeContainer owner) {
		NodeContainer container = owner;
		while (container != null) {
			if (container instanceof Node && !(container instanceof NodeRoot) && !((Node) container).is(Feature))
				return (Node) container;
			container = container.container();
		}
		return null;
	}

	private static Node firstNoFeatureAndNamed(NodeContainer owner) {
		NodeContainer container = owner;
		while (container != null) {
			if (container instanceof Node && !(container instanceof NodeRoot) && !((Node) container).isAnonymous() &&
				!((Node) container).is(Feature))
				return (Node) container;
			container = container.container();
		}
		return null;
	}

	private static FacetTarget facetTargetContainer(Node node) {
		NodeContainer container = node.container();
		while (container != null) if (container instanceof FacetTarget) return (FacetTarget) container;
		else container = container.container();
		return null;
	}

	public static String calculatePackage(NodeContainer container) {
		final NodeContainer nodeContainer = firstNamedContainer(container);
		return nodeContainer == null ? "" : nodeContainer.qualifiedNameCleaned().replace("$", ".").toLowerCase();
	}

	private static NodeContainer firstNamedContainer(NodeContainer container) {
		List<NodeContainer> containers = collectStructure(container);
		NodeContainer candidate = null;
		for (NodeContainer nodeContainer : containers) {
			if (nodeContainer instanceof Node && !((Node) nodeContainer).isAnonymous()) candidate = nodeContainer;
			else if (nodeContainer instanceof Node) break;
			else candidate = nodeContainer;
		}
		return candidate;
	}

	private static List<NodeContainer> collectStructure(NodeContainer container) {
		List<NodeContainer> containers = new ArrayList<>();
		NodeContainer current = container;
		while (current != null && !(current instanceof NodeRoot)) {
			containers.add(0, current);
			current = current.container();
		}
		return containers;
	}

	public static String getSignature(PsiClass nativeInterface) {
		if (nativeInterface.getMethods().length == 0) return "void NoSignatureFound()";
		final String text = nativeInterface.getMethods()[0].getText();
		return text.substring(0, text.length() - 1);
	}

	public static String getReturn(PsiClass nativeInterface, String body) {
		if (nativeInterface.getAllFields().length == 0) return "";
		if (body.isEmpty()) return body;
		body = body.endsWith(";") || body.endsWith("}") ? body : body + ";";
		if (!(nativeInterface.getMethods()[0].getReturnType() == null) && !body.contains("\n") && body.split(";").length == 1 && !body.startsWith(RETURN))
			return RETURN + " ";
		return "";
	}

	public static String getReturn(String body, String signature) {
		final String returnText = RETURN + " ";
		body = body.endsWith(";") || body.endsWith("}") ? body : body + ";";
		if (!signature.contains(" void ") && !body.contains("\n") && !body.startsWith(returnText))
			return returnText;
		return "";
	}

	public static String getReturn(String body) {
		final String returnText = RETURN + " ";
		body = body.endsWith(";") || body.endsWith("}") ? body : body + ";";
		if (!body.contains("\n") && !body.startsWith(returnText))
			return returnText;
		return "";
	}

}
