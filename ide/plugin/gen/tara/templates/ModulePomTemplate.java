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
			rule().add((condition("type", "pom"))).add(literal("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n\t\t xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n\t\t xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0\n\t\t\t\t\t\t\t http://maven.apache.org/maven-v4_0_0.xsd\">\n\t<modelVersion>4.0.0</modelVersion>\n\n\t<groupId>")).add(mark("project")).add(literal("</groupId>\n\t<artifactId>")).add(mark("name")).add(literal("</artifactId>\n\t<version>")).add(mark("version")).add(literal("</version>\n\n\t<properties>\n\t\t<maven.compiler.source>1.8</maven.compiler.source>\n\t\t<maven.compiler.target>1.8</maven.compiler.target>\n\t</properties>\n\n\t<build>\n\t\t<outputDirectory>../out/production</outputDirectory>\n\t\t<testOutputDirectory>../out/test</testOutputDirectory>\n\t\t<directory>../out/build</directory>\n\t\t<resources>\n\t\t\t<resource>\n\t\t\t\t<directory>${basedir}/../.tara/refactors</directory>\n\t\t\t</resource>\n\t\t</resources>\n\t</build>\n\n\t<repositories>\n        <repository>\n            <id>siani-maven</id>\n            <name>siani-maven-releases</name>\n            <url>https://artifactory.siani.es/artifactory/libs-release</url>\n        </repository>\n    </repositories>\n\n    <distributionManagement>\n        <repository>\n            <id>siani-maven</id>\n            <name>siani-maven-releases</name>\n            <url>https://artifactory.siani.es/artifactory/libs-release</url>\n        </repository>\n    </distributionManagement>\n\n\t<dependencies>\n\t\t")).add(mark("magritte").multiple("\n")).add(literal("\n\t\t")).add(mark("parentModule")).add(literal("\n\t\t<dependency>\n\t\t\t<groupId>com.esotericsoftware</groupId>\n\t\t\t<scope>runtime</scope>\n\t\t\t<artifactId>kryo</artifactId>\n\t\t\t<version>LATEST</version>\n\t\t</dependency>\n\t\t<dependency>\n\t\t\t<groupId>junit</groupId>\n\t\t\t<artifactId>junit</artifactId>\n\t\t\t<scope>test</scope>\n\t\t\t<version>LATEST</version>\n\t\t</dependency>\n\t</dependencies>\n</project>")),
			rule().add((condition("type", "parent")), (condition("trigger", "parentModule"))).add(literal("<dependency>\n    <groupId>")).add(mark("groupId")).add(literal("</groupId>\n    <artifactId>")).add(mark("artifactId")).add(literal("</artifactId>\n    <scope>compile</scope>\n    <version>")).add(mark("version")).add(literal("</version>\n</dependency>")),
			rule().add((condition("type", "magritte")), (condition("type", "local")), (condition("trigger", "magritte"))).add(literal("<dependency>\n    <groupId>org.siani.magritte</groupId>\n    <artifactId>magritte</artifactId>\n    <scope>system</scope>\n    <version>1.0</version>\n    <systemPath>${project.basedir}/")).add(mark("filePath")).add(literal("</systemPath>\n</dependency>")),
			rule().add((condition("type", "magritte")), (condition("trigger", "magritte"))).add(literal("<dependency>\n    <groupId>org.siani.magritte</groupId>\n    <artifactId>magritte</artifactId>\n    <version>LATEST</version>\n    <!--<systempath>${project.basedir}</systemPath> use it to include local dependency-->\n</dependency>"))
		);
		return this;
	}
}