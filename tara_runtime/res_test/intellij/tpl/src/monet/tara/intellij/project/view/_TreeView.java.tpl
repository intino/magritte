package monet.::projectName::.intellij.project.view;

import com.intellij.openapi.actionSystem.DataKey;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiUtilCore;

import java.util.Collection;
import java.util.HashSet;

public class DefinitionTreeView implements Navigatable {
	public static final DataKey<DefinitionTreeView[]> DATA_KEY = DataKey.create("form.array");

	private final Collection<PsiFile> ::projectName::Files;
	private final PsiClass myClassToBind;

	public DefinitionTreeView(PsiClass classToBind, Collection<PsiFile> formFiles) {
		myClassToBind = classToBind;
		::projectName::Files = new HashSet<>(formFiles);
	}

	public boolean equals(Object object) {
		if (object instanceof DefinitionTreeView) {
			DefinitionTreeView form = (DefinitionTreeView) object;
			return ::projectName::Files.equals(form.::projectName::Files) && myClassToBind.equals(form.myClassToBind);
		} else {
			return false;
		}
	}



	public int hashCode() {
		return ::projectName::Files.hashCode() ^ myClassToBind.hashCode();
	}

	public String getName() {
		return myClassToBind.getName();
	}

	public PsiClass getClassToBind() {
		return myClassToBind;
	}

	public PsiFile[] get::projectProperName::Files() {
		return PsiUtilCore.toPsiFileArray(::projectName::Files);
	}

	public void navigate(boolean requestFocus) {
		for (PsiFile psiFile \: ::projectName::Files) {
			if (psiFile != null && psiFile.canNavigate())
				psiFile.navigate(requestFocus);
		}
	}

	public boolean canNavigateToSource() {
		for (PsiFile psiFile \: ::projectName::Files)
			if (psiFile != null && psiFile.canNavigateToSource()) return true;
		return false;
	}

	public boolean canNavigate() {
		for (PsiFile psiFile \: ::projectName::Files)
			if (psiFile != null && psiFile.canNavigate()) return true;
		return false;
	}

	public boolean isValid() {
		if (::projectName::Files.isEmpty()) return false;
		for (PsiFile psiFile \: ::projectName::Files) {
			if (!psiFile.isValid()) {
				return false;
			}
		}
		return myClassToBind.isValid();
	}

}
