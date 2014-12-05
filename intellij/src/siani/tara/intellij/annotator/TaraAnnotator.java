package siani.tara.intellij.annotator;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiElement;
import siani.tara.intellij.annotator.semanticAnalizers.TaraAnalyzer;
import siani.tara.intellij.highlighting.TaraSyntaxHighlighter;
import siani.tara.intellij.lang.psi.Concept;

import java.util.Map;

public abstract class TaraAnnotator implements Annotator {

	protected AnnotationHolder holder = null;

	protected void analyzeAndAnnotate(TaraAnalyzer analyzer) {
		analyzer.analyze();
		annotateAndFix(analyzer.results());
	}

	protected void annotateAndFix(Map<PsiElement, AnnotateAndFix> annotations) {
		Annotation annotation = null;
		for (Map.Entry<PsiElement, AnnotateAndFix> entry : annotations.entrySet()) {
			switch (entry.getValue().level()) {
				case INFO:
					annotation = holder.createInfoAnnotation(entry.getKey().getNode(), entry.getValue().message());
					break;
				case WARNING:
					annotation = holder.createInfoAnnotation(entry.getKey().getNode(), entry.getValue().message());
					break;
				case ERROR:
					annotation = holder.createErrorAnnotation(entry.getKey().getNode(), entry.getValue().message());
					break;
			}
			if (entry.getValue().textAttributes() != null)
				annotation.setTextAttributes(TaraSyntaxHighlighter.ANNOTATION_ERROR);
			if (entry.getValue().action() != null)
				annotation.registerFix(entry.getValue().action());
		}
	}
	@Deprecated
	protected Annotation annotateAndFix(PsiElement element, AnnotateAndFix annotateAndFix) {
		Annotation errorAnnotation = holder.createErrorAnnotation(element.getNode(), annotateAndFix.message());
		errorAnnotation.registerFix(annotateAndFix.action());
		return errorAnnotation;
	}

	protected boolean isLinkConcept(Concept concept) {
		return concept.getName() == null && concept.getBody() == null;
	}

	public static class AnnotateAndFix {
		private Level level;
		private String message;
		private IntentionAction action;
		private TextAttributesKey attributes;

		public AnnotateAndFix(Level level, String message) {
			this(level, message, (IntentionAction) null);
		}

		public AnnotateAndFix(Level level, String message, IntentionAction action) {
			this(level, message, null, action);
		}

		public AnnotateAndFix(Level level, String message, TextAttributesKey attributes) {
			this(level, message, attributes, null);
		}

		public AnnotateAndFix(Level level, String message, TextAttributesKey attributes, IntentionAction action) {
			this.level = level;
			this.message = message;
			this.attributes = attributes;
			this.action = action;
		}

		public String message() {
			return message;
		}

		public IntentionAction action() {
			return action;
		}

		public Level level() {
			return level;
		}

		public TextAttributesKey textAttributes() {
			return attributes;
		}

		public void setAttributes(TextAttributesKey attributes) {
			this.attributes = attributes;
		}

		public static enum Level {INFO, WARNING, ERROR}
	}
}