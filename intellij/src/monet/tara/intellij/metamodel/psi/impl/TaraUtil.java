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

	public static Concept resolveReferences(Project project, PsiElement identifier) {
		Concept reference;
		if (identifier.getParent() instanceof TaraReferenceIdentifier) {
			List<TaraIdentifier> route = ((TaraReferenceIdentifier) (identifier.getParent())).getIdentifierList();
			route = route.subList(0, route.indexOf(identifier) + 1);
			if (!isRootConcept(TaraPsiImplUtil.resolveContextOfRef((TaraReferenceIdentifier) identifier.getParent())) &&
				(reference = resolveRelativeReference(route, (TaraIdentifier) identifier)) != null) return reference;
			else return resolveAbsoluteReference(project, route);
		}
		return null;
	}

	private static boolean isRootConcept(Concept concept) {
		return TaraPsiImplUtil.getContextOf(concept) == null;
	}

	private static Concept resolveRelativeReference(List<TaraIdentifier> route, TaraIdentifier element) {
		Concept context = TaraPsiImplUtil.resolveContextOfRef((TaraReferenceIdentifier) element.getParent());
		Concept concept = TaraPsiImplUtil.getContextOf(context);
		for (TaraIdentifier identifier : route)
			if ((concept = findChildOf(concept, identifier.getIdentifier())) == null) break;
		return concept;
	}

	private static Concept resolveAbsoluteReference(Project project, List<TaraIdentifier> identifiers) {
		List<Concept> rootConcepts = findRootConcept(project, identifiers.get(0).getIdentifier());
		Concept reference = null;
		for (Concept rootConcept : rootConcepts) {
			Concept internRef = rootConcept;
			for (int i = 1; i < identifiers.size(); i++) {
				internRef = findChildOf(internRef, identifiers.get(i).getIdentifier());
				if (internRef == null) break;
			}
			reference = internRef;
		}
		return reference;
	}

	public static TaraConcept findSibling(Concept context, String identifier) {
		PsiElement element = context;
		List<TaraConcept> childrenInBody;
		while (element != null && !(element instanceof TaraBody))
			element = element.getParent();
		if (element != null) {
			childrenInBody = TaraPsiImplUtil.getChildrenInBody((TaraBody) element);
			return findChildIn(childrenInBody, identifier);
		}
		return null;
	}

	public static List<TaraConcept> getSiblings(Concept context) {
		PsiElement element = context;
		while ((element != null) && !(element instanceof TaraFile) && !(element instanceof TaraBody))
			element = element.getParent();
		if (element != null && !(element instanceof TaraFile))
			return TaraPsiImplUtil.getChildrenInBody((TaraBody) element);
		return Collections.EMPTY_LIST;
	}

	private static TaraConcept findChildIn(List<TaraConcept> list, String identifier) {
		for (TaraConcept concept : list)
			if (concept.getName() != null && concept.getName().equals(identifier)) return concept;
		return null;
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
		TaraReferenceIdentifier extendedConcept = (TaraReferenceIdentifier) reference.getParent();
		List<TaraIdentifier> identifiers = extendedConcept.getIdentifierList();
		identifiers = identifiers.subList(0, identifiers.indexOf(reference) + 1);
		return identifiers.toArray(new TaraIdentifier[identifiers.size()]);
	}
}
