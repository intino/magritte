package io.intino.tara.plugin.lang.psi.impl;

import com.intellij.psi.*;
import com.intellij.psi.search.GlobalSearchScope;
import io.intino.tara.lang.model.rules.CustomRule;
import io.intino.tara.lang.model.rules.variable.VariableRule;
import io.intino.tara.plugin.lang.psi.TaraVariable;
import io.intino.tara.plugin.project.module.ModuleProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PsiCustomWordRule implements VariableRule<Object>, CustomRule {

	private final String destiny;
	private final TaraVariable variable;
	private final PsiClass psiClass;
	private final List<String> words;

	public PsiCustomWordRule(String destiny, TaraVariable variable) {
		this.destiny = destiny;
		this.variable = variable;
		psiClass = findClass();
		words = collectEnums();
	}

	private PsiClass findClass() {
		return JavaPsiFacade.getInstance(variable.getProject()).findClass(TaraUtil.workingPackage(variable).toLowerCase() + ".rules." + destiny, GlobalSearchScope.moduleScope(ModuleProvider.moduleOf(variable)));
	}

	@Override
	public boolean accept(Object value) {
		if (psiClass != null && !isEnumType() || !(value instanceof List)) return true;
		for (Object o : ((List) value)) if (!words.contains(o.toString())) return false;
		return true;
	}

	public List<String> words() {
		return words;
	}

	@Override
	public List<Object> errorParameters() {
		return words.stream().map(v -> v).collect(Collectors.toList());
	}

	private List<String> collectEnums() {
		List<String> list = new ArrayList<>();
		if (psiClass == null) return Collections.emptyList();
		for (PsiField psiField : psiClass.getFields())
			if (psiField instanceof PsiEnumConstant) list.add(psiField.getName());
		return list;
	}

	private boolean isEnumType() {
		for (PsiClassType psiClassType : psiClass.getImplementsListTypes())
			if (psiClassType.getClassName().equals("Rule") && psiClassType.getParameters().length == 1 &&
					"Enum".equals(psiClassType.getParameters()[0].getPresentableText())) return true;
		return false;
	}

	@Override
	public Class<?> getLoadedClass() {
		return null;
	}

	@Override
	public void setLoadedClass(Class<?> loadedClass) {

	}

	@Override
	public String getExternalWordClass() {
		return psiClass.getQualifiedName();
	}
}
