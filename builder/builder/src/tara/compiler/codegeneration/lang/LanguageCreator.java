package tara.compiler.codegeneration.lang;

import org.siani.itrules.Template;
import org.siani.itrules.engine.FrameBuilder;
import org.siani.itrules.model.AbstractFrame;
import tara.compiler.codegeneration.Format;
import tara.compiler.core.CompilerConfiguration;
import tara.compiler.model.Model;

class LanguageCreator {
	private final CompilerConfiguration conf;
	private Model model;

	LanguageCreator(CompilerConfiguration conf, Model model) {
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
		builder.register(Model.class, new LanguageModelAdapter(conf.outDSL(), conf.getLocale(), conf.language(), conf.level(), conf.workingPackage(), conf.calculateLanguageWorkingPackage()));
		return builder.build(model);
	}
}
