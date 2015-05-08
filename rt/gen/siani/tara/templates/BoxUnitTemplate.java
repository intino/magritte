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
			rule().add((condition("type", "model"))).add(literal("package magritte.store;\n\n")).add(mark("importFacet").multiple("\n")).add(literal("\nimport static magritte.Tag.*;\n")).add(mark("importMetric").multiple("\n")).add(literal("\n\npublic class ")).add(mark("name")).add(literal(" extends magritte.handlers.Box.")).add(mark("terminal")).add(literal(" {\n    public static final magritte.handlers.Box box = new ")).add(mark("name")).add(literal("();\n\n    @Override\n    public magritte.handlers.Box[] dependencies() {\n        return new magritte.handlers.Box[]{")).add(expression().add(literal("magritte.store.")).add(mark("language", "firstUpperCase")).add(literal(".box"))).add(literal("};\n    }\n\n    @Override\n    public void doWrite() {\n        ")).add(mark("node").multiple("\n")).add(literal("\n    }\n}")),
			rule().add((condition("trigger", "terminal")), (condition("value", "true"))).add(literal("Terminal")),
			rule().add((condition("trigger", "terminal")), (condition("value", "false"))).add(literal("Unit")),
			rule().add((condition("type", "Node")), (condition("trigger", "node"))).add(literal("edit(")).add(mark("key")).add(literal(")")).add(expression().add(literal(".name(\"")).add(mark("name")).add(literal("\")"))).add(expression().add(mark("nodeType").multiple(""))).add(expression().add(mark("facet").multiple(""))).add(expression().add(mark("address"))).add(expression().add(mark("annotation").multiple(""))).add(expression().add(literal(".")).add(mark("parent"))).add(expression().add(mark("variable").multiple(""))).add(expression().add(literal(".owner(")).add(mark("componentOf")).add(literal(")"))).add(expression().add(mark("include").multiple(""))).add(literal(";")),
			rule().add((condition("trigger", "nodeType"))).add(literal(".type(\"")).add(mark("value")).add(literal("\")")),
			rule().add((condition("type", "facetapply")), (condition("trigger", "facet"))).add(literal(".type(\"")).add(mark("name")).add(literal("\"")).add(expression().add(literal(", ")).add(mark("apply"))).add(literal(")")),
			rule().add((condition("trigger", "address"))).add(literal(".address(")).add(mark("value")).add(literal(")")),
			rule().add((condition("trigger", "parent"))).add(literal("childOf(\"")).add(mark("value")).add(literal("\")")),
			rule().add((condition("type", "aggregated")), (condition("trigger", "include"))).add(literal(".hasHoldings(")).add(mark("value")).add(literal(")")),
			rule().add((condition("type", "composed")), (condition("trigger", "include"))).add(literal(".hasComponents(")).add(mark("value")).add(literal(")")),
			rule().add((condition("type", "variable")), (condition("type", "target")), (condition("type", "measure")), (condition("slot", "variableValue")), (condition("trigger", "variable"))).add(literal(".set(\"!")).add(mark("node")).add(literal(":")).add(mark("name")).add(literal("<")).add(mark("target")).add(literal("\", ")).add(mark("measureValue")).add(literal(".value(")).add(mark("variableValue")).add(literal("))")),
			rule().add((condition("type", "variable")), (condition("type", "target")), (condition("type", "string")), (condition("slot", "variableValue")), (condition("trigger", "variable"))).add(literal(".set(\"!")).add(mark("node")).add(literal(":")).add(mark("name")).add(literal("<")).add(mark("target")).add(literal("\", ")).add(mark("multiple")).add(literal("(")).add(mark("variableValue", "quoted").multiple(", ")).add(literal("))")),
			rule().add((condition("type", "variable")), (condition("type", "target")), (condition("type", "word")), (condition("slot", "variableValue")), (condition("trigger", "variable"))).add(literal(".set(\"!")).add(mark("node")).add(literal(":")).add(mark("name")).add(literal("<")).add(mark("target")).add(literal("\", ")).add(mark("multiple")).add(literal("(")).add(mark("variableValue", "quoted").multiple(", ")).add(literal("))")),
			rule().add((condition("type", "variable")), (condition("type", "target")), (condition("type", "reference")), (condition("slot", "variableValue")), (condition("trigger", "variable"))).add(literal(".set(\"!")).add(mark("node")).add(literal(":")).add(mark("name")).add(literal("<")).add(mark("target")).add(literal("\", ")).add(mark("multiple")).add(literal("(node(")).add(mark("variableValue").multiple(", ")).add(literal(")))")),
			rule().add((condition("type", "variable")), (condition("type", "target")), (condition("slot", "variableValue")), (condition("trigger", "variable"))).add(literal(".set(\"!")).add(mark("node")).add(literal(":")).add(mark("name")).add(literal("<")).add(mark("target")).add(literal("\", ")).add(mark("multiple")).add(literal("(")).add(mark("variableValue").multiple(", ")).add(literal("))")),
			rule().add((condition("type", "variable")), (condition("type", "reference")), (condition("slot", "variableValue")), (condition("trigger", "variable"))).add(literal(".set(\"")).add(mark("terminal")).add(mark("name")).add(literal("\", ")).add(mark("multiple")).add(literal("(node(")).add(mark("variableValue").multiple(", ")).add(literal(")))")),
			rule().add((condition("type", "variable")), (condition("type", "native")), (condition("slot", "variableValue")), (condition("trigger", "variable"))).add(literal(".set(\"")).add(mark("terminal")).add(mark("name")).add(literal("\", ")).add(mark("multiple")).add(literal("((Class<magritte.handlers.NativeContainer>) ")).add(mark("variableValue")).add(literal("))")),
			rule().add((condition("type", "Variable")), (condition("type", "resource")), (condition("slot", "variableValue")), (condition("trigger", "variable"))).add(literal(".set(\"")).add(mark("terminal")).add(mark("name")).add(literal("\", ")).add(mark("multiple")).add(literal("(resource(")).add(mark("variableValue", "quoted").multiple(", ")).add(literal(")))")),
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