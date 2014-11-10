package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.Variable;
import siani.tara.lang.Model;
import siani.tara.lang.Node;

import java.awt.*;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;
import static siani.tara.lang.Annotations.Annotation.PROPERTY;
import static siani.tara.lang.Annotations.Annotation.TERMINAL;

public class PropertiesAnnotator extends TaraAnnotator {

	private PsiElement element;

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder annotationHolder) {
		this.element = element;
		holder = annotationHolder;
		if (!Concept.class.isInstance(element)) return;
		Concept concept = (Concept) element;
		if (concept.isProperty())
			checkContainsTerminal(concept);
		Model model = TaraLanguage.getMetaModel(element.getContainingFile());
		Node node;
		if (model == null || (node = findNode(concept, model)) == null) return;
		if (node.getObject().is(PROPERTY)) {
			checkPropertyConstrains(concept);
			Annotation property = holder.createInfoAnnotation(element, "Property");
			property.setTextAttributes(createTextProperty());
			holder.createInfoAnnotation(element.getFirstChild(), "Property").setTextAttributes(createKeyWordProperty());
		}
	}

	private void checkPropertyConstrains(Concept concept) {
		if (concept.getName() != null && !concept.getName().isEmpty())
			holder.createErrorAnnotation(element.getFirstChild(), "Properties are unnamed").setTextAttributes(createKeyWordProperty());
	}

	private void checkContainsTerminal(Concept concept) {
		if ((concept.isComponent() && concept.isProperty()) && concept.isAggregated())
			holder.createErrorAnnotation(element, "An aggregated concept cannot be component or property");
		for (Variable variable : concept.getVariables())
			if (variable.getAnnotations().contains(TERMINAL.getName())) {
				holder.createErrorAnnotation(element, "Property concept cannot have terminal variables");
				return;
			}
		for (Concept inner : concept.getInnerConcepts())
			checkContainsTerminal(inner);
	}

	private TextAttributesKey createTextProperty() {
		return createTextAttributesKey("PROPERTY",
			new TextAttributes(null, null, null, null, Font.ITALIC));
	}

	private TextAttributesKey createKeyWordProperty() {
		return createTextAttributesKey("KEYWORD_PROPERTY",
			new TextAttributes(null, null, null, null, Font.ITALIC + Font.BOLD));
	}
}
