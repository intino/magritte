package tara.intellij.annotator.semanticanalizer;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.module.Module;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import tara.intellij.annotator.TaraAnnotator;
import tara.intellij.annotator.fix.CreateNodeRuleClassIntention;
import tara.intellij.codeinsight.languageinjection.helpers.Format;
import tara.intellij.lang.psi.Rule;
import tara.intellij.lang.psi.TaraRuleContainer;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.messages.MessageProvider;
import tara.intellij.project.TaraModuleType;
import tara.intellij.project.module.ModuleProvider;
import tara.lang.model.Node;
import tara.lang.model.Tag;
import tara.lang.model.rules.custom.Url;

import static com.intellij.psi.search.GlobalSearchScope.moduleScope;
import static tara.intellij.highlighting.TaraSyntaxHighlighter.UNRESOLVED_ACCESS;
import static tara.lang.semantics.errorcollector.SemanticNotification.Level.ERROR;

public class NodeRuleAnalyzer extends TaraAnalyzer {

	private static final String RULES_PACKAGE = ".rules.";
	private final String rulesPackage;
	private final Rule rule;
	private final Node node;

	public NodeRuleAnalyzer(TaraRuleContainer ruleContainer) {
		this.node = TaraPsiImplUtil.getContainerByType(ruleContainer, Node.class);
		this.rule = ruleContainer.getRule();
		rulesPackage = (TaraModuleType.isTara(module()) ? TaraUtil.workingPackage(ruleContainer) : "").toLowerCase() + RULES_PACKAGE;
	}

	@Override
	public void analyze() {
		if (rule == null) {
			error();
			return;
		} else if (node.is(Tag.Instance)) {
			instanceError();
			return;
		}
		final Module module = module();
		if (rule.isLambda() || module == null) return;
		PsiClass aClass = JavaPsiFacade.getInstance(rule.getProject()).findClass(rulesPackage + rule.getText(), moduleScope(module));
		if (aClass == null && !isProvided()) error();
	}

	private void instanceError() {
		results.put(rule, new TaraAnnotator.AnnotateAndFix(ERROR, MessageProvider.message("instance.node.with.rule"), UNRESOLVED_ACCESS, collectFixes()));
	}

	private boolean isProvided() {
		try {
			return Class.forName(Url.class.getPackage().getName() + "." + Format.reference().format(rule.getText())) != null;
		} catch (ClassNotFoundException e) {
			return false;
		}
	}

	private Module module() {
		if (rule == null) return null;
		return ModuleProvider.moduleOf(rule.getContainingFile());
	}

	private void error() {
		if (rule == null)
			results.put((PsiElement) node, new TaraAnnotator.AnnotateAndFix(ERROR, MessageProvider.message("error.link.to.rule"), UNRESOLVED_ACCESS));
		else
			results.put(rule, new TaraAnnotator.AnnotateAndFix(ERROR, MessageProvider.message("error.link.to.rule"), UNRESOLVED_ACCESS, collectFixes()));
	}

	private IntentionAction[] collectFixes() {
		if (rule == null) return new IntentionAction[0];
		return new IntentionAction[]{new CreateNodeRuleClassIntention(rule)};
	}
}
