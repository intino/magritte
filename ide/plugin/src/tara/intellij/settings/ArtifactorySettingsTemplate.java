package tara.intellij.settings;

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
			rule().add((condition("type", "artifactory"))).add(literal("<settings>\n\t<servers>\n\t\t<server>\n\t\t\t<id>")).add(mark("server")).add(literal("</id>\n\t\t\t<username>")).add(mark("username")).add(literal("</username>\n\t\t\t<password>")).add(mark("password")).add(literal("</password>\n\t\t</server>\n\t</servers>\n</settings>"))
		);
		return this;
	}
}