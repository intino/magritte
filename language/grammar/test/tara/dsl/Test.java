package tara.dsl;

import tara.lang.model.Tag;

import java.util.Locale;

import static tara.lang.semantics.constraints.RuleFactory.*;

public class Test extends Tara {
	public Test() {
		in("").def(context("").allow(multiple("Test", Tag.MAIN), multiple("Record", Tag.MAIN), name()).doc("Test.test", 0, ""));
		in("Test").def(context("Concept").allow(parameter("parent", false, null, 1, null)
			, name()).require(_parameter("recordType", false, null, 0, null, "DEFINITION")
			, _parameter("label", tara.lang.model.Primitive.STRING, false, null, 2, null, "FINAL")
			, _parameter("e1", tara.lang.model.Primitive.WORD, false, null, 3, new tara.lang.model.rules.WordRule(java.util.Arrays.asList("A", "B", "C", "D"), false))
			, _parameter("e2", tara.lang.model.Primitive.WORD, false, null, 4, new tara.lang.model.rules.WordRule(java.util.Arrays.asList("A", "B", "C", "D"), true))
			, _parameter("e3", tara.lang.model.Primitive.FILE, false, null, 5, new tara.lang.model.rules.FileRule("json"))
			, _parameter("e4", tara.lang.model.Primitive.FILE, false, null, 6, new test.rules.CustomFile())
			, _parameter("e5", tara.lang.model.Primitive.STRING, false, null, 7, new tara.lang.model.rules.StringRule("regex"))
			, _parameter("e6", tara.lang.model.Primitive.STRING, false, null, 8, new test.rules.CustomString())
			, _parameter("e7", tara.lang.model.Primitive.DOUBLE, false, null, 9, new tara.lang.model.rules.DoubleRule(1.0, 100.0, "€"))
			, _parameter("e8", tara.lang.model.Primitive.DOUBLE, false, null, 10, new test.rules.CustomDouble())
			, _parameter("e9", tara.lang.model.Primitive.INTEGER, false, null, 11, new test.rules.CustomInteger())
			, _parameter("e10", tara.lang.model.Primitive.DOUBLE, false, null, 12, new test.rules.Energy())
			, _parameter("e11", tara.lang.model.Primitive.NATIVE, false, null, 13, new tara.lang.model.rules.NativeRule("CustomNative", "public void check()", "test"))
			, _plate()).assume(isMain()).doc("/Users/oroncal/workspace/sandbox/Test/Test2/model/Main.tara", 4, ""));
		in("Record").def(context("Concept").allow(name()).require(_plate()).assume(isMain()).doc("/Users/oroncal/workspace/sandbox/Test/Test2/model/Main.tara", 20, ""));
	}

	@Override
	public String languageName() {
		return "Test";
	}

	@Override
	public Locale locale() {
		return Locale.ENGLISH;
	}

	@Override
	public boolean isTerminalLanguage() {
		return false;
	}
}
