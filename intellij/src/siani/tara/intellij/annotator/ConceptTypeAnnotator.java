package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.TaraBundle;
import siani.tara.intellij.highlighting.TaraSyntaxHighlighter;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.MetaIdentifier;
import siani.tara.intellij.lang.psi.TaraFile;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.lang.Model;
import siani.tara.lang.Node;

public class ConceptTypeAnnotator extends TaraAnnotator {


	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (element instanceof MetaIdentifier) {
			Concept concept = TaraPsiImplUtil.getContextOf(element);
			if (concept == null) return;
			Model tree = TaraLanguage.getHeritage(((TaraFile) element.getContainingFile()).getParentModel());
			if (tree == null) {
				if (!"Concept".equals(element.getNode().getText())) {
					Annotation errorAnnotation = holder.createErrorAnnotation
						(concept, TaraBundle.message("Unknown.concept.without.heritage.key.error.message"));
					errorAnnotation.setTextAttributes(TaraSyntaxHighlighter.ANNOTATION_ERROR);
				}
			} else {
				Node node = findNode(concept, tree);
				if (node == null) {
					Annotation errorAnnotation = holder.createErrorAnnotation
						(concept, TaraBundle.message("Unknown.concept.key.error.message"));
					errorAnnotation.setTextAttributes(TaraSyntaxHighlighter.ANNOTATION_ERROR);
				}
			}
		}
	}


	private Node findNode(Concept concept, Model tree) {
		String metaQualifiedName = TaraUtil.getMetaQualifiedName(concept);
		String[] path = metaQualifiedName.split("\\.");
		for (String s : path) {

		}
		Node node = tree.get(metaQualifiedName);
		return (node != null) ? node : tree.get(asAnonymous(metaQualifiedName));
	}

	private String asAnonymous(String name) {
		String subPath = name.substring(0, name.lastIndexOf("."));
		return subPath + "." + "[" + name.substring(name.lastIndexOf(".") + 1) + "@annonymous]";
	}
}
