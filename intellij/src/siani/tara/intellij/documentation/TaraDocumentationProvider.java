package siani.tara.intellij.documentation;

import com.intellij.lang.documentation.AbstractDocumentationProvider;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.psi.Node;
import siani.tara.intellij.lang.psi.MetaIdentifier;
import siani.tara.intellij.lang.psi.TaraModel;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;


public class TaraDocumentationProvider extends AbstractDocumentationProvider {

	@NotNull
	private String renderConceptValue(Node node) {
		if (node == null) return "<i>empty</i>";
		String raw = node.getText();
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
			Node node = TaraPsiImplUtil.getContainerNodeOf(originalElement);
			String doc = "";
			return TaraDocumentationFormatter.doc2Html(null, doc);
		}
		if (element instanceof Node)
			return ((Node) element).getDocCommentText();
		if (element instanceof TaraModel)
			return "";
		return renderConceptValue(TaraPsiImplUtil.getContainerNodeOf(element));
	}

//	private String generateDocForNode(Node node, String contextNode) {
//		StringBuilder builder = new StringBuilder("**" + node.getObject().getType() + " " + node.getObject().getName() + "**\n");
//		for (Variable inner : node.getObject().getVariables()) {
//			builder.append("\t\t").append(inner.toString()).append("\n");
//			if (inner.getDoc() != null) builder.append(inner.getDoc()).append("\n");
//		}
//		for (Node inner : node.getInnerNodes()) {
//			builder.append("\t\t").append(inner.getObject().getType()).append(" ").append(inner.getObject().getName()).append("\n");
//			if (inner.getObject().getDoc() != null) builder.append(inner.getObject().getDoc()).append("\n");
//		}
//		if (node.getObject().is(INTENTION)) {
//			builder.append(generateDocForFacetApply((DeclaredNode) node, contextNode));
//		} else if (!node.getObject().getAllowedFacets().isEmpty()) {
//			builder.append("\n\t\t").append("Allowed facets:").append("\n");
//			for (String key : node.getObject().getAllowedFacets().keySet())
//				builder.append("\t\t\t").append(key).append("\n");
//		}
//		return builder.toString();
//	}
//
//	private String generateDocForFacetApply(DeclaredNode node, String contextNode) {
//		StringBuilder builder = new StringBuilder();
//		FacetTarget target = null;
//		for (FacetTarget facetTarget : node.getObject().getFacetTargets())
//			if (facetTarget.getDestinyName().equals(contextNode) || facetTarget.getDestinyName().endsWith("." + contextNode))
//				target = facetTarget;
//		if (target == null) return "";
//		builder.append("\n\t\t").append("On ").append(contextNode).append(":").append("\n");
//		for (Variable inner : target.getVariables()) {
//			builder.append("\t\t\t").append(inner.toString()).append("\n");
//			if (inner.getDoc() != null) builder.append(inner.getDoc()).append("\n");
//		}
//		return builder.toString();
//	}
}