package tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import tara.intellij.annotator.semanticanalizer.ModelAnalyzer;
import tara.intellij.annotator.semanticanalizer.NodeAnalyzer;
import tara.intellij.annotator.semanticanalizer.NodeReferenceAnalyzer;
import tara.intellij.annotator.semanticanalizer.TaraAnalyzer;
import tara.intellij.lang.psi.TaraIdentifier;
import tara.intellij.lang.psi.TaraModel;
import tara.intellij.lang.psi.TaraNode;
import tara.intellij.lang.psi.TaraNodeReference;
import tara.lang.model.Node;

import java.awt.*;

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
		if (isDeclaration(node)) addDeclarationAnnotation(node);
	}

	private void asModel(TaraModel model) {
		TaraAnalyzer analyzer = new ModelAnalyzer(model);
		analyzeAndAnnotate(analyzer);
	}

	private boolean isDeclaration(Node node) {
		return node.isTerminalInstance();
	}

	private void asNodeReference(TaraNodeReference nodeReference) {
		TaraAnalyzer analyzer = new NodeReferenceAnalyzer(nodeReference);
		analyzeAndAnnotate(analyzer);
	}

	@SuppressWarnings("deprecation")
	private void addDeclarationAnnotation(Node node) {
		TextAttributesKey root = createTextAttributesKey("node_declaration", new TextAttributes(null, null, null, null, Font.ITALIC));
		final TaraIdentifier identifier = ((TaraNode) node).getSignature().getIdentifier();
		if (identifier != null) holder.createInfoAnnotation(identifier, "declaration").setTextAttributes(root);
	}
}
