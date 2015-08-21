package tara.intellij.lang.psi.resolve;

import com.intellij.openapi.module.Module;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveResult;
import com.intellij.psi.search.GlobalSearchScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.psi.Identifier;

public class OutDefinedWordReferenceSolver extends TaraReferenceSolver {
	private final Identifier identifier;
	private final Module module;
	private final String generatedDslName;

	public OutDefinedWordReferenceSolver(Identifier identifier, Module module, String generatedDslName) {
		super(identifier, identifier.getTextRange());
		this.identifier = identifier;
		this.module = module;
		this.generatedDslName = generatedDslName;
	}

	@Override
	protected PsiElement doMultiResolve() {
		return JavaPsiFacade.getInstance(myElement.getProject()).findClass(generatedDslName + ".words." + identifier.getText(), GlobalSearchScope.moduleScope(module));
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
