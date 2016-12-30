package io.intino.tara.plugin.annotator.semanticanalizer;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.module.Module;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import io.intino.tara.dsl.Proteo;
import io.intino.tara.dsl.ProteoConstants;
import io.intino.tara.plugin.annotator.TaraAnnotator;
import io.intino.tara.plugin.annotator.fix.CreateNodeRuleClassIntention;
import io.intino.tara.plugin.codeinsight.languageinjection.helpers.Format;
import io.intino.tara.plugin.lang.psi.Rule;
import io.intino.tara.plugin.lang.psi.TaraRuleContainer;
import io.intino.tara.plugin.lang.psi.impl.TaraPsiImplUtil;
import io.intino.tara.plugin.lang.psi.impl.TaraUtil;
import io.intino.tara.plugin.messages.MessageProvider;
import io.intino.tara.plugin.project.TaraModuleType;
import io.intino.tara.plugin.project.module.ModuleProvider;
import io.intino.tara.lang.model.Node;
import io.intino.tara.lang.model.Tag;
import io.intino.tara.lang.model.rules.custom.Url;

import static com.intellij.psi.search.GlobalSearchScope.moduleScope;
import static io.intino.tara.plugin.highlighting.TaraSyntaxHighlighter.UNRESOLVED_ACCESS;
import static io.intino.tara.lang.semantics.errorcollector.SemanticNotification.Level.ERROR;

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
		if (rule.isLambda()) {
			if (node.type().startsWith(ProteoConstants.FACET + Proteo.FACET_SEPARATOR))
				facetError();
			return;
		} else if (module == null) return;

		PsiClass aClass = JavaPsiFacade.getInstance(rule.getProject()).findClass(rulesPackage + rule.getText(), moduleScope(module));
		if (aClass == null && !isProvided()) error();
	}

	private void facetError() {
		results.put(rule, new TaraAnnotator.AnnotateAndFix(ERROR, MessageProvider.message("reject.facet.with.size.constraint")));
	}

	private void instanceError() {
		results.put(rule, new TaraAnnotator.AnnotateAndFix(ERROR, MessageProvider.message("reject.instance.node.with.rule"), UNRESOLVED_ACCESS));
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