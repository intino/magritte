package tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import tara.Language;
import tara.intellij.annotator.semanticanalizer.ModelAnalyzer;
import tara.intellij.annotator.semanticanalizer.NodeAnalyzer;
import tara.intellij.annotator.semanticanalizer.NodeReferenceAnalyzer;
import tara.intellij.annotator.semanticanalizer.TaraAnalyzer;
import tara.intellij.lang.psi.*;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.lang.model.Node;
import tara.lang.semantics.Assumption;

import java.awt.*;
import java.util.List;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class NodeAnnotator extends TaraAnnotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (element instanceof TaraModel) asModel((TaraModel) element);
		if (element instanceof Node && ((Node) element).isReference()) asNodeReference((TaraNodeReference) element);
		if (element instanceof Node) asNode((Node) element);
	}

	private void asNode(Node node) {
		TaraAnalyzer analyzer = new NodeAnalyzer(node);
		analyzeAndAnnotate(analyzer);
		if (analyzer.hasErrors()) return;
		if (isRoot(node)) addRootAnnotation(node);
		else if (isProperty(node)) addPropertyAnnotation(node);
	}

	private void asModel(TaraModel model) {
		TaraAnalyzer analyzer = new ModelAnalyzer(model);
		analyzeAndAnnotate(analyzer);
	}

	private boolean isRoot(Node node) {
		List<Node> rootNodes = TaraUtil.getMainNodesOfFile((TaraModel) ((PsiElement) node).getContainingFile());
		return rootNodes.contains(node) && ((TaraNode) node).getSignature().getIdentifier() != null;
	}

	private void asNodeReference(TaraNodeReference nodeReference) {
		TaraAnalyzer analyzer = new NodeReferenceAnalyzer(nodeReference);
		analyzeAndAnnotate(analyzer);
	}

	private boolean isProperty(Node node) {
		Language language = TaraUtil.getLanguage((PsiElement) node);
		if (language == null) return false;
		List<Assumption> assumptions = language.assumptions(node.resolve().type());
		if (assumptions == null) return false;
		for (Assumption assumption : assumptions)
			if (assumption instanceof Assumption.Implicit)
				return true;
		return false;
	}

	@SuppressWarnings("deprecation")
	private void addRootAnnotation(Node node) {
		TextAttributesKey root = createTextAttributesKey("node_ROOT", new TextAttributes(null, null, null, null, Font.BOLD));
		final TaraIdentifier identifier = ((TaraNode) node).getSignature().getIdentifier();
		if (identifier != null) holder.createInfoAnnotation(identifier, "Root").setTextAttributes(root);
	}

	private void addPropertyAnnotation(Node node) {
		TextAttributesKey keywordProperty = createTextAttributesKey("KEYWORD_PROPERTY", DefaultLanguageHighlighterColors.STATIC_METHOD);
		final TaraMetaIdentifier meta = ((TaraNode) node).getSignature().getMetaIdentifier();
		if (meta != null) holder.createInfoAnnotation(meta, "Property").setTextAttributes(keywordProperty);
	}

}
