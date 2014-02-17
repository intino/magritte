package monet.tara.compiler.intellij.psi.impl;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.indexing.FileBasedIndex;
import monet.tara.compiler.intellij.metamodel.file.TaraFile;
import monet.tara.compiler.intellij.metamodel.file.TaraFileType;
import monet.tara.compiler.intellij.psi.IConcept;
import monet.tara.compiler.intellij.psi.TaraComponent;
import monet.tara.compiler.intellij.psi.TaraConcept;
import monet.tara.compiler.intellij.psi.TaraExtendedConcept;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TaraUtil {

	@NotNull
	public static List<IConcept> findConcept(Project project, String key) {
		List<IConcept> result = null;
		Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().
			getContainingFiles(FileTypeIndex.NAME, TaraFileType.INSTANCE, GlobalSearchScope.allScope(project));
		for (VirtualFile virtualFile : virtualFiles) {
			TaraFile taraFile = (TaraFile) PsiManager.getInstance(project).findFile(virtualFile);
			if (taraFile != null) {
				IConcept[] concepts = PsiTreeUtil.getChildrenOfType(taraFile, TaraConcept.class);
				if (concepts != null)
					for (IConcept concept : concepts)
						if (key.equals(concept.getName())) {
							if (result == null)
								result = new ArrayList<>();
							result.add(concept);
						}
			}
		}
		return result != null ? result : Collections.<IConcept>emptyList();
	}

	@NotNull
	public static List<IConcept> findConcept(Project project, String key, PsiElement ctx) {
		List<IConcept> result = new ArrayList<>();
		Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().
			getContainingFiles(FileTypeIndex.NAME, TaraFileType.INSTANCE, GlobalSearchScope.allScope(project));
		for (VirtualFile virtualFile : virtualFiles) {
			TaraFile taraFile = (TaraFile) PsiManager.getInstance(project).findFile(virtualFile);
			if (taraFile != null) {
				IConcept[] concepts = PsiTreeUtil.getChildrenOfType(taraFile, TaraConcept.class);
				if (concepts != null && !(concepts.length == 0))
					for (IConcept concept : concepts)
						if (key.equals(concept.getName()))
							result.add(concept);
				if (ctx.getContainingFile().equals(PsiManager.getInstance(project).findFile(virtualFile)))
					findComponents(key, (TaraExtendedConcept) ctx, result);
			}
		}
		return result;
	}

	private static List<IConcept> findComponents(String key, TaraExtendedConcept ctx, List<IConcept> result) {
		List<IConcept> components = TaraPsiImplUtil.getChildren(ctx);
		if (components != null)
			for (IConcept component : components)
				if (key.equals(component.getName()))
					result.add(component);
		return result;
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
	public static List<IConcept> findConcepts(Project project) {
		List<IConcept> result = new ArrayList<>();
		Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().
			getContainingFiles(FileTypeIndex.NAME, TaraFileType.INSTANCE, GlobalSearchScope.allScope(project));
		for (VirtualFile virtualFile : virtualFiles) {
			TaraFile taraFile = (TaraFile) PsiManager.getInstance(project).findFile(virtualFile);
			if (taraFile != null) {
				TaraConcept[] concepts = PsiTreeUtil.getChildrenOfType(taraFile, TaraConcept.class);
				TaraComponent[] components = PsiTreeUtil.getChildrenOfType(taraFile, TaraComponent.class);
				if (concepts != null)
					Collections.addAll(result, concepts);
				if (components != null)
					Collections.addAll(result, components);
			}
		}
		return result;
	}


}
