package tara.intellij.lang.psi.resolve;

import com.intellij.openapi.module.Module;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveResult;
import com.intellij.psi.search.GlobalSearchScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.psi.Identifier;
import tara.language.model.Primitives;
import tara.language.model.Variable;

public class OutDefinedReferenceSolver extends TaraReferenceSolver {
	private final Identifier identifier;
	private final Module module;
	private final String generatedDslName;

	public OutDefinedReferenceSolver(Identifier identifier, Module module, String generatedDslName) {
		super(identifier, identifier.getTextRange());
		this.identifier = identifier;
		this.module = module;
		this.generatedDslName = generatedDslName;
	}

	@Override
	protected PsiElement doMultiResolve() {
		return JavaPsiFacade.getInstance(myElement.getProject()).findClass(getPackage() + identifier.getText(), GlobalSearchScope.moduleScope(module));
	}

	@NotNull
	private String getPackage() {
		String type = getVariableType();
		if (type == null) return "";
		switch (type) {
			case Primitives.WORD:
				return generatedDslName.toLowerCase() + ".words.";
			case Primitives.NATIVE:
				return generatedDslName.toLowerCase() + ".natives.";
			case Primitives.MEASURE:
				return generatedDslName.toLowerCase() + ".metrics.";
		}
		return "";
	}

	private String getVariableType() {
		PsiElement parent = identifier;

		while (parent != null) if (parent instanceof Variable)
			return ((Variable) parent).type();
		else parent = parent.getParent();
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