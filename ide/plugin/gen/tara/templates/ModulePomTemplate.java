package tara.templates;

import org.siani.itrules.LineSeparator;
import org.siani.itrules.Template;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.LF;

public class ModulePomTemplate extends Template {

	protected ModulePomTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new ModulePomTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "pom"))).add(literal("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n     xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n     xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0\n                         http://maven.apache.org/maven-v4_0_0.xsd\">\n<modelVersion>4.0.0</modelVersion>\n\n<groupId>")).add(mark("project")).add(literal("</groupId>\n<artifactId>")).add(mark("name")).add(literal("</artifactId>\n<version>")).add(mark("version")).add(literal("</version>\n\n<properties>\n    <maven.compiler.source>1.8</maven.compiler.source>\n    <maven.compiler.target>1.8</maven.compiler.target>\n</properties>\n\n<build>\n    <outputDirectory>../out/production</outputDirectory>\n    <testOutputDirectory>../out/test</testOutputDirectory>\n    <directory>../out/build</directory>\n    <resources>\n        <resource>\n            <directory>${basedir}/../.tara/refactors</directory>\n        </resource>\n    </resources>\n</build>\n\n<repositories>\n    <repository>\n        <id>gisc-maven</id>\n        <name>gisc-maven-releases</name>\n        <url>https://artifactory.gisc.siani.es/artifactory/libs-release-local</url>\n    </repository>\n</repositories>\n\n\n<dependencies>\n    ")).add(mark("magritte").multiple("\n")).add(literal("\n    ")).add(mark("parentModule")).add(literal("\n    <dependency>\n        <groupId>com.esotericsoftware</groupId>\n        <scope>runtime</scope>\n        <artifactId>kryo</artifactId>\n        <version>3.0.0</version>\n    </dependency>\n    <dependency>\n        <groupId>junit</groupId>\n        <artifactId>junit</artifactId>\n        <scope>test</scope>\n        <version>LATEST</version>\n    </dependency>\n</dependencies>\n</project>")),
			rule().add((condition("type", "parent")), (condition("trigger", "parentModule"))).add(literal("<dependency>\n    <groupId>")).add(mark("groupId")).add(literal("</groupId>\n    <artifactId>")).add(mark("artifactId")).add(literal("</artifactId>\n    <scope>compile</scope>\n    <version>")).add(mark("version")).add(literal("</version>\n</dependency>")),
			rule().add((condition("type", "magritte")), (condition("type", "local")), (condition("trigger", "magritte"))).add(literal("<dependency>\n    <groupId>org.siani.magritte</groupId>\n    <artifactId>magritte</artifactId>\n    <scope>system</scope>\n    <version>1.0</version>\n    <systemPath>${project.basedir}/")).add(mark("filePath")).add(literal("</systemPath>\n</dependency>")),
			rule().add((condition("type", "magritte")), (condition("trigger", "magritte"))).add(literal("<dependency>\n    <groupId>org.siani.magritte</groupId>\n    <artifactId>magritte</artifactId>\n    <version>LATEST</version>\n    <!--<systempath>${project.basedir}</systemPath> use it to include local dependency-->\n</dependency>"))
		);
		return this;
	}
}