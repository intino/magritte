package siani.tara.intellij.framework.maven;

import org.siani.itrules.LineSeparator;
import org.siani.itrules.Template;

import java.util.Locale;

public class ProjectPomTemplate extends Template {

	protected ProjectPomTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new ProjectPomTemplate(Locale.ENGLISH, LineSeparator.LF).define();
	}

	protected Template define() {
		add(
			rule().add(condition("type", "pom")).add(literal("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
				"<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n" +
				"         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
				"         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
				"  <modelVersion>4.0.0</modelVersion>\n" +
				"\n" +
				"<!--project-->\n" +
				"  <groupId>org.")).add(mark("project")).
				add(literal("</groupId>\n" + "  <artifactId>")).add(mark("project")).add(literal("</artifactId>\n" +
				"  <packaging>pom</packaging>\n" +
				"  <version>1.0</version>\n" +
				"  <name>")).add(mark("project")).add(literal(" Project</name>\n" +
				"\n" +
				"  <properties>\n" +
				"    <maven.compiler.source>1.7</maven.compiler.source>\n" +
				"    <maven.compiler.target>1.7</maven.compiler.target>\n" +
				"  </properties>\n" +
				"\n" +
				"</project>"))
		);
		return this;
	}
}
