package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.Language;
import siani.tara.intellij.annotator.semanticanalizer.ModelAnalyzer;
import siani.tara.intellij.annotator.semanticanalizer.NodeAnalyzer;
import siani.tara.intellij.annotator.semanticanalizer.NodeReferenceAnalyzer;
import siani.tara.intellij.annotator.semanticanalizer.TaraAnalyzer;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.Node;
import siani.tara.intellij.lang.psi.NodeReference;
import siani.tara.intellij.lang.psi.TaraModel;
import siani.tara.intellij.lang.psi.TaraNodeReference;
import siani.tara.intellij.lang.psi.impl.ReferenceManager;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.semantic.Assumption;

import java.awt.*;
import java.util.Collection;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;
import static siani.tara.intellij.MessageProvider.message;

public class NodeAnnotator extends TaraAnnotator {

	private static final char DOT = '.';
	private static final String INTENTIONS = "intentions";
	private static final String EXTENSIONS = "extensions";
	private static final String INTENTION = "Intention";

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (element instanceof Node) asNode((Node) element);
		else if (element instanceof TaraModel) asModel((TaraModel) element);
		else if (element instanceof NodeReference)
			asNodeReference((TaraNodeReference) element);

	}

	private void asNode(Node node) {
		TaraAnalyzer analyzer = new NodeAnalyzer(node);
		analyzeAndAnnotate(analyzer);
		if (analyzer.hasErrors()) return;
		if (isRoot(node)) addRootAnnotation(node);
		else if (isProperty(node)) addPropertyAnnotation(node);
		analyzeJavaClassCreation(node);
	}

	private void analyzeJavaClassCreation(Node node) {
		if (node.isIntention() && !isCreated(intentionClass(node), node))
			holder.createErrorAnnotation(node.getSignature(), message("no.java.generated.class"));
		if ((node.isFacet() && node.isIntentionInstance() && !isCreated(extensionClass(node), node)))
			holder.createErrorAnnotation(node.getSignature(), message("no.java.generated.class"));

	}

	private boolean isCreated(String qn, Node node) {
		return ReferenceManager.resolveJavaClassReference(node.getProject(), qn) != null;
	}

	private String intentionClass(Node node) {
		return node.getProject().getName().toLowerCase() + DOT + INTENTIONS + DOT + node.getName() + INTENTION;
	}

	private String extensionClass(Node node) {
		return node.getProject().getName().toLowerCase() + DOT + EXTENSIONS + DOT + node.getName() + node.getType();
	}

	private void asModel(TaraModel model) {
		TaraAnalyzer analyzer = new ModelAnalyzer(model);
		analyzeAndAnnotate(analyzer);
	}

	private boolean isRoot(Node node) {
		Collection<Node> rootNodes = TaraUtil.getRootNodesOfFile(node.getFile());
		return rootNodes.contains(node) && node.getIdentifierNode() != null;
	}

	private void asNodeReference(TaraNodeReference nodeReference) {
		TaraAnalyzer analyzer = new NodeReferenceAnalyzer(nodeReference);
		analyzeAndAnnotate(analyzer);
	}

	private boolean isProperty(Node node) {
		Language language = TaraLanguage.getLanguage(node.getFile());
		if (language == null) return false;
		Collection<Assumption> assumptions = language.assumptions(node.resolve().getFullType());
		if (assumptions == null) return false;
		for (Assumption assumption : assumptions)
			if (assumption instanceof Assumption.Property)
				return true;
		return false;
	}

	@SuppressWarnings("deprecation")
	private void addRootAnnotation(Node node) {
		TextAttributesKey root = createTextAttributesKey("node_ROOT", new TextAttributes(null, null, null, null, Font.BOLD));
		holder.createInfoAnnotation(node.getIdentifierNode(), "Root").setTextAttributes(root);
	}

	private void addPropertyAnnotation(Node node) {
		TextAttributesKey keywordProperty = createTextAttributesKey("KEYWORD_PROPERTY", DefaultLanguageHighlighterColors.STATIC_METHOD);
		holder.createInfoAnnotation(node.getMetaIdentifier(), "Property").setTextAttributes(keywordProperty);
	}

}
