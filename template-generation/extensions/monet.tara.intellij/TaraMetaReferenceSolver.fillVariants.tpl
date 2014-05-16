	private Object[] fillVariants(List<String> elements) {
		List<LookupElement> variants = new ArrayList<>();
		for (final String element \: elements)
			if (element.length() != 0)
				variants.add(LookupElementBuilder.create(element).withIcon(::projectProperName::Icons.TARA).withTypeText("Tara"));
		return variants.toArray();
	}