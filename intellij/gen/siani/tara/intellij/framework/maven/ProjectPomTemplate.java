package siani.tara.intellij.framework.maven;

import org.siani.itrules.*;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.*;

public class ProjectPomTemplate extends Template {

	protected ProjectPomTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new ProjectPomTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "pom"))).add(literal("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0\n                             http://maven.apache.org/maven-v4_0_0.xsd\">\n  <modelVersion>4.0.0</modelVersion>\n\n  <groupId>")).add(mark("name")).add(literal("</groupId>\n  <artifactId>")).add(mark("name")).add(literal("</artifactId>\n  <version>")).add(mark("version")).add(literal("</version>\n\n  <properties>\n    <maven.compiler.source>1.7</maven.compiler.source>\n    <maven.compiler.target>1.7</maven.compiler.target>\n  </properties>\n\n  <build>\n    <outputDirectory>../dist/production/")).add(mark("name")).add(literal("</outputDirectory>\n    <testOutputDirectory>../dist/test/")).add(mark("name")).add(literal("</testOutputDirectory>\n  </build>\n\n</project>"))
		);
		return this;
	}
}