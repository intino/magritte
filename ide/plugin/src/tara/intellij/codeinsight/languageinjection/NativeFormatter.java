package tara.intellij.codeinsight.languageinjection;

import com.intellij.openapi.module.Module;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.siani.itrules.model.Frame;
import tara.Language;
import tara.dsl.Proteo;
import tara.intellij.codeinsight.languageinjection.helpers.QualifiedNameFormatter;
import tara.intellij.codeinsight.languageinjection.helpers.TemplateTags;
import tara.intellij.codeinsight.languageinjection.imports.Imports;
import tara.intellij.lang.psi.TaraRuleContainer;
import tara.intellij.lang.psi.TaraVariable;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.module.ModuleProvider;
import tara.lang.model.*;
import tara.lang.model.rules.variable.NativeReferenceRule;
import tara.lang.model.rules.variable.NativeRule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Collections.emptySet;
import static tara.intellij.codeinsight.languageinjection.helpers.QualifiedNameFormatter.cleanQn;
import static tara.intellij.codeinsight.languageinjection.helpers.QualifiedNameFormatter.getQn;
import static tara.intellij.lang.LanguageManager.JSON;
import static tara.intellij.lang.psi.resolve.ReferenceManager.resolveRule;
import static tara.lang.model.Tag.Feature;
import static tara.lang.model.Tag.Instance;

@SuppressWarnings("Duplicates")
public class NativeFormatter implements TemplateTags {

	private Set<String> imports = new HashSet<>();
	private final Imports allImports;
	private final String generatedLanguage;
	private final Language language;
	private final boolean m0;

	NativeFormatter(Module module, String generatedLanguage, Language language) {
		this.generatedLanguage = generatedLanguage;
		allImports = new Imports(module.getProject());
		this.language = language;
		this.m0 = isM0(module);
	}

	void fillFrameForNativeVariable(Frame frame, Variable variable) {
		final TaraRuleContainer ruleContainer = ((TaraVariable) variable).getRuleContainer();
		if (ruleContainer == null || ruleContainer.getRule() == null) return;
		PsiElement nativeInterface = resolveRule(ruleContainer.getRule());
		if (nativeInterface == null) return;
		imports.addAll(collectImports((tara.intellij.lang.psi.Valued) variable));
		imports.addAll(collectImports((PsiClass) nativeInterface));
		frame.addFrame(IMPORTS, imports.toArray(new String[imports.size()]));
		frame.addFrame(NAME, variable.name());
		frame.addFrame(SIGNATURE, NativeFormatter.getSignature((PsiClass) nativeInterface));
		frame.addFrame(GENERATED_LANGUAGE, generatedLanguage.toLowerCase());
		frame.addFrame(NATIVE_CONTAINER, cleanQn(NativeFormatter.buildContainerPath((NativeRule) variable.rule(), variable.container(), language, generatedLanguage)));
		if (!(language instanceof Proteo)) frame.addFrame(LANGUAGE, language.languageName());
		if (ruleContainer.getRule() != null) frame.addFrame(RULE, ruleContainer.getRule().getText());
		final String aReturn = NativeFormatter.getReturn((PsiClass) nativeInterface, variable.values().get(0).toString());
		if (!aReturn.isEmpty()) frame.addFrame(RETURN, aReturn);
	}

	void fillFrameForNativeParameter(Frame frame, Parameter parameter, String body) {
		if (parameter.rule() == null) return;
		final String signature = NativeFormatter.getSignature(parameter);
		final List<String> imports = ((NativeRule) parameter.rule()).imports();
		imports.addAll(collectImports((tara.intellij.lang.psi.Valued) parameter));
		frame.addFrame(IMPORTS, imports.toArray(new String[imports.size()]));
		frame.addFrame(NAME, parameter.name());
		frame.addFrame(GENERATED_LANGUAGE, generatedLanguage.toLowerCase());
		frame.addFrame(NATIVE_CONTAINER, cleanQn(NativeFormatter.buildContainerPath((NativeRule) parameter.rule(), parameter.container(), language, generatedLanguage)));
		if (!(language instanceof Proteo)) frame.addFrame(LANGUAGE, getLanguageScope(parameter, language));
		if (signature != null) frame.addFrame(SIGNATURE, signature);
		final String anInterface = NativeFormatter.getInterface(parameter);
		if (anInterface != null) frame.addFrame(RULE, cleanQn(anInterface));
		if (signature != null) {
			final String aReturn = NativeFormatter.getReturn(body, signature);
			if (!aReturn.isEmpty()) frame.addFrame(RETURN, aReturn);
		}
	}

	void fillFrameExpressionVariable(Frame frame, Variable variable, String body) {
		final List<String> imports = new ArrayList<>(collectImports((tara.intellij.lang.psi.Valued) variable));
		frame.addFrame(NAME, variable.name());
		frame.addFrame(IMPORTS, imports.toArray(new String[imports.size()]));
		frame.addFrame(GENERATED_LANGUAGE, generatedLanguage);
		frame.addFrame(NATIVE_CONTAINER, buildContainerPathOfExpression(variable, generatedLanguage, m0));
		frame.addFrame(TYPE, variable.isReference() ? QualifiedNameFormatter.getQn(variable.destinyOfReference(), generatedLanguage, false) : variable.type().javaName());
		frame.addFrame(RETURN, NativeFormatter.getReturn(body));
	}

	void fillFrameExpressionParameter(Frame frame, Parameter parameter, String body) {
		final List<String> imports = new ArrayList<>(collectImports((tara.intellij.lang.psi.Valued) parameter));
		frame.addTypes(NATIVE);
		frame.addFrame(NAME, parameter.name());
		frame.addFrame(IMPORTS, imports.toArray(new String[imports.size()]));
		frame.addFrame(GENERATED_LANGUAGE, generatedLanguage);
		frame.addFrame(NATIVE_CONTAINER, buildContainerPathOfExpression(parameter, language, generatedLanguage));
		frame.addFrame(TYPE, parameter.type().equals(Primitive.REFERENCE) ? referenceType(parameter) : parameter.type().javaName());
		frame.addFrame(RETURN, NativeFormatter.getReturn(body));
	}

	private Set<String> collectImports(tara.intellij.lang.psi.Valued valued) {
		final NodeContainer containerOf = TaraPsiImplUtil.getContainerOf(valued);
		if (containerOf == null || allImports.get(importsFile(valued)) == null ||
			!allImports.get(importsFile(valued)).containsKey(composeQn(valued, containerOf)))
			return emptySet();
		else {
			final Set<String> set = allImports.get(importsFile(valued)).get(composeQn(valued, containerOf));
			return set == null ? emptySet() : set;
		}
	}

	@NotNull
	private String importsFile(tara.intellij.lang.psi.Valued valued) {
		final String moduleName = ModuleProvider.getModuleOf(valued).getName();
		return moduleName + (TaraUtil.isDefinitionFile(valued.getContainingFile()) ? "" : "_model") + JSON;
	}

	private Set<String> collectImports(PsiClass nativeInterface) {
		if (nativeInterface.getDocComment() == null) return emptySet();
		final String[] lines = nativeInterface.getDocComment().getText().split("\n");
		Set<String> set = new HashSet<>();
		for (String line : lines)
			if (line.contains("import "))
				set.add(line.trim().startsWith("*") ? line.trim().substring(1).trim() : line.trim());
		return set;
	}

	private String composeQn(tara.intellij.lang.psi.Valued valued, NodeContainer containerOf) {
		return containerOf.qualifiedName() + "." + valued.name();
	}


	private static String getLanguageScope(Parameter parameter, Language language) {
		final NativeRule rule = (NativeRule) parameter.rule();
		if (rule != null && !rule.getLanguage().isEmpty()) return rule.getLanguage();
		else return language.languageName();
	}

	private String referenceType(Parameter parameter) {
		if (parameter.rule() instanceof NativeReferenceRule)
			return generatedLanguage.toLowerCase() + DOT + ((NativeReferenceRule) parameter.rule()).allowedTypes().get(0);
		return "";

	}

	private static String buildContainerPathOfExpression(Variable variable, String generatedLanguage, boolean m0) {
		if (variable.container() instanceof Node)
			return getQn(firstNoFeatureAndNamed(variable.container()), (Node) variable.container(), generatedLanguage, m0);
		return getQn((FacetTarget) variable.container(), generatedLanguage);
	}

	private static String buildContainerPathOfExpression(Parameter parameter, Language language, String generatedLanguage) {
		if (parameter.container() instanceof Node && parameter.rule() instanceof NativeRule)
			return buildExpressionContainerPath((NativeRule) parameter.rule(), parameter.container(), language, generatedLanguage);
		return "";
	}

	public static String getSignature(Parameter parameter) {
		final NativeRule rule = (NativeRule) parameter.rule();
		return rule != null ? rule.signature() : null;
	}


	private static String getInterface(Parameter parameter) {
		final NativeRule rule = (NativeRule) parameter.rule();
		if (rule == null)
			return null;//throw new SemanticException(new SemanticError("reject.native.signature.notfound", new LanguageParameter(parameter)));
		return rule.interfaceClass();
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
			if (scope.facetTarget() != null)
				return getQn(scope.facetTarget(), scope, generatedLanguage);
			return getQn(scope, (Node) owner, generatedLanguage, false);
		} else if (owner instanceof Node)
			return getQn(((Node) owner).facetTarget(), (Node) owner, generatedLanguage);
		else if (owner instanceof Facet) {
			final Node parent = firstNoFeatureAndNamed(owner);
			if (parent == null) return "";
			return parent.is(Instance) ? getTypeAsScope(parent, language.languageName()) : getQn(parent, generatedLanguage, false);
		} else return "";
	}

	private static String buildExpressionContainerPath(NativeRule rule, NodeContainer owner, Language language, String generatedLanguage) {
		final String ruleLanguage = extractLanguageScope(rule, generatedLanguage);
		if (owner instanceof Node) {
			final Node scope = ((Node) owner).is(Instance) ? firstNoFeature(owner) : firstNoFeatureAndNamed(owner);
			if (scope == null) return "";
			if (scope.is(Instance)) return getTypeAsScope(scope, ruleLanguage);
			else return getQn(scope, (Node) owner, generatedLanguage, false);
		} else if (owner instanceof FacetTarget)
			return getQn((FacetTarget) owner, generatedLanguage);
		else if (owner instanceof Facet) {
			final Node parent = firstNoFeatureAndNamed(owner);
			if (parent == null) return "";
			return parent.is(Instance) ? getTypeAsScope(parent, language.languageName()) : getQn(parent, generatedLanguage, false);
		} else return "";
	}

	private static String getTypeAsScope(Node scope, String language) {
		return language.toLowerCase() + QualifiedNameFormatter.DOT + cleanQn(scope.type());
	}

	private static String extractLanguageScope(NativeRule rule, String language) {
		return rule != null && !rule.getLanguage().isEmpty() ? rule.getLanguage() : language;
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

	public static String getSignature(PsiClass nativeInterface) {
		if (nativeInterface.getMethods().length == 0) return "void NoSignatureFound()";
		final String text = nativeInterface.getMethods()[0].getText();
		return text.substring(0, text.length() - 1);
	}

	private static String getReturn(PsiClass nativeInterface, String body) {
		if (nativeInterface.getAllFields().length == 0) return "";
		if (body.isEmpty()) return body;
		body = body.endsWith(";") || body.endsWith("}") ? body : body + ";";
		if (!(nativeInterface.getMethods()[0].getReturnType() == null) && !body.contains("\n") && body.split(";").length == 1 && !body.startsWith(RETURN))
			return RETURN + " ";
		return "";
	}

	private static String getReturn(String body, String signature) {
		final String returnText = RETURN + " ";
		body = body.endsWith(";") || body.endsWith("}") ? body : body + ";";
		if (!signature.contains(" void ") && !body.contains("\n") && !body.startsWith(returnText))
			return returnText;
		return "";
	}

	private static String getReturn(String body) {
		final String returnText = RETURN + " ";
		body = body.endsWith(";") || body.endsWith("}") ? body : body + ";";
		if (!body.contains("\n") && !body.startsWith(returnText))
			return returnText;
		return "";
	}

	private boolean isM0(Module module) {
		final TaraFacet facet = TaraFacet.of(module);
		return facet != null && facet.getConfiguration().isM0();
	}

}
