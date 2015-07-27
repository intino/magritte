package tara.intellij.codeinsight.languageinjection;

import com.intellij.ide.util.DirectoryUtil;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.JavaDirectoryService;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.file.PsiDirectoryImpl;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.TaraModel;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.lang.psi.impl.TaraVariableImpl;
import tara.intellij.lang.psi.resolve.ReferenceManager;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.facet.TaraFacetConfiguration;
import tara.intellij.project.module.ModuleProvider;
import tara.language.model.FacetTarget;
import tara.language.model.Node;
import tara.language.model.Variable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NativesGenerator {

	private static final Logger LOG = Logger.getInstance(NativesGenerator.class.getName());
	private static final String NATIVES = "natives";
	private static final String DOT = ".";
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
		PsiClass aClass = (PsiClass) ReferenceManager.resolveContract(((TaraVariableImpl) variable).getContract());
		if (aClass == null) aClass = JavaDirectoryService.getInstance().createInterface(destiny, variable.contract());
		return aClass;
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
			getNativeVariables(node.facetTargets(), natives);
		}
	}

	private void getNativeVariablesOfNode(Node node, List<Variable> natives) {
		natives.addAll(node.variables().stream().filter(variable -> "native".equals(variable.type())).collect(Collectors.toList()));
		getNativeVariablesOfNodes(node.components(), natives);
	}

	private void getNativeVariables(List<FacetTarget> facetTargets, List<Variable> natives) {
		for (FacetTarget facetTarget : facetTargets) {
			natives.addAll(facetTarget.variables().stream().filter(variable -> "native".equals(variable.type())).collect(Collectors.toList()));
			getNativeVariablesOfNodes(facetTarget.components(), natives);
		}
	}
}
