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
			rule().add((condition("type", "pom"))).add(literal("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd\">\n    <modelVersion>4.0.0</modelVersion>\n\n    <groupId>")).add(mark("name", "lowercase")).add(literal("</groupId>\n    <artifactId>$module</artifactId>\n    <version>1.0</version>\n\n    <properties>\n        <maven.compiler.source>1.8</maven.compiler.source>\n        <maven.compiler.target>1.8</maven.compiler.target>\n    </properties>\n\n    <build>\n        <outputDirectory>../out/production/$module</outputDirectory>\n        <testOutputDirectory>../out/test/$module</testOutputDirectory>\n        <directory>../out/build/$module</directory>\n        <resources>\n            <resource>\n                <directory>${basedir}/../.tara/refactors/</directory>\n            </resource>\n        </resources>\n        ")).add(mark("m1")).add(literal("\n    </build>\n\n\t<repositories>\n        <repository>\n            <id>siani-maven</id>\n            <name>siani-maven-releases</name>\n            <url>http://artifactory.siani.es/artifactory/libs-release</url>\n        </repository>\n    </repositories>\n\n\t<distributionManagement>\n\t\t<repository>\n\t\t\t<id>siani-maven</id>\n\t\t\t<name>siani-maven-releases</name>\n\t\t\t<url>https://artifactory.siani.es/artifactory/libs-release-local</url>\n\t\t</repository>\n\t</distributionManagement>\n\n\n\n    <dependencies>\n        ")).add(mark("dependency").multiple("\n")).add(literal("\n        <dependency>\n            <groupId>junit</groupId>\n            <artifactId>junit</artifactId>\n            <scope>test</scope>\n            <version>LATEST</version>\n        </dependency>\n    </dependencies>\n</project>")),
			rule().add((condition("type", "dependency")), (condition("trigger", "dependency"))).add(literal("<dependency>\n    <groupId>")).add(mark("groupId")).add(literal("</groupId>\n    <artifactId>")).add(mark("artifactId")).add(literal("</artifactId>\n    <version>")).add(mark("version")).add(literal("</version>\n</dependency>")),
			rule().add((condition("value", "true")), (condition("trigger", "m1"))).add(literal("<finalName>${project.artifactId}</finalName>\n<plugins>\n\t<plugin>\n\t\t<groupId>org.apache.maven.plugins</groupId>\n\t\t<artifactId>maven-compiler-plugin</artifactId>\n\t\t<version>3.3</version>\n\t\t<configuration>\n\t\t\t<source>1.8</source>\n\t\t\t<target>1.8</target>\n\t\t</configuration>\n\t</plugin>\n\t<plugin>\n\t\t<groupId>org.apache.maven.plugins</groupId>\n\t\t<artifactId>maven-assembly-plugin</artifactId>\n\t\t<configuration>\n\t\t\t<archive>\n\t\t\t\t<manifest>\n\t\t\t\t\t<mainClass>test.Main</mainClass>\n\t\t\t\t</manifest>\n\t\t\t</archive>\n\t\t\t<descriptorRefs>\n\t\t\t\t<descriptorRef>jar-with-dependencies</descriptorRef>\n\t\t\t</descriptorRefs>\n\t\t\t<appendAssemblyId>false</appendAssemblyId>\n\t\t</configuration>\n\t\t<executions>\n\t\t\t<execution>\n\t\t\t\t<phase>package</phase>\n\t\t\t\t<goals>\n\t\t\t\t\t<goal>single</goal>\n\t\t\t\t</goals>\n\t\t\t</execution>\n\t\t</executions>\n\t</plugin>\n\t<plugin>\n\t\t<groupId>org.apache.maven.plugins</groupId>\n\t\t<artifactId>maven-war-plugin</artifactId>\n\t\t<configuration>\n\t\t\t<webXml>web\WEB-INF\web.xml</webXml>\n\t\t</configuration>\n\t</plugin>\n</plugins>")),
			rule().add((condition("value", "false")), (condition("trigger", "m1")))
		);
		return this;
	}
}