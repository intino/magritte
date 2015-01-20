package siani.tara.intellij.codegeneration;

import com.intellij.ide.util.DirectoryUtil;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.impl.file.PsiDirectoryImpl;
import com.intellij.psi.search.GlobalSearchScope;
import org.jetbrains.annotations.NotNull;
import org.siani.itrules.formatter.InflectorFactory;
import siani.tara.intellij.lang.psi.*;
import siani.tara.intellij.lang.psi.impl.ReferenceManager;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.intellij.project.module.ModuleProvider;

import java.util.*;

import static com.intellij.psi.JavaPsiFacade.getElementFactory;
import static com.intellij.psi.JavaPsiFacade.getInstance;
import static siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil.getParentOf;

public class IntentionsGenerator {

	private static final String INTENTIONS = "intentions";
	private static final String INTENTION = "Intention";

	private final Project project;
	private final TaraBoxFile taraBoxFile;
	private final PsiDirectory srcDirectory;
	private PsiDirectory destiny;
	private final Module module;

	public IntentionsGenerator(Project project, TaraBoxFile taraBoxFile) {
		this.project = project;
		this.taraBoxFile = taraBoxFile;
		VirtualFile srcDirectory = TaraUtil.getSrcRoot(TaraUtil.getSourceRoots(taraBoxFile));
		this.srcDirectory = new PsiDirectoryImpl((com.intellij.psi.impl.PsiManagerImpl) taraBoxFile.getManager(), srcDirectory);
		this.module = ModuleProvider.getModuleOf(taraBoxFile);
	}

	public void generate() {
		WriteCommandAction action = new WriteCommandAction(project, taraBoxFile) {
			@Override
			protected void run(@NotNull Result result) throws Throwable {
				try {
					processFile(taraBoxFile);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		action.execute();
	}

	private void processFile(PsiFile psiFile) {
		if (psiFile instanceof TaraBoxFile) {
			Concept[] intentions = getIntentions(((TaraBoxFile) psiFile));
			if (intentions.length > 0) this.destiny = findIntentionsDestiny();
			for (Concept intention : intentions) {
				createIntentionClass(intention);
				createTargetInterfaces(intention);
			}
		}
	}

	private PsiClass createIntentionClass(Concept concept) {
		PsiClass aClass = getClass(concept);
		if (aClass == null) aClass = JavaDirectoryService.getInstance().createInterface(destiny, getClassName(concept));
		Concept parentConcept = concept.getParentConcept() != null ? findParent(concept) : null;
		setParent(parentConcept != null ? parentConcept.getName() : "magritte.Intention", aClass);
		return aClass;
	}

	private void createTargetInterfaces(Concept concept) {
		for (TaraFacetTarget facetTarget : concept.getFacetTargets())
			createTargetInterface(concept, facetTarget);
	}

	private void createTargetInterface(Concept concept, TaraFacetTarget target) {
		PsiDirectory targetDestiny = findTargetDestiny(concept.getName());
		PsiClass aClass = getClass(INTENTIONS + "." + targetDestiny.getName() + "." + getClassName(concept, target));
		if (aClass != null) return;
		PsiClass anInterface = JavaDirectoryService.getInstance().createInterface(targetDestiny, getClassName(concept, target));
		TaraFacetTarget parentTarget = searchParentTarget(concept, target);
		setParent(parentTarget == null ? INTENTIONS + "." + getClassName(concept) : getTargetClassQN(concept, parentTarget), anInterface);
	}

	private TaraFacetTarget searchParentTarget(Concept concept, TaraFacetTarget target) {
		Concept conceptTarget = resolveTargetDestiny(target);
		if (conceptTarget == null) return null;
		List<Concept> concepts = getConceptHierarchy(conceptTarget);
		if (concepts.isEmpty()) return null;
		return searchParentTarget(concept, target, concepts);
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
		return TaraPsiImplUtil.getConceptContainerOf(ReferenceManager.resolve(target.getIdentifierReference()));
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

	private String getClassName(Concept concept, TaraFacetTarget target) {
		if (target.getIdentifierReference() == null) return null;
		List<TaraIdentifier> identifierList = target.getIdentifierReference().getIdentifierList();
		return identifierList.get(identifierList.size() - 1).getName() + concept.getName() + INTENTION;
	}


	private String getTargetClassQN(Concept concept, TaraFacetTarget target) {
		if (target.getIdentifierReference() == null) return null;
		List<TaraIdentifier> identifierList = target.getIdentifierReference().getIdentifierList();
		String name = identifierList.get(identifierList.size() - 1).getName() + concept.getName() + INTENTION;
		return INTENTIONS + "." + InflectorFactory.getInflector(Locale.getDefault()).plural(concept.getName()) + "." + name;
	}

	private void setParent(String parent, PsiClass aClass) {
		if (parent == null) return;
		PsiClass parentClass = getClass(parent);
		if (!isParentAdded(aClass.getExtendsList().getReferencedTypes(), parentClass))
			setParent(aClass, parentClass);
	}

	private void setParent(final PsiClass aClass, final PsiClass parentClass) {
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

	private PsiClass getClass(Concept concept) {
		return getInstance(project).findClass(INTENTIONS + "." + getClassName(concept),
			GlobalSearchScope.moduleScope(module));
	}

	private String getClassName(Concept concept) {
		return concept.getName() + INTENTION;
	}

	private PsiClass getClass(String qn) {
		return getInstance(project).findClass(qn, GlobalSearchScope.moduleWithLibrariesScope(module));
	}

	private PsiDirectory findTargetDestiny(String name) {
		final PsiDirectory intentionsDir = findIntentionsDestiny();
		final String pluralName = InflectorFactory.getInflector(Locale.getDefault()).plural(name);
		PsiDirectory subdirectory = intentionsDir.findSubdirectory(pluralName);
		if (subdirectory != null) return subdirectory;
		final PsiDirectory[] destiny = new PsiDirectory[1];
		WriteCommandAction action = new WriteCommandAction(project, taraBoxFile) {
			@Override
			protected void run(@NotNull Result result) throws Throwable {
				destiny[0] = DirectoryUtil.createSubdirectories(pluralName, intentionsDir, ".");
			}
		};
		action.execute();
		return destiny[0];
	}

	private PsiDirectory findIntentionsDestiny() {
		PsiDirectory subdirectory = srcDirectory.findSubdirectory(INTENTIONS);
		if (subdirectory != null) return subdirectory;
		final PsiDirectory[] destiny = new PsiDirectory[1];
		WriteCommandAction action = new WriteCommandAction(project, taraBoxFile) {
			@Override
			protected void run(@NotNull Result result) throws Throwable {
				destiny[0] = DirectoryUtil.createSubdirectories(INTENTIONS, srcDirectory, ".");
			}
		};
		action.execute();
		return destiny[0];
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
