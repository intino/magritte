package tara.intellij.lang.psi.impl;

import tara.lang.model.Rule;

public class PsiCustomRule implements Rule<Object> {

	private final String destiny;

	public PsiCustomRule(String destiny) {
		this.destiny = destiny;
	}

	@Override
	public boolean accept(Object value) {
		return true;
	}
}
