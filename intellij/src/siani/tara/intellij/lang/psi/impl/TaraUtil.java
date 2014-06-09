package siani.tara.intellij.lang.psi.impl;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.indexing.FileBasedIndex;
import siani.tara.intellij.lang.file.TaraFileType;
import siani.tara.intellij.lang.psi.Attribute;
import siani.tara.intellij.lang.psi.Body;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.TaraFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.model.java.JavaModuleSourceRootTypes;

import java.util.*;

public class TaraUtil {

	private TaraUtil() {
	}

	@NotNull
	public static List<Concept> findRootConcept(PsiElement element, String identifier) {
		List<Concept> result = new ArrayList<>();
		for (TaraFileImpl taraFile : getModuleFiles(element.getContainingFile()))
			if (identifier.equals(taraFile.getConcept().getName())) result.add(taraFile.getConcept());
		return result;
	}

	public static Concept getRootConceptOfFile(TaraFileImpl taraFile) {
		Concept[] concepts = PsiTreeUtil.getChildrenOfType(taraFile, Concept.class);
		return (concepts != null) ? concepts[0] : null;
	}

	@NotNull
	public static Concept getBaseConceptOf(Concept concept) {
		Concept contextOf = TaraPsiImplUtil.getContextOf(concept);
		if (contextOf.isBase()) return contextOf;
		else return TaraPsiImplUtil.getContextOf(contextOf);
	}

	@NotNull
	private static TaraFileImpl[] getModuleFiles(PsiFile psiFile) {
		List<TaraFileImpl> taraFiles = new ArrayList<>();
		Module module = ProjectRootManager.getInstance(psiFile.getProject()).getFileIndex().getModuleForFile(psiFile.getVirtualFile());
		if (module == null) return new TaraFileImpl[0];
		Collection<VirtualFile> files = FileBasedIndex.getInstance().
			getContainingFiles(FileTypeIndex.NAME, TaraFileType.INSTANCE, GlobalSearchScope.moduleScope(module));
		for (VirtualFile file : files)
			if (file != null) taraFiles.add((TaraFileImpl) PsiManager.getInstance(psiFile.getProject()).findFile(file));
		return taraFiles.toArray(new TaraFileImpl[taraFiles.size()]);
	}

	public static int findDuplicates(Project project, Concept concept) {
		Concept parent = TaraPsiImplUtil.getContextOf(concept);
		if (parent != null)
			return checkChildDuplicates(concept, parent);
		return findRootConcept(concept.getIdentifierNode(), concept.getIdentifierNode().getText()).size();
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

	public static VirtualFile findChildFileOf(VirtualFile file, String name) {
		if (file == null || file.getChildren() == null) return null;
		for (VirtualFile virtualFile : file.getChildren())
			if (virtualFile.getName().split("\\.")[0].equals(name))
				return virtualFile;
		return null;
	}

	public static TaraFile getTaraFileFromVirtual(Project project, VirtualFile vFile) {
		PsiFile file = PsiManager.getInstance(project).findFile(vFile);
		if (file instanceof TaraFile) return (TaraFile) file;
		return null;
	}

	public static List<Concept> getSiblings(Concept context) {
		PsiElement element = context;
		while ((element != null) && !(element instanceof TaraFile) && !(element instanceof Body))
			element = element.getParent();
		if (element != null && !(element instanceof TaraFile))
			return TaraPsiImplUtil.getChildrenInBody((Body) element);
		return Collections.EMPTY_LIST;
	}

	@NotNull
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

	public static VirtualFile getSourcePath(Project project) {
		Module[] modules = ModuleManager.getInstance(project).getModules();
		for (Module module : modules) {
			ModuleRootManager mrm = ModuleRootManager.getInstance(module);
			List<VirtualFile> virtualFiles = mrm.getSourceRoots(JavaModuleSourceRootTypes.SOURCES);
			for (VirtualFile file : virtualFiles)
				if (file.isDirectory() && "src".equals(file.getName()))
					return file;
		}
		return null;
	}

	public static TaraFile getOrCreateFile(String myDestination, Project myProject) {
		return null;
	}

	public static void deleteTaraFile(String path) {

	}

	@NotNull
	public static Collection<VirtualFile> getSourceRoots(@NotNull PsiElement foothold) {
		final Module module = ModuleUtilCore.findModuleForPsiElement(foothold);
		if (module != null) return getSourceRoots(module);
		return Collections.emptyList();
	}

	@NotNull
	public static Collection<VirtualFile> getSourceRoots(@NotNull Module module) {
		final Set<VirtualFile> result = new LinkedHashSet<>();
		final ModuleRootManager manager = ModuleRootManager.getInstance(module);
		result.addAll(Arrays.asList(manager.getSourceRoots()));
		result.addAll(Arrays.asList(manager.getContentRoots()));
		return result;
	}
}
