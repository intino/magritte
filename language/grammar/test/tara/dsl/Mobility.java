package tara.dsl;

import tara.lang.model.Tag;

import java.util.Locale;

import static tara.lang.semantics.constraints.RuleFactory.*;

public class Mobility extends Tara {
	public Mobility() {
		in("").def(context("").allow(multiple("House", Tag.MAIN, Tag.TERMINAL_INSTANCE), multiple("Work", Tag.MAIN, Tag.TERMINAL_INSTANCE), multiple("Service", Tag.MAIN, Tag.TERMINAL_INSTANCE), multiple("Town", Tag.MAIN, Tag.TERMINAL_INSTANCE), multiple("House", Tag.MAIN, Tag.TERMINAL_INSTANCE), multiple("Work", Tag.MAIN, Tag.TERMINAL_INSTANCE), multiple("Service", Tag.MAIN, Tag.TERMINAL_INSTANCE), multiple("Town", Tag.MAIN, Tag.TERMINAL_INSTANCE), multiple("Person", Tag.MAIN, Tag.TERMINAL_INSTANCE), multiple("Car", Tag.MAIN, Tag.TERMINAL_INSTANCE), multiple("Bus", Tag.MAIN, Tag.TERMINAL_INSTANCE), multiple("Car", Tag.MAIN, Tag.TERMINAL_INSTANCE), multiple("Bus", Tag.MAIN, Tag.TERMINAL_INSTANCE), multiple("VehicleModel", Tag.MAIN, Tag.TERMINAL_INSTANCE), multiple("Activity", Tag.MAIN, Tag.TERMINAL_INSTANCE), multiple("HeatMap"), name()).require(_multiple("Simulation")).doc("tafat.Mobility", 0, ""));
		in("House").def(context("Entity", "Concept").allow(name()).require(_parameter("coordinates", tara.lang.model.Primitive.DOUBLE, true, null, 0, null, "TERMINAL")
		).assume(isMain(), isTerminalInstance()).doc("/Users/octavio/workspace/sandbox/tafat/Mobility/model/Main.tara", 5, ""));
		in("Work").def(context("Entity", "Concept").allow(name()).require(_parameter("coordinates", tara.lang.model.Primitive.DOUBLE, true, null, 0, null, "TERMINAL")
		).assume(isMain(), isTerminalInstance()).doc("/Users/octavio/workspace/sandbox/tafat/Mobility/model/Main.tara", 6, ""));
		in("Service").def(context("Entity", "Concept").allow(name()).require(_parameter("coordinates", tara.lang.model.Primitive.DOUBLE, true, null, 0, null, "TERMINAL")
		).assume(isMain(), isTerminalInstance()).doc("/Users/octavio/workspace/sandbox/tafat/Mobility/model/Main.tara", 7, ""));
		in("Town").def(context("Entity", "Concept").allow(name()).require(_parameter("coordinates", tara.lang.model.Primitive.DOUBLE, true, null, 0, null, "TERMINAL")
		).assume(isMain(), isTerminalInstance()).doc("/Users/octavio/workspace/sandbox/tafat/Mobility/model/Main.tara", 8, ""));
		in("Person").def(context("Agent", "Concept").allow(parameter("goTo", true, null, 2, new tara.lang.model.rules.ReferenceRule(java.util.Arrays.asList("Town", "Work", "Service", "House")), "TERMINAL")
			, name(), facet("Activities", true, "Driver").require(_parameter("activities", true, null, 3, new tara.lang.model.rules.ReferenceRule(java.util.Arrays.asList("Activity")), "TERMINAL")
			, _parameter("startsAt", tara.lang.model.Primitive.TIME, false, null, 4, null, "TERMINAL")
		).allow(parameter("calculateDuration", tara.lang.model.Primitive.NATIVE, false, null, 0, new tara.lang.model.rules.NativeRule("UniformDuration", "public double calculate(double duration, double deviation)", "Mobility"), "TERMINAL")
			, parameter("doActivity", tara.lang.model.Primitive.NATIVE, false, null, 1, new tara.lang.model.rules.NativeRule("SetTimeout", "public void set(Activity activity)", "Mobility"), "TERMINAL")
			, parameter("currentIndex", tara.lang.model.Primitive.INTEGER, false, null, 2, null, "TERMINAL")
			, multiple("EquationSystem")), facet("MobilityPatternB", true, "Driver").allow(multiple("EquationSystem")), facet("Driver", true).require(_parameter("vehicle", false, null, 0, new tara.lang.model.rules.ReferenceRule(java.util.Arrays.asList("Bus", "Car")))
		).allow(multiple("EquationSystem")), facet("MobilityPatternA", true, "Driver").allow(multiple("EquationSystem"))).require(_parameter("liveIn", false, null, 0, new tara.lang.model.rules.ReferenceRule(java.util.Arrays.asList("Town", "Work", "Service", "House")), "TERMINAL")
			, _parameter("workIn", false, null, 1, new tara.lang.model.rules.ReferenceRule(java.util.Arrays.asList("Town", "Work", "Service", "House")), "TERMINAL")
		).assume(isMain(), isTerminalInstance()).doc("/Users/octavio/workspace/sandbox/tafat/Mobility/model/Main.tara", 10, ""));
		in("Car").def(context("Entity", "Concept").allow(parameter("place", false, null, 1, new tara.lang.model.rules.ReferenceRule(java.util.Arrays.asList("Town", "Work", "Service", "House")), "TERMINAL")
			, parameter("speed", tara.lang.model.Primitive.DOUBLE, false, null, 2, null, "TERMINAL")
			, parameter("moveTo", tara.lang.model.Primitive.NATIVE, false, null, 3, new tara.lang.model.rules.NativeRule("MoveTo", "public void moveTo(List<? extends Place> places)", "Mobility"), "TERMINAL")
			, name(), facet("Electrical", true).allow(parameter("power", tara.lang.model.Primitive.DOUBLE, false, null, 0, null, "TERMINAL")
			, parameter("stateOfCharge", tara.lang.model.Primitive.DOUBLE, false, null, 1, new tara.lang.model.rules.DoubleRule(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, "%"), "TERMINAL")
			, parameter("distance", tara.lang.model.Primitive.NATIVE, false, null, 2, new tara.lang.model.rules.NativeRule("Function", "public double distance(double speed)", "Mobility"), "TERMINAL")
			, multiple("EquationSystem"))).require(_parameter("model", false, null, 0, new tara.lang.model.rules.ReferenceRule(java.util.Arrays.asList("VehicleModel")), "FINAL", "TERMINAL")
		).assume(isMain(), isTerminalInstance()).doc("/Users/octavio/workspace/sandbox/tafat/Mobility/model/Main.tara", 26, ""));
		in("Bus").def(context("Entity", "Concept").allow(parameter("place", false, null, 1, new tara.lang.model.rules.ReferenceRule(java.util.Arrays.asList("Town", "Work", "Service", "House")), "TERMINAL")
			, parameter("speed", tara.lang.model.Primitive.DOUBLE, false, null, 2, null, "TERMINAL")
			, parameter("moveTo", tara.lang.model.Primitive.NATIVE, false, null, 3, new tara.lang.model.rules.NativeRule("MoveTo", "public void moveTo(List<? extends Place> places)", "Mobility"), "TERMINAL")
			, name(), facet("Electrical", true).allow(parameter("power", tara.lang.model.Primitive.DOUBLE, false, null, 0, null, "TERMINAL")
			, parameter("stateOfCharge", tara.lang.model.Primitive.DOUBLE, false, null, 1, new tara.lang.model.rules.DoubleRule(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, "%"), "TERMINAL")
			, parameter("distance", tara.lang.model.Primitive.NATIVE, false, null, 2, new tara.lang.model.rules.NativeRule("Function", "public double distance(double speed)", "Mobility"), "TERMINAL")
			, multiple("EquationSystem"))).require(_parameter("model", false, null, 0, new tara.lang.model.rules.ReferenceRule(java.util.Arrays.asList("VehicleModel")), "FINAL", "TERMINAL")
		).assume(isMain(), isTerminalInstance()).doc("/Users/octavio/workspace/sandbox/tafat/Mobility/model/Main.tara", 27, ""));
		in("VehicleModel").def(context("Entity", "Concept").allow(name(), facet("Electric", true).require(_parameter("capacity", tara.lang.model.Primitive.DOUBLE, false, null, 0, null)
			, _parameter("autonomy", tara.lang.model.Primitive.DOUBLE, false, null, 1, new tara.lang.model.rules.DoubleRule(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, ""))
		)).assume(isMain(), isTerminalInstance()).doc("/Users/octavio/workspace/sandbox/tafat/Mobility/model/Main.tara", 29, ""));
		in("Electric").def(context("Aspect", "Concept").require(_name()).assume(isTerminalInstance(), isFacetInstance()).doc("/Users/octavio/workspace/sandbox/tafat/Mobility/model/Main.tara", 31, ""));
		in("Driver").def(context("Behavior", "Concept").allow(multiple("EquationSystem")).require(_parameter("step", tara.lang.model.Primitive.INTEGER, false, null, 0, null, "TERMINAL_INSTANCE")
			, _parameter("vehicle", false, null, 1, new tara.lang.model.rules.ReferenceRule(java.util.Arrays.asList("Bus", "Car")), "TERMINAL")
			, _name()).assume(isTerminalInstance(), isFacetInstance()).doc("/Users/octavio/workspace/sandbox/tafat/Mobility/model/Main.tara", 36, ""));
		in("Activities").def(context("Behavior", "Concept").allow(multiple("EquationSystem")).require(_parameter("step", tara.lang.model.Primitive.INTEGER, false, null, 0, null, "TERMINAL_INSTANCE")
			, _name()).assume(isTerminalInstance(), isFacetInstance()).doc("/Users/octavio/workspace/sandbox/tafat/Mobility/model/Main.tara", 41, ""));
		in("MobilityPatternA").def(context("Behavior", "Concept").allow(multiple("EquationSystem")).require(_parameter("step", tara.lang.model.Primitive.INTEGER, false, null, 0, null, "TERMINAL_INSTANCE")
			, _name()).assume(isTerminalInstance(), isFacetInstance()).doc("/Users/octavio/workspace/sandbox/tafat/Mobility/model/Main.tara", 60, ""));
		in("MobilityPatternB").def(context("Behavior", "Concept").allow(multiple("EquationSystem")).require(_parameter("step", tara.lang.model.Primitive.INTEGER, false, null, 0, null, "TERMINAL_INSTANCE")
			, _name()).assume(isTerminalInstance(), isFacetInstance()).doc("/Users/octavio/workspace/sandbox/tafat/Mobility/model/Main.tara", 66, ""));
		in("Electrical").def(context("Behavior", "Concept").allow(multiple("EquationSystem")).require(_parameter("step", tara.lang.model.Primitive.INTEGER, false, null, 0, null, "TERMINAL_INSTANCE")
			, _name()).assume(isTerminalInstance(), isFacetInstance()).doc("/Users/octavio/workspace/sandbox/tafat/Mobility/model/Main.tara", 73, ""));
		in("Activity").def(context("Knol", "Concept").allow(name()).require(_parameter("duration", tara.lang.model.Primitive.INTEGER, false, null, 0, new tara.lang.model.rules.IntegerRule(-2147483648, 2147483647, ""), "TERMINAL")
			, _parameter("deviation", tara.lang.model.Primitive.INTEGER, false, null, 1, new tara.lang.model.rules.IntegerRule(-2147483648, 2147483647, ""), "TERMINAL")
		).assume(isMain(), isTerminalInstance()).doc("/Users/octavio/workspace/sandbox/tafat/Mobility/model/Main.tara", 90, ""));
		in("HeatMap").def(context("Concept").allow(name(), parameter("path", tara.lang.model.Primitive.STRING, false, null, 0, null)
		).require(_parameter("path", tara.lang.model.Primitive.STRING, false, null, 0, null)
		).assume(isMain(), isTerminalInstance()));
		in("Output").def(context("Concept").allow(name(), parameter("language", tara.lang.model.Primitive.STRING, false, null, 0, null)
			, parameter("simulationId", tara.lang.model.Primitive.STRING, false, null, 1, null)
		).require(_parameter("language", tara.lang.model.Primitive.STRING, false, null, 0, null)
			, _parameter("simulationId", tara.lang.model.Primitive.STRING, false, null, 1, null)
		).assume(isTerminalInstance()));
		in("EquationSystem").def(context("Concept").allow(parameter("solver", tara.lang.model.Primitive.WORD, false, null, 0, new tara.lang.model.rules.WordRule(java.util.Arrays.asList("Euler")))
			, parameter("step", tara.lang.model.Primitive.DOUBLE, false, null, 1, null)
			, name()).assume(isFeatureInstance(), isTerminalInstance()));
		in("Simulation").def(context("Concept").allow(single("Output"), name(), parameter("from", tara.lang.model.Primitive.DATE, false, null, 0, null)
			, parameter("to", tara.lang.model.Primitive.DATE, false, null, 1, null)
		).require(_parameter("from", tara.lang.model.Primitive.DATE, false, null, 0, null)
			, _parameter("to", tara.lang.model.Primitive.DATE, false, null, 1, null)
		).assume(isTerminalInstance(), isMain()));
	}

	@Override
	public String languageName() {
		return "Mobility";
	}

	@Override
	public Locale locale() {
		return Locale.ENGLISH;
	}

	@Override
	public boolean isTerminalLanguage() {
		return true;
	}
}
