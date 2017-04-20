package io.intino.tara.compiler.codegeneration.lang;

import io.intino.tara.compiler.codegeneration.Format;
import io.intino.tara.compiler.core.CompilerConfiguration;
import io.intino.tara.compiler.model.Model;
import org.siani.itrules.Template;
import org.siani.itrules.engine.FrameBuilder;
import org.siani.itrules.model.Frame;

import java.util.Collection;

class LanguageCreator {
	private final CompilerConfiguration conf;
	private Collection<Model> models;

	LanguageCreator(CompilerConfiguration conf, Collection<Model> models) {
		this.conf = conf;
		this.models = models;
	}

	public String create() {
		final Template template = LanguageTemplate.create();
		template.add("string", Format.string());
		template.add("reference", Format.reference());
		template.add("toCamelCase", Format.toCamelCase());
		Frame frame = null;
		for (Model model : models) {
			if (frame == null) frame = createFrame(model);
			else merge(frame, createFrame(model));
		}
		return template.format(frame).replace("$", "");
	}

	private void merge(Frame main, Frame newFrame) {
		newFrame.frames("node").forEachRemaining(n -> main.addFrame("node", n));
	}

	private Frame createFrame(final Model model) {
		final FrameBuilder builder = new FrameBuilder();
		final CompilerConfiguration.DSL dsl = conf.languages().stream().filter(d -> d.name().equals(model.languageName())).findFirst().orElse(null);
		builder.register(Model.class, new LanguageModelAdapter(conf.outDSL(), conf.getLocale(), model.language(), conf.level(), conf.workingPackage(), dsl.generationPackage()));
		return (Frame) builder.build(model);
	}
}
