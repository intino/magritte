package monet.tara.metamodel.psi.impl;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.indexing.FileBasedIndex;
import monet.tara.metamodel.file.TaraFile;
import monet.tara.metamodel.file.TaraFileType;
import monet.tara.metamodel.psi.TaraConceptDefinition;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TaraUtil {

	@NotNull
	public static List<TaraConceptDefinition> findConcept(Project project, String key) {
		List<TaraConceptDefinition> result = null;
		Collection<VirtualFile> virtualFiles =
				FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, TaraFileType.INSTANCE,
						GlobalSearchScope.allScope(project));
		for (VirtualFile virtualFile : virtualFiles) {
			TaraFile taraFile = (TaraFile) PsiManager.getInstance(project).findFile(virtualFile);
			if (taraFile != null) {
				TaraConceptDefinition[] concepts = PsiTreeUtil.getChildrenOfType(taraFile, TaraConceptDefinition.class);
				if (concepts != null) {
					for (TaraConceptDefinition concept : concepts) {
						if (key.equals(concept.getNameIdentifier().getText())) {
							if (result == null)
								result = new ArrayList<>();
							result.add(concept);
						}
					}
				}
			}
		}
		return result != null ? result : Collections.<TaraConceptDefinition>emptyList();
	}

	@NotNull
	public static List<TaraConceptDefinition> getConcepts(Project project) {
		List<TaraConceptDefinition> result = null;
		Collection<VirtualFile> virtualFiles =
				FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, TaraFileType.INSTANCE,
						GlobalSearchScope.allScope(project));
		for (VirtualFile virtualFile : virtualFiles) {
			TaraFile taraFile = (TaraFile) PsiManager.getInstance(project).findFile(virtualFile);
			if (taraFile != null) {
				TaraConceptDefinition[] concepts = PsiTreeUtil.getChildrenOfType(taraFile, TaraConceptDefinition.class);
				if (concepts != null)
					for (TaraConceptDefinition concept : concepts) {
						if (result == null)
							result = new ArrayList<>();
						result.add(concept);
					}

			}
		}
		return result != null ? result : Collections.<TaraConceptDefinition>emptyList();
	}

	@NotNull
	public static List<TaraConceptDefinition> findProperties(Project project) {
		List<TaraConceptDefinition> result = new ArrayList<>();
		Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().
				getContainingFiles(FileTypeIndex.NAME, TaraFileType.INSTANCE, GlobalSearchScope.allScope(project));
		for (VirtualFile virtualFile : virtualFiles) {
			TaraFile simpleFile = (TaraFile) PsiManager.getInstance(project).findFile(virtualFile);
			if (simpleFile != null) {
				TaraConceptDefinition[] properties = PsiTreeUtil.getChildrenOfType(simpleFile, TaraConceptDefinition.class);
				if (properties != null)
					Collections.addAll(result, properties);
			}
		}
		return result;
	}


}
