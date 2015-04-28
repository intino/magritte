package siani.tara.templates;

import org.siani.itrules.*;
import java.util.Locale;

import static org.siani.itrules.Encoding.LineSeparator.*;

public class BoxUnitTemplate extends Template {

public BoxUnitTemplate(Locale locale, Encoding encoding) {
		super(locale, encoding);
	}

	public static String format(Object object) {
		return template().render(object);
	}

	public static Template template() {
		return new BoxUnitTemplate(Locale.ENGLISH, Encoding.with("UTF-8", LF));
	}

    @Override
    public void definition() {
        add(
        rule().add(condition("type", "model")).add(literal("package magritte.store;\n\nimport magritte.Model;\nimport magritte.Node;\nimport magritte.editors.Box;\nimport magritte.store.*;\nimport magritte.editors.ModelEditor;\n")).add(mark("importFacet").multiple("\n")).add(literal("\n\nimport static magritte.Tag.*;\n")).add(mark("importMetric").multiple("\n")).add(literal("\n\npublic class ")).add(mark("name")).add(literal(" extends Box.Unit {\npublic static final Box box = new ")).add(mark("name")).add(literal("();\n\n@Override\npublic Box[] dependencies() {\n    return new Box[]{")).add(expression().add(literal("magritte.store.metamodel.")).add(mark("language", "firstUpperCase")).add(literal(".box"))).add(literal("};\n}\n\n@Override\nprotected ModelEditor modelEditor(Model model) {\n    return new ModelEditor(model) {\n        @Override\n        public void write() {\n            ")).add(mark("node").multiple("\n")).add(literal("\n        }\n    };\n}\n}")),
        rule().add(condition("type", "Node"), condition("trigger", "node")).add(literal("def(")).add(mark("key")).add(literal(")")).add(expression().add(literal(".name(\"")).add(mark("name")).add(literal("\")"))).add(expression().add(mark("nodeType").multiple(""))).add(expression().add(mark("facet").multiple(""))).add(expression().add(mark("address"))).add(expression().add(mark("annotation").multiple(""))).add(expression().add(literal(".")).add(mark("parent"))).add(expression().add(mark("variable", "set").multiple("")).add(literal("\""))).add(expression().add(literal(".componentOf(")).add(mark("componentOf")).add(literal(")"))).add(expression().add(mark("include").multiple(""))).add(literal(";\"")),
        rule().add(condition("trigger", "nodeType")).add(literal(".type(\"")).add(mark("value")).add(literal("\")")),
        rule().add(condition("type", "facetapply"), condition("trigger", "facet")).add(literal(".type(\"")).add(mark("name")).add(literal("\"")).add(expression().add(literal(", ")).add(mark("apply"))).add(literal(")")),
        rule().add(condition("trigger", "address")).add(literal(".address(")).add(mark("value")).add(literal(")")),
        rule().add(condition("trigger", "parent")).add(literal("childOf(\"")).add(mark("value")).add(literal("\")")),
        rule().add(condition("type", "aggregated"), condition("trigger", "include")).add(literal(".add(")).add(mark("value")).add(literal(")")),
        rule().add(condition("type", "composed"), condition("trigger", "include")).add(literal(".has(")).add(mark("value")).add(literal(")")),
        rule().add(condition("type", "variable"), condition("type", "target"), condition("type", "measure"), condition("type", "List"), condition("slot", "variableValue"), condition("trigger", "set")).add(literal(".set(\"!")).add(mark("node")).add(literal(":")).add(mark("name")).add(literal("<")).add(mark("target")).add(literal("\", ")).add(mark("measureValue")).add(literal(".value(")).add(mark("variableValue")).add(literal("))")),
        rule().add(condition("type", "variable"), condition("type", "target"), condition("type", "string"), condition("slot", "variableValue"), condition("trigger", "set")).add(literal(".set(\"!")).add(mark("node")).add(literal(":")).add(mark("name")).add(literal("<")).add(mark("target")).add(literal("\", ")).add(mark("variableValue", "quoted").multiple(", ")).add(literal(")")),
        rule().add(condition("type", "variable"), condition("type", "target"), condition("type", "word"), condition("slot", "variableValue"), condition("trigger", "set")).add(literal(".set(\"!")).add(mark("node")).add(literal(":")).add(mark("name")).add(literal("<")).add(mark("target")).add(literal("\", ")).add(mark("variableValue", "quoted").multiple(", ")).add(literal(")")),
        rule().add(condition("type", "variable"), condition("type", "target"), condition("type", "reference"), condition("slot", "variableValue"), condition("trigger", "set")).add(literal(".set(\"!")).add(mark("node")).add(literal(":")).add(mark("name")).add(literal("<")).add(mark("target")).add(literal("\", node(")).add(mark("variableValue").multiple(", ")).add(literal("))")),
        rule().add(condition("type", "variable"), condition("type", "target"), condition("slot", "variableValue"), condition("trigger", "set")).add(literal(".set(\"!")).add(mark("node")).add(literal(":")).add(mark("name")).add(literal("<")).add(mark("target")).add(literal("\", ")).add(mark("variableValue").multiple(", ")).add(literal(")")),
        rule().add(condition("type", "variable"), condition("type", "reference"), condition("slot", "variableValue"), condition("trigger", "set")).add(literal(".set(\"")).add(mark("terminal")).add(mark("name")).add(literal("\", node(")).add(mark("variableValue", "quoted").multiple(", ")).add(literal("))")),
        rule().add(condition("type", "Variable"), condition("type", "resource"), condition("slot", "variableValue"), condition("trigger", "set")).add(literal(".set(\"")).add(mark("terminal")).add(mark("name")).add(literal("\", resource(")).add(mark("variableValue", "quoted").multiple(", ")).add(literal("))")),
        rule().add(condition("type", "variable"), condition("type", "word"), condition("slot", "variableValue"), condition("trigger", "set")).add(literal(".set(\"")).add(mark("terminal")).add(mark("name")).add(literal("\", ")).add(mark("variableValue", "quoted").multiple(", ")).add(literal(")")),
        rule().add(condition("type", "Variable"), condition("type", "string"), condition("slot", "variableValue"), condition("trigger", "set")).add(literal(".set(\"")).add(mark("terminal")).add(mark("name")).add(literal("\", ")).add(mark("variableValue", "quoted").multiple(", ")).add(literal(")")),
        rule().add(condition("type", "Variable"), condition("slot", "variableValue"), condition("trigger", "set")).add(literal(".set(\"")).add(mark("terminal")).add(mark("name")).add(literal("\", ")).add(mark("variableValue").multiple(", ")).add(literal(")")),
        rule().add(condition("type", "Annotation"), condition("trigger", "annotation")).add(literal(".set(")).add(mark("value", "CamelCase").multiple(", ")).add(literal(")")),
        rule().add(condition("trigger", "variableValue"), condition("slot", "date")).add(literal("magritte.types.TimeInstant.value(")).add(mark("value", "date")).add(literal(")")),
        rule().add(condition("trigger", "variableValue"), condition("slot", "coordinate")).add(literal("magritte.types.Coordinate.of(")).add(mark("value")).add(literal(")")),
        rule().add(condition("trigger", "quoted")).add(literal("\"")).add(mark("value")).add(literal("\""))
        );
    }
}