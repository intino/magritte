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
import siani.tara.intellij.lang.psi.Node;
import siani.tara.intellij.lang.psi.TaraModel;
import siani.tara.intellij.lang.psi.Variable;
import siani.tara.intellij.lang.psi.impl.ReferenceManager;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.intellij.project.module.ModuleProvider;

import java.util.ArrayList;
import java.util.List;

import static com.intellij.psi.JavaPsiFacade.getElementFactory;

public class NativesGenerator {

	private static final Logger LOG = Logger.getInstance(NativesGenerator.class.getName());
	private static final String NATIVES = "natives";
	private static final String DOT = ".";
	private static final String MAGRITTE_NATIVE = "magritte.Native";
	private final Project project;
	private final TaraModel taraModel;
	private final PsiDirectory srcDirectory;
	private PsiDirectory destiny;
	private final Module module;

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
			Variable[] natives = getNativeVariables((TaraModel) psiFile);
			if (natives.length > 0) this.destiny = findNativesDirectory();
			for (Variable aNative : natives) createNativeClass(aNative);
		}
	}

	private PsiClass createNativeClass(Variable variable) {
		PsiClass aClass = (PsiClass) ReferenceManager.resolveNative(variable.getNativeName());
		if (aClass == null) {
			aClass = JavaDirectoryService.getInstance().createInterface(destiny, variable.getNativeName().getFormattedName());
			setParent(MAGRITTE_NATIVE, aClass);
		}
		return aClass;
	}

	private PsiClass findClass(String qn) {
		return JavaPsiFacade.getInstance(project).findClass(qn, GlobalSearchScope.moduleWithLibrariesScope(module));
	}

	private void setParent(String parent, PsiClass aClass) {
		PsiClass parentClass = findClass(parent);
		if (!isParentAdded(aClass.getExtendsList().getReferencedTypes(), parentClass))
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
		String[] path = new String[]{module.getName().toLowerCase(), NATIVES};
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


	private Variable[] getNativeVariables(TaraModel model) {
		List<Variable> natives = new ArrayList<>();
		for (Node node : TaraUtil.getAllNodesOfFile(model))
			for (Variable variable : node.getVariables())
				if ("native".equals(variable.getType())) natives.add(variable);
		return natives.toArray(new Variable[natives.size()]);
	}
}
