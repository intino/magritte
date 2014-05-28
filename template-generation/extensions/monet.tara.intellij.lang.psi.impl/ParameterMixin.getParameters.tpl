	public monet.tara.intellij.lang.psi.Parameter[] getParameters() {
		java.util.List<monet.tara.intellij.lang.psi.TaraParameter> parameterList = ((TaraParametersImpl) this).getParameterList();
		return parameterList.toArray(new monet.tara.intellij.lang.psi.Parameter[parameterList.size()]);
	}