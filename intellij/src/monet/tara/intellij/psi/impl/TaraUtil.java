package monet.tara.intellij.psi.impl;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.indexing.FileBasedIndex;
import monet.tara.intellij.metamodel.file.TaraFile;
import monet.tara.intellij.metamodel.file.TaraFileType;
import monet.tara.compiler.intellij.psi.*;
import monet.tara.intellij.psi.IConcept;
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
	public static List<IConcept> findDuplicates(Project project, IConcept concept) {
		if (concept instanceof TaraConcept)
			return findConcept(project, concept.getIdentifierNode().getText());
		if (concept instanceof TaraComponent)
			return findDuplicatesInComponent((TaraComponent) concept);
		return findDuplicatesInFromComponent((TaraFromComponent) concept);
	}

	private static List<IConcept> findDuplicatesInFromComponent(TaraFromComponent component) {
		List<IConcept> result = new ArrayList<>();
		List<TaraFromComponent> components = ((TaraFromBody) component.getParent()).getFromComponentList();
		for (TaraFromComponent taraFromComponent : components)
			if (taraFromComponent.getIdentifier().equals(component.getName()))
				result.add(taraFromComponent);
		return result;
	}

	private static List<IConcept> findDuplicatesInComponent(TaraComponent component) {
		List<IConcept> result = new ArrayList<>();
		List<TaraComponent> components = TaraPsiImplUtil.getChildrenInBody((TaraConceptBody) component.getParent().getParent());
		for (TaraComponent taraComponent : components)
			if (taraComponent.getIdentifier() != null && taraComponent.getIdentifier().equals(component.getName()))
				result.add(taraComponent);
		return result;
	}


	@NotNull
	public static List<TaraAttribute> findAttributeDuplicates(TaraAttribute attribute) {
		List<TaraAttribute> result = new ArrayList<>();
		List<TaraAttribute> attributes = TaraPsiImplUtil.getAttributesInBody((TaraConceptBody) attribute.getParent().getParent());
		for (TaraAttribute taraAttribute : attributes)
			if (getAttributeName(taraAttribute).equals(getAttributeName(attribute)))
				result.add(taraAttribute);
		return result;
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

	public static String getAttributeName(TaraAttribute attribute) {
		return attribute.getText().split(" ")[2];
	}
}
