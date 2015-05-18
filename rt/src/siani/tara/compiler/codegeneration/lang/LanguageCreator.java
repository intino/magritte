package siani.tara.compiler.codegeneration.lang;

import org.siani.itrules.Template;
import org.siani.itrules.engine.FrameBuilder;
import org.siani.itrules.model.AbstractFrame;
import siani.tara.compiler.codegeneration.Format;
import siani.tara.compiler.core.CompilerConfiguration;
import siani.tara.compiler.model.impl.Model;
import siani.tara.templates.LanguageTemplate;

import java.util.logging.Logger;

public class LanguageCreator {
	private static final Logger LOG = Logger.getLogger(LanguageCreator.class.getName());
	private final CompilerConfiguration configuration;
	private Model model;

	public LanguageCreator(CompilerConfiguration configuration, Model model) {
		this.configuration = configuration;
		this.model = model;
	}

	public String create() {
		final Template template = LanguageTemplate.create();
		template.add("date", Format.date());
		template.add("string", Format.string());
		template.add("reference", Format.reference());
		template.add("toCamelCase", Format.toCamelCase());
		return template.format(createFrame(model)).replace("$", "");
	}

	private AbstractFrame createFrame(final Model model) {
		final FrameBuilder builder = new FrameBuilder();
		builder.register(Model.class, new LanguageModelAdapter(configuration.getGeneratedLanguage(), configuration.getLocale(), configuration.loadLanguage(), configuration.isPlateRequired()));
		return builder.build(model);
	}
}
