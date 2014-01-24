package monet.tafat.intellij.psi.impl;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.indexing.FileBasedIndex;
import monet.tafat.intellij.metamodel.file.TafatFile;
import monet.tafat.intellij.metamodel.file.TafatFileType;
import monet.tafat.intellij.psi.TafatDefinition;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TafatUtil {

	@NotNull
	public static List<TafatDefinition> findDefinition(Project project, String key) {
		List<TafatDefinition> result = null;
		Collection<VirtualFile> virtualFiles =
			FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, TafatFileType.INSTANCE,
				GlobalSearchScope.allScope(project));
		for (VirtualFile virtualFile : virtualFiles) {
			TafatFile tafatFile = (TafatFile) PsiManager.getInstance(project).findFile(virtualFile);
			if (tafatFile != null) {
				TafatDefinition[] definitions = PsiTreeUtil.getChildrenOfType(tafatFile, TafatDefinition.class);
				if (definitions != null)
					for (TafatDefinition definition : definitions)
						if (key.equals(definition.getNameIdentifier().getText())) {
							if (result == null)
								result = new ArrayList<>();
							result.add(definition);
						}
			}
		}
		return result != null ? result : Collections.<TafatDefinition>emptyList();
	}

	@NotNull
	public static List<TafatDefinition> getDefinitions(Project project) {
		List<TafatDefinition> result = null;
		Collection<VirtualFile> virtualFiles =
			FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, TafatFileType.INSTANCE,
				GlobalSearchScope.allScope(project));
		for (VirtualFile virtualFile : virtualFiles) {
			TafatFile tafatFile = (TafatFile) PsiManager.getInstance(project).findFile(virtualFile);
			if (tafatFile != null) {
				TafatDefinition[] definitions = PsiTreeUtil.getChildrenOfType(tafatFile, TafatDefinition.class);
				if (definitions != null)
					for (TafatDefinition definition : definitions) {
						if (result == null)
							result = new ArrayList<>();
						result.add(definition);
					}

			}
		}
		return result != null ? result : Collections.<TafatDefinition>emptyList();
	}

	@NotNull
	public static List<TafatDefinition> findProperties(Project project) {
		List<TafatDefinition> result = new ArrayList<>();
		Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().
			getContainingFiles(FileTypeIndex.NAME, TafatFileType.INSTANCE, GlobalSearchScope.allScope(project));
		for (VirtualFile virtualFile : virtualFiles) {
			TafatFile tafatFile = (TafatFile) PsiManager.getInstance(project).findFile(virtualFile);
			if (tafatFile != null) {
				TafatDefinition[] properties = PsiTreeUtil.getChildrenOfType(tafatFile, TafatDefinition.class);
				if (properties != null)
					Collections.addAll(result, properties);
			}
		}
		return result;
	}


}
