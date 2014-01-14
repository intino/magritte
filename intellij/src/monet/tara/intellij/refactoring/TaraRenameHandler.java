package monet.tara.intellij.refactoring;

import com.intellij.codeInsight.TargetElementUtilBase;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.ScrollType;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiReference;
import com.intellij.psi.ResolveResult;
import com.intellij.psi.impl.source.resolve.reference.impl.PsiMultiReference;
import com.intellij.refactoring.rename.PsiElementRenameHandler;
import monet.tara.intellij.TaraReference;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by oroncal on 07/01/14.
 */
public class TaraRenameHandler extends PsiElementRenameHandler {

	public boolean isAvailableOnDataContext(final DataContext dataContext) {
		final Editor editor = LangDataKeys.EDITOR.getData(dataContext);
		if (editor != null) {
			if (getPsiElement(editor) != null) return true;
		}
		return false;
	}

	@Nullable
	private static PsiElement getPsiElement(final Editor editor) {
		final PsiReference reference = TargetElementUtilBase.findReference(editor);
		if (reference instanceof TaraReference) {
			final ResolveResult[] resolveResults = ((TaraReference) reference).multiResolve(false);
			return resolveResults.length > 0 ? resolveResults[0].getElement() : null;
		} else if (reference instanceof PsiMultiReference) {
			final PsiReference[] references = ((PsiMultiReference) reference).getReferences();
			for (PsiReference psiReference : references)
				if (psiReference instanceof TaraReference) {
					final ResolveResult[] resolveResults = ((TaraReference) psiReference).multiResolve(false);
					if (resolveResults.length > 0) return resolveResults[0].getElement();
				}
		}
		return null;
	}

	@Override
	public void invoke(@NotNull final Project project, final Editor editor, final PsiFile file, final DataContext dataContext) {
		PsiElement element = getPsiElement(editor);
		editor.getScrollingModel().scrollToCaret(ScrollType.MAKE_VISIBLE);
		final PsiElement nameSuggestionContext = file.findElementAt(editor.getCaretModel().getOffset());
		invoke(element, project, nameSuggestionContext, editor);
	}
}