package monet.tara.transpiler.intellij.psi.impl;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.indexing.FileBasedIndex;
import monet.tara.transpiler.intellij.metamodel.file.TaraFile;
import monet.tara.transpiler.intellij.metamodel.file.TaraFileType;
import monet.tara.transpiler.intellij.psi.TaraConcept;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TaraUtil {

	@NotNull
	public static List<TaraConcept> findConcept(Project project, String key) {
		List<TaraConcept> result = null;
		Collection<VirtualFile> virtualFiles =
			FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, TaraFileType.INSTANCE,
				GlobalSearchScope.allScope(project));
		for (VirtualFile virtualFile : virtualFiles) {
			TaraFile taraFile = (TaraFile) PsiManager.getInstance(project).findFile(virtualFile);
			if (taraFile != null) {
				TaraConcept[] concepts = PsiTreeUtil.getChildrenOfType(taraFile, TaraConcept.class);
				if (concepts != null)
					for (TaraConcept concept : concepts)
						if (key.equals(concept.getNameIdentifier().getText())) {
							if (result == null)
								result = new ArrayList<>();
							result.add(concept);
						}
			}
		}
		return result != null ? result : Collections.<TaraConcept>emptyList();
	}

	@NotNull
	public static List<TaraConcept> getConcepts(Project project) {
		List<TaraConcept> result = null;
		Collection<VirtualFile> virtualFiles =
			FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, TaraFileType.INSTANCE,
				GlobalSearchScope.allScope(project));
		for (VirtualFile virtualFile : virtualFiles) {
			TaraFile taraFile = (TaraFile) PsiManager.getInstance(project).findFile(virtualFile);
			if (taraFile != null) {
				TaraConcept[] concepts = PsiTreeUtil.getChildrenOfType(taraFile, TaraConcept.class);
				if (concepts != null)
					for (TaraConcept concept : concepts) {
						if (result == null)
							result = new ArrayList<>();
						result.add(concept);
					}

			}
		}
		return result != null ? result : Collections.<TaraConcept>emptyList();
	}

	@NotNull
	public static List<TaraConcept> findProperties(Project project) {
		List<TaraConcept> result = new ArrayList<>();
		Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().
			getContainingFiles(FileTypeIndex.NAME, TaraFileType.INSTANCE, GlobalSearchScope.allScope(project));
		for (VirtualFile virtualFile : virtualFiles) {
			TaraFile taraFile = (TaraFile) PsiManager.getInstance(project).findFile(virtualFile);
			if (taraFile != null) {
				TaraConcept[] properties = PsiTreeUtil.getChildrenOfType(taraFile, TaraConcept.class);
				if (properties != null)
					Collections.addAll(result, properties);
			}
		}
		return result;
	}


}
