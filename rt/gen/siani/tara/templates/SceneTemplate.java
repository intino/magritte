package siani.tara.templates;

import org.siani.itrules.*;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.*;

public class SceneTemplate extends Template {

	protected SceneTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new SceneTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "scene"))).add(literal("package ")).add(mark("name", "LowerCase")).add(literal(";\n\npublic class ")).add(mark("name", "FirstCamelCase")).add(literal(" extends magritte.wraps.Scene {\n\n    public Monet(magritte.Model model) {\n        super(model);\n    }\n\n\t")).add(mark("root").multiple("\n\n")).add(literal("\n}")),
			rule().add((condition("type", "root")), (condition("trigger", "root"))).add(literal("public ")).add(mark("qn", "reference")).add(literal("[] ")).add(mark("name", "FirstLowerCase", "plural")).add(literal("() {\n    return roots(")).add(mark("qn", "reference")).add(literal(".class);\n}\n\npublic ")).add(mark("qn", "reference")).add(literal(" ")).add(mark("name", "FirstLowerCase")).add(literal("(int index) {\n    return ")).add(mark("name", "FirstLowerCase", "plural")).add(literal("()[index];\n}"))
		);
		return this;
	}
}