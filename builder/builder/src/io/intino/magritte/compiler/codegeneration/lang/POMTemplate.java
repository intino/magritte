package io.intino.magritte.compiler.codegeneration.lang;

import io.intino.itrules.RuleSet;
import io.intino.itrules.Template;

public class POMTemplate extends Template {

	public RuleSet ruleSet() {
		return new RuleSet().add(
				rule().condition((type("POM"))).output(literal("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<project xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\" xmlns=\"http://maven.apache.org/POM/4.0.0\"\n    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n  <modelVersion>4.0.0</modelVersion>\n  <groupId>tara.language</groupId>\n  <artifactId>")).output(mark("name")).output(literal("</artifactId>\n  <version>")).output(mark("version")).output(literal("</version>\n</project>\n\n"))
		);
	}
}