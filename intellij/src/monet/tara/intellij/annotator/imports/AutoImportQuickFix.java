package monet.tara.intellij.annotator.imports;

import com.intellij.codeInsight.FileModificationService;
import com.intellij.codeInsight.daemon.impl.ShowAutoImportPass;
import com.intellij.codeInsight.hint.HintManager;
import com.intellij.codeInsight.intention.HighPriorityAction;
import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectFileIndex;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileSystemItem;
import com.intellij.psi.PsiReference;
import com.intellij.psi.util.QualifiedName;
import com.intellij.util.IncorrectOperationException;
import monet.tara.intellij.metamodel.psi.Concept;
import monet.tara.intellij.metamodel.psi.TaraImportStatement;
import monet.tara.intellij.metamodel.psi.TaraPsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;

public class AutoImportQuickFix implements LocalQuickFix, HighPriorityAction {

	private final Concept myNode;
	private final PsiReference myReference;
	private final ArrayList<ImportCandidateHolder> myImports;
	private final String myInitialName;
	boolean myUseQualifiedImport;
	private boolean myExpended;

	/**
	 * Creates a new, empty fix object.
	 *
	 * @param node    to which the fix applies.
	 * @param qualify if true, add an "import ..." statement and qualify the name; else use "from ... import name"
	 */
	public AutoImportQuickFix(Concept node, PsiReference reference, boolean qualify) {
		myNode = node;
		myReference = reference;
		myImports = new ArrayList<>();
		myInitialName = getNameToImport();
		myUseQualifiedImport = qualify;
		myExpended = false;
	}

	/**
	 * Adds another import source.
	 *
	 * @param importable    an element that could be imported either from import element or from file.
	 * @param file          the file which is the source of the importable
	 * @param importElement an existing import element that can be a source for the importable.
	 */
	public void addImport(@NotNull PsiElement importable, @NotNull PsiFile file, @Nullable TaraPsiElement importElement) {
		myImports.add(new ImportCandidateHolder(importable, file, (TaraImportStatement) importElement, null));
	}

	/**
	 * Adds another import source.
	 *
	 * @param importable an element that could be imported either from import element or from file.
	 * @param file       the file which is the source of the importable
	 * @param path       import path for the file, as a qualified name (a.b.c)
	 */
	public void addImport(@NotNull PsiElement importable, @NotNull PsiFileSystemItem file, @Nullable QualifiedName path) {
		myImports.add(new ImportCandidateHolder(importable, file, null, path));
	}

	@NotNull
	public String getText() {
		if (myUseQualifiedImport) return "MODULE";//TaraBundle.message("ACT.qualify.with.module");
		else if (myImports.size() == 1) {
			return "Import '" + myImports.get(0).getPresentableText(getNameToImport()) + "'";
		} else {
			return "USE IMPORT";//TaraBundle.message("ACT.NAME.use.import");
		}
	}

	@NotNull
	public String getName() {
		return getText();
	}

	@NotNull
	public String getFamilyName() {
		return "Import";//TaraBundle.message("ACT.FAMILY.import");
	}

	public boolean showHint(Editor editor) {
		if (myNode == null || !myNode.isValid() || myNode.getName() == null || myImports.size() <= 0) {
			return false; // TODO: also return false if an on-the-fly unambiguous fix is possible?
		}
		if (ImportFromExistingAction.isResolved(myReference)) {
			return false;
		}
		if (HintManager.getInstance().hasShownHintsThatWillHideByOtherHint(true)) {
			return false;
		}
		String name = getNameToImport();
		if (!name.equals(myInitialName)) {
			return false;
		}
		final String message = ShowAutoImportPass.getMessage(
			myImports.size() > 1,
			ImportCandidateHolder.getQualifiedName(name, myImports.get(0).getPath(), myImports.get(0).getImportElement())
		);
		final ImportFromExistingAction action = new ImportFromExistingAction(myNode, myImports, name, myUseQualifiedImport);
		action.onDone(new Runnable() {
			public void run() {
				myExpended = true;
			}
		});
		HintManager.getInstance().showQuestionHint(
			editor, message,
			myNode.getTextOffset(),
			myNode.getTextRange().getEndOffset(), action);
		return true;
	}

	public boolean isAvailable() {
		return !myExpended && myNode != null && myNode.isValid() && myImports.size() > 0;
	}

	public void applyFix(@NotNull Project project, @NotNull ProblemDescriptor descriptor) {
		invoke(descriptor.getPsiElement().getContainingFile());
		myExpended = true;
	}

	public void invoke(PsiFile file) throws IncorrectOperationException {
		// make sure file is committed, writable, etc
		if (!myReference.getElement().isValid()) return;
		if (!FileModificationService.getInstance().prepareFileForWrite(file)) return;
		if (ImportFromExistingAction.isResolved(myReference)) return;
		// act
		ImportFromExistingAction action = new ImportFromExistingAction(myNode, myImports, getNameToImport(), myUseQualifiedImport);
		action.execute(); // assume that action runs in WriteAction on its own behalf
		myExpended = true;
	}

	public void sortCandidates() {
		Collections.sort(myImports);
	}


	public String getNameToImport() {
		final String text = myReference.getElement().getText();
		return myReference.getRangeInElement().substring(text); // text of the part we're working with
	}

	public boolean hasProjectImports() {
		ProjectFileIndex fileIndex = ProjectFileIndex.SERVICE.getInstance(myNode.getProject());
		for (ImportCandidateHolder anImport : myImports) {
			VirtualFile file = anImport.getFile().getVirtualFile();
			if (file != null && fileIndex.isInContent(file)) {
				return true;
			}
		}
		return false;
	}
}