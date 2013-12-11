package monet.tara.model.psi.impl;

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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TaraUtil {

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
					for (TaraConceptDefinition conceptDefinition : concepts)
						if (key.equals(conceptDefinition.getIdentifier())) {
							if (result == null)
								result = new ArrayList<TaraConceptDefinition>();
							result.add(conceptDefinition);
						}
				}
			}
		}
		return result != null ? result : Collections.<TaraConceptDefinition>emptyList();
	}

	public static List<TaraConceptDefinition> getConcepts(Project project) {
		List<TaraConceptDefinition> result = null;
		Collection<VirtualFile> virtualFiles =
				FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, TaraFileType.INSTANCE,
						                                               GlobalSearchScope.allScope(project));
		for (VirtualFile virtualFile : virtualFiles) {
			TaraFile taraFile = (TaraFile) PsiManager.getInstance(project).findFile(virtualFile);
			if (taraFile != null) {
				TaraConceptDefinition[] properties = PsiTreeUtil.getChildrenOfType(taraFile, TaraConceptDefinition.class);
				if (properties != null)
					for (TaraConceptDefinition conceptDefinition : properties) {
						if (result == null)
							result = new ArrayList<TaraConceptDefinition>();
						result.add(conceptDefinition);
					}

			}
		}
		return result != null ? result : Collections.<TaraConceptDefinition>emptyList();
	}

	public static List<TaraConceptDefinition> findProperties(Project project) {
		List<TaraConceptDefinition> result = new ArrayList<TaraConceptDefinition>();
		Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, TaraFileType.INSTANCE, GlobalSearchScope.allScope(project));
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
