package io.intino.tara.plugin.project.configuration.maven;

import org.siani.itrules.*;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.*;

public class ModulePomTemplate extends Template {

	protected ModulePomTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new ModulePomTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "pom"))).add(literal("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n\t\t xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd\">\n\t<modelVersion>4.0.0</modelVersion>\n\n\t<groupId>")).add(mark("project")).add(literal("</groupId>\n\t<artifactId>")).add(mark("name")).add(literal("</artifactId>\n\t<version>")).add(mark("version")).add(literal("</version>\n\n\t<properties>\n\t\t<maven.compiler.source>1.8</maven.compiler.source>\n\t\t<maven.compiler.target>1.8</maven.compiler.target>\n\t\t<tara.platform.dsl>Proteo</tara.platform.dsl>\n\t\t<tara.application.dsl></tara.application.dsl>\n\t\t<tara.system.dsl></tara.system.dsl>\n\t\t<tara.application.dsl.from.artifactory></tara.application.dsl.from.artifactory>\n\t\t<tara.system.dsl.from.artifactory></tara.system.dsl.from.artifactory>\n\t\t<tara.supported.languages>tara</tara.supported.languages>\n\t</properties>\n\n\t<build>\n\t\t<outputDirectory>")).add(expression().add(mark("default")).or(expression().add(literal("../")))).add(literal("out/production/")).add(mark("name")).add(literal("</outputDirectory>\n\t\t<testOutputDirectory>")).add(expression().add(mark("default")).or(expression().add(literal("../")))).add(literal("out/test/")).add(mark("name")).add(literal("</testOutputDirectory>\n\t\t<directory>")).add(expression().add(mark("default")).or(expression().add(literal("../")))).add(literal("out/build/")).add(mark("name")).add(literal("</directory>\n\t\t<resources>\n\t\t\t<resource>\n\t\t\t\t<directory>${basedir}/../.tara/refactors</directory>\n\t\t\t</resource>\n\t\t</resources>\n\t</build>\n\t<repositories>\n\t\t<repository>\n\t\t\t<id>siani-maven</id>\n\t\t\t<name>siani-maven-releases</name>\n\t\t\t<url>http://artifactory.siani.es/artifactory/libs-release</url>\n\t\t</repository>\n\t</repositories>\n\n\t<distributionManagement>\n\t\t<repository>\n\t\t\t<id>siani-maven</id>\n\t\t\t<name>siani-maven-releases</name>\n\t\t\t<url>https://artifactory.siani.es/artifactory/libs-release-local</url>\n\t\t</repository>\n\t</distributionManagement>\n\n\t<dependencies>\n\t\t")).add(mark("magritte").multiple("\n")).add(literal("\n\t\t")).add(mark("parentModule")).add(literal("\n\t\t<dependency>\n\t\t\t<groupId>junit</groupId>\n\t\t\t<artifactId>junit</artifactId>\n\t\t\t<scope>test</scope>\n\t\t\t<version>LATEST</version>\n\t\t</dependency>\n\t</dependencies>\n</project>")),
			rule().add((condition("type", "parent")), (condition("trigger", "parentModule"))).add(literal("<dependency>\n    <groupId>")).add(mark("groupId")).add(literal("</groupId>\n    <artifactId>")).add(mark("artifactId")).add(literal("</artifactId>\n    <version>")).add(mark("version")).add(literal("</version>\n</dependency>")),
			rule().add((condition("type", "magritte")), (condition("trigger", "magritte"))).add(literal("<dependency>\n    <groupId>io.intino.tara</groupId>\n    <artifactId>magritte</artifactId>\n    <version>LATEST</version>\n</dependency>"))
		);
		return this;
	}
}