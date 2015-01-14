package siani.tara.intellij.codegeneration;

import com.intellij.ide.util.DirectoryUtil;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.JavaDirectoryService;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.impl.file.PsiDirectoryImpl;
import com.intellij.psi.search.GlobalSearchScope;
import org.jetbrains.annotations.NotNull;
import org.siani.itrules.formatter.Inflector;
import org.siani.itrules.formatter.InflectorFactory;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.TaraBoxFile;
import siani.tara.intellij.lang.psi.TaraFacetApply;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.intellij.project.module.ModuleProvider;

import java.util.*;

public class FacetApplyGenerator {

	public static final String SRC = "src";
	private final Project project;
	private final Module module;
	private final String[] FACETS_PATH;
	private final TaraBoxFile taraBoxFile;
	private final PsiDirectory facetsHome;
	private final PsiDirectory srcDirectory;
	private final Inflector inflector;

	public FacetApplyGenerator(TaraBoxFile file) {
		this.taraBoxFile = file;
		this.project = taraBoxFile.getProject();
		module = ModuleProvider.getModuleOf(taraBoxFile);
		VirtualFile src = getSrcDirectory(TaraUtil.getSourceRoots(taraBoxFile));
		srcDirectory = new PsiDirectoryImpl((com.intellij.psi.impl.PsiManagerImpl) taraBoxFile.getManager(), src);
		FACETS_PATH = new String[]{project.getName().toLowerCase(), "extensions"};
		facetsHome = findFacetsDestiny();
		inflector = InflectorFactory.getInflector(Locale.getDefault());
	}

	public void generate() {
		final Set<VirtualFile> pathsToRefresh = new HashSet<>();
		final List<PsiClass> classes = new ArrayList<>();
		WriteCommandAction action = new WriteCommandAction(project, taraBoxFile) {
			@Override
			protected void run(@NotNull Result result) throws Throwable {
				try {
					pathsToRefresh.add(facetsHome.getVirtualFile());
					classes.addAll(processFile());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		action.execute();
		for (VirtualFile virtualFile : pathsToRefresh) virtualFile.refresh(true, true);
	}

	private List<PsiClass> processFile() {
		List<PsiClass> psiClasses = new ArrayList<>();
		final Concept[] concepts = getFacets(taraBoxFile);
		for (Concept concept : concepts)
			if (concept.getName() != null)
				psiClasses.addAll(createFacetApplyClasses(concept));
		return psiClasses;
	}

	private Collection<PsiClass> createFacetApplyClasses(Concept concept) {
		List<PsiClass> psiClasses = new ArrayList<>();
		for (TaraFacetApply apply : concept.getFacetApplies())
			psiClasses.add(createFacetClass(concept, apply));
		return psiClasses;
	}

	private PsiClass createFacetClass(Concept concept, TaraFacetApply apply) {
		String facetName = apply.getMetaIdentifierList().get(0).getText();
		String className = concept.getName();
		String packageName = inflector.plural(facetName.toLowerCase());
		String qn = project.getName() + "." + facetsHome.getName() + "." + packageName + "." + className + concept.getType() + facetName;
		PsiClass aClass = findClassInModule(qn);
		JavaDirectoryService instance = JavaDirectoryService.getInstance();
		return (aClass != null) ? aClass :
			instance.createClass(createPackage(facetsHome, packageName), className, "TaraFacetApplyClass", false, options(concept.getType(), facetName));
	}


	private PsiDirectory createPackage(final PsiDirectory parent, final String name) {
		PsiDirectory subdirectory = parent.findSubdirectory(name);
		if (subdirectory != null) return subdirectory;
		final PsiDirectory[] destiny = new PsiDirectory[1];
		WriteCommandAction action = new WriteCommandAction(project, parent.getContainingFile()) {
			@Override
			protected void run(@NotNull Result result) throws Throwable {
				destiny[0] = DirectoryUtil.createSubdirectories(name, parent, ".");
			}
		};
		action.execute();
		return destiny[0];
	}

	private Map<String, String> options(String conceptType, String facet) {
		Map<String, String> map = new HashMap<>();
		map.put("TYPE", conceptType);
		map.put("FACET", facet);
		map.put("FACET_PLURAL", inflector.plural(facet));
		return map;
	}


	private Concept[] getFacets(TaraBoxFile file) {
		Set<Concept> facets = new HashSet<>();
		for (Concept concept : file.getConcepts())
			facets.addAll(search(concept));
		for (Concept concept : file.getConcepts())
			if (concept.getFacetApplies().length > 0) facets.add(concept);
		return facets.toArray(new Concept[facets.size()]);
	}


	public Set<Concept> search(Concept concept) {
		Set<Concept> facets = new HashSet<>();
		Queue<Concept> queue = new LinkedList<>();
		queue.add(concept);
		while (queue.size() != 0) {
			Concept head = queue.poll();
			if (head.getFacetApplies().length > 0) {
				facets.add(head);
			} else
				for (Concept child : head.getInnerConcepts())
					queue.add(child);
		}
		return facets;
	}


	private VirtualFile getSrcDirectory(Collection<VirtualFile> virtualFiles) {
		for (VirtualFile file : virtualFiles)
			if (file.isDirectory() && SRC.equals(file.getName())) return file;
		throw new RuntimeException("Src directory not found");
	}

	private PsiDirectory findFacetsDestiny() {
		PsiDirectory directory = srcDirectory;
		for (String name : FACETS_PATH)
			directory = directory.findSubdirectory(name.toLowerCase());
		return directory;
	}

	private PsiClass findClassInModule(String qn) {

		return JavaPsiFacade.getInstance(project).findClass(qn, GlobalSearchScope.moduleScope(module));
	}
}