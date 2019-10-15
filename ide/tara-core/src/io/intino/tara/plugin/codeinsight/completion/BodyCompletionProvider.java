package io.intino.tara.plugin.codeinsight.completion;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.psi.PsiElement;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import io.intino.tara.Checker;
import io.intino.tara.plugin.lang.psi.MetaIdentifier;
import io.intino.tara.plugin.lang.psi.impl.TaraUtil;
import io.intino.tara.lang.model.Node;
import io.intino.tara.lang.semantics.errorcollector.SemanticFatalException;

import static com.intellij.codeInsight.lookup.LookupElementBuilder.create;
import static io.intino.tara.plugin.lang.psi.impl.TaraPsiUtil.getContainerNodeOf;
import static io.intino.tara.lang.model.Tag.Instance;

class BodyCompletionProvider extends CompletionProvider<CompletionParameters> {


	BodyCompletionProvider() {
	}

	public void addCompletions(@NotNull CompletionParameters parameters,
							   ProcessingContext context,
							   @NotNull CompletionResultSet resultSet) {
		if (!(parameters.getPosition().getContext() instanceof MetaIdentifier)) return;
		final CompletionUtils completionUtils = new CompletionUtils(parameters, resultSet);
		completionUtils.collectAllowedTypes();
		completionUtils.collectBodyParameters();
		if (!isDeclaration(getContainerNodeOf(parameters.getPosition().getContext()))) addKeywords(resultSet);
	}

	private boolean isDeclaration(Node node) {
		final Node container = check((PsiElement) node);
		return container != null && (node.is(Instance) || container.is(Instance));
	}

	private Node check(PsiElement node) {
		Checker checker = new Checker(TaraUtil.getLanguage(node));
		final Node container = getContainerNodeOf(node);
		if (container == null) return null;
		try {
			checker.check(container);
		} catch (SemanticFatalException ignored) {
		}
		return container;
	}

	private void addKeywords(CompletionResultSet resultSet) {
		resultSet.addElement(create("has "));
		resultSet.addElement(create("sub "));
		resultSet.addElement(create("var "));
	}
}
