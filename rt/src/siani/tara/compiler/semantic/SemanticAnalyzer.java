package siani.tara.compiler.semantic;

import siani.tara.Checker;
import siani.tara.Language;
import siani.tara.compiler.model.impl.Model;
import siani.tara.compiler.semantic.wrappers.LanguageRoot;
import siani.tara.semantic.SemanticException;

public class SemanticAnalyzer {
	private final Model model;
	private final Language language;

	public SemanticAnalyzer(Model model, Language language) {
		this.model = model;
		this.language = language;
	}

	public void analyze() throws SemanticException {
		new Checker(language).check(new LanguageRoot(model));
	}
}
