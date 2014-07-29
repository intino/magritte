package siani.tara.intellij.documentation;

import com.intellij.lang.documentation.AbstractDocumentationProvider;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.MetaIdentifier;
import siani.tara.intellij.lang.psi.TaraFacetApply;
import siani.tara.intellij.lang.psi.TaraFile;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.lang.IntentionNode;
import siani.tara.lang.Model;
import siani.tara.lang.Node;
import siani.tara.lang.Variable;


public class TaraDocumentationProvider extends AbstractDocumentationProvider {

	@NotNull
	private String renderConceptValue(Concept concept) {
		if (concept == null) return "<i>empty</i>";
		String raw = concept.getText();
		if (raw == null) return "<i>empty</i>";
		return StringUtil.escapeXml(raw);
	}

	@Nullable
	public String getQuickNavigateInfo(PsiElement element, PsiElement originalElement) {
		return generateDoc(element, originalElement);
	}

	@NonNls
	public String generateDoc(final PsiElement element, @Nullable final PsiElement originalElement) {

		if (originalElement instanceof MetaIdentifier) {
			Concept concept = TaraPsiImplUtil.getContextOf(originalElement);
			String doc = originalElement.getParent() instanceof TaraFacetApply ?
				extractMetaDocumentationOfFacetApply(concept, originalElement.getText())
				: extractMetaDocumentationOfConcept(concept);
			return TaraDocumentationFormatter.doc2Html(null, doc);
		}
		if (element instanceof Concept)
			return ((Concept) element).getDocCommentText();
		if (element instanceof TaraFile)
			return renderConceptValue(((TaraFile) element).getConcepts()[0]); //TODO
		return renderConceptValue(TaraPsiImplUtil.getContextOf(element));
	}

	private String extractMetaDocumentationOfFacetApply(Concept concept, String facet) {
		Model model = TaraLanguage.getMetaModel(((TaraFile) concept.getContainingFile()).getParentModel());
		if (model == null) return null;
		Node facetNode = findFacet(model, facet);
		return generateDocForNode(facetNode);
	}

	private Node findFacet(Model model, String facet) {
		for (Node node : model.getTree())
			if (node instanceof IntentionNode && node.getName().equals(facet))
				return node;
		return null;
	}

	private String extractMetaDocumentationOfConcept(Concept concept) {
		Node node = getNode(concept);
		if (node == null) return null;
		String doc = node.getObject().getDoc();
		return doc != null ? doc : generateDocForNode(node);
	}

	private Node getNode(Concept concept) {
		Model model = TaraLanguage.getMetaModel(((TaraFile) concept.getContainingFile()).getParentModel());
		if (model == null) return null;
		return model.searchNode(TaraUtil.getMetaQualifiedName(concept));
	}

	private String generateDocForNode(Node node) {
		StringBuilder builder = new StringBuilder("**" + node.getObject().getType() + " " + node.getObject().getName() + "**\n");
		for (Variable inner : node.getObject().getVariables()) {
			builder.append("\t\t_").append(inner.getType()).append("_ _").append(inner.getName()).append("_\n");
			if (inner.getDoc() != null) builder.append(inner.getDoc()).append("\n");
		}
		for (Node inner : node.getInnerNodes()) {
			builder.append("\t\t_").append(inner.getObject().getType()).append("_ _").append(inner.getObject().getName()).append("_\n");
			if (inner.getObject().getDoc() != null) builder.append(inner.getObject().getDoc()).append("\n");
		}
		if (!node.getObject().getAllowedFacets().isEmpty()) {
			builder.append("\t\t_").append("*").append("Allowed facets:").append("*").append("_\n");
			for (String key : node.getObject().getAllowedFacets().keySet())
				builder.append("\t\t\t*").append(key).append("*\n");
		}
		return builder.toString();
	}
}