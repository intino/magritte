package tara.intellij.actions.utils;

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
			rule().add((condition("type", "pom"))).add(literal("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd\">\n    <modelVersion>4.0.0</modelVersion>\n\n    <groupId>")).add(mark("name", "lowercase")).add(literal("</groupId>\n    <artifactId>$module</artifactId>\n    <version>1.0</version>\n\n    <properties>\n        <maven.compiler.source>1.8</maven.compiler.source>\n        <maven.compiler.target>1.8</maven.compiler.target>\n    </properties>\n\n    <build>\n        <outputDirectory>../out/production/$module</outputDirectory>\n        <testOutputDirectory>../out/test/$module</testOutputDirectory>\n        <directory>../out/build/$module</directory>\n        <resources>\n            <resource>\n                <directory>${basedir}/../.tara/refactors/</directory>\n            </resource>\n        </resources>\n    </build>\n\n\t<repositories>\n        <repository>\n            <id>siani-maven</id>\n            <name>siani-maven-releases</name>\n            <url>http://artifactory.siani.es/artifactory/libs-release</url>\n        </repository>\n    </repositories>\n\n\t<distributionManagement>\n\t\t<repository>\n\t\t\t<id>siani-maven</id>\n\t\t\t<name>siani-maven-releases</name>\n\t\t\t<url>https://artifactory.siani.es/artifactory/libs-release-local</url>\n\t\t</repository>\n\t</distributionManagement>\n\n\n\n    <dependencies>\n        ")).add(mark("dependency").multiple("\n")).add(literal("\n        <dependency>\n            <groupId>junit</groupId>\n            <artifactId>junit</artifactId>\n            <scope>test</scope>\n            <version>LATEST</version>\n        </dependency>\n    </dependencies>\n</project>")),
			rule().add((condition("type", "dependency")), (condition("trigger", "dependency"))).add(literal("<dependency>\n    <groupId>")).add(mark("groupId")).add(literal("</groupId>\n    <artifactId>")).add(mark("artifactId")).add(literal("</artifactId>\n    <version>")).add(mark("version")).add(literal("</version>\n</dependency>"))
		);
		return this;
	}
}