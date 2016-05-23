package tara.intellij.codeinsight.completion;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.psi.PsiElement;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import tara.Checker;
import tara.intellij.lang.psi.MetaIdentifier;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.lang.model.Node;
import tara.lang.semantics.errorcollector.SemanticFatalException;

import static com.intellij.codeInsight.lookup.LookupElementBuilder.create;
import static tara.intellij.lang.psi.impl.TaraPsiImplUtil.getContainerNodeOf;
import static tara.lang.model.Tag.Instance;

class BodyCompletionProvider extends CompletionProvider<CompletionParameters> {


	BodyCompletionProvider() {
	}

	public void addCompletions(@NotNull CompletionParameters parameters,
	                           ProcessingContext context,
	                           @NotNull CompletionResultSet resultSet) {
		if (!(parameters.getPosition().getContext() instanceof MetaIdentifier)) return;
		final CompletionUtils completionUtils = new CompletionUtils(parameters, resultSet);
		completionUtils.collectAllowedTypes();
		completionUtils.collectParameters();
		if (!isDeclaration(getContainerNodeOf(parameters.getPosition().getContext()))) addKeywords(resultSet);
	}

	private boolean isDeclaration(Node node) {
		final Node containerNodeOf = check((PsiElement) node);
		return node.is(Instance) || containerNodeOf != null && containerNodeOf.is(Instance);
	}

	private Node check(PsiElement node) {
		Checker checker = new Checker(TaraUtil.getLanguage(node));
		final Node containerNodeOf = getContainerNodeOf(node);
		try {
			checker.check(containerNodeOf);
		} catch (SemanticFatalException ignored) {
		}
		return containerNodeOf;
	}


	private void addKeywords(CompletionResultSet resultSet) {
		resultSet.addElement(create("has "));
		resultSet.addElement(create("sub "));
		resultSet.addElement(create("var "));
	}
}
