package siani.tara.intellij.refactoring.move;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.refactoring.BaseRefactoringProcessor;
import com.intellij.refactoring.RefactoringBundle;
import com.intellij.refactoring.move.MoveCallback;
import com.intellij.refactoring.move.MoveHandlerDelegate;
import com.intellij.refactoring.util.CommonRefactoringUtil;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.MessageProvider;
import siani.tara.intellij.lang.psi.Node;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;

public class TaraMoveNode extends MoveHandlerDelegate {

	private static final Logger LOG = Logger.getInstance(TaraMoveNode.class.getName());


	@Nullable
	public static PsiNamedElement getElementToMove(@NotNull PsiElement element) {
		if (element instanceof PsiNamedElement) return (PsiNamedElement) element;
		return null;
	}

	@Override
	public boolean canMove(PsiElement[] elements, @Nullable PsiElement targetContainer) {
		for (PsiElement element : elements) {
			if (element instanceof Node) continue;
			return false;
		}
		return super.canMove(elements, targetContainer);
	}

	@Override
	public void doMove(Project project,
	                   PsiElement[] elements,
	                   @Nullable PsiElement targetContainer,
	                   @Nullable MoveCallback callback) {
		PsiNamedElement[] elementsToMove = new PsiNamedElement[elements.length];
		for (int i = 0; i < elements.length; i++) {
			final PsiNamedElement e = getElementToMove(elements[i]);
			if (e == null) return;
			elementsToMove[i] = e;
		}
		String initialDestination = null;
		if (targetContainer instanceof PsiFile) {
			final VirtualFile virtualFile = ((PsiFile) targetContainer).getVirtualFile();
			if (virtualFile != null) initialDestination = FileUtil.toSystemDependentName(virtualFile.getPath());
		}
		final TaraMoveNodeDialog dialog = new TaraMoveNodeDialog(project, elementsToMove, initialDestination);
		dialog.show();
		if (!dialog.isOK()) return;
		final String destination = dialog.getTargetPath();
		final boolean previewUsages = dialog.isPreviewUsages();
		try {
			final BaseRefactoringProcessor processor = new TaraMoveNodeProcessor(project, elementsToMove, destination, previewUsages);
			processor.run();
		} catch (IncorrectOperationException e) {
			LOG.error(e.getMessage(), e);
			CommonRefactoringUtil.showErrorMessage(RefactoringBundle.message("error.title"), e.getMessage(), null, project);
		}
	}

	@Override
	public boolean tryToMove(@NotNull PsiElement element,
	                         @NotNull Project project,
	                         @Nullable DataContext dataContext,
	                         @Nullable PsiReference reference,
	                         @Nullable Editor editor) {
		final PsiNamedElement e = getElementToMove(element);
		if (e instanceof Node) {
			if (TaraPsiImplUtil.getContainerNodeOf(e) == null) {
				PsiElement targetContainer = null;
				if (editor != null)
					targetContainer = PsiDocumentManager.getInstance(project).getPsiFile(editor.getDocument());
				doMove(project, new PsiElement[]{e}, targetContainer, null);
			} else
				CommonRefactoringUtil.showErrorHint(project, editor, MessageProvider.message("refactoring.move.class.or.function.error.selection"), RefactoringBundle.message("error.title"), null);
			return true;
		}
		return false;
	}
}