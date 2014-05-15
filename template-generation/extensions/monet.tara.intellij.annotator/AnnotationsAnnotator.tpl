	private List<PsiElement> checkAnnotationList(PsiElement[] annotations, String[] correctAnnotation) {
		List<PsiElement> incorrectAnnotations = new ArrayList<>();
		for (PsiElement annotation \: annotations) {
			if (annotation instanceof PsiWhiteSpace) continue;
			count(annotation);
			if (!isIn(correctAnnotation, annotation.getText()))
				incorrectAnnotations.add(annotation);
			else if (::projectProperName::Types.CODE.equals(annotation.getNode().getElementType()))
				isCodeableDefinition(::projectProperName::PsiImplUtil.getContextOf(annotation));

		}
		return incorrectAnnotations;
	}

	private boolean isCodeableDefinition(Definition definition) {
		::projectProperName::MetaIdentifier[] metaIds = com.intellij.psi.util.PsiTreeUtil.getChildrenOfType(definition.getSignature(), ::projectProperName::MetaIdentifier.class);
		::projectProperName::MetaIdentifier metaId;
		if (metaIds == null) metaId = searchBaseDefinition(definition);
		else metaId = metaIds[0];

		return conceptHasCode(metaId);
	}

	private ::projectProperName::MetaIdentifier searchBaseDefinition(Definition definition) {
		Definition baseDefinition = ::projectProperName::Util.getBaseDefinitionOf(definition);
		return com.intellij.psi.util.PsiTreeUtil.getChildrenOfType(baseDefinition.getSignature(), ::projectProperName::MetaIdentifier.class)[0];
	}

	private boolean conceptHasCode(::projectProperName::MetaIdentifier metaId) {
		monet.tara.lang.ASTWrapper heritage = monet.::projectName::.intellij.metamodel.::projectProperName::Language.getHeritage();
		monet.tara.lang.ASTNode node = heritage.getNodeNameLookUpTable().get(metaId.getText()).get(0);
		if (node.hasCode()) return true;
		monet.tara.lang.ASTNode ancestry;
		while ((ancestry = heritage.searchAncestry(node)) != null) if (ancestry.hasCode()) return true;
		return false;
	}