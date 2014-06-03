	private List<PsiElement> checkAnnotationList(PsiElement[] annotations, String[] correctAnnotation) {
		List<PsiElement> incorrectAnnotations = new ArrayList<>();
		for (PsiElement annotation \: annotations) {
			if (annotation instanceof PsiWhiteSpace) continue;
			count(annotation);
			if (!isIn(correctAnnotation, annotation.getText()))
				incorrectAnnotations.add(annotation);
		}
		return incorrectAnnotations;
	}

	private monet.::projectName::.intellij.lang.psi.::projectProperName::MetaIdentifier searchBaseDefinition(Definition definition) {
		Definition baseDefinition = monet.::projectName::.intellij.lang.psi.impl.::projectProperName::Util.getBaseDefinitionOf(definition);
		return com.intellij.psi.util.PsiTreeUtil.getChildrenOfType(baseDefinition.getSignature(), monet.::projectName::.intellij.lang.psi.::projectProperName::MetaIdentifier.class)[0];
	}