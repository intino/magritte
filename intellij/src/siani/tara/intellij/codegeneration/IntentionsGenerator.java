package siani.tara.intellij.codegeneration;

import com.intellij.ide.util.DirectoryUtil;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.impl.file.PsiDirectoryImpl;
import com.intellij.psi.search.GlobalSearchScope;
import org.jetbrains.annotations.NotNull;
import org.siani.itrules.formatter.Inflector;
import org.siani.itrules.formatter.InflectorFactory;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.TaraBoxFile;
import siani.tara.intellij.lang.psi.TaraFacetTarget;
import siani.tara.intellij.lang.psi.TaraIdentifier;
import siani.tara.intellij.lang.psi.impl.ReferenceManager;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.intellij.project.module.ModuleConfiguration;
import siani.tara.intellij.project.module.ModuleProvider;

import java.util.*;

import static com.intellij.psi.JavaPsiFacade.getElementFactory;
import static com.intellij.psi.JavaPsiFacade.getInstance;
import static siani.tara.intellij.lang.psi.impl.ReferenceManager.resolveToConcept;
import static siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil.getParentOf;

public class IntentionsGenerator {

	private static final Logger LOG = Logger.getInstance(IntentionsGenerator.class.getName());
	private static final String INTENTIONS = "intentions";
	private static final String INTENTION = "Intention";
	private static final String DOT = ".";
	private static final String MAGRITTE_INTENTION = "magritte.Intention";
	private final Project project;
	private final String basePath;
	private final TaraBoxFile taraBoxFile;
	private final PsiDirectory srcDirectory;
	private PsiDirectory destiny;
	private final Module module;
	Map<String, PsiClass> createdClasses = new HashMap();

	public IntentionsGenerator(Project project, TaraBoxFile taraBoxFile) {
		this.project = project;
		basePath = project.getName().toLowerCase() + DOT + INTENTIONS;
		this.taraBoxFile = taraBoxFile;
		VirtualFile srcVDirectory = TaraUtil.getSrcRoot(TaraUtil.getSourceRoots(taraBoxFile));
		this.srcDirectory = new PsiDirectoryImpl((com.intellij.psi.impl.PsiManagerImpl) taraBoxFile.getManager(), srcVDirectory);
		this.module = ModuleProvider.getModuleOf(taraBoxFile);
	}

	public void generate() {
		WriteCommandAction action = new WriteCommandAction(project, taraBoxFile) {
			@Override
			protected void run(@NotNull Result result) throws Throwable {
				try {
					processFile(taraBoxFile);
				} catch (Exception e) {
					LOG.error(e.getMessage(), e);
				}
			}
		};
		action.execute();
	}

	private void processFile(PsiFile psiFile) {
		if (psiFile instanceof TaraBoxFile) {
			Concept[] intentions = getIntentions((TaraBoxFile) psiFile);
			if (intentions.length > 0) this.destiny = findIntentionsDestiny();
			for (Concept intention : intentions) {
				createIntentionClass(intention);
				createTargetInterfaces(intention);
			}
		}
	}

	private PsiClass createIntentionClass(Concept concept) {
		PsiClass aClass = findClass(concept);
		if (aClass == null) aClass = JavaDirectoryService.getInstance().createInterface(destiny, getClassName(concept));
		Concept parentConcept = concept.getParentConcept() != null ? findParent(concept) : null;
		setParent(parentConcept != null ? parentConcept.getName() : MAGRITTE_INTENTION, aClass);
		return aClass;
	}

	private void createTargetInterfaces(Concept concept) {
		Collection<TaraFacetTarget> facetTargets = concept.getFacetTargets();
		sort(facetTargets);
		for (TaraFacetTarget facetTarget : facetTargets)
			createTargetInterface(concept, facetTarget);
	}

	private void sort(Collection<TaraFacetTarget> facetTargets) {
		Collections.sort((List<TaraFacetTarget>) facetTargets, new Comparator<TaraFacetTarget>() {
			@Override
			public int compare(TaraFacetTarget f1, TaraFacetTarget f2) {
				Concept d1 = resolveToConcept(f1.getIdentifierReference());
				Concept d2 = resolveToConcept(f2.getIdentifierReference());
				if (d1 != null && d1.getParentConcept() == null) return 1;
				if (d2 != null && d2.getParentConcept() == null) return -1;
				return 0;
			}
		});
	}

	private void createTargetInterface(Concept facetConcept, TaraFacetTarget target) {
		PsiDirectory facetDirectory = findFacetDirectory(facetConcept.getName());
		String facetsBasePath = basePath + DOT + facetDirectory.getName().toLowerCase();
		List<Concept> compositionRoute = TaraUtil.buildConceptCompositionPathOf(resolveToConcept(target.getIdentifierReference()));

		for (int i = 0; i < compositionRoute.size(); i++) {
			Concept targetConcept = compositionRoute.get(i);
			PsiClass aClass = findClass(facetsBasePath + DOT + buildMiddlePath(createdClasses, subList(compositionRoute, targetConcept)) + getClassName(targetConcept, facetConcept));
			if (aClass == null) {
				aClass = i == 0 ? createTargetInterface(getClassName(targetConcept, facetConcept), facetDirectory) :
					createTargetInnerInterface(getClassName(targetConcept, facetConcept), createdClasses.get(compositionRoute.get(i - 1).getName()));
			}
			if (targetConcept.equals(resolveToConcept(target.getIdentifierReference()))) {
				TaraFacetTarget parentTarget = searchParentTarget(facetConcept, target, targetConcept);
				setParent(parentTarget == null ? basePath + DOT + getClassName(facetConcept) : getTargetClassQN(facetConcept, parentTarget), aClass);
			}
			createdClasses.put(targetConcept.getName(), aClass);
		}
	}

	private PsiClass createTargetInnerInterface(String className, PsiClass container) {
		return (PsiClass) container.add(JavaPsiFacade.getElementFactory(project).createInterface(className));
	}

	private String buildMiddlePath(Map<String, PsiClass> createdClasses, List<Concept> concepts) {
		String path = "";
		for (Concept concept : concepts) {
			PsiClass psiClass = createdClasses.get(concept.getName());
			if (psiClass != null)
				path += psiClass.getName() + DOT;
		}
		return path;
	}

	private List<Concept> subList(List<Concept> compositionRoute, Concept targetConcept) {
		List<Concept> subList = new ArrayList<>();
		for (Concept concept : compositionRoute) {
			if (concept.equals(targetConcept)) break;
			subList.add(concept);
		}
		return subList;
	}

	private PsiClass createTargetInterface(String qn, PsiDirectory facetDirectory) {
		return JavaDirectoryService.getInstance().createInterface(facetDirectory, qn);
	}

	private TaraFacetTarget searchParentTarget(Concept facet, TaraFacetTarget facetTarget, Concept target) {
		if (target == null) return null;
		List<Concept> concepts = getConceptHierarchy(target);
		if (concepts.isEmpty()) return null;
		return searchParentTarget(facet, facetTarget, concepts);
	}

	private TaraFacetTarget searchParentTarget(Concept concept, TaraFacetTarget target, List<Concept> hierarchy) {
		TaraFacetTarget closestParent = null;
		int parentIndex = hierarchy.size();
		for (TaraFacetTarget taraFacetTarget : concept.getFacetTargets()) {
			if (taraFacetTarget == target) continue;
			int indexOf = hierarchy.indexOf(resolveTargetDestiny(taraFacetTarget));
			if (indexOf >= 0 && indexOf < parentIndex) {
				parentIndex = indexOf;
				closestParent = taraFacetTarget;
			}
		}
		return closestParent;
	}

	private Concept resolveTargetDestiny(TaraFacetTarget target) {
		return ReferenceManager.resolveToConcept(target.getIdentifierReference());
	}

	private List<Concept> getConceptHierarchy(Concept conceptTarget) {
		List<Concept> concepts = new ArrayList<>();
		Concept parent = getParentOf(conceptTarget);
		while (parent != null) {
			concepts.add(parent);
			parent = getParentOf(parent);
		}
		return concepts;
	}

	private String getClassName(Concept target, Concept facet) {
		return target.getName() + facet.getName() + INTENTION;
	}

	private String getTargetClassQN(Concept concept, TaraFacetTarget target) {
		if (target.getIdentifierReference() == null) return null;
		List<TaraIdentifier> identifierList = target.getIdentifierReference().getIdentifierList();
		String name = identifierList.get(identifierList.size() - 1).getName() + concept.getName() + INTENTION;
		return basePath + DOT + getInflector().plural(concept.getName()).toLowerCase() + DOT + name;
	}

	private void setParent(String parent, PsiClass aClass) {
		PsiClass parentClass = findClass(parent);
		if (!isParentAdded(aClass.getExtendsList().getReferencedTypes(), parentClass))
			setParent(aClass, parentClass);
	}

	private void setParent(final PsiClass aClass, final PsiClass parentClass) {
		if (parentClass == null) return; //CANNOT BE POSSIBLE
		PsiJavaCodeReferenceElement classReferenceElement = getElementFactory(project).createClassReferenceElement(parentClass);
		if (!isParentAdded(aClass.getExtendsListTypes(), parentClass))
			aClass.getExtendsList().add(classReferenceElement);
	}

	private boolean isParentAdded(PsiClassType[] extendsList, PsiClass parentClass) {
		for (PsiClassType psiClassType : extendsList)
			if (parentClass.getName().equals(psiClassType.getClassName())) return true;
		return false;
	}

	private Concept findParent(Concept concept) {
		return concept.getParentConcept();
	}

	private PsiClass findClass(Concept concept) {
		return getInstance(project).findClass(basePath + DOT + getClassName(concept),
			GlobalSearchScope.moduleScope(module));
	}

	private String getClassName(Concept concept) {
		return concept.getName() + INTENTION;
	}

	private PsiClass findClass(String qn) {
		return getInstance(project).findClass(qn, GlobalSearchScope.moduleWithLibrariesScope(module));
	}

	private PsiDirectory findFacetDirectory(String name) {
		final PsiDirectory intentionsDir = findIntentionsDestiny();
		final String pluralName = getInflector().plural(name).toLowerCase();
		PsiDirectory subdirectory = intentionsDir.findSubdirectory(pluralName);
		if (subdirectory != null) return subdirectory;
		final PsiDirectory[] facetDestiny = new PsiDirectory[1];
		WriteCommandAction action = new WriteCommandAction(project, taraBoxFile) {
			@Override
			protected void run(@NotNull Result result) throws Throwable {
				facetDestiny[0] = DirectoryUtil.createSubdirectories(pluralName, intentionsDir, DOT);
			}
		};
		action.execute();
		return facetDestiny[0];
	}

	private Inflector getInflector() {
		return InflectorFactory.getInflector(ModuleConfiguration.getInstance(module).getLanguage());
	}

	private PsiDirectory findIntentionsDestiny() {
		String[] path = new String[]{project.getName().toLowerCase(), INTENTIONS};
		PsiDirectory destinyDir = srcDirectory;
		for (String name : path)
			destinyDir = destinyDir.findSubdirectory(name) == null ? createDirectory(destinyDir, name) : destinyDir.findSubdirectory(name);
		return destinyDir;
	}

	@NotNull
	private PsiDirectory createDirectory(final PsiDirectory basePath, final String name) {
		final PsiDirectory[] newDir = new PsiDirectory[1];
		WriteCommandAction action = new WriteCommandAction(project, taraBoxFile) {
			@Override
			protected void run(@NotNull Result result) throws Throwable {
				newDir[0] = DirectoryUtil.createSubdirectories(name, basePath, DOT);
			}
		};
		action.execute();
		return newDir[0];
	}


	private Concept[] getIntentions(TaraBoxFile taraBoxFile) {
		List<Concept> intentions = new ArrayList<>();
		List<Concept> allConceptsOfFile = TaraUtil.getAllConceptsOfFile(taraBoxFile);
		for (Concept concept : allConceptsOfFile)
			if (concept.isIntention())
				intentions.add(concept);
		return intentions.toArray(new Concept[intentions.size()]);
	}
}
