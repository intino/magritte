package tara.templates;

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
			rule().add((condition("type", "pom"))).add(literal("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0\n                             http://maven.apache.org/maven-v4_0_0.xsd\">\n  <modelVersion>4.0.0</modelVersion>\n\n  <groupId>")).add(mark("project")).add(literal("</groupId>\n  <artifactId>")).add(mark("name")).add(literal("</artifactId>\n  <version>")).add(mark("version")).add(literal("</version>\n\n  <properties>\n    <maven.compiler.source>1.8</maven.compiler.source>\n    <maven.compiler.target>1.8</maven.compiler.target>\n  </properties>\n\n  <build>\n    <outputDirectory>../out/production/")).add(mark("name")).add(literal("</outputDirectory>\n    <testOutputDirectory>../out/test/")).add(mark("name")).add(literal("</testOutputDirectory>\n\t<directory>../out/build/")).add(mark("name")).add(literal("</directory>\n\t<resources>\n\t  <resource>\n\t\t<directory>${basedir}/../.tara/refactors</directory>\n\t  </resource>\n\t</resources>\n  </build>\n\n  <dependencies>\n\t")).add(mark("magritte").multiple("\n")).add(literal("\n    ")).add(mark("parentModule")).add(literal("\n\t<dependency>\n\t\t<groupId>com.esotericsoftware</groupId>\n\t\t<scope>runtime</scope>\n\t\t<artifactId>kryo</artifactId>\n\t\t<version>3.0.0</version>\n\t</dependency>\n\t<dependency>\n\t  <groupId>junit</groupId>\n\t  <artifactId>junit</artifactId>\n\t  <scope>test</scope>\n\t  <version>LATEST</version>\n\t</dependency>\n  </dependencies>\n</project>")),
			rule().add((condition("type", "parent")), (condition("trigger", "parentModule"))).add(literal("<dependency>\n\t<groupId>")).add(mark("groupId")).add(literal("</groupId>\n\t<artifactId>")).add(mark("artifactId")).add(literal("</artifactId>\n\t<scope>compile</scope>\n\t<version>")).add(mark("version")).add(literal("</version>\n</dependency>")),
			rule().add((condition("type", "magritte")), (condition("type", "local")), (condition("trigger", "magritte"))).add(literal("<dependency>\n\t<groupId>org.siani.magritte</groupId>\n\t<artifactId>magritte</artifactId>\n\t<scope>system</scope>\n\t<version>1.0</version>\n\t<systemPath>${project.basedir}/")).add(mark("filePath")).add(literal("</systemPath>\n</dependency>")),
			rule().add((condition("type", "magritte")), (condition("trigger", "magritte"))).add(literal("<dependency>\n\t<groupId>org.siani.magritte</groupId>\n\t<artifactId>magritte</artifactId>\n\t<version>LATEST</version>\n\t<!--<systempath>${project.basedir}</systemPath> use it to include local dependency-->\n</dependency>"))
		);
		return this;
	}
}