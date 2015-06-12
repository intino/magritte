package siani.tara.templates;

import org.siani.itrules.*;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.*;

public class ModelTemplate extends Template {

	protected ModelTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new ModelTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "scene"))).add(literal("package ")).add(mark("name", "LowerCase")).add(literal(";\n\npublic class ")).add(mark("name", "FirstCamelCase")).add(literal(" extends magritte.wraps.Model {\n\n    public ")).add(mark("name", "FirstCamelCase")).add(literal("(magritte.wraps.Morph morph) {\n        super(morph);\n    }\n\n\t")).add(mark("root").multiple("\n\n")).add(literal("\n}")),
			rule().add((condition("type", "root")), not(condition("type", "single")), (condition("trigger", "root"))).add(literal("public magritte.Set<")).add(mark("qn", "reference")).add(literal("> ")).add(mark("name", "FirstLowerCase", "plural")).add(literal("() {\n    return _get(")).add(mark("qn", "reference")).add(literal(".class);\n}\n\npublic ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "FirstLowerCase")).add(literal("(int index) {\n    return ")).add(mark("name", "FirstLowerCase", "plural")).add(literal("().get(index);\n}")),
			rule().add((condition("type", "root")), (condition("type", "single")), (condition("trigger", "root"))).add(literal("public ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "FirstLowerCase")).add(literal("() {\n    return _get(")).add(mark("qn", "reference")).add(literal(".class).get(0);\n}"))
		);
		return this;
	}
}