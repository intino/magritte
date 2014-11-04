package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.EffectType;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.psi.PsiElement;
import com.intellij.ui.JBColor;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.lang.Model;
import siani.tara.lang.Node;

import java.awt.*;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;
import static siani.tara.lang.Annotations.Annotation.PROPERTY;

public class PropertiesAnnotator extends TaraAnnotator {
	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder annotationHolder) {
		if (!Concept.class.isInstance(element)) return;
		Concept concept = (Concept) element;
		Model model = TaraLanguage.getMetaModel(element.getContainingFile());
		Node node;
		if (model == null || (node = findNode(concept, model)) == null) return;
		if (node.getObject().is(PROPERTY)) {
			Annotation property = annotationHolder.createInfoAnnotation(element, "Property");
			property.setTextAttributes(createTextProperty());
		}
	}

	private TextAttributesKey createTextProperty() {
		return createTextAttributesKey("PROPERTY",
			new TextAttributes(null, null, JBColor.ORANGE, EffectType.LINE_UNDERSCORE, Font.PLAIN));
	}
}
