package tara.compiler.semantic.wrappers;

import tara.semantic.model.Element;

public abstract class LanguageElement implements Element {

	public abstract tara.compiler.model.Element element();
}
