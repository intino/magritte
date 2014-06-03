public void checkWellReferenced() {
		PsiElement reference = ReferenceManager.resolve((Identifier) element, false);
		if (reference == null && !isWellMetaReferenced(element.getParent().getText())) {
			Annotation errorAnnotation;
			if (element.getParent() instanceof IdentifierReference)
				addImportAlternatives((Identifier) element);
			else {
				String message = ::projectProperName::Bundle.message("reference.definition.key.error.message");
				errorAnnotation = annotateAndFix(element, new RemoveImportFix((::projectProperName::PsiElement) element.getParent()), message);
				errorAnnotation.setTextAttributes(::projectProperName::SyntaxHighlighter.UNRESOLVED_ACCESS);
			}
		}
	}

	private boolean isWellMetaReferenced(String reference) {
        monet.tara.lang.TreeWrapper heritage = monet.goros.intellij.lang.GorosLanguage.getHeritage();
        String[] refRoute = reference.split("\\\\.");
        List<monet.tara.lang.AbstractNode> nodes = heritage.getNodeNameLookUpTable().get(refRoute[0]);
        return nodes != null && nodes.get(0) != null &&
            nodes.get(0).resolveChild(java.util.Arrays.copyOfRange(refRoute, 1, refRoute.length));
    }