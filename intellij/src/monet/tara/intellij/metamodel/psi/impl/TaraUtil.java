package monet.tara.intellij.metamodel.psi.impl;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.indexing.FileBasedIndex;
import monet.tara.intellij.metamodel.file.TaraFileType;
import monet.tara.intellij.metamodel.psi.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TaraUtil {

	@NotNull
	public static List<IConcept> findRootConcept(Project project, String identifier) {
		List<IConcept> result = new ArrayList<>();
		for (TaraFileImpl taraFile : getProjectFiles(project))
			result.addAll(getConceptsOfFileByName(taraFile, identifier));
		return result;
	}

	@NotNull
	public static List<IConcept> getConceptsOfFileByName(TaraFileImpl taraFile, String identifier) {
		List<IConcept> result = new ArrayList<>();
		IConcept[] concepts = PsiTreeUtil.getChildrenOfType(taraFile, TaraConcept.class);
		if (concepts != null) result.addAll(getConceptsByName(concepts, identifier));
		return result;
	}

	public static List<IConcept> getConceptsOfFile(TaraFileImpl taraFile) {
		List<IConcept> result = new ArrayList<>();
		IConcept[] concepts = PsiTreeUtil.getChildrenOfType(taraFile, TaraConcept.class);
		if (concepts != null) Collections.addAll(result, concepts);
		return result;
	}

	@NotNull
	public static List<IConcept> findConceptInContext(Project project, String identifier, PsiElement ctx) {
		List<IConcept> result = new ArrayList<>();
		for (TaraFileImpl taraFile : getProjectFiles(project)) {
			IConcept[] concepts = PsiTreeUtil.getChildrenOfType(taraFile, TaraConcept.class);
			if (concepts != null && !(concepts.length == 0))
				result.addAll(getConceptsByName(concepts, identifier));
			if (ctx.getContainingFile().equals(taraFile))
				findComponents(identifier, (TaraIdentifier) ctx, result);
		}
		return result;
	}

	@NotNull
	private static TaraFileImpl[] getProjectFiles(Project project) {
		List<TaraFileImpl> taraFiles = new ArrayList<>();
		Collection<VirtualFile> files = FileBasedIndex.getInstance().
			getContainingFiles(FileTypeIndex.NAME, TaraFileType.INSTANCE, GlobalSearchScope.allScope(project));
		for (VirtualFile file : files)
			if (file != null) taraFiles.add((TaraFileImpl) PsiManager.getInstance(project).findFile(file));
		return taraFiles.toArray(new TaraFileImpl[taraFiles.size()]);
	}

	@NotNull
	private static List<IConcept> getConceptsByName(IConcept[] concepts, String identifier) {
		List<IConcept> result = new ArrayList<>();
		for (IConcept concept : concepts)
			if (concept.getName() != null && identifier.equals(concept.getName())) result.add(concept);
		return result;
	}

	@NotNull
	private static List<IConcept> findComponents(String key, TaraIdentifier ctx, List<IConcept> result) {
		List<IConcept> components = TaraPsiImplUtil.getChildren(ctx);
		for (IConcept component : components)
			if (key.equals(component.getName()))
				result.add(component);
		return result;
	}

	@NotNull
	public static List<IConcept> getRootConcepts(Project project) {
		List<IConcept> result = new ArrayList<>();
		for (TaraFileImpl taraFile : getProjectFiles(project)) {
			TaraConcept[] concepts = PsiTreeUtil.getChildrenOfType(taraFile, TaraConcept.class);
			if (concepts != null) Collections.addAll(result, concepts);
		}
		return result;
	}

	@NotNull
	public static List<IConcept> findDuplicates(Project project, IConcept concept) {
		if (concept instanceof TaraConcept)
			return findRootConcept(project, concept.getIdentifierNode().getText());
		if (concept instanceof TaraComponent)
			return findDuplicatesInComponent((TaraComponent) concept);
		return findDuplicatesInFromComponent((TaraFromComponent) concept);
	}

	@NotNull
	private static List<IConcept> findDuplicatesInFromComponent(TaraFromComponent component) {
		List<IConcept> result = new ArrayList<>();
		List<TaraFromComponent> components = ((TaraFromBody) component.getParent()).getFromComponentList();
		for (TaraFromComponent taraFromComponent : components)
			if (taraFromComponent.getIdentifier().equals(component.getName()))
				result.add(taraFromComponent);
		return result;
	}

	@NotNull
	private static List<IConcept> findDuplicatesInComponent(TaraComponent component) {
		List<IConcept> result = new ArrayList<>();
		List<TaraComponent> components = TaraPsiImplUtil.getChildrenInBody((TaraConceptBody) component.getParent().getParent());
		result.addAll(getConceptsByName(components.toArray(new IConcept[components.size()]), component.getName()));
		return result;
	}


	@NotNull
	public static List<TaraAttribute> findAttributeDuplicates(TaraAttribute attribute) {
		List<TaraAttribute> result = new ArrayList<>();
		List<TaraAttribute> attributes = TaraPsiImplUtil.getAttributesInBody((TaraConceptBody) attribute.getParent().getParent());
		for (TaraAttribute taraAttribute : attributes)
			if (taraAttribute.getName().equals(attribute.getName()))
				result.add(taraAttribute);
		return result;
	}

	@NotNull
	public static List<IConcept> findAllConcepts(Project project) {
		List<IConcept> result = new ArrayList<>();
		for (TaraFileImpl taraFile : getProjectFiles(project))
			result.addAll(findAllConceptsOfFile(taraFile));
		return result;
	}

	@NotNull
	public static List<IConcept> findAllConceptsOfFile(TaraFileImpl taraFile) {
		List<IConcept> result = new ArrayList<>();
		TaraConcept[] concepts = PsiTreeUtil.getChildrenOfType(taraFile, TaraConcept.class);
		if (concepts != null) {
			Collections.addAll(result, concepts);
			for (TaraConcept concept : concepts)
				result.addAll(TaraPsiImplUtil.getChildrenOf(concept));
		}
		return result;

	}

	public static List<IConcept> resolveReferences(Project project, String key, PsiElement myElement) {
		return null;
	}

	public static List<TaraComponent> getChildrenOf(IConcept concept) {
		return TaraPsiImplUtil.getChildrenOf(concept);
	}
}
