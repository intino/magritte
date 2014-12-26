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
import siani.tara.intellij.lang.psi.*;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.intellij.project.module.ModuleProvider;

import java.util.*;

import static com.intellij.psi.JavaPsiFacade.getElementFactory;
import static com.intellij.psi.JavaPsiFacade.getInstance;

public class IntentionsGenerator {

	public static final String SRC = "src";
	private static final String INTENTIONS = "intentions";
	private static final String INTENTION = "Intention";

	private final Project project;
	private final TaraBoxFile taraBoxFile;
	private final PsiDirectory srcDirectory;
	private final PsiDirectory destiny;
	private final Module module;
	private Map<PsiClass, PsiClass> classToContainer = new HashMap<>();
	private Map<Concept, PsiClass> psiClasses = new HashMap<>();

	public IntentionsGenerator(Project project, TaraBoxFile taraBoxFile) {
		this.project = project;
		this.taraBoxFile = taraBoxFile;
		VirtualFile srcDirectory = getSrcDirectory(TaraUtil.getSourceRoots(taraBoxFile));
		this.srcDirectory = new PsiDirectoryImpl((com.intellij.psi.impl.PsiManagerImpl) taraBoxFile.getManager(), srcDirectory);
		this.destiny = findDestiny();
		this.module = ModuleProvider.getModuleOfFile(taraBoxFile);
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
		action = new WriteCommandAction(project, getCreatedInterfaces()) {
			@Override
			protected void run(@NotNull Result result) throws Throwable {
				for (Map.Entry<Concept, PsiClass> entry : psiClasses.entrySet())
					addInnerTargets(entry.getKey(), entry.getValue());
			}
		};
		action.execute();
	}

	private PsiFile[] getCreatedInterfaces() {
		Set<PsiFile> psiFiles = new HashSet<>();
		for (PsiClass psiClass : psiClasses.values()) psiFiles.add(psiClass.getContainingFile());
		return psiFiles.toArray(new PsiFile[psiFiles.size()]);
	}

	private void processFile(PsiFile psiFile) {
		if (psiFile instanceof TaraBoxFile)
			for (Concept intention : getIntentions(((TaraBoxFile) psiFile))) {
				classToContainer.clear();
				psiClasses.put(intention, createIntentionClass(getIntentionsPath(intention)));
			}
	}

	private PsiClass createIntentionClass(List<Concept> concepts) {
		Concept concept = concepts.get(0);
		PsiClass aClass = getClass(concept);
		if (aClass == null) aClass = JavaDirectoryService.getInstance().createInterface(destiny, getName(concept));
		Concept parentConcept = concept.getParentConcept() != null ? findParent(concept) : null;
		setParent(parentConcept != null ? parentConcept.getName() : "magritte.Intention", aClass);
		return aClass;
	}

	private void addInnerTargets(Concept concept, PsiClass root) {
		if (concept.getBody() == null) return;
		for (TaraFacetTarget facetTarget : concept.getBody().getFacetTargets())
			addFacetTarget(facetTarget, root);
	}

	private void addFacetTarget(TaraFacetTarget facetTarget, PsiClass container) {
		PsiClass innerInterface = createInnerInterface(facetTarget, container);
		if (facetTarget.getBody() == null) return;
		for (TaraFacetTarget inner : facetTarget.getBody().getFacetTargetList())
			addFacetTarget(inner, getContainingClass(innerInterface));
	}

	private PsiClass getContainingClass(PsiClass innerInterface) {
		return classToContainer.get(innerInterface) != null ? classToContainer.get(innerInterface) : innerInterface.getContainingClass();
	}

	private PsiClass createInnerInterface(TaraFacetTarget target, final PsiClass container) {
		TaraIdentifierReference targetReference = target.getIdentifierReference();
		List<TaraIdentifier> identifiers = targetReference.getIdentifierList();
		String qn = getQn(identifiers, identifiers.size());
		PsiClass aClass = getClass(container.getQualifiedName() + "." + qn);
		if (aClass != null) return aClass;
		if (identifiers.size() == 1) {
			PsiElement contextOf = TaraPsiImplUtil.getContextOf(target);
			aClass = createInnerClass(qn, container, getParent(contextOf));
		} else {
			PsiClass intermediateClass = createIntermediateClasses(container, identifiers.subList(0, identifiers.size() - 1));
			aClass = createClass(identifiers.get(identifiers.size() - 1).getName());
			classToContainer.put(aClass, intermediateClass);
			setParent(container.getQualifiedName(), aClass);
			intermediateClass.add(aClass);  //DON'T MOVE UP
		}
		return aClass;
	}

	private PsiClass createIntermediateClasses(PsiClass container, List<TaraIdentifier> identifiers) {
		PsiClass innerContainer = container;
		for (TaraIdentifier identifier : identifiers) {
			PsiClass aClass = getClass(innerContainer.getQualifiedName() + "." + identifier.getName());
			if (aClass == null) innerContainer = (PsiClass) innerContainer.add(createClass(identifier.getName()));
			else innerContainer = aClass;
		}
		return innerContainer;
	}

	private PsiClass createInnerClass(String name, PsiClass container, String parent) {
		PsiClass aClass = (PsiClass) container.add(createClass(name));
		classToContainer.put(aClass, container);
		if (parent != null) setParent(container.getQualifiedName() + (parent.isEmpty() ? "" : "." + parent), aClass);
		else setParent(aClass, container);
		return aClass;
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

	private String getQn(List<TaraIdentifier> identifierList, int i) {
		String qn = "";
		for (TaraIdentifier identifier : identifierList.subList(0, i))
			qn += "." + identifier.getName();
		return qn.substring(1);
	}

	private PsiClass createClass(String identifier) {
		return getElementFactory(project).createInterface(identifier);
	}

	private String getParent(PsiElement contextOf) {
		if (contextOf instanceof TaraFacetTarget) {
			List<TaraIdentifier> identifierList = ((TaraFacetTarget) contextOf).getIdentifierReference().getIdentifierList();
			return identifierList.get(identifierList.size() - 1).getText();
		}
		return null;
	}

	private Concept findParent(Concept concept) {
		return concept.getParentConcept();
	}

	private PsiClass getClass(Concept concept) {
		return getInstance(project).findClass(INTENTIONS + "." + getName(concept),
			GlobalSearchScope.moduleScope(module));
	}

	private String getName(Concept concept) {
		return concept.getName() + INTENTION;
	}

	private PsiClass getClass(String qn) {
		return getInstance(project).findClass(qn, GlobalSearchScope.moduleWithLibrariesScope(module));
	}

	private PsiDirectory findDestiny() {
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

	private VirtualFile getSrcDirectory(Collection<VirtualFile> virtualFiles) {
		for (VirtualFile file : virtualFiles)
			if (file.isDirectory() && SRC.equals(file.getName())) return file;
		throw new RuntimeException("src directory not found");
	}

	private Concept[] getIntentions(TaraBoxFile taraBoxFile) {
		List<Concept> intentions = new ArrayList<>();
		List<Concept> allConceptsOfFile = TaraUtil.getAllConceptsOfFile(taraBoxFile);
		for (Concept concept : allConceptsOfFile)
			if (concept.isIntention())
				intentions.add(concept);
		return intentions.toArray(new Concept[intentions.size()]);
	}

	private List<Concept> getIntentionsPath(Concept intention) {
		List<Concept> list = new ArrayList<>();
		Concept contextOf = intention;
		while ((contextOf = TaraPsiImplUtil.getConceptContainerOf(contextOf)) != null)
			list.add(0, contextOf);
		list.add(intention);
		return list;
	}

}
