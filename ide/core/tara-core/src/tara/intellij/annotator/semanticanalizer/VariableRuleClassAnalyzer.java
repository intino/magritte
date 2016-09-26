package tara.intellij.annotator.semanticanalizer;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.module.Module;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import tara.intellij.annotator.TaraAnnotator;
import tara.intellij.annotator.fix.CreateMetricClassIntention;
import tara.intellij.annotator.fix.CreateVariableRuleClassIntention;
import tara.intellij.codeinsight.languageinjection.CreateFunctionInterfaceIntention;
import tara.intellij.codeinsight.languageinjection.helpers.Format;
import tara.intellij.lang.psi.Rule;
import tara.intellij.lang.psi.TaraRuleContainer;
import tara.intellij.lang.psi.TaraVariable;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.lang.psi.resolve.ReferenceManager;
import tara.intellij.messages.MessageProvider;
import tara.intellij.project.TaraModuleType;
import tara.intellij.project.module.ModuleProvider;
import tara.lang.model.Primitive;
import tara.lang.model.Variable;
import tara.lang.model.rules.custom.Url;

import static com.intellij.psi.search.GlobalSearchScope.moduleScope;
import static tara.lang.semantics.errorcollector.SemanticNotification.Level.ERROR;

public class VariableRuleClassAnalyzer extends TaraAnalyzer {

	private static final String NATIVES_PACKAGE = ".functions.";
	private static final String RULES_PACKAGE = ".rules.";
	private final String rulesPackage;
	private final Rule rule;
	private final String workingPackage;
	private final Variable variable;

	public VariableRuleClassAnalyzer(TaraRuleContainer ruleContainer) {
		this.variable = TaraPsiImplUtil.getContainerByType(ruleContainer, Variable.class);
		this.rule = ruleContainer.getRule();
		workingPackage = TaraModuleType.isTara(module()) ? TaraUtil.workingPackage(ruleContainer) : "";
		rulesPackage = workingPackage.toLowerCase() + (isNative() ? NATIVES_PACKAGE : RULES_PACKAGE);
	}

	public boolean isNative() {
		return variable != null && Primitive.FUNCTION.equals(variable.type());
	}

	private boolean hasSignature(TaraVariable variable) {
		final TaraRuleContainer ruleContainer = variable.getRuleContainer();
		if (ruleContainer == null) return false;
		final PsiClass psiClass = (PsiClass) ReferenceManager.resolveJavaClassReference(variable.getProject(), nativeClass(ruleContainer.getRule(), variable.type()));
		return psiClass != null && psiClass.getAllMethods().length != 0;
	}

	private String nativeClass(Rule rule, Primitive type) {
		return workingPackage.toLowerCase() + getPackage(type) + rule.getText();
	}

	@Override
	public void analyze() {
		if (rule == null) {
			error();
			return;
		}
		final Module module = module();
		if (rule.isLambda() || module == null) return;
		PsiClass aClass = JavaPsiFacade.getInstance(rule.getProject()).findClass(rulesPackage + rule.getText(), moduleScope(module));
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
		if (rule == null)
			results.put((PsiElement) variable, new TaraAnnotator.AnnotateAndFix(ERROR, MessageProvider.message("error.link.to.rule")));
		else {
			IntentionAction[] fixes = collectFixes();
			results.put((PsiElement) variable, new TaraAnnotator.AnnotateAndFix(ERROR, MessageProvider.message("error.link.to.rule"), fixes));
		}
	}

	private IntentionAction[] collectFixes() {
		if (variable == null) return new IntentionAction[0];
		if (Primitive.FUNCTION.equals(variable.type())) return new IntentionAction[]{new CreateFunctionInterfaceIntention(variable)};
		if (Primitive.WORD.equals(variable.type())) return new IntentionAction[]{new CreateVariableRuleClassIntention(rule)};
		return new IntentionAction[]{new CreateVariableRuleClassIntention(rule), new CreateMetricClassIntention(rule)};
	}

	private Object getPackage(Primitive type) {
		return type.equals(Primitive.FUNCTION) ? NATIVES_PACKAGE : RULES_PACKAGE;
	}

	private Module module() {
		if (rule == null) return null;
		return ModuleProvider.moduleOf(rule.getContainingFile());
	}
}