package tara.intellij.codegeneration;

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
import tara.intellij.lang.psi.FacetTarget;
import tara.intellij.lang.psi.Node;
import tara.intellij.lang.psi.TaraModel;
import tara.intellij.lang.psi.Variable;
import tara.intellij.lang.psi.resolve.ReferenceManager;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.facet.TaraFacetConfiguration;
import tara.intellij.project.module.ModuleProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.intellij.psi.JavaPsiFacade.getElementFactory;

public class NativesGenerator {

	private static final Logger LOG = Logger.getInstance(NativesGenerator.class.getName());
	private static final String NATIVES = "natives";
	private static final String DOT = ".";
	private static final String MAGRITTE_NATIVE = "magritte.NativeCode";
	private final Project project;
	private final TaraModel taraModel;
	private final PsiDirectory srcDirectory;
	private final Module module;
	private PsiDirectory destiny;

	public NativesGenerator(Project project, TaraModel taraModel) {
		this.project = project;
		this.taraModel = taraModel;
		VirtualFile srcVDirectory = TaraUtil.getSrcRoot(TaraUtil.getSourceRoots(taraModel));
		this.srcDirectory = new PsiDirectoryImpl((com.intellij.psi.impl.PsiManagerImpl) taraModel.getManager(), srcVDirectory);
		this.module = ModuleProvider.getModuleOf(taraModel);
	}

	public void generate() {
		WriteCommandAction action = new WriteCommandAction(project, taraModel) {
			@Override
			protected void run(@NotNull Result result) throws Throwable {
				try {
					processFile(taraModel);
				} catch (Exception e) {
					LOG.error(e.getMessage(), e);
				}
			}
		};
		action.execute();
	}

	private void processFile(PsiFile psiFile) {
		if (psiFile instanceof TaraModel) {
			List<Variable> natives = getNativeVariables((TaraModel) psiFile);
			if (!natives.isEmpty()) this.destiny = findNativesDirectory();
			natives.forEach(this::createNativeClass);
		}
	}

	private PsiClass createNativeClass(Variable variable) {
		PsiClass aClass = (PsiClass) ReferenceManager.resolveContract(variable.getContract());
		if (aClass == null) {
			aClass = JavaDirectoryService.getInstance().createInterface(destiny, variable.getContract().getFormattedName());
			setParent(MAGRITTE_NATIVE, aClass);
		} else if (aClass.getExtendsList() == null || !isParentAdded(aClass.getExtendsList().getReferencedTypes(), findClass(MAGRITTE_NATIVE)))
			setParent(MAGRITTE_NATIVE, aClass);
		return aClass;
	}

	private PsiClass findClass(String qn) {
		return JavaPsiFacade.getInstance(project).findClass(qn, GlobalSearchScope.moduleWithLibrariesScope(module));
	}

	private void setParent(String parent, PsiClass aClass) {
		PsiClass parentClass = findClass(parent);
		if (aClass.getExtendsList() == null || !isParentAdded(aClass.getExtendsList().getReferencedTypes(), parentClass))
			setParent(aClass, parentClass);
	}

	private void setParent(final PsiClass aClass, final PsiClass parentClass) {
		if (parentClass == null) return;
		PsiJavaCodeReferenceElement classReferenceElement = getElementFactory(project).createClassReferenceElement(parentClass);
		if (!isParentAdded(aClass.getExtendsListTypes(), parentClass))
			aClass.getExtendsList().add(classReferenceElement);
	}

	private boolean isParentAdded(PsiClassType[] extendsList, PsiClass parentClass) {
		for (PsiClassType psiClassType : extendsList)
			if (parentClass.getName().equals(psiClassType.getClassName())) return true;
		return false;
	}

	private PsiDirectory findNativesDirectory() {
		final TaraFacetConfiguration configuration = TaraFacet.getTaraFacetByModule(module).getConfiguration();
		String[] path = new String[]{configuration.getGeneratedDslName().toLowerCase(), NATIVES};
		PsiDirectory destinyDir = srcDirectory;
		for (String name : path)
			destinyDir = destinyDir.findSubdirectory(name) == null ? createDirectory(destinyDir, name) : destinyDir.findSubdirectory(name);
		return destinyDir;
	}

	@NotNull
	private PsiDirectory createDirectory(final PsiDirectory basePath, final String name) {
		final PsiDirectory[] newDir = new PsiDirectory[1];
		WriteCommandAction action = new WriteCommandAction(project, taraModel) {
			@Override
			protected void run(@NotNull Result result) throws Throwable {
				newDir[0] = DirectoryUtil.createSubdirectories(name, basePath, DOT);
			}
		};
		action.execute();
		return newDir[0];
	}


	private List<Variable> getNativeVariables(TaraModel model) {
		List<Variable> natives = new ArrayList<>();
		getNativeVariablesOfNodes(TaraUtil.getMainNodesOfFile(model), natives);
		return natives;
	}

	private void getNativeVariablesOfNodes(List<Node> nodesOfFile, List<Variable> natives) {
		for (Node node : nodesOfFile) {
			getNativeVariablesOfNode(node, natives);
			getNativeVariables(node.getFacetTargets(), natives);
		}
	}

	private void getNativeVariablesOfNode(Node node, List<Variable> natives) {
		natives.addAll(node.variables().stream().filter(variable -> "native".equals(variable.getType())).collect(Collectors.toList()));
		getNativeVariablesOfNodes(node.components(), natives);
	}

	private void getNativeVariables(List<FacetTarget> facetTargets, List<Variable> natives) {
		for (FacetTarget facetTarget : facetTargets) {
			natives.addAll(facetTarget.variables().stream().filter(variable -> "native".equals(variable.getType())).collect(Collectors.toList()));
			getNativeVariablesOfNodes(facetTarget.components(), natives);
		}
	}
}
