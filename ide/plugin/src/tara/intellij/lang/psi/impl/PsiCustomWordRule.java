package tara.intellij.lang.psi.impl;

import com.intellij.openapi.module.Module;
import com.intellij.psi.*;
import com.intellij.psi.search.GlobalSearchScope;
import tara.intellij.project.facet.TaraFacet;
import tara.lang.model.Rule;

import java.util.ArrayList;
import java.util.List;

public class PsiCustomWordRule implements Rule<Object> {

	private final String destiny;
	private final Module module;
	private final PsiClass psiClass;
	private final List<?> enums;

	public PsiCustomWordRule(String destiny, Module module) {
		this.destiny = destiny;
		this.module = module;
		psiClass = findClass();
		enums = collectEnums();
	}

	private PsiClass findClass() {
		final TaraFacet facet = TaraFacet.of(module);
		if (facet == null) return null;
		final String generatedDslName = facet.getConfiguration().getGeneratedDslName();
		return JavaPsiFacade.getInstance(module.getProject()).findClass(generatedDslName.toLowerCase() + ".rules." + destiny, GlobalSearchScope.moduleScope(module));
	}

	@Override
	public boolean accept(Object value) {
		if (!isEnumType() || !(value instanceof List)) return true;
		for (Object o : ((List) value)) if (!enums.contains(o.toString())) return false;
		return true;

	}

	@Override
	public List<Object> errorParameters() {
		return (List<Object>) enums;
	}

	public List<?> collectEnums() {
		List<String> list = new ArrayList<>();
		for (PsiField psiField : psiClass.getFields()) if (psiField instanceof PsiEnumConstant) list.add(psiField.getName());
		return list;
	}

	public boolean isEnumType() {
		for (PsiClassType psiClassType : psiClass.getImplementsListTypes())
			if (psiClassType.getClassName().equals("Rule") && psiClassType.getParameters().length == 1 &&
				"Enum".equals(psiClassType.getParameters()[0].getPresentableText())) return true;
		return false;
	}
}
