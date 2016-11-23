package tara.intellij.lang.psi.resolve;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.psi.Identifier;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.project.TaraModuleType;
import tara.intellij.project.module.ModuleProvider;

import java.util.Collections;
import java.util.List;

import static com.intellij.psi.search.GlobalSearchScope.moduleScope;
import static tara.intellij.lang.psi.impl.TaraUtil.methodReference;

public class MethodReferenceSolver extends TaraReferenceSolver {
	private final Module module;
	private String outputDsl;

	public MethodReferenceSolver(Identifier identifier, TextRange range) {
		super(identifier, range);
		this.module = ModuleProvider.moduleOf(identifier);
		this.outputDsl = (TaraModuleType.isTara(module)) ? TaraUtil.workingPackage(identifier) : null;
	}

	@Override
	protected List<PsiElement> doMultiResolve() {
		if (outputDsl == null) return Collections.emptyList();
		final PsiClass aClass = JavaPsiFacade.getInstance(myElement.getProject()).findClass(methodReference(myElement), moduleScope(module));
		if (aClass == null) return Collections.emptyList();
		else return Collections.singletonList(findMethod(aClass.getMethods()));
	}

	private PsiElement findMethod(PsiMethod[] methods) {
		for (PsiMethod method : methods) if (method.getName().equals(myElement.getText())) return method;
		return null;
	}

	@Nullable
	@Override
	public PsiElement resolve() {
		ResolveResult[] resolveResults = multiResolve(false);
		return resolveResults.length == 1 ? resolveResults[0].getElement() : null;
	}

	@NotNull
	@Override
	public Object[] getVariants() {
		return new Object[0];
	}

}
