package siani.tara.intellij.project.view;

import com.intellij.openapi.actionSystem.DataKey;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiUtilCore;

import java.util.Collection;
import java.util.HashSet;

public class NodeTreeView implements Navigatable {
	public static final DataKey<NodeTreeView[]> DATA_KEY = DataKey.create("form.array");

	private final Collection<PsiFile> taraFiles;
	private final PsiClass myClassToBind;

	public NodeTreeView(PsiClass classToBind, Collection<PsiFile> formFiles) {
		myClassToBind = classToBind;
		taraFiles = new HashSet<>(formFiles);
	}

	public boolean equals(Object object) {
		if (object instanceof NodeTreeView) {
			NodeTreeView form = (NodeTreeView) object;
			return taraFiles.equals(form.taraFiles) && myClassToBind.equals(form.myClassToBind);
		} else {
			return false;
		}
	}


	public int hashCode() {
		return taraFiles.hashCode() ^ myClassToBind.hashCode();
	}

	public String getName() {
		return myClassToBind.getName();
	}

	public PsiClass getClassToBind() {
		return myClassToBind;
	}

	public PsiFile[] getTaraFiles() {
		return PsiUtilCore.toPsiFileArray(taraFiles);
	}

	public void navigate(boolean requestFocus) {
		for (PsiFile psiFile : taraFiles) {
			if (psiFile != null && psiFile.canNavigate())
				psiFile.navigate(requestFocus);
		}
	}

	public boolean canNavigateToSource() {
		for (PsiFile psiFile : taraFiles)
			if (psiFile != null && psiFile.canNavigateToSource()) return true;
		return false;
	}

	public boolean canNavigate() {
		for (PsiFile psiFile : taraFiles)
			if (psiFile != null && psiFile.canNavigate()) return true;
		return false;
	}

	public boolean isValid() {
		if (taraFiles.isEmpty()) return false;
		for (PsiFile psiFile : taraFiles) {
			if (!psiFile.isValid()) {
				return false;
			}
		}
		return myClassToBind.isValid();
	}

}
