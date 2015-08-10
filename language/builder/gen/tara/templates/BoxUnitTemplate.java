package tara.templates;

import org.siani.itrules.*;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.*;

public class BoxUnitTemplate extends Template {

	protected BoxUnitTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new BoxUnitTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "model"))).add(literal("package magritte.boxes;\n\nimport siani.tara.magritte.Box;\nimport siani.tara.magritte.Morph;\nimport siani.tara.magritte.NativeCode;\nimport java.time.LocalDateTime;\nimport java.time.format.DateTimeFormatter;\nimport java.util.*;\n")).add(expression().add(literal("import ")).add(mark("language", "lowercase")).add(literal(".*;"))).add(literal("\n")).add(expression().add(literal("import ")).add(mark("generatedLanguage", "lowercase")).add(literal(".*;"))).add(literal("\n\n")).add(mark("importMetric").multiple("\n")).add(literal("\n\npublic class ")).add(mark("name", "javaValidName")).add(literal(" extends Box {\n\tpublic static final Box box = new ")).add(mark("name")).add(literal("();\n\n\t@Override\n\tpublic Box[] dependencies() {\n\t\treturn new Box[]{")).add(expression().add(literal("magritte.dsl.")).add(mark("language", "javaValidName")).add(literal("Dsl.box"))).add(literal("};\n\t}\n\n\t@Override\n\tpublic void write() {\n\t\tregisterTypes();\n\t\t")).add(mark("node").multiple("\n")).add(literal("\n\t}\n\n\tprivate LocalDateTime asDate(String date) {\n\t\treturn LocalDateTime.from(DateTimeFormatter.ofPattern(\"dd/MM/yyyy hh:mm:ss\").parse(date));\n\t}\n\n\tprivate void registerTypes() {\n\t\t")).add(mark("register")).add(literal("\n\t}\n\n\t")).add(mark("native").multiple("\n\n")).add(literal("\n}")),
			rule().add((condition("type", "nodecontainer")), (condition("trigger", "register"))).add(literal("register")).add(mark("Abstract")).add(literal("(")).add(mark("type", "quoted")).add(literal(", ")).add(mark("class")).add(literal(".class);")),
			rule().add((condition("type", "nodecontainer")), (condition("type", "prototype")), (condition("trigger", "node"))).add(expression().add(literal("proto(\"")).add(mark("name")).add(mark("plate")).add(literal("\")"))).add(expression().add(literal(".")).add(mark("root")).add(literal("()"))).add(expression().add(mark("type").multiple(""))).add(expression().add(mark("include").multiple(""))).add(expression().add(mark("parameter").multiple(""))).add(literal(";")),
			rule().add((condition("type", "nodecontainer")), (condition("type", "terminal_instance")), (condition("trigger", "node"))).add(expression().add(literal("thing(\"")).add(mark("name")).add(mark("plate")).add(literal("\")"))).add(expression().add(literal(".")).add(mark("root")).add(literal("()"))).add(expression().add(mark("type").multiple(""))).add(expression().add(mark("include").multiple(""))).add(expression().add(mark("parameter").multiple(""))).add(literal(";")),
			rule().add((condition("type", "nodecontainer")), (condition("trigger", "node"))).add(expression().add(literal("def(\"")).add(mark("name")).add(mark("plate")).add(literal("\")"))).add(expression().add(literal(".")).add(mark("root")).add(literal("()"))).add(expression().add(mark("type").multiple(""))).add(expression().add(mark("include").multiple(""))).add(expression().add(mark("parameter").multiple(""))).add(literal(";")),
			rule().add((condition("type", "native")), (condition("trigger", "native"))).add(literal("public static class ")).add(mark("className", "javaValidName")).add(literal(" implements ")).add(expression().add(mark("parentIntention", "lowercase")).add(literal(".natives."))).add(mark("interface")).add(literal(", NativeCode {\n\t")).add(mark("container")).add(literal(" $;\n\n\t")).add(mark("signature")).add(literal(" {\n\t\t")).add(mark("body")).add(literal("\n\t}\n\n\tpublic void set(Morph context) {\n\t\t$ = (")).add(mark("container")).add(literal(") context;\n\t}\n\n\tpublic Class<? extends Morph> ")).add(mark("Class")).add(literal("() {\n\t\treturn ")).add(mark("container")).add(literal(".class;\n\t}\n}")),
			rule().add((condition("trigger", "quoted"))).add(literal("\"")).add(mark("value")).add(literal("\""))
		);
		return this;
	}
}