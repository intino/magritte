package io.intino.tara.compiler.codegeneration.lang;

import io.intino.itrules.Frame;
import io.intino.itrules.FrameBuilder;
import io.intino.itrules.Template;
import io.intino.tara.compiler.codegeneration.Format;
import io.intino.tara.compiler.core.CompilerConfiguration;
import io.intino.tara.compiler.model.Model;

import java.util.Collection;
import java.util.Iterator;

class LanguageCreator {
	private final CompilerConfiguration conf;
	private Collection<Model> models;

	LanguageCreator(CompilerConfiguration conf, Collection<Model> models) {
		this.conf = conf;
		this.models = models;
	}

	public String create() {
		final Template template = new LanguageTemplate();
		template.add("string", Format.string());
		template.add("reference", Format.reference());
		template.add("toCamelCase", Format.toCamelCase());
		template.add("withDollar", Format.withDollar());
		Iterator<Model> iterator = models.iterator();
		FrameBuilder builder = createFrame(iterator.next());
		iterator.forEachRemaining(m -> merge(builder, createFrame(m)));
		Frame frame = builder.toFrame();
		return template.render(frame);
	}

	private void merge(FrameBuilder main, FrameBuilder newBuilder) {
		newBuilder.toFrame().frames("node").forEachRemaining(n -> main.add("node", n));
	}

	private FrameBuilder createFrame(final Model model) {
		final FrameBuilder builder = new FrameBuilder();
		final CompilerConfiguration.Language language = conf.model().language();
		builder.put(Model.class, new LanguageModelAdapter(conf.model().outDsl(), conf.getLocale(), model.language(), conf.model().level(), conf.workingPackage(), language.generationPackage()));
		return builder.append(model);
	}
}
