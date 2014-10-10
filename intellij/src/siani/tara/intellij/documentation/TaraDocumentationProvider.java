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
import siani.tara.intellij.lang.psi.TaraBoxFile;
import siani.tara.intellij.lang.psi.TaraFacetApply;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.lang.*;

import static siani.tara.lang.Annotations.Annotation.INTENTION;


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
			Concept concept = TaraPsiImplUtil.getConceptContextOf(originalElement);
			String doc = originalElement.getParent() instanceof TaraFacetApply ?
				extractMetaDocumentationOfFacetApply(concept, originalElement.getText())
				: extractMetaDocumentationOfConcept(concept);
			return TaraDocumentationFormatter.doc2Html(null, doc);
		}
		if (element instanceof Concept)
			return ((Concept) element).getDocCommentText();
		if (element instanceof TaraBoxFile)
			return ((TaraBoxFile) element).getConcepts().length > 0 ? renderConceptValue(((TaraBoxFile) element).getConcepts()[0]) : "";
		return renderConceptValue(TaraPsiImplUtil.getConceptContextOf(element));
	}

	private String extractMetaDocumentationOfFacetApply(Concept concept, String facet) {
		Model model = TaraLanguage.getMetaModel(concept.getContainingFile());
		if (model == null) return null;
		Node facetNode = findFacet(model, facet);
		return generateDocForNode(facetNode, concept.getType());
	}

	private Node findFacet(Model model, String facet) {
		for (Node node : model.getTreeModel())
			if (node.getObject().is(INTENTION) && node.getName().equals(facet))
				return node;
		return null;
	}

	private String extractMetaDocumentationOfConcept(Concept concept) {
		Node node = getNode(concept);
		if (node == null) return null;
		String doc = node.getObject().getDoc();
		return doc != null ? doc : generateDocForNode(node, concept.getType());
	}

	private Node getNode(Concept concept) {
		Model model = TaraLanguage.getMetaModel(concept.getContainingFile());
		if (model == null) return null;
		return model.searchNode(TaraUtil.getMetaQualifiedName(concept));
	}

	private String generateDocForNode(Node node, String contextNode) {
		StringBuilder builder = new StringBuilder("**" + node.getObject().getType() + " " + node.getObject().getName() + "**\n");
		for (Variable inner : node.getObject().getVariables()) {
			builder.append("\t\t").append(inner.toString()).append("\n");
			if (inner.getDoc() != null) builder.append(inner.getDoc()).append("\n");
		}
		for (Node inner : node.getInnerNodes()) {
			builder.append("\t\t").append(inner.getObject().getType()).append(" ").append(inner.getObject().getName()).append("\n");
			if (inner.getObject().getDoc() != null) builder.append(inner.getObject().getDoc()).append("\n");
		}
		if (node.getObject().is(INTENTION)) {
			builder.append(generateDocForFacetApply((DeclaredNode) node, contextNode));
		} else if (!node.getObject().getAllowedFacets().isEmpty()) {
			builder.append("\n\t\t").append("Allowed facets:").append("\n");
			for (String key : node.getObject().getAllowedFacets().keySet())
				builder.append("\t\t\t").append(key).append("\n");
		}
		return builder.toString();
	}

	private String generateDocForFacetApply(DeclaredNode node, String contextNode) {
		StringBuilder builder = new StringBuilder();
		FacetTarget target = null;
		for (FacetTarget facetTarget : node.getObject().getFacetTargets())
			if (facetTarget.getDestinyName().equals(contextNode) || facetTarget.getDestinyName().endsWith("." + contextNode))
				target = facetTarget;
		if (target == null) return "";
		builder.append("\n\t\t").append("On ").append(contextNode).append(":").append("\n");
		for (Variable inner : target.getVariables()) {
			builder.append("\t\t\t").append(inner.toString()).append("\n");
			if (inner.getDoc() != null) builder.append(inner.getDoc()).append("\n");
		}
		return builder.toString();
	}
}