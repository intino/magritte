package io.intino.tara.plugin.codeinsight.languageinjection;

import com.intellij.openapi.module.Module;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import io.intino.tara.plugin.codeinsight.languageinjection.helpers.Format;
import io.intino.tara.plugin.codeinsight.languageinjection.helpers.TemplateTags;
import io.intino.tara.plugin.codeinsight.languageinjection.imports.Imports;
import io.intino.tara.plugin.lang.psi.Valued;
import io.intino.tara.plugin.lang.psi.impl.TaraPsiImplUtil;
import io.intino.tara.plugin.lang.psi.impl.TaraUtil;
import io.intino.tara.lang.model.*;
import org.siani.itrules.model.Frame;
import io.intino.tara.Language;
import io.intino.tara.compiler.shared.Configuration;
import io.intino.tara.dsl.Proteo;
import io.intino.tara.dsl.Verso;
import io.intino.tara.plugin.codeinsight.languageinjection.helpers.QualifiedNameFormatter;
import io.intino.tara.plugin.lang.psi.TaraRuleContainer;
import io.intino.tara.plugin.lang.psi.TaraVariable;
import io.intino.tara.lang.model.rules.NativeWordRule;
import io.intino.tara.lang.model.rules.variable.NativeObjectRule;
import io.intino.tara.lang.model.rules.variable.NativeReferenceRule;
import io.intino.tara.lang.model.rules.variable.NativeRule;
import io.intino.tara.lang.semantics.Constraint;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Collections.emptySet;
import static io.intino.tara.compiler.shared.Configuration.Level.Solution;
import static io.intino.tara.plugin.codeinsight.languageinjection.helpers.QualifiedNameFormatter.cleanQn;
import static io.intino.tara.plugin.codeinsight.languageinjection.helpers.QualifiedNameFormatter.getQn;
import static io.intino.tara.plugin.lang.psi.resolve.ReferenceManager.resolveRule;
import static io.intino.tara.lang.model.Tag.Feature;
import static io.intino.tara.lang.model.Tag.Instance;
import static io.intino.tara.lang.model.Primitive.*;

@SuppressWarnings("Duplicates")
public class NativeFormatter implements TemplateTags {

	private Set<String> imports = new HashSet<>();
	private final Imports allImports;
	private final String workingPackage;
	private final Language language;
	private final boolean m0;

	NativeFormatter(Module module, String workingPackage, Language language) {
		this.workingPackage = workingPackage;
		allImports = new Imports(module.getProject());
		this.language = language;
		final Configuration facetConfiguration = TaraUtil.configurationOf(module);
		this.m0 = facetConfiguration != null && Solution.equals(facetConfiguration.level());
	}

	void fillFrameForNativeVariable(Frame frame, Variable variable, boolean isMultiline) {
		final TaraRuleContainer ruleContainer = ((TaraVariable) variable).getRuleContainer();
		if (ruleContainer == null || ruleContainer.getRule() == null) return;
		PsiElement nativeInterface = resolveRule(ruleContainer.getRule());
		if (nativeInterface == null) return;
		imports.addAll(collectImports((io.intino.tara.plugin.lang.psi.Valued) variable));
		imports.addAll(collectImports((PsiClass) nativeInterface));
		frame.addSlot(IMPORTS, imports.toArray(new String[imports.size()]));
		frame.addSlot(NAME, variable.name());
		frame.addSlot(SIGNATURE, getSignature((PsiClass) nativeInterface));
		frame.addSlot(GENERATED_LANGUAGE, workingPackage.toLowerCase());
		frame.addSlot(NATIVE_CONTAINER, cleanQn(buildContainerPath(variable.scope(), variable.container(), workingPackage)));
		if (!(language instanceof Proteo) && !(language instanceof Verso))
			frame.addSlot(LANGUAGE, language.languageName());
		if (ruleContainer.getRule() != null) frame.addSlot(RULE, ruleContainer.getRule().getText());
		final String aReturn = getReturn((PsiClass) nativeInterface, variable.values().get(0).toString());
		if (!aReturn.isEmpty() && !isMultiline) frame.addSlot(RETURN, aReturn);
	}

	void fillFrameForFunctionParameter(Frame frame, Parameter parameter, String body, boolean isMultiLine) {
		if (parameter.rule() == null) return;
		final String signature = getSignature(parameter);
		final List<String> imports = ((NativeRule) parameter.rule()).imports();
		imports.addAll(collectImports((Valued) parameter));
		frame.addSlot(IMPORTS, imports.toArray(new String[imports.size()]));
		frame.addSlot(NAME, parameter.name());
		frame.addSlot(GENERATED_LANGUAGE, workingPackage.toLowerCase());
		frame.addSlot(SCOPE, parameter.scope());
		frame.addSlot(NATIVE_CONTAINER, cleanQn(buildContainerPath(parameter.scope(), parameter.container(), workingPackage)));
		if (!(language instanceof Proteo) && !(language instanceof Verso))
			frame.addSlot(LANGUAGE, getLanguageScope(parameter, language));
		if (signature != null) frame.addSlot(SIGNATURE, signature);
		final String anInterface = getInterface(parameter);
		if (anInterface != null) frame.addSlot(RULE, cleanQn(anInterface));
		if (signature != null) {
			final String aReturn = NativeFormatter.getReturn(body, signature);
			if (!aReturn.isEmpty() && !isMultiLine) frame.addSlot(RETURN, aReturn);
		}
	}

	void fillFrameExpressionVariable(Frame frame, Variable variable, String body, boolean isMultiline) {
		final List<String> imports = new ArrayList<>(collectImports((Valued) variable));
		frame.addSlot(NAME, variable.name());
		frame.addSlot(IMPORTS, imports.toArray(new String[imports.size()]));
		frame.addSlot(GENERATED_LANGUAGE, workingPackage);
		frame.addSlot(NATIVE_CONTAINER, buildContainerPathOfExpression(variable, workingPackage, m0));
		frame.addSlot(TYPE, typeFrame(type(variable), variable.isMultiple()));
		if (!isMultiline) frame.addSlot(RETURN, NativeFormatter.getReturn(body));
	}

	void fillFrameExpressionParameter(Frame frame, Parameter parameter, String body, boolean isMultiline) {
		final List<String> imports = new ArrayList<>(collectImports((Valued) parameter));
		frame.addTypes(NATIVE);
		frame.addSlot(NAME, parameter.name());
		frame.addSlot(IMPORTS, imports.toArray(new String[imports.size()]));
		frame.addSlot(GENERATED_LANGUAGE, workingPackage);
		frame.addSlot(NATIVE_CONTAINER, buildContainerPathOfExpression(parameter, workingPackage));
		frame.addSlot(TYPE, typeFrame(type(parameter), isMultiple(parameter)));
		if (!isMultiline) frame.addSlot(RETURN, NativeFormatter.getReturn(body));
	}

	private boolean isMultiple(Parameter parameter) {
		final Constraint.Parameter constraint = TaraUtil.parameterConstraintOf(parameter);
		return constraint != null && !constraint.size().isSingle();
	}

	private Frame typeFrame(String type, boolean multiple) {
		return multiple ? new Frame().addTypes("type", "list").addSlot("value", type) : new Frame().addTypes("type").addSlot("value", type);
	}

	private String type(Variable variable) {
		if (variable.isReference())
			return QualifiedNameFormatter.getQn(variable.destinyOfReference(), workingPackage, false);
		if (variable.type().equals(WORD)) return wordType(variable);
		else if (OBJECT.equals(variable.type()))
			return variable.rule() == null ? "" : ((NativeObjectRule) variable.rule()).type();
		else return variable.type().javaName();
	}

	private String type(Parameter parameter) {
		if (parameter.type().equals(REFERENCE)) return referenceType(parameter);
		if (parameter.type().equals(WORD)) return wordType(parameter);
		else if (OBJECT.equals(parameter.type())) return ((NativeObjectRule) parameter.rule()).type();
		else return parameter.type().javaName();
	}

	private Set<String> collectImports(Valued valued) {
		final Node containerOf = TaraPsiImplUtil.getContainerNodeOf(valued);
		if (containerOf == null || allImports.get(TaraUtil.importsFile(valued)) == null ||
				!allImports.get(TaraUtil.importsFile(valued)).containsKey(composeQn(valued, containerOf)))
			return emptySet();
		else {
			if (allImports.get(TaraUtil.importsFile(valued)) == null) return emptySet();
			final Set<String> set = allImports.get(TaraUtil.importsFile(valued)).get(composeQn(valued, containerOf));
			return set == null ? emptySet() : set;
		}
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

	private String composeQn(Valued valued, Node containerOf) {
		return containerOf.qualifiedName() + "." + valued.name();
	}


	private static String getLanguageScope(Parameter parameter, Language language) {
		if (!parameter.scope().isEmpty()) return parameter.scope();
		else return language.languageName();
	}

	private String referenceType(Parameter parameter) {
		if (parameter.rule() instanceof NativeReferenceRule)
			return workingPackage.toLowerCase() + DOT + ((NativeReferenceRule) parameter.rule()).allowedTypes().get(0);
		return "";
	}

	private String wordType(Variable variable) {
		return workingPackage.toLowerCase() + DOT + variable.container().qualifiedName() + "." + Format.firstUpperCase().format(variable.name());
	}

	private String wordType(Parameter parameter) {
		if (parameter.rule() instanceof NativeWordRule)
			return workingPackage.toLowerCase() + DOT + ((NativeWordRule) parameter.rule()).words().get(0);
		return "";
	}

	private static String buildContainerPathOfExpression(Variable variable, String outDsl, boolean m0) {
		return getQn(firstNoFeatureAndNamed(variable.container()), variable.container(), outDsl, m0);
	}

	private static String buildContainerPathOfExpression(Parameter parameter, String outDsl) {
		return  buildExpressionContainerPath(parameter.scope(), parameter.container(), outDsl);
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

	public static String buildContainerPath(String scopeLanguage, Node owner, String workingPackage) {
		if (owner != null && owner.facetTarget() == null) {
			final Node scope = owner.is(Instance) ? firstNoFeature(owner) : firstNoFeatureAndNamed(owner);
			if (scope == null) return "";
			if (scope.is(Instance)) return getTypeAsScope(scope, scopeLanguage);
			if (scope.facetTarget() != null) return getQn(scope.facetTarget(), scope, workingPackage);
			return getQn(scope, owner, workingPackage, false);
		} else return getQn(owner.facetTarget(), owner, workingPackage);
	}


	private static String buildExpressionContainerPath(String typeWorkingPackage, Node owner, String workingPackage) {
		final String trueWorkingPackage = extractWorkingPackage(typeWorkingPackage, workingPackage);
		if (owner != null && owner.facetTarget() == null) {
			final Node scope = owner.is(Instance) ? firstNoFeature(owner) : firstNoFeatureAndNamed(owner);
			if (scope == null) return "";
			if (scope.is(Instance)) return getTypeAsScope(scope, trueWorkingPackage);
			if (scope.facetTarget() != null) return getQn(scope.facetTarget(), scope, workingPackage);
			else return getQn(scope, owner, workingPackage, false);
		} else return getQn((FacetTarget) owner, workingPackage);
	}

	private static String extractWorkingPackage(String scope, String language) {
		return scope != null && !scope.isEmpty() ? scope : language;
	}

	private static String getTypeAsScope(Node scope, String language) {
		return language.toLowerCase() + QualifiedNameFormatter.DOT + cleanQn(scope.type());
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
		return owner instanceof Node && ((Node) owner).isAnonymous() ? (Node) owner : TaraPsiImplUtil.getContainerNodeOf((PsiElement) owner);
	}

	public static String getSignature(PsiClass nativeInterface) {
		if (nativeInterface.getMethods().length == 0) return "void NoSignatureFound()";
		final String text = nativeInterface.getMethods()[0].getText();
		return text.substring(0, text.length() - 1);
	}

	private static String getReturn(PsiClass nativeInterface, String body) {
		if (nativeInterface.getAllMethods().length == 0) return "";
		if (body.isEmpty()) return body;
		body = body.endsWith(";") || body.endsWith("}") ? body : body + ";";
		if (nativeInterface.getMethods()[0].getReturnType() != null &&
				!("void".equals(nativeInterface.getMethods()[0].getReturnType().getCanonicalText())) &&
				!body.contains("\n") && body.split(";").length == 1 && !body.startsWith(RETURN))
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

}
