package tara.intellij.lang.psi.resolve;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveResult;
import com.intellij.psi.search.GlobalSearchScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.module.ModuleProvider;
import tara.lang.model.Primitive;
import tara.lang.model.Variable;

import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;
import static tara.lang.model.Primitive.FUNCTION;

public class OutDefinedReferenceSolver extends TaraReferenceSolver {
	private final Module module;
	private String outputDsl;

	public OutDefinedReferenceSolver(@NotNull PsiElement element, TextRange range) {
		super(element, range);
		this.module = ModuleProvider.getModuleOf(element);
		final TaraFacet facet = TaraFacet.of(module);
		if (facet != null) this.outputDsl = facet.getConfiguration().outputDsl();
	}

	@Override
	protected List<PsiElement> doMultiResolve() {
		if (outputDsl == null) return Collections.emptyList();
		return singletonList(JavaPsiFacade.getInstance(myElement.getProject()).findClass(getPackage() + myElement.getText(), GlobalSearchScope.moduleScope(module)));
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

	@NotNull
	private String getPackage() {
		Primitive type = getVariableType();
		return type == null ? "" :
			FUNCTION.equals(type) ?
				outputDsl.toLowerCase() + ".functions." :
				outputDsl.toLowerCase() + ".rules.";
	}

	private Primitive getVariableType() {
		PsiElement parent = myElement;
		while (parent != null) if (parent instanceof Variable)
			return ((Variable) parent).type();
		else parent = parent.getParent();
		return null;
	}


}
