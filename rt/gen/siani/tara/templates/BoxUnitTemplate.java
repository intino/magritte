package siani.tara.templates;

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
			rule().add((condition("type", "model"))).add(literal("package magritte.store;\n\n")).add(mark("importFacet").multiple("\n")).add(literal("\nimport static magritte.Tag.*;\nimport static magritte.editors.NodeEditor.Cardinality.*;\n")).add(mark("importMetric").multiple("\n")).add(literal("\n\npublic class ")).add(mark("name")).add(literal(" extends magritte.editors.Box.")).add(mark("terminal")).add(literal(" {\n    public static final magritte.editors.Box box = new ")).add(mark("name")).add(literal("();\n\n    @Override\n    public magritte.editors.Box[] dependencies() {\n        return new magritte.editors.Box[]{")).add(expression().add(literal("magritte.store.")).add(mark("language", "firstUpperCase")).add(literal(".box"))).add(literal("};\n    }\n\n    @Override\n    public void doWrite() {\n        ")).add(mark("node").multiple("\n")).add(literal("\n    }\n}")),
			rule().add((condition("trigger", "terminal")), (condition("value", "true"))).add(literal("Terminal")),
			rule().add((condition("trigger", "terminal")), (condition("value", "false"))).add(literal("Unit")),
			rule().add((condition("type", "Node")), (condition("trigger", "node"))).add(literal("edit(")).add(mark("key")).add(literal(")")).add(expression().add(literal(".name(\"")).add(mark("name")).add(mark("address")).add(literal("\")"))).add(expression().add(mark("nodeType").multiple(""))).add(expression().add(mark("facet").multiple(""))).add(expression().add(mark("annotation").multiple(""))).add(expression().add(literal(".")).add(mark("parent"))).add(expression().add(mark("variable").multiple(""))).add(expression().add(mark("include").multiple(""))).add(literal(";")),
			rule().add((condition("trigger", "nodeType"))).add(literal(".type(\"")).add(mark("value")).add(literal("\")")),
			rule().add((condition("type", "facetapply")), (condition("trigger", "facet"))).add(literal(".type(\"")).add(mark("name")).add(literal("\"")).add(expression().add(literal(", ")).add(mark("apply"))).add(literal(")")),
			rule().add((condition("trigger", "parent"))).add(literal("parent(\"")).add(mark("value")).add(literal("\")")),
			rule().add((condition("type", "composed")), (condition("trigger", "include"))).add(literal(".has(")).add(mark("inbox")).add(literal("(")).add(mark("value")).add(literal(")")).add(expression().add(literal(", ")).add(mark("tag", "firstUpperCase").multiple(", "))).add(literal(")")),
			rule().add((condition("type", "feature")), (condition("trigger", "include"))).add(literal(".features(")).add(mark("inbox")).add(literal("(")).add(mark("value")).add(literal("))")),
			rule().add((condition("trigger", "inbox")), (condition("value", "true"))).add(literal("$")),
			rule().add((condition("trigger", "inbox")), (condition("value", "false"))).add(literal("node")),
			rule().add((condition("type", "variable")), (condition("type", "target")), (condition("type", "measure")), (condition("slot", "variableValue")), (condition("trigger", "variable"))).add(literal(".set(\"!")).add(mark("node")).add(literal(":")).add(mark("name")).add(literal("<")).add(mark("target")).add(literal("\", ")).add(mark("measureValue")).add(literal(".value(")).add(mark("variableValue")).add(literal("))")),
			rule().add((condition("type", "variable")), (condition("type", "target")), (condition("type", "string")), (condition("slot", "variableValue")), (condition("trigger", "variable"))).add(literal(".set(\"!")).add(mark("node")).add(literal(":")).add(mark("name")).add(literal("<")).add(mark("target")).add(literal("\", ")).add(mark("multiple")).add(literal("(")).add(mark("variableValue", "quoted").multiple(", ")).add(literal("))")),
			rule().add((condition("type", "variable")), (condition("type", "target")), (condition("type", "word")), (condition("slot", "variableValue")), (condition("trigger", "variable"))).add(literal(".set(\"!")).add(mark("node")).add(literal(":")).add(mark("name")).add(literal("<")).add(mark("target")).add(literal("\", ")).add(mark("multiple")).add(literal("(")).add(mark("variableValue", "quoted").multiple(", ")).add(literal("))")),
			rule().add((condition("type", "variable")), (condition("type", "target")), (condition("type", "reference")), (condition("slot", "variableValue")), (condition("trigger", "variable"))).add(literal(".set(\"!")).add(mark("node")).add(literal(":")).add(mark("name")).add(literal("<")).add(mark("target")).add(literal("\", ")).add(mark("multiple")).add(literal("(node(")).add(mark("variableValue").multiple(", ")).add(literal(")))")),
			rule().add((condition("type", "variable")), (condition("type", "target")), (condition("slot", "variableValue")), (condition("trigger", "variable"))).add(literal(".set(\"!")).add(mark("node")).add(literal(":")).add(mark("name")).add(literal("<")).add(mark("target")).add(literal("\", ")).add(mark("multiple")).add(literal("(")).add(mark("variableValue").multiple(", ")).add(literal("))")),
			rule().add((condition("type", "variable")), (condition("type", "reference")), (condition("slot", "variableValue")), (condition("trigger", "variable"))).add(literal(".set(\"")).add(mark("terminal")).add(mark("name")).add(literal("\", ")).add(mark("multiple")).add(literal("(node(")).add(mark("variableValue").multiple(", ")).add(literal(")))")),
			rule().add((condition("type", "variable")), (condition("type", "native")), (condition("slot", "variableValue")), (condition("trigger", "variable"))).add(literal(".set(\"")).add(mark("terminal")).add(mark("name")).add(literal("\", ")).add(mark("multiple")).add(literal("((Class<magritte.NativeContainer>) ")).add(mark("variableValue")).add(literal("))")),
			rule().add((condition("type", "Variable")), (condition("type", "file")), (condition("slot", "variableValue")), (condition("trigger", "variable"))).add(literal(".set(\"")).add(mark("terminal")).add(mark("name")).add(literal("\", ")).add(mark("multiple")).add(literal("(resource(")).add(mark("variableValue", "quoted").multiple(", ")).add(literal(")))")),
			rule().add((condition("type", "variable")), (condition("type", "word")), (condition("slot", "variableValue")), (condition("trigger", "variable"))).add(literal(".set(\"")).add(mark("terminal")).add(mark("name")).add(literal("\", ")).add(mark("multiple")).add(literal("(")).add(mark("variableValue", "quoted").multiple(", ")).add(literal("))")),
			rule().add((condition("type", "Variable")), (condition("type", "string")), (condition("slot", "variableValue")), (condition("trigger", "variable"))).add(literal(".set(\"")).add(mark("terminal")).add(mark("name")).add(literal("\", ")).add(mark("multiple")).add(literal("(")).add(mark("variableValue", "quoted").multiple(", ")).add(literal("))")),
			rule().add((condition("type", "Variable")), (condition("slot", "variableValue")), (condition("trigger", "variable"))).add(literal(".set(\"")).add(mark("terminal")).add(mark("name")).add(literal("\", ")).add(mark("multiple")).add(literal("(")).add(mark("variableValue", "quoted").multiple(", ")).add(literal("))")),
			rule().add((condition("type", "Variable")), (condition("trigger", "variable"))),
			rule().add((condition("type", "Annotation")), (condition("trigger", "annotation"))).add(literal(".set(")).add(mark("value", "CamelCase").multiple(", ")).add(literal(")")),
			rule().add((condition("trigger", "variableValue")), (condition("slot", "date"))).add(literal("magritte.types.TimeInstant.value(")).add(mark("value", "date")).add(literal(")")),
			rule().add((condition("trigger", "variableValue")), (condition("slot", "coordinate"))).add(literal("magritte.types.Coordinate.of(")).add(mark("value")).add(literal(")")),
			rule().add((condition("trigger", "quoted"))).add(literal("\"")).add(mark("value")).add(literal("\"")),
			rule().add((condition("trigger", "multiple")), (condition("value", "true"))).add(literal("multiple")),
			rule().add((condition("trigger", "multiple")), (condition("value", "false")))
		);
		return this;
	}
}