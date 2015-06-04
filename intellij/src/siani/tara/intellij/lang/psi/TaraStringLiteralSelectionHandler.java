package siani.tara.intellij.lang.psi;

import com.intellij.codeInsight.editorActions.ExtendWordSelectionHandler;
import com.intellij.lang.injection.InjectedLanguageManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.ElementManipulators;
import com.intellij.psi.PsiElement;

import java.util.ArrayList;
import java.util.List;

public class TaraStringLiteralSelectionHandler implements ExtendWordSelectionHandler {
	@Override
	public boolean canSelect(PsiElement e) {
		return e.getParent() instanceof StringValue &&
			!InjectedLanguageManager.getInstance(e.getProject()).isInjectedFragment(e.getContainingFile());
	}

	@Override
	public List<TextRange> select(PsiElement e, CharSequence editorText, int cursorOffset, Editor editor) {
		final List<TextRange> result = new ArrayList<>();
		final PsiElement parent = e.getParent();
		result.add(ElementManipulators.getValueTextRange(parent).shiftRight(parent.getTextOffset()));
		return result;
	}
}