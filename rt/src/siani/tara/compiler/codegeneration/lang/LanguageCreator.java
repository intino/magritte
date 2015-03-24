package siani.tara.compiler.codegeneration.lang;

import org.siani.itrules.Document;
import org.siani.itrules.ItrRulesReader;
import org.siani.itrules.RuleEngine;
import org.siani.itrules.framebuilder.FrameBuilder;
import org.siani.itrules.model.Frame;
import siani.tara.compiler.codegeneration.ResourceManager;
import siani.tara.compiler.core.CompilerConfiguration;
import siani.tara.compiler.core.errorcollection.TaraException;
import siani.tara.compiler.model.impl.Model;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LanguageCreator {
	private static final Logger LOG = Logger.getLogger(LanguageCreator.class.getName());
	private static final String LANGUAGE_ITR = "Language.itr";
	private final CompilerConfiguration configuration;
	private Model model;

	public LanguageCreator(CompilerConfiguration configuration, Model model) {
		this.configuration = configuration;
		this.model = model;
	}

	public String create() {
		try {
			Document document = new Document();
			RuleEngine ruleEngine = new RuleEngine(ItrRulesReader.read(loadRules(LANGUAGE_ITR)));
			ruleEngine.render(createFrame(model), document);
			return document.content().replace("$","");
		} catch (TaraException e) {
			return null;
		}
	}

	private Frame createFrame(final Model model) {
		final FrameBuilder builder = new FrameBuilder();
		builder.register(Model.class, new ModelAdapter(configuration.getGeneratedLanguage(), configuration.getLocale(), configuration.loadLanguage()));
		return builder.createFrame(model);
	}


	private InputStream loadRules(String itr) throws TaraException {
		InputStream stream = getRulesFromResources(itr);
		if (stream == null) {
			LOG.log(Level.SEVERE, itr + ".itr rules file not found.");
			throw new TaraException(itr + ".itr rules file not found.");
		}
		return stream;
	}

	private InputStream getRulesFromResources(String rules) throws TaraException {
		return ResourceManager.getStream("rules/" + rules);
	}


}
