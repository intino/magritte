package siani.tara.intellij.refactoring.move;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiNamedElement;
import com.intellij.refactoring.move.moveFilesOrDirectories.MoveFileHandler;
import com.intellij.usageView.UsageInfo;
import com.intellij.util.IncorrectOperationException;
import siani.tara.intellij.codeinsight.imports.TaraImportOptimizer;
import siani.tara.intellij.lang.file.TaraFileType;
import siani.tara.intellij.lang.psi.HeaderReference;
import siani.tara.intellij.lang.psi.TaraFile;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.intellij.refactoring.TaraRefactoringUtil;

import java.io.File;
import java.util.*;

public class TaraFileMoveHandler extends MoveFileHandler {
	private static final Key<PsiNamedElement> REFERENCED_ELEMENT = Key.create("TARA_REFERENCED_ELEMENT");

	@Override
	public boolean canProcessElement(PsiFile element) {
		return element.getFileType() == TaraFileType.INSTANCE;
	}

	@Override
	public void prepareMovedFile(PsiFile file, PsiDirectory moveDestination, Map<PsiElement, PsiElement> oldToNewMap) {
		if (file != null) {
			oldToNewMap.put(file, moveDestination);
			final Collection<VirtualFile> roots = TaraUtil.getSourceRoots(file);
			PsiDirectory root = moveDestination;
			while (root != null && !roots.contains(root.getVirtualFile())) root = root.getParentDirectory();
			if (root == null) return;
			String rootPath = root.getVirtualFile().getPath();
			Project project = moveDestination.getProject();
			Module moduleForFile = ProjectRootManager.getInstance(project).getFileIndex().getModuleForFile(moveDestination.getVirtualFile());
			String path = moveDestination.getVirtualFile().getPath().replace(rootPath, "").replaceAll(File.separator, ".").substring(1);
			TaraFile taraFile = (TaraFile) file;
			taraFile.setBox(project.getName() + "." + moduleForFile.getName() + "." + path + "." + taraFile.getPresentableName());
		}
	}

	@Override
	public List<UsageInfo> findUsages(PsiFile file, PsiDirectory newParent, boolean searchInComments, boolean searchInNonJavaFiles) {
		if (file != null) {
			final List<UsageInfo> usages = TaraRefactoringUtil.findUsages(file, false);
			for (UsageInfo usage : usages) {
				final PsiElement element = usage.getElement();
				if (element != null) element.putCopyableUserData(REFERENCED_ELEMENT, file);
			}
			return usages;
		}
		return null;
	}

	@Override
	public void retargetUsages(List<UsageInfo> usages, Map<PsiElement, PsiElement> oldToNewMap) {
		final Set<PsiFile> updatedFiles = new HashSet<>();
		for (UsageInfo usage : usages) {
			final PsiElement reference = usage.getElement();
			if (reference != null) {
				final PsiNamedElement newElement = reference.getCopyableUserData(REFERENCED_ELEMENT);
				reference.putCopyableUserData(REFERENCED_ELEMENT, null);
				if (newElement != null) {
					updatedFiles.add((PsiFile) newElement);
					final TaraFile file = (TaraFile) reference.getContainingFile();
					if (!(reference.getParent() instanceof HeaderReference) && inTheSamePackageAsReference(reference, usages)) {
						updatedFiles.add(file);
						TaraRefactoringUtil.addImport(file, (TaraFile) newElement);
					} else if (reference.getParent() instanceof HeaderReference) {
						updatedFiles.add(file);
						TaraRefactoringUtil.updateImportOfElement((HeaderReference) reference.getParent(), (TaraFile) newElement);
					}
				}
			}
			if (!updatedFiles.isEmpty()) {
				final TaraImportOptimizer optimizer = new TaraImportOptimizer();
				for (PsiFile file : updatedFiles)
					optimizer.processFile(file).run();
			}
		}
	}

	@Override
	public void updateMovedFile(PsiFile psiFile) throws IncorrectOperationException {
		TaraFile file = (TaraFile) psiFile;

	}


	private boolean inTheSamePackageAsReference(PsiElement element, List<UsageInfo> usages) {
		for (UsageInfo usage : usages)
			if (usage.getElement() != null && usage.getElement().getContainingFile().equals(element.getContainingFile()) &&
				usage.getElement().getParent() instanceof HeaderReference) return false;
		return true;
	}
}