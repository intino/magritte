package siani.tara.intellij.codeinsight.completion;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.psi.PsiElement;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import siani.tara.Language;
import siani.tara.intellij.lang.psi.FacetApply;
import siani.tara.intellij.lang.psi.MetaIdentifier;
import siani.tara.intellij.lang.psi.Node;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.semantic.Allow;

import java.util.List;

import static com.intellij.codeInsight.lookup.LookupElementBuilder.create;
import static siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil.getContainerNodeOf;

class BodyCompletionProvider extends CompletionProvider<CompletionParameters> implements CompletionUtils {


	public BodyCompletionProvider() {
	}

	public void addCompletions(@NotNull CompletionParameters parameters,
	                           ProcessingContext context,
	                           @NotNull CompletionResultSet resultSet) {
		if (!(parameters.getPosition().getContext() instanceof MetaIdentifier)) return;
		collectAllowedTypes(parameters, resultSet);
		collectParameters(parameters, resultSet);
		if (!inFacetApply(parameters.getPosition().getContext())) {
			addKeywords(resultSet);
			addFacetAlternatives(parameters, resultSet);
		}
	}

	private boolean inFacetApply(PsiElement context) {
		return TaraPsiImplUtil.getContextOf(context) instanceof FacetApply;
	}

	private void addFacetAlternatives(@NotNull CompletionParameters parameters, CompletionResultSet resultSet) {
		Language language = TaraUtil.getLanguage(parameters.getOriginalFile());
		Node node = getContainerNodeOf(getContainerNodeOf(parameters.getPosition()));
		if (node == null) return;
		if (node.isFacet()) resultSet.addElement(create("on "));
		else if (language != null && allowsFacets(language.allows(node.getType()))) resultSet.addElement(create("as "));
	}

	private boolean allowsFacets(List<Allow> allows) {
		for (Allow allow : allows) if (allow instanceof Allow.Facet) return true;
		return false;
	}

	private void addKeywords(CompletionResultSet resultSet) {
		resultSet.addElement(create("has "));
		resultSet.addElement(create("sub "));
		resultSet.addElement(create("var "));
	}
}
