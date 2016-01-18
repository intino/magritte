package tara.templates;

import org.siani.itrules.LineSeparator;
import org.siani.itrules.Template;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.LF;

public class ExportPomTemplate extends Template {

	protected ExportPomTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new ExportPomTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "pom"))).add(literal("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0\n                             http://maven.apache.org/maven-v4_0_0.xsd\">\n  <modelVersion>4.0.0</modelVersion>\n\n  <groupId>")).add(mark("name")).add(literal("</groupId>\n  <artifactId>$module</artifactId>\n  <version>1.0</version>\n\n  <properties>\n    <maven.compiler.source>1.8</maven.compiler.source>\n    <maven.compiler.target>1.8</maven.compiler.target>\n  </properties>\n\n  <build>\n    <outputDirectory>../out/production</outputDirectory>\n    <testOutputDirectory>../out/test</testOutputDirectory>\n    <directory>../out/build</directory>\n\t<resources>\n\t  <resource>\n\t\t<directory>${basedir}/../.tara/refactors/</directory>\n\t  </resource>\n\t</resources>\n  </build>\n\n  <dependencies>\n\t")).add(mark("dependency").multiple("\n")).add(literal("\n\t<dependency>\n\t  <groupId>junit</groupId>\n\t  <artifactId>junit</artifactId>\n\t  <scope>test</scope>\n\t  <version>LATEST</version>\n\t</dependency>\n  </dependencies>\n</project>")),
			rule().add((condition("type", "dependency")), (condition("trigger", "dependency"))).add(literal("<dependency>\n\t<groupId>")).add(mark("groupId")).add(literal("</groupId>\n\t<artifactId>")).add(mark("artifactId")).add(literal("</artifactId>\n\t<scope>")).add(mark("scope")).add(literal("</scope>\n\t<version>")).add(mark("version")).add(literal("</version>\n\t")).add(expression().add(literal("<systemPath>")).add(literal("$")).add(literal("{basedir}")).add(mark("path")).add(literal("</systemPath>"))).add(literal("\n</dependency>"))
		);
		return this;
	}
}