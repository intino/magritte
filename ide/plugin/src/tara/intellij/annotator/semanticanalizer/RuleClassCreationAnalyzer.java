package tara.intellij.annotator.semanticanalizer;

import com.intellij.openapi.module.Module;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import tara.intellij.MessageProvider;
import tara.intellij.annotator.TaraAnnotator;
import tara.intellij.annotator.fix.CreateRuleClassIntention;
import tara.intellij.lang.psi.Rule;
import tara.intellij.lang.psi.TaraRuleContainer;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.module.ModuleProvider;
import tara.lang.model.Primitive;
import tara.lang.model.Variable;

import static com.intellij.psi.search.GlobalSearchScope.moduleScope;
import static tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.ERROR;

public class RuleClassCreationAnalyzer extends TaraAnalyzer {

	private static final String NATIVES_PACKAGE = ".natives";
	private static final String RULES_PACKAGE = ".rules";
	private final String rulesPackage;
	private final Rule rule;
	private final TaraRuleContainer ruleContainer;

	public RuleClassCreationAnalyzer(TaraRuleContainer ruleContainer) {
		this.ruleContainer = ruleContainer;
		this.rule = ruleContainer.getRule();
		TaraFacet facet = TaraFacet.getTaraFacetByModule(getModule());
		final String generatedDslName = facet != null ? facet.getConfiguration().getGeneratedDslName() : "";
		rulesPackage = generatedDslName.toLowerCase() + (isNative(ruleContainer) ? NATIVES_PACKAGE : RULES_PACKAGE);
	}

	public boolean isNative(TaraRuleContainer ruleContainer) {
		final Variable variable = TaraPsiImplUtil.getContainerByType(ruleContainer, Variable.class);
		return variable != null && Primitive.NATIVE.equals(variable.type());
	}

	@Override
	public void analyze() {
		if (rule == null) {
			error();
			return;
		}
		if (rule.isLambda()) return;
		PsiClass aClass = JavaPsiFacade.getInstance(rule.getProject()).findClass(rulesPackage + "." + rule.getText(), moduleScope(getModule()));
		if (aClass == null) error();
	}

	private void error() {
		if (rule == null) {
			final TaraAnnotator.AnnotateAndFix annotation = new TaraAnnotator.AnnotateAndFix(ERROR, MessageProvider.message("error.link.to.rule"));
			results.put(ruleContainer, annotation);
		} else
			results.put(rule, new TaraAnnotator.AnnotateAndFix(ERROR, MessageProvider.message("error.link.to.rule"), new CreateRuleClassIntention(rule)));
	}

	private Module getModule() {
		if (rule == null) return null;
		return ModuleProvider.getModuleOf(rule.getContainingFile());
	}
}
