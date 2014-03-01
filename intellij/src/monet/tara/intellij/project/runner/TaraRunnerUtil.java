package monet.tara.intellij.project.runner;

import com.intellij.execution.configurations.RunConfigurationModule;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import monet.tara.intellij.metamodel.psi.Concept;
import monet.tara.intellij.metamodel.psi.impl.TaraFileImpl;
import org.jetbrains.annotations.Nullable;

public class TaraRunnerUtil {
	@Nullable
	public static PsiClass getRunningClass(@Nullable PsiElement element) {
		if (element == null) return null;

		final PsiFile file = element.getContainingFile();
		if (!(file instanceof TaraFileImpl)) return null;

		for (PsiClass clazz = PsiTreeUtil.getParentOfType(element, PsiClass.class);
		     clazz != null;
		     clazz = PsiTreeUtil.getParentOfType(clazz, PsiClass.class)) {
			if (canBeRunByTara(clazz)) return clazz;
		}

//		if (((TaraFileImpl)file).isScript()) return ((TaraFileImpl)file).getScriptClass();
//		final PsiClass[] classes = ((TaraFileImpl)file).getClasses();
//		if (classes.length > 0) {
//			return classes[0];
//		}

		return null;
	}

	public static boolean isRunnable(@Nullable final PsiClass psiClass) {
		if (psiClass == null) return false;
		final PsiClass runnable =
			JavaPsiFacade.getInstance(psiClass.getProject()).findClass(CommonClassNames.JAVA_LANG_RUNNABLE, psiClass.getResolveScope());
		if (runnable == null) return false;

		return psiClass instanceof Concept && //TODO
			!(psiClass instanceof PsiAnonymousClass) &&
			!psiClass.isInterface() &&
			psiClass.isInheritor(runnable, true);
	}

	public static boolean canBeRunByTara(final PsiClass psiClass) {
//		return psiClass instanceof GroovyScriptClass ||
//				isRunnable(psiClass) ||
//				psiClass instanceof GrTypeDefinition && PsiMethodUtil.hasMainMethod(psiClass);
		return true;
	}

	public static String getConfigurationName(PsiClass aClass, RunConfigurationModule module) {
		String qualifiedName = aClass.getQualifiedName();
		Project project = module.getProject();
		if (qualifiedName == null) {
			return module.getModuleName();
		}

		PsiClass psiClass =
			JavaPsiFacade.getInstance(project).findClass(qualifiedName.replace('$', '.'), GlobalSearchScope.projectScope(project));
		if (psiClass != null) {
			return psiClass.getName();
		} else {
			int lastDot = qualifiedName.lastIndexOf('.');
			if (lastDot == -1 || lastDot == qualifiedName.length() - 1) {
				return qualifiedName;
			}
			return qualifiedName.substring(lastDot + 1, qualifiedName.length());
		}
	}
}
