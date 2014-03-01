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
	public static List<Concept> findRootConcept(Project project, String identifier) {
		List<Concept> result = new ArrayList<>();
		for (TaraFileImpl taraFile : getProjectFiles(project))
			result.addAll(getConceptsOfFileByName(taraFile, identifier));
		return result;
	}

	@NotNull
	public static List<Concept> getConceptsOfFileByName(TaraFileImpl taraFile, String identifier) {
		List<Concept> result = new ArrayList<>();
		Concept[] concepts = PsiTreeUtil.getChildrenOfType(taraFile, Concept.class);
		if (concepts != null) result.addAll(getConceptsByName(concepts, identifier));
		return result;
	}

	public static List<Concept> getConceptsOfFile(TaraFileImpl taraFile) {
		List<Concept> result = new ArrayList<>();
		Concept[] concepts = PsiTreeUtil.getChildrenOfType(taraFile, Concept.class);
		if (concepts != null) Collections.addAll(result, concepts);
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
	private static List<Concept> getConceptsByName(Concept[] concepts, String identifier) {
		List<Concept> result = new ArrayList<>();
		for (Concept concept : concepts)
			if (concept.getName() != null && identifier.equals(concept.getName())) result.add(concept);
		return result;
	}

	@NotNull
	public static List<Concept> getRootConcepts(Project project) {
		List<Concept> result = new ArrayList<>();
		for (TaraFileImpl taraFile : getProjectFiles(project)) {
			Concept[] concepts = PsiTreeUtil.getChildrenOfType(taraFile, Concept.class);
			if (concepts != null) Collections.addAll(result, concepts);
		}
		return result;
	}

	public static int findDuplicates(Project project, Concept concept) {
		TaraConcept parent = TaraPsiImplUtil.getContextOf(concept);
		if (parent != null)
			return checkChildDuplicates(concept, parent);
		return findRootConcept(project, concept.getIdentifierNode().getText()).size();
	}

	private static int checkChildDuplicates(Concept concept, TaraConcept parent) {
		int duplicates = 0;
		for (TaraConcept taraConcept : TaraPsiImplUtil.getChildrenOf(parent))
			if (taraConcept.getName() != null && concept.getName() != null && taraConcept.getName().equals(concept.getName()))
				duplicates++;
		return duplicates;
	}

	@NotNull
	public static TaraAttribute[] findAttributeDuplicates(TaraAttribute attribute) {
		List<TaraAttribute> result = new ArrayList<>();
		List<TaraAttribute> attributes = TaraPsiImplUtil.getAttributesInBody((TaraBody) attribute.getParent());
		for (TaraAttribute taraAttribute : attributes)
			if (taraAttribute.getName() != null && taraAttribute.getName().equals(attribute.getName()))
				result.add(taraAttribute);
		return result.toArray(new TaraAttribute[result.size()]);
	}

	@NotNull
	public static List<Concept> findAllConcepts(Project project) {
		List<Concept> result = new ArrayList<>();
		for (TaraFileImpl taraFile : getProjectFiles(project))
			result.addAll(findAllConceptsOfFile(taraFile));
		return result;
	}

	@NotNull
	public static List<Concept> findAllConceptsOfFile(TaraFileImpl taraFile) {
		List<Concept> result = new ArrayList<>();
		Concept[] concepts = PsiTreeUtil.getChildrenOfType(taraFile, Concept.class);
		if (concepts != null) {
			Collections.addAll(result, concepts);
			for (Concept concept : concepts)
				result.addAll(TaraPsiImplUtil.getChildrenOf(concept));
		}
		return result;

	}

	public static Concept resolveReferences(Project project, PsiElement myElement) {
		if (myElement.getParent() instanceof TaraExtendedConcept) {
			TaraIdentifier[] identifiers = getAbsoluteReference((TaraIdentifier) myElement);
			if (identifiers.length == 1)
				return resolveSimpleReference(project, identifiers[0]);
			else return resolveComposedReference(project, identifiers);
		}
		return null;
	}

	private static Concept resolveSimpleReference(Project project, TaraIdentifier identifier) {
		List<Concept> rootConcept = findRootConcept(project, identifier.getIdentifier());
		if (!rootConcept.isEmpty()) return rootConcept.get(0);
		else {
			Concept context = TaraPsiImplUtil.resolveContextOfRef((TaraExtendedConcept) identifier.getParent());
			return findSibling(context, identifier.getIdentifier());
		}
	}

	private static Concept findSibling(Concept context, String identifier) {
		PsiElement element = context;
		List<TaraConcept> childrenInBody;
		while (element != null && !(element instanceof TaraBody))
			element = element.getParent();
		childrenInBody = TaraPsiImplUtil.getChildrenInBody((TaraBody) element);
		return findChildIn((List<Concept>) (List<?>) childrenInBody, identifier);
	}

	private static Concept findChildIn(List<Concept> list, String identifier) {
		for (Concept concept : list)
			if (concept.getName() != null && concept.getName().equals(identifier)) return concept;
		return null;
	}

	private static Concept resolveComposedReference(Project project, TaraIdentifier[] identifiers) {
		List<Concept> rootConcepts = findRootConcept(project, identifiers[0].getIdentifier());
		Concept reference = null;
		for (Concept rootConcept : rootConcepts) {
			Concept internRef = rootConcept;
			for (int i = 1; i < identifiers.length; i++) {
				internRef = findChildOf(internRef, identifiers[i].getIdentifier());
				if (internRef == null) break;
			}
			reference = internRef;
		}
		return reference;
	}


	public static TaraConcept[] getChildrenOf(Concept concept) {
		List<TaraConcept> childrenOf = TaraPsiImplUtil.getChildrenOf(concept);
		return childrenOf.toArray(new TaraConcept[childrenOf.size()]);
	}

	public static Concept findChildOf(Concept concept, String name) {
		List<TaraConcept> children = TaraPsiImplUtil.getChildrenOf(concept);
		for (Concept child : children)
			if (child.getName() != null && child.getName().equals(name))
				return child;
		return null;
	}

	public static TaraIdentifier[] getAbsoluteReference(TaraIdentifier reference) {
		TaraExtendedConcept extendedConcept = (TaraExtendedConcept) reference.getParent();
		List<TaraIdentifier> identifiers = extendedConcept.getIdentifierList();
		identifiers = identifiers.subList(0, identifiers.indexOf(reference) + 1);
		return identifiers.toArray(new TaraIdentifier[identifiers.size()]);
	}
}
