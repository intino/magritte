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

	private TaraUtil() {
	}

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
		Concept parent = TaraPsiImplUtil.getContextOf(concept);
		if (parent != null)
			return checkChildDuplicates(concept, parent);
		return findRootConcept(project, concept.getIdentifierNode().getText()).size();
	}

	private static int checkChildDuplicates(Concept concept, Concept parent) {
		int duplicates = 0;
		for (Concept taraConcept : TaraPsiImplUtil.getChildrenOf(parent))
			if (taraConcept.getName() != null && concept.getName() != null && taraConcept.getName().equals(concept.getName()))
				duplicates++;
		return duplicates;
	}

	@NotNull
	public static Attribute[] findAttributeDuplicates(Attribute attribute) {
		List<Attribute> result = new ArrayList<>();
		List<Attribute> attributes = TaraPsiImplUtil.getAttributesInBody((Body) attribute.getParent());
		for (Attribute taraAttribute : attributes)
			if (taraAttribute.getName() != null && taraAttribute.getName().equals(attribute.getName()))
				result.add(taraAttribute);
		return result.toArray(new Attribute[result.size()]);
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
		if (identifier.getParent() instanceof ReferenceIdentifier) {
			List<Identifier> route = (List<Identifier>) ((ReferenceIdentifier) (identifier.getParent())).getIdentifierList();
			route = route.subList(0, route.indexOf(identifier) + 1);
			Concept reference = resolveRelativeReference(route, (TaraIdentifier) identifier);
			if (!isRootConcept(TaraPsiImplUtil.resolveContextOfRef((ReferenceIdentifier) identifier.getParent())) && reference != null)
				return reference;
			else return resolveAbsoluteReference(project, route);
		}
		return null;
	}

	private static boolean isRootConcept(Concept concept) {
		return TaraPsiImplUtil.getContextOf(concept) == null;
	}

	private static Concept resolveRelativeReference(List<Identifier> route, Identifier element) {
		Concept context = TaraPsiImplUtil.resolveContextOfRef((ReferenceIdentifier) element.getParent());
		Concept concept = TaraPsiImplUtil.getContextOf(context);
		for (Identifier identifier : route) {
			concept = findChildOf(concept, ((TaraIdentifierImpl) identifier).getIdentifier());
			if (concept == null) break;
		}
		return concept;
	}

	private static Concept resolveAbsoluteReference(Project project, List<Identifier> identifiers) {
		List<Concept> rootConcepts = findRootConcept(project, ((TaraIdentifierImpl) identifiers.get(0)).getIdentifier());
		Concept reference = null;
		for (Concept rootConcept : rootConcepts) {
			Concept internRef = rootConcept;
			for (int i = 1; i < identifiers.size(); i++) {
				internRef = findChildOf(internRef, ((TaraIdentifierImpl) identifiers.get(i)).getIdentifier());
				if (internRef == null) break;
			}
			reference = internRef;
		}
		return reference;
	}

	public static List<Concept> getSiblings(Concept context) {
		PsiElement element = context;
		while ((element != null) && !(element instanceof TaraFile) && !(element instanceof Body))
			element = element.getParent();
		if (element != null && !(element instanceof TaraFile))
			return TaraPsiImplUtil.getChildrenInBody((Body) element);
		return Collections.EMPTY_LIST;
	}

	public static Concept[] getChildrenOf(Concept concept) {
		List<Concept> childrenOf = TaraPsiImplUtil.getChildrenOf(concept);
		return childrenOf.toArray(new Concept[childrenOf.size()]);
	}

	public static Concept findChildOf(Concept concept, String name) {
		List<Concept> children = TaraPsiImplUtil.getChildrenOf(concept);
		for (Concept child : children)
			if (child.getName() != null && child.getName().equals(name))
				return child;
		return null;
	}
}
