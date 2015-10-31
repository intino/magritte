package tara.dsl;

import java.util.Locale;

import static tara.lang.semantics.constraints.RuleFactory.*;

public class Sumus extends Tara {
	public Sumus() {
		in("").def(context("").has(component("Political", new tara.lang.model.rules.Size(0, 2147483647), tara.lang.model.Tag.TERMINAL_INSTANCE, tara.lang.model.Tag.FACET_INSTANCE), component("Automatic", new tara.lang.model.rules.Size(0, 2147483647), tara.lang.model.Tag.TERMINAL_INSTANCE, tara.lang.model.Tag.FACET_INSTANCE), name()).doc("sumus.Sumus", 0, ""));
		in("Olap").def(context("Concept").has(component("Ticket", new tara.lang.model.rules.Size(0, 2147483647), tara.lang.model.Tag.MAIN, tara.lang.model.Tag.FINAL, tara.lang.model.Tag.TERMINAL_INSTANCE), component("Olap.ZoomGroup", new tara.lang.model.rules.Size(0, 2147483647), tara.lang.model.Tag.TERMINAL_INSTANCE, tara.lang.model.Tag.SINGLE), name()).assume(isTerminalInstance(), isMain()).doc("/Users/octavio/workspace/sandbox/sumus/Base/model/Visualization.tara", 8, ""));
		in("Olap.ZoomGroup").def(context("Concept").has(component("Olap.ZoomGroup.Zoom", new tara.lang.model.rules.Size(0, 2147483647), tara.lang.model.Tag.TERMINAL_INSTANCE), name()).assume(isTerminalInstance()).doc("/Users/octavio/workspace/sandbox/sumus/Base/model/Visualization.tara", 10, ""));
		in("Olap.ZoomGroup.Zoom").def(context("Concept").has(name()).assume(isTerminalInstance()).doc("/Users/octavio/workspace/sandbox/sumus/Base/model/Visualization.tara", 11, ""));
		in("Dashboard").def(context("Concept").has(component("Dashboard.Heatmap", new tara.lang.model.rules.Size(0, 2147483647), tara.lang.model.Tag.FINAL, tara.lang.model.Tag.TERMINAL_INSTANCE), parameter("layout", tara.lang.model.Primitive.STRING, new tara.lang.model.rules.Size(1, 1), null, 1, new tara.lang.model.rules.Size(1, 1), "TERMINAL"), name()).assume(isTerminalInstance(), isMain()).doc("/Users/octavio/workspace/sandbox/sumus/Base/model/Visualization.tara", 15, ""));
		in("Dashboard.Chart.Display").def(context("Concept").has(component("Dashboard.Chart.Display.ColorScheme", new tara.lang.model.rules.Size(0, 2147483647), tara.lang.model.Tag.SINGLE, tara.lang.model.Tag.FINAL, tara.lang.model.Tag.TERMINAL_INSTANCE), name()).assume(isTerminalInstance()).doc("/Users/octavio/workspace/sandbox/sumus/Base/model/Visualization.tara", 20, ""));
		in("Dashboard.Chart.Display.ColorScheme").def(context("Concept").has(name()).assume(isTerminalInstance()).doc("/Users/octavio/workspace/sandbox/sumus/Base/model/Visualization.tara", 22, ""));
		in("Dashboard.Heatmap").def(context("Concept").has(component("Dashboard.Chart.Display", new tara.lang.model.rules.Size(0, 2147483647), tara.lang.model.Tag.TERMINAL_INSTANCE), name()).assume(isTerminalInstance()).doc("/Users/octavio/workspace/sandbox/sumus/Base/model/Visualization.tara", 24, ""));
		in("Timeline").def(context("Concept").has(name()).assume(isTerminalInstance(), isMain()).doc("/Users/octavio/workspace/sandbox/sumus/Base/model/Visualization.tara", 27, ""));
		in("Categorization").def(context("Concept").has(component("Categorization.Category", new tara.lang.model.rules.Size(0, 2147483647), tara.lang.model.Tag.TERMINAL_INSTANCE), parameter("parent", new tara.lang.model.rules.Size(1, 1), null, 2, new tara.lang.model.rules.variable.ReferenceRule(java.util.Arrays.asList()), "TERMINAL"), name(), facet("Political", true).has(component("Political.RegionSet", new tara.lang.model.rules.Size(0, 2147483647), tara.lang.model.Tag.SINGLE, tara.lang.model.Tag.REQUIRED, tara.lang.model.Tag.TERMINAL_INSTANCE)), facet("Automatic", true)).assume(isMain(), isTerminalInstance()).doc("/Users/octavio/workspace/sandbox/sumus/Base/model/Query.tara", 5, ""));
		in("Categorization.Category").def(context("Concept").has(component("Categorization.Category", new tara.lang.model.rules.Size(0, 2147483647), tara.lang.model.Tag.TERMINAL_INSTANCE), name()).assume(isTerminalInstance()).doc("/Users/octavio/workspace/sandbox/sumus/Base/model/Query.tara", 9, ""));
		in("Political").def(context("Concept").has(component("Political.RegionSet", new tara.lang.model.rules.Size(0, 2147483647), tara.lang.model.Tag.SINGLE, tara.lang.model.Tag.REQUIRED, tara.lang.model.Tag.TERMINAL_INSTANCE), name()).assume(isTerminalInstance(), isFacetInstance()).doc("/Users/octavio/workspace/sandbox/sumus/Base/model/Query.tara", 14, ""));
		in("Political.RegionSet").def(context("Concept").has(name()).assume(isTerminalInstance()).doc("/Users/octavio/workspace/sandbox/sumus/Base/model/Query.tara", 16, ""));
		in("Automatic").def(context("Concept").has(name()).assume(isTerminalInstance(), isFacetInstance()).doc("/Users/octavio/workspace/sandbox/sumus/Base/model/Query.tara", 19, ""));
		in("Ticket").def(context("Concept").has(name()).assume(isMain(), isTerminalInstance()).doc("/Users/octavio/workspace/sandbox/sumus/Base/model/Query.tara", 23, ""));
		in("Indicator").def(context("Concept").has(component("Indicator.Aggregate", new tara.lang.model.rules.Size(0, 2147483647), tara.lang.model.Tag.SINGLE, tara.lang.model.Tag.REQUIRED, tara.lang.model.Tag.TERMINAL_INSTANCE), component("Indicator.Analyze", new tara.lang.model.rules.Size(0, 2147483647), tara.lang.model.Tag.FINAL, tara.lang.model.Tag.TERMINAL_INSTANCE), name()).assume(isMain(), isTerminalInstance()).doc("/Users/octavio/workspace/sandbox/sumus/Base/model/Query.tara", 29, ""));
		in("Indicator.Aggregate").def(context("Concept").has(name()).assume(isTerminalInstance()).doc("/Users/octavio/workspace/sandbox/sumus/Base/model/Query.tara", 32, ""));
		in("Indicator.Analyze").def(context("Concept").has(name()).assume(isTerminalInstance()).doc("/Users/octavio/workspace/sandbox/sumus/Base/model/Query.tara", 35, ""));
		in("RestFunction").def(context("Concept").has(name()).assume(isMain(), isTerminalInstance()).doc("/Users/octavio/workspace/sandbox/sumus/Base/model/Query.tara", 38, ""));
		in("Record").def(context("Concept").has(name()).assume(isMain()).doc("/Users/octavio/workspace/sandbox/sumus/Base/model/Main.tara", 3, ""));
		in("Member").def(context("Concept").has(parameter("label", tara.lang.model.Primitive.STRING, new tara.lang.model.rules.Size(1, 1), null, 0, new tara.lang.model.rules.Size(1, 1), "TERMINAL"), name()).assume(isMain()).doc("/Users/octavio/workspace/sandbox/sumus/Base/model/Main.tara", 5, ""));
		in("Geolocated").def(context("Concept").has(parameter("x", tara.lang.model.Primitive.DOUBLE, new tara.lang.model.rules.Size(1, 1), null, 0, new tara.lang.model.rules.Size(1, 1), "TERMINAL"), parameter("y", tara.lang.model.Primitive.DOUBLE, new tara.lang.model.rules.Size(1, 1), null, 1, new tara.lang.model.rules.Size(1, 1), "TERMINAL"), name()).assume(isFacet()).doc("/Users/octavio/workspace/sandbox/sumus/Base/model/Main.tara", 8, ""));
		in("OriginGroup").def(context("Concept").has(component("OriginGroup.Origin", new tara.lang.model.rules.Size(0, 2147483647), tara.lang.model.Tag.TERMINAL_INSTANCE), name()).assume(isTerminalInstance(), isMain()).doc("/Users/octavio/workspace/sandbox/sumus/Base/model/Main.tara", 12, ""));
		in("OriginGroup.Origin").def(context("Concept").has(parameter("label", tara.lang.model.Primitive.STRING, new tara.lang.model.rules.Size(1, 1), null, 1, new tara.lang.model.rules.Size(1, 1), "TERMINAL"), name()).assume(isTerminalInstance()).doc("/Users/octavio/workspace/sandbox/sumus/Base/model/Main.tara", 14, ""));
		in("Fact").def(context("Concept").has(parameter("instant", tara.lang.model.Primitive.DATE, new tara.lang.model.rules.Size(1, 1), null, 0, new tara.lang.model.rules.Size(1, 1), "TERMINAL"), parameter("origin", tara.lang.model.Primitive.STRING, new tara.lang.model.rules.Size(1, 1), null, 1, new tara.lang.model.rules.Size(1, 1), "TERMINAL"), parameter("mode", tara.lang.model.Primitive.WORD, new tara.lang.model.rules.Size(1, 1), null, 2, new tara.lang.model.rules.variable.WordRule(java.util.Arrays.asList("None", "Sum", "Max", "Min", "MostFrequent", "Average")), "TERMINAL"), parameter("count", tara.lang.model.Primitive.INTEGER, new tara.lang.model.rules.Size(1, 1), null, 3, new tara.lang.model.rules.Size(1, 1), "TERMINAL"), name()).assume(isMain()).doc("/Users/octavio/workspace/sandbox/sumus/Base/model/Main.tara", 18, ""));
	}

	@Override
	public String languageName() {
		return "Sumus";
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