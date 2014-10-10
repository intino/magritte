package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.TaraBundle;
import siani.tara.intellij.highlighting.TaraSyntaxHighlighter;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.MetaIdentifier;
import siani.tara.intellij.lang.psi.TaraConceptReference;
import siani.tara.intellij.lang.psi.TaraTypes;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.lang.Model;
import siani.tara.lang.Node;

import java.util.ArrayList;
import java.util.List;

public class ConceptTypeAnnotator extends TaraAnnotator {

	protected static final String CONCEPT = "Concept";

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (element instanceof MetaIdentifier) {
			Concept concept = TaraPsiImplUtil.getConceptContextOf(element);
			if (concept == null) return;
			Model model = TaraLanguage.getMetaModel(element.getContainingFile());
			if (CONCEPT.equals(element.getText())) {
				if (concept.getName() == null)
					holder.createErrorAnnotation(concept, "Concept without name");
				IElementType elementType = getPreviousToken(concept);
				if (!elementType.equals(TaraTypes.NEWLINE) && !elementType.equals(TaraTypes.NEW_LINE_INDENT))
					holder.createErrorAnnotation(concept, "Concept in bad position");
				if (model != null) {
					Annotation errorAnnotation = holder.createErrorAnnotation(concept, "Concept type not allowed here");
					errorAnnotation.setTextAttributes(TaraSyntaxHighlighter.UNRESOLVED_ACCESS);
				}
			}
			if (model == null) {
				if (!element.getText().equals(CONCEPT))
					holder.createErrorAnnotation(concept, "Concept type not allowed here");
			} else {
				if (findNode(concept, model) == null) {
					Annotation errorAnnotation = holder.createErrorAnnotation
						(concept, TaraBundle.message("Unknown.concept.key.error.message"));
					errorAnnotation.setTextAttributes(TaraSyntaxHighlighter.UNRESOLVED_ACCESS);
				}
				List<TaraConceptReference> incorrectInnerLinks = getIncorrectInnerLinks(concept, model);
				for (TaraConceptReference incorrectInnerLink : incorrectInnerLinks)
					holder.createErrorAnnotation(incorrectInnerLink, TaraBundle.message("Unknown.concept.key.error.message"));
			}
		}
	}

	private IElementType getPreviousToken(Concept concept) {
		PsiElement prevSibling = concept.getPsiElement().getPrevSibling();
		while (prevSibling.getNode().getElementType() == TokenType.WHITE_SPACE)
			prevSibling = prevSibling.getPrevSibling();
		return prevSibling.getNode().getElementType();
	}

	private List<TaraConceptReference> getIncorrectInnerLinks(Concept concept, Model model) {
		List<TaraConceptReference> list = new ArrayList();
		TaraConceptReference[] conceptLinks = concept.getConceptLinks();
		for (TaraConceptReference conceptLink : conceptLinks) {
			Node reference = model.searchNode(TaraUtil.getMetaQualifiedName(conceptLink));
			if (reference == null) list.add(conceptLink);
		}
		return list;
	}
}



