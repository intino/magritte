package monet.tara.intellij;

import com.intellij.codeInsight.FileModificationService;
import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.lang.ASTNode;
import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import monet.tara.intellij.codeinspection.fix.RemoveConceptFix;
import monet.tara.intellij.metamodel.TaraBundle;
import monet.tara.intellij.metamodel.TaraSyntaxHighlighter;
import monet.tara.intellij.metamodel.psi.TaraConceptDefinition;
import monet.tara.intellij.psi.impl.TaraUtil;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TaraAnnotator implements Annotator {

	@Override
	public void annotate(@NotNull final PsiElement element, @NotNull AnnotationHolder holder) {
		if (element instanceof TaraConceptDefinition) {
			final TaraConceptDefinition concept = (TaraConceptDefinition) element;
			List<TaraConceptDefinition> others = TaraUtil.findConcept(concept.getProject(), concept.getNameIdentifier().getText());
			ASTNode identifierNode = concept.getNameIdentifier().getNode();
			if (others.size() != 1) {
				Annotation annotation = holder.createErrorAnnotation(identifierNode, TaraBundle.message("duplicate.concept.key.error.message"));
				annotation.registerFix(new RemoveConceptFix(concept));
			}
			highlightTokens(concept, identifierNode, holder, new TaraSyntaxHighlighter());
		}
	}


	private static void highlightTokens(final TaraConceptDefinition concept, final ASTNode node, final AnnotationHolder holder, TaraSyntaxHighlighter highlighter) {
		Lexer lexer = highlighter.getHighlightingLexer();
		final String s = node.getText();
		lexer.start(s);

		while (lexer.getTokenType() != null) {
			IElementType elementType = lexer.getTokenType();
			TextAttributesKey[] keys = highlighter.getTokenHighlights(elementType);
			for (TextAttributesKey key : keys) {
				Pair<String, HighlightSeverity> pair = TaraSyntaxHighlighter.DISPLAY_NAMES.get(key);
				String displayName = pair.getFirst();
				HighlightSeverity severity = pair.getSecond();
				if (severity != null) {
					int start = lexer.getTokenStart() + node.getTextRange().getStartOffset();
					int end = lexer.getTokenEnd() + node.getTextRange().getStartOffset();
					TextRange textRange = new TextRange(start, end);
					final Annotation annotation;
					if (severity == HighlightSeverity.WARNING) {
						annotation = holder.createWarningAnnotation(textRange, displayName);
					} else if (severity == HighlightSeverity.ERROR) {
						annotation = holder.createErrorAnnotation(textRange, displayName);
					} else {
						annotation = holder.createInfoAnnotation(textRange, displayName);
					}
					TextAttributes attributes = EditorColorsManager.getInstance().getGlobalScheme().getAttributes(key);
					annotation.setEnforcedTextAttributes(attributes);
					if (key == TaraSyntaxHighlighter.BAD_CHARACTER) {
						annotation.registerFix(new IntentionAction() {
							@NotNull
							public String getText() {
								return TaraBundle.message("unescape");
							}

							@NotNull
							public String getFamilyName() {
								return getText();
							}

							public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file) {
								if (!concept.isValid() || !concept.getManager().isInProject(concept)) return false;

								String text = concept.getContainingFile().getText();
								int startOffset = annotation.getStartOffset();
								return text.length() > startOffset && text.charAt(startOffset) == '\\';
							}

							public void invoke(@NotNull Project project, Editor editor, PsiFile file) {
								if (!FileModificationService.getInstance().prepareFileForWrite(file)) return;
								int offset = annotation.getStartOffset();
								if (concept.getContainingFile().getText().charAt(offset) == '\\') {
									editor.getDocument().deleteString(offset, offset + 1);
								}
							}

							public boolean startInWriteAction() {
								return true;
							}
						});
					}
				}
			}
			lexer.advance();
		}
	}
}