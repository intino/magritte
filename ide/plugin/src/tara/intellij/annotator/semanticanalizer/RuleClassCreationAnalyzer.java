package tara.intellij.annotator.semanticanalizer;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.module.Module;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import tara.intellij.MessageProvider;
import tara.intellij.annotator.TaraAnnotator;
import tara.intellij.annotator.fix.CreateMetricClassIntention;
import tara.intellij.annotator.fix.CreateRuleClassIntention;
import tara.intellij.codeinsight.languageinjection.CreateNativeClassIntention;
import tara.intellij.codeinsight.languageinjection.helpers.Format;
import tara.intellij.lang.psi.Rule;
import tara.intellij.lang.psi.TaraRuleContainer;
import tara.intellij.lang.psi.TaraVariable;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.intellij.lang.psi.resolve.ReferenceManager;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.module.ModuleProvider;
import tara.lang.model.Primitive;
import tara.lang.model.Variable;
import tara.lang.model.rules.custom.Url;

import static com.intellij.psi.search.GlobalSearchScope.moduleScope;
import static tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.TYPE.ERROR;

public class RuleClassCreationAnalyzer extends TaraAnalyzer {

	private static final String NATIVES_PACKAGE = ".natives.";
	private static final String RULES_PACKAGE = ".rules.";
	private final String rulesPackage;
	private final Rule rule;
	private final TaraRuleContainer ruleContainer;
	private final String generatedDslName;
	private final Variable variable;

	public RuleClassCreationAnalyzer(TaraRuleContainer ruleContainer) {
		this.ruleContainer = ruleContainer;
		this.variable = TaraPsiImplUtil.getContainerByType(ruleContainer, Variable.class);
		this.rule = ruleContainer.getRule();
		TaraFacet facet = TaraFacet.of(getModule());
		generatedDslName = facet != null ? facet.getConfiguration().getGeneratedDslName() : "";
		rulesPackage = generatedDslName.toLowerCase() + (isNative() ? NATIVES_PACKAGE : RULES_PACKAGE);
	}

	public boolean isNative() {
		return variable != null && Primitive.FUNCTION.equals(variable.type());
	}

	private boolean hasSignature(TaraVariable variable) {
		final TaraRuleContainer ruleContainer = variable.getRuleContainer();
		if (ruleContainer == null) return false;
		final PsiClass psiClass = (PsiClass) ReferenceManager.resolveJavaClassReference(variable.getProject(), nativeClass(ruleContainer.getRule(), variable.type()));
		return psiClass != null && psiClass.getMethods().length != 0;
	}

	private String nativeClass(Rule rule, Primitive type) {
		return generatedDslName.toLowerCase() + getPackage(type) + rule.getText();
	}

	@Override
	public void analyze() {
		if (rule == null) {
			error();
			return;
		}
		if (rule.isLambda()) return;
		PsiClass aClass = JavaPsiFacade.getInstance(rule.getProject()).findClass(rulesPackage + rule.getText(), moduleScope(getModule()));
		if (aClass == null && !isProvided()) error();
		if (isNative() && !hasSignature((TaraVariable) variable)) {
			results.put((PsiElement) variable, new TaraAnnotator.AnnotateAndFix(ERROR, MessageProvider.message("no.java.signature.found")));
		}
	}

	private boolean isProvided() {
		try {
			return Class.forName(Url.class.getPackage().getName() + "." + Format.reference().format(rule.getText())) != null;
		} catch (ClassNotFoundException e) {
			return false;
		}
	}

	private void error() {
		if (rule == null) {
			final TaraAnnotator.AnnotateAndFix annotation = new TaraAnnotator.AnnotateAndFix(ERROR, MessageProvider.message("error.link.to.rule"));
			results.put(ruleContainer, annotation);
		} else {
			IntentionAction[] fixes = collectFixes();
			results.put(rule, new TaraAnnotator.AnnotateAndFix(ERROR, MessageProvider.message("error.link.to.rule"), fixes));
		}
	}

	private IntentionAction[] collectFixes() {
		if  (variable == null) return new IntentionAction[0];
		if (Primitive.FUNCTION.equals(variable.type())) return new IntentionAction[]{new CreateNativeClassIntention(variable)};
		return new IntentionAction[]{new CreateRuleClassIntention(rule), new CreateMetricClassIntention(rule)};

	}

	private Object getPackage(Primitive type) {
		return type.equals(Primitive.FUNCTION) ? NATIVES_PACKAGE : RULES_PACKAGE;
	}

	private Module getModule() {
		if (rule == null) return null;
		return ModuleProvider.getModuleOf(rule.getContainingFile());
	}
}
