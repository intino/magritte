package tara.dsl;

import tara.lang.model.Primitive;
import tara.lang.model.Tag;
import tara.lang.model.rules.*;
import test.rules.*;

import java.util.Arrays;
import java.util.Locale;

import static tara.lang.semantics.constraints.RuleFactory.*;

public class Test extends Tara {
	public Test() {
		in("").def(context("").allow(multiple("Test", Tag.MAIN), multiple("Record", Tag.MAIN), name()).doc("Test.test", 0, ""));
		in("Test").def(context("Concept").allow(parameter("parent", false, null, 1, new ReferenceRule(Arrays.asList("Test")))
			, name()).require(_parameter("recordType", false, null, 0, new ReferenceRule(Arrays.asList("Record")), "DEFINITION")
			, _parameter("label", Primitive.STRING, false, null, 2, null, "FINAL")
			, _parameter("e1", Primitive.WORD, false, null, 3, new WordRule(Arrays.asList("A", "B", "C", "D"), false))
			, _parameter("e2", Primitive.WORD, false, null, 4, new WordRule(Arrays.asList("A", "B", "C", "D"), true))
			, _parameter("e3", Primitive.FILE, false, null, 5, new FileRule("json"))
			, _parameter("e4", Primitive.FILE, false, null, 6, new CustomFile())
			, _parameter("e5", Primitive.STRING, false, null, 7, new StringRule("regex"))
			, _parameter("e6", Primitive.STRING, false, null, 8, new CustomString())
			, _parameter("e7", Primitive.DOUBLE, false, null, 9, new DoubleRule(1.0, 100.0, "€"))
			, _parameter("e8", Primitive.DOUBLE, false, null, 10, new CustomDouble())
			, _parameter("e9", Primitive.INTEGER, false, null, 11, new CustomInteger())
			, _parameter("e10", Primitive.DOUBLE, false, null, 12, Energy.Wh)
			, _parameter("e11", Primitive.NATIVE, false, null, 13, new NativeRule("CustomNative", "public void check()", "test"))
			, _plate()).assume(isMain()).doc("/Users/octavio/workspace/sandbox/Test/Test2/model/Main.tara", 4, ""));
		in("Record").def(context("Concept").allow(name()).require(_plate()).assume(isMain()).doc("/Users/octavio/workspace/sandbox/Test/Test2/model/Main.tara", 20, ""));
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
