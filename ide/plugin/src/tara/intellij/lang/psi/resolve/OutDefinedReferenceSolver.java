package tara.intellij.lang.psi.resolve;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveResult;
import com.intellij.psi.search.GlobalSearchScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.module.ModuleProvider;
import tara.lang.model.Primitive;
import tara.lang.model.Variable;
import tara.lang.model.rules.variable.NativeObjectRule;

import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;
import static tara.intellij.lang.psi.impl.TaraUtil.outputDsl;
import static tara.lang.model.Primitive.FUNCTION;
import static tara.lang.model.Primitive.OBJECT;

public class OutDefinedReferenceSolver extends TaraReferenceSolver {
	private final Module module;
	private String outputDsl;

	public OutDefinedReferenceSolver(@NotNull PsiElement element, TextRange range) {
		super(element, range);
		this.module = ModuleProvider.getModuleOf(element);
		final TaraFacet facet = TaraFacet.of(module);
		if (facet != null) this.outputDsl = outputDsl(element);
	}

	@Override
	protected List<PsiElement> doMultiResolve() {
		if (outputDsl == null) return Collections.emptyList();
		return singletonList(JavaPsiFacade.getInstance(myElement.getProject()).findClass(reference(), GlobalSearchScope.allScope(module.getProject())));
	}

	@NotNull
	private String reference() {
		Variable variable = TaraPsiImplUtil.getContainerByType(myElement, Variable.class);
		if (variable == null) return "";
		if (OBJECT.equals(variable.type()))
			return ((NativeObjectRule) variable.rule()).type();
		else return getPackage(variable.type()) + myElement.getText();
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
	private String getPackage(Primitive type) {
		return type == null ? "" :
			FUNCTION.equals(type) ?
				outputDsl.toLowerCase() + ".functions." :
				outputDsl.toLowerCase() + ".rules.";
	}

}
