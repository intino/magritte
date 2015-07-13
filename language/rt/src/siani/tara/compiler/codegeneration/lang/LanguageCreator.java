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
	private final CompilerConfiguration conf;
	private Model model;

	public LanguageCreator(CompilerConfiguration conf, Model model) {
		this.conf = conf;
		this.model = model;
	}

	public String create() {
		final Template template = LanguageTemplate.create();
		template.add("string", Format.string());
		template.add("reference", Format.reference());
		template.add("toCamelCase", Format.toCamelCase());
		return template.format(createFrame(model)).replace("$", "");
	}

	private AbstractFrame createFrame(final Model model) {
		final FrameBuilder builder = new FrameBuilder();
		builder.register(Model.class, new LanguageModelAdapter(conf.getGeneratedLanguage(), conf.getLocale(), conf.loadLanguage(), conf.isPlateRequired(), conf.getLevel()));
		return builder.build(model);
	}
}
