package siani.tara.compiler.semantic;

import siani.tara.Checker;
import siani.tara.Language;
import siani.tara.compiler.semantic.wrappers.LanguageNode;
import siani.tara.compiler.model.Model;
import siani.tara.compiler.model.Node;
import siani.tara.semantic.SemanticException;

public class SemanticAnalyzer {
	private final Model model;
	private final Language language;

	public SemanticAnalyzer(Model model, Language language) {
		this.model = model;
		this.language = language;
	}


	public void analyze() throws SemanticException {
		Checker checker = new Checker(language);
		for (Node node : model.getNodeTree()) {
			checker.check(new LanguageNode(node));
		}
	}
}
