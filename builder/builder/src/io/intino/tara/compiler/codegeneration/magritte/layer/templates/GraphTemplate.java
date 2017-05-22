package io.intino.tara.compiler.codegeneration.magritte.layer.templates;

import org.siani.itrules.*;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.*;

public class GraphTemplate extends Template {

	protected GraphTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new GraphTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "wrapper"))).add(literal("package ")).add(mark("workingPackage", "lowercase")).add(literal(";\n\nimport io.intino.tara.magritte.Graph;\n\npublic class ")).add(mark("generatedLanguage", "javaValidName", "FirstUpperCase")).add(literal(" extends ")).add(mark("workingPackage", "lowercase")).add(literal(".AbstractGraph {\n\n\tpublic ")).add(mark("generatedLanguage", "javaValidName", "FirstUpperCase")).add(literal("(Graph graph) {\n\t\tsuper(graph);\n\t}\n}"))
		);
		return this;
	}
}