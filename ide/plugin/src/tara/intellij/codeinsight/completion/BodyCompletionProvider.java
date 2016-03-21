package tara.intellij.codeinsight.completion;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.psi.PsiElement;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import tara.Checker;
import tara.Language;
import tara.dsl.ProteoConstants;
import tara.intellij.lang.psi.MetaIdentifier;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.lang.model.Facet;
import tara.lang.model.Node;
import tara.lang.model.NodeContainer;
import tara.lang.semantics.Constraint;
import tara.lang.semantics.errorcollector.SemanticFatalException;

import java.util.List;

import static com.intellij.codeInsight.lookup.LookupElementBuilder.create;
import static tara.intellij.lang.psi.impl.TaraPsiImplUtil.getContainerNodeOf;
import static tara.intellij.lang.psi.impl.TaraPsiImplUtil.getContainerOf;
import static tara.lang.model.Tag.Instance;

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
		final boolean inFacet = inFacetApply(getContainerOf(parameters.getPosition().getContext()));
		if (!inFacet) addFacetAlternatives(parameters, resultSet);
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

	private boolean inFacetApply(NodeContainer container) {
		return container instanceof Facet || getContainerOf((PsiElement) container) instanceof Facet;
	}

	private void addFacetAlternatives(@NotNull CompletionParameters parameters, CompletionResultSet resultSet) {
		Language language = TaraUtil.getLanguage(parameters.getOriginalFile());
		Node node = getContainerNodeOf((PsiElement) getContainerNodeOf(parameters.getPosition()));
		if (node == null) return;
		if (node.type().equals(ProteoConstants.FACET) || node.metaTypes().contains(ProteoConstants.METAFACET))
			resultSet.addElement(create("on "));
		else if (language != null && allowsFacets(language.constraints(node.type()))) resultSet.addElement(create("as "));
	}

	private boolean allowsFacets(List<Constraint> allows) {
		if (allows == null) return false;
		for (Constraint allow : allows) if (allow instanceof Constraint.Facet) return true;
		return false;
	}

	private void addKeywords(CompletionResultSet resultSet) {
		resultSet.addElement(create("has "));
		resultSet.addElement(create("sub "));
		resultSet.addElement(create("var "));
	}
}
