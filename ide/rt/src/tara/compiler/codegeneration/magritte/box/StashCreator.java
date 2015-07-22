package tara.compiler.codegeneration.magritte.box;

import tara.Language;
import tara.compiler.core.CompilerConfiguration;
import tara.compiler.model.Model;
import tara.io.Stash;
import tara.language.model.Node;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class StashCreator {

	private final String project;
	private final String generatedLanguage;
	private final Language language;
	private final int level;
	private final Model model;
	private final Locale locale;
	private final List<Node> nodes;
	private Map<Node, Long> keymap = new LinkedHashMap<>();
	private long count = 1;

	private StashCreator(String project, String generatedLanguage, Language language, int level, Model model, Locale locale, List<Node> nodes) {
		this.project = project;
		this.generatedLanguage = generatedLanguage;
		this.language = language;
		this.level = level;
		this.model = model;
		this.locale = locale;
		this.nodes = nodes;
	}

	public StashCreator(CompilerConfiguration conf, Model model, List<Node> nodes) {
		this(conf.getProject(), conf.getGeneratedLanguage() != null ? conf.getGeneratedLanguage() : conf.getModule(), conf.getLanguage(), conf.getLevel(), model, conf.getLocale(), nodes);
	}


	public Stash create() {
		final Stash stash = new Stash();
		for (Node node : nodes) {
		}
		return stash;
	}
}
