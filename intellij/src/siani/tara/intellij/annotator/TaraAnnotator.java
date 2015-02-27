package siani.tara.intellij.annotator;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiElement;
import siani.tara.intellij.annotator.semanticanalizer.TaraAnalyzer;

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
					annotation = holder.createWarningAnnotation(entry.getKey().getNode(), entry.getValue().message());
					break;
				case ERROR:
					annotation = holder.createErrorAnnotation(entry.getKey().getNode(), entry.getValue().message());
					break;
			}
			if (entry.getValue().textAttributes() != null)
				annotation.setTextAttributes(entry.getValue().attributes);
			for (IntentionAction action : entry.getValue().actions()) annotation.registerFix(action);
		}
	}

	@Deprecated
	protected Annotation annotateAndFix(PsiElement element, AnnotateAndFix annotateAndFix) {
		Annotation errorAnnotation = holder.createErrorAnnotation(element.getNode(), annotateAndFix.message());
		for (IntentionAction action : annotateAndFix.actions()) errorAnnotation.registerFix(action);
		return errorAnnotation;
	}

	public static class AnnotateAndFix {
		private Level level;
		private String message;
		private IntentionAction actions[] = IntentionAction.EMPTY_ARRAY;
		private TextAttributesKey attributes;

		public AnnotateAndFix(Level level, String message) {
			this(level, message, null, IntentionAction.EMPTY_ARRAY);
		}

		public AnnotateAndFix(Level level, String message, IntentionAction... actions) {
			this(level, message, null, actions);
		}

		public AnnotateAndFix(Level level, String message, TextAttributesKey attributes) {
			this(level, message, attributes, IntentionAction.EMPTY_ARRAY);
		}

		public AnnotateAndFix(Level level, String message, TextAttributesKey attributes, IntentionAction... actions) {
			this.level = level;
			this.message = message;
			this.attributes = attributes;
			this.actions = actions;
		}

		public String message() {
			return message;
		}

		public IntentionAction[] actions() {
			return actions;
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

		public void setActions(IntentionAction[] actions) {
			this.actions = actions.clone();
		}

		public static enum Level {
			INFO, WARNING, ERROR
		}
	}
}