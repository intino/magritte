package tara.intellij.codeinsight.completion;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.psi.PsiElement;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import tara.Language;
import tara.intellij.lang.psi.MetaIdentifier;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.language.model.Facet;
import tara.language.model.Node;
import tara.language.model.NodeContainer;
import tara.language.semantics.Allow;

import java.util.List;

import static com.intellij.codeInsight.lookup.LookupElementBuilder.create;

class BodyCompletionProvider extends CompletionProvider<CompletionParameters> {


	public BodyCompletionProvider() {
	}

	public void addCompletions(@NotNull CompletionParameters parameters,
	                           ProcessingContext context,
	                           @NotNull CompletionResultSet resultSet) {
		if (!(parameters.getPosition().getContext() instanceof MetaIdentifier)) return;
		final CompletionUtils completionUtils = new CompletionUtils(parameters, resultSet);
		completionUtils.collectAllowedTypes();
		completionUtils.collectParameters();
		if (!inFacetApply(parameters.getPosition().getContext())) {
			addKeywords(resultSet);
			addFacetAlternatives(parameters, resultSet);
		}
	}

	private boolean inFacetApply(PsiElement context) {
		final NodeContainer container = TaraPsiImplUtil.getContainerOf(context);
		return container instanceof Facet || TaraPsiImplUtil.getContainerOf((PsiElement) container) instanceof Facet;
	}

	private void addFacetAlternatives(@NotNull CompletionParameters parameters, CompletionResultSet resultSet) {
		Language language = TaraUtil.getLanguage(parameters.getOriginalFile());
		Node node = TaraPsiImplUtil.getContainerNodeOf((PsiElement) TaraPsiImplUtil.getContainerNodeOf(parameters.getPosition()));
		if (node == null) return;
		if (node.isFacet()) resultSet.addElement(create("on "));
		else if (language != null && allowsFacets(language.allows(node.type()))) resultSet.addElement(create("as "));
	}

	private boolean allowsFacets(List<Allow> allows) {
		if (allows == null) return false;
		for (Allow allow : allows) if (allow instanceof Allow.Facet) return true;
		return false;
	}

	private void addKeywords(CompletionResultSet resultSet) {
		resultSet.addElement(create("has "));
		resultSet.addElement(create("sub "));
		resultSet.addElement(create("var "));
	}
}
