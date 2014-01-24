package monet.::projectName::.intellij.psi.impl;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.indexing.FileBasedIndex;
import monet.::projectName::.intellij.metamodel.file.::projectProperName::File;
import monet.::projectName::.intellij.metamodel.file.::projectProperName::FileType;
import monet.::projectName::.intellij.psi.::projectProperName::Definition;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ::projectProperName::Util {

	\@NotNull
	public static List<::projectProperName::Definition> findDefinition(Project project, String key) {
		List<::projectProperName::Definition> result = null;
		Collection<VirtualFile> virtualFiles =
			FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, ::projectProperName::FileType.INSTANCE,
				GlobalSearchScope.allScope(project));
		for (VirtualFile virtualFile : virtualFiles) {
			::projectProperName::File ::projectName::File = (::projectProperName::File) PsiManager.getInstance(project).findFile(virtualFile);
			if (::projectName::File != null) {
				::projectProperName::Definition[] definitions = PsiTreeUtil.getChildrenOfType(::projectName::File, ::projectProperName::Definition.class);
				if (definitions != null)
					for (::projectProperName::Definition definition : definitions)
						if (key.equals(definition.getNameIdentifier().getText())) {
							if (result == null)
								result = new ArrayList<>();
							result.add(definition);
						}
			}
		}
		return result != null ? result : Collections.<::projectProperName::Definition>emptyList();
	}

	\@NotNull
	public static List<::projectProperName::Definition> getDefinitions(Project project) {
		List<::projectProperName::Definition> result = null;
		Collection<VirtualFile> virtualFiles =
			FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, ::projectProperName::FileType.INSTANCE,
				GlobalSearchScope.allScope(project));
		for (VirtualFile virtualFile : virtualFiles) {
			::projectProperName::File ::projectName::File = (::projectProperName::File) PsiManager.getInstance(project).findFile(virtualFile);
			if (::projectName::File != null) {
				::projectProperName::Definition[] definitions = PsiTreeUtil.getChildrenOfType(::projectName::File, ::projectProperName::Definition.class);
				if (definitions != null)
					for (::projectProperName::Definition definition : definitions) {
						if (result == null)
							result = new ArrayList<>();
						result.add(definition);
					}

			}
		}
		return result != null ? result : Collections.<::projectProperName::Definition>emptyList();
	}

	\@NotNull
	public static List<::projectProperName::Definition> findProperties(Project project) {
		List<::projectProperName::Definition> result = new ArrayList<>();
		Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().
			getContainingFiles(FileTypeIndex.NAME, ::projectProperName::FileType.INSTANCE, GlobalSearchScope.allScope(project));
		for (VirtualFile virtualFile : virtualFiles) {
			::projectProperName::File ::projectName::File = (::projectProperName::File) PsiManager.getInstance(project).findFile(virtualFile);
			if (::projectName::File != null) {
				::projectProperName::Definition[] properties = PsiTreeUtil.getChildrenOfType(::projectName::File, ::projectProperName::Definition.class);
				if (properties != null)
					Collections.addAll(result, properties);
			}
		}
		return result;
	}


}
