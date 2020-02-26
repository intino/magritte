package io.intino.magritte.dsl;

import io.intino.magritte.lang.model.rules.Size;
import io.intino.magritte.lang.semantics.constraints.RuleFactory;

import java.util.Collections;
import java.util.Locale;

import static io.intino.magritte.lang.semantics.constraints.RuleFactory.*;

public class Proteo extends Tara {

	private static Size MULTIPLE = new Size(0, Integer.MAX_VALUE);
	public static final String GROUP_ID = "io.intino.tara";
	public static final String ARTIFACT_ID = "magritte";

	public Proteo() {
		def(Tara.Root).with(context(Tara.Root).has(
				component(ProteoConstants.CONCEPT, Collections.singletonList(MULTIPLE)),
				component(ProteoConstants.FACET, Collections.singletonList(MULTIPLE))));
		def(ProteoConstants.CONCEPT).with(context(ProteoConstants.META_CONCEPT).has(name(),
				component(ProteoConstants.CONCEPT, Collections.singletonList(MULTIPLE)),
				component(ProteoConstants.ASPECT, Collections.singletonList(MULTIPLE)),
				component(ProteoConstants.META_CONCEPT, Collections.singletonList(MULTIPLE)),
				component(ProteoConstants.META_ASPECT, Collections.singletonList(MULTIPLE)))
				.assume(RuleFactory.isTerminal()));
		def(ProteoConstants.ASPECT).with(context(ProteoConstants.ASPECT).has(name(),
				component(ProteoConstants.CONCEPT, Collections.singletonList(MULTIPLE))));
		def(ProteoConstants.FACET).with(context(ProteoConstants.FACET).has(name(),
				component(ProteoConstants.META_CONCEPT, Collections.singletonList(MULTIPLE)),
				component(ProteoConstants.CONCEPT, Collections.singletonList(MULTIPLE))).assume(isTerminal()));
	}

	@Override
	public String languageName() {
		return "Proteo";
	}

	@Override
	public Locale locale() {
		return Locale.ENGLISH;
	}

	@Override
	public boolean isTerminalLanguage() {
		return false;
	}

	@Override
	public String metaLanguage() {
		return "";
	}
}