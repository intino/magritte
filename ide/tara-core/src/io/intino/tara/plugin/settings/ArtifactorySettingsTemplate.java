package io.intino.tara.plugin.settings;

import org.siani.itrules.LineSeparator;
import org.siani.itrules.Template;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.LF;

public class ArtifactorySettingsTemplate extends Template {

	protected ArtifactorySettingsTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new ArtifactorySettingsTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "artifactory"))).add(literal("<settings>\n\t<servers>\n\t\t")).add(mark("server").multiple("\n")).add(literal("\n\t</servers>\n</settings>")),
			rule().add((condition("type", "server"))).add(literal("<server>\n\t<id>")).add(mark("name")).add(literal("</id>\n\t<username>")).add(mark("username")).add(literal("</username>\n\t<password>")).add(mark("password")).add(literal("</password>\n</server>"))
		);
		return this;
	}
}