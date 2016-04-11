package tara.intellij.lang.psi.impl;

import com.intellij.openapi.module.Module;
import com.intellij.psi.*;
import com.intellij.psi.search.GlobalSearchScope;
import tara.intellij.project.facet.TaraFacet;
import tara.lang.model.rules.variable.VariableRule;

import java.util.ArrayList;
import java.util.List;

public class PsiCustomWordRule implements VariableRule<Object> {

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
		final String outputDsl = facet.getConfiguration().outputDsl();
		return JavaPsiFacade.getInstance(module.getProject()).findClass(outputDsl.toLowerCase() + ".rules." + destiny, GlobalSearchScope.moduleScope(module));
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

	private List<?> collectEnums() {
		List<String> list = new ArrayList<>();
		for (PsiField psiField : psiClass.getFields()) if (psiField instanceof PsiEnumConstant) list.add(psiField.getName());
		return list;
	}

	private boolean isEnumType() {
		for (PsiClassType psiClassType : psiClass.getImplementsListTypes())
			if (psiClassType.getClassName().equals("Rule") && psiClassType.getParameters().length == 1 &&
				"Enum".equals(psiClassType.getParameters()[0].getPresentableText())) return true;
		return false;
	}

}
