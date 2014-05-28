package monet.::projectName::.intellij.refactoring.move;

import com.intellij.openapi.util.Key;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiNamedElement;
import com.intellij.refactoring.move.moveFilesOrDirectories.MoveFileHandler;
import com.intellij.usageView.UsageInfo;
import com.intellij.util.IncorrectOperationException;
import monet.::projectName::.intellij.codeinsight.imports.::projectProperName::ImportOptimizer;
import monet.::projectName::.intellij.lang.file.::projectProperName::FileType;
import monet.::projectName::.intellij.lang.psi.HeaderReference;
import monet.::projectName::.intellij.lang.psi.::projectProperName::File;
import monet.::projectName::.intellij.lang.psi.impl.::projectProperName::Util;
import monet.::projectName::.intellij.refactoring.::projectProperName::RefactoringUtil;

import java.io.File;
import java.util.*;

public class ::projectProperName::FileMoveHandler extends MoveFileHandler {
	private static final Key<PsiNamedElement> REFERENCED_ELEMENT = Key.create("::projectUpperName::_REFERENCED_ELEMENT");

	\@Override
	public boolean canProcessElement(PsiFile element) {
		return element.getFileType() == ::projectProperName::FileType.INSTANCE;
	}

	\@Override
	public void prepareMovedFile(PsiFile file, PsiDirectory moveDestination, Map<PsiElement, PsiElement> oldToNewMap) {
		if (file != null) {
			oldToNewMap.put(file, moveDestination);
			final Collection<VirtualFile> roots = ::projectProperName::Util.getSourceRoots(file);
			PsiDirectory root = moveDestination;
			while (root != null && !roots.contains(root.getVirtualFile())) root = root.getParentDirectory();
			if (root == null) return;
			String rootPath = root.getVirtualFile().getPath();
			String path = moveDestination.getVirtualFile().getPath().replace(rootPath, "").replaceAll(File.separator, ".").substring(1);
			((::projectProperName::File) file).setPackage(path);
		}
	}

	\@Override
	public List<UsageInfo> findUsages(PsiFile file, PsiDirectory newParent, boolean searchInComments,
	                                  boolean searchInNonJavaFiles) {
		if (file != null) {
			final List<UsageInfo> usages = ::projectProperName::RefactoringUtil.findUsages(file, false);
			for (UsageInfo usage \: usages) {
				final PsiElement element = usage.getElement();
				if (element != null) element.putCopyableUserData(REFERENCED_ELEMENT, file);
			}
			return usages;
		}
		return null;
	}

	\@Override
	public void retargetUsages(List<UsageInfo> usages, Map<PsiElement, PsiElement> oldToNewMap) {
		final Set<PsiFile> updatedFiles = new HashSet<>();
		for (UsageInfo usage \: usages) {
			final PsiElement reference = usage.getElement();
			if (reference != null) {
				final PsiNamedElement newElement = reference.getCopyableUserData(REFERENCED_ELEMENT);
				reference.putCopyableUserData(REFERENCED_ELEMENT, null);
				if (newElement != null) {
					updatedFiles.add((PsiFile) newElement);
					final ::projectProperName::File file = (::projectProperName::File) reference.getContainingFile();
					if (!(reference.getParent() instanceof HeaderReference) && inTheSamePackageAsReference(reference, usages)) {
						updatedFiles.add(file);
						::projectProperName::RefactoringUtil.addImport(file, (::projectProperName::File) newElement);
					} else if (reference.getParent() instanceof HeaderReference) {
						updatedFiles.add(file);
						::projectProperName::RefactoringUtil.updateImportOfElement((HeaderReference) reference.getParent(), (::projectProperName::File) newElement);
					}
				}
			}
			if (!updatedFiles.isEmpty()) {
				final ::projectProperName::ImportOptimizer optimizer = new ::projectProperName::ImportOptimizer();
				for (PsiFile file \: updatedFiles)
					optimizer.processFile(file).run();
			}
		}
	}

	\@Override
	public void updateMovedFile(PsiFile psiFile) throws IncorrectOperationException {
		::projectProperName::File file = (::projectProperName::File) psiFile;

	}


	private boolean inTheSamePackageAsReference(PsiElement element, List<UsageInfo> usages) {
		for (UsageInfo usage \: usages)
			if (usage.getElement() != null && usage.getElement().getContainingFile().equals(element.getContainingFile()) &&
				usage.getElement().getParent() instanceof HeaderReference) return false;
		return true;
	}
}