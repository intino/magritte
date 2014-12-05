package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.Variable;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.lang.Model;
import siani.tara.lang.Node;

import java.util.ArrayList;
import java.util.List;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;
import static siani.tara.lang.Annotations.Annotation.PROPERTY;
import static siani.tara.lang.Annotations.Annotation.TERMINAL;

public class PropertiesAnnotator extends TaraAnnotator {

	private Concept concept;

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder annotationHolder) {
		if (!Concept.class.isInstance(element)) return;
		holder = annotationHolder;
		this.concept = (Concept) element;
		if (concept.isProperty())
			checkContainsTerminal(concept);
		Model model = TaraLanguage.getMetaModel(element.getContainingFile());
		Node node;
		if (model == null || (node = TaraUtil.findNode(concept, model)) == null) return;
		if (node.getObject().is(PROPERTY) && ((Concept) element).getMetaIdentifier() != null) {
			checkPropertyConstrains(concept);
			holder.createInfoAnnotation(((Concept) element).getMetaIdentifier(), "Property").setTextAttributes(createKeyWordProperty());
		}
	}

	private void checkPropertyConstrains(Concept concept) {
		if (concept.getName() != null && !concept.getName().isEmpty())
			holder.createErrorAnnotation(this.concept.getFirstChild(), "Properties are unnamed");
		if (areMultiple())
			holder.createErrorAnnotation(this.concept.getFirstChild(), "Properties are single");
	}

	private boolean areMultiple() {
		List<Concept> concepts = new ArrayList<>();
		if (concept.getType() == null) return false;
		for (Concept inner : TaraUtil.getInnerConceptsOf(TaraPsiImplUtil.getConceptContainerOf(concept)))
			if (concept.getType().equals(inner.getType()))
				concepts.add(inner);
		return concepts.size() > 1;
	}

	private void checkContainsTerminal(Concept concept) {
		if ((concept.isComponent() && concept.isProperty()) && concept.isAggregated())
			holder.createErrorAnnotation(this.concept, "An aggregated concept cannot be component or property");
		for (Variable variable : concept.getVariables())
			if (variable.getAnnotations().contains(TERMINAL.getName())) {
				holder.createErrorAnnotation(this.concept, "Property concept cannot have terminal variables");
				return;
			}
		for (Concept inner : concept.getInnerConcepts())
			checkContainsTerminal(inner);
	}

	private TextAttributesKey createKeyWordProperty() {
		return createTextAttributesKey("KEYWORD_PROPERTY", DefaultLanguageHighlighterColors.STATIC_METHOD);
	}
}
