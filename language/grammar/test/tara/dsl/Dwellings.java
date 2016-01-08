package tara.dsl;

import java.util.Locale;

import static tara.lang.semantics.constraints.RuleFactory.*;

public class Dwellings extends Tara {
	public Dwellings() {
		def("").with(context("").has(component("City.Building.Apartment.Room", new tara.lang.model.rules.Size(0, 2147483647), tara.lang.model.Tag.Instance), component("Load", new tara.lang.model.rules.Size(0, 2147483647), tara.lang.model.Tag.Instance), component("City.Building.Apartment.DomesticHotWater", new tara.lang.model.rules.Size(0, 1), tara.lang.model.Tag.Instance), component("City.Building.Apartment.SelfLoad", new tara.lang.model.rules.Size(0, 1), tara.lang.model.Tag.Instance), component("City", new tara.lang.model.rules.Size(0, 2147483647), tara.lang.model.Tag.Instance), component("AndreasNormalisation", new tara.lang.model.rules.Size(0, 2147483647), tara.lang.model.Tag.Instance), component("Weather", new tara.lang.model.rules.Size(0, 2147483647), tara.lang.model.Tag.Instance), component("HeatingSystem", new tara.lang.model.rules.Size(0, 2147483647), tara.lang.model.Tag.Instance), component("Temperature", new tara.lang.model.rules.Size(0, 2147483647), tara.lang.model.Tag.Instance), component("Command", new tara.lang.model.rules.Size(0, 2147483647)), component("Query", new tara.lang.model.rules.Size(0, 2147483647)), component("PoliticalCategorization", new tara.lang.model.rules.Size(0, 2147483647)), component("Categorization", new tara.lang.model.rules.Size(0, 2147483647)), component("PoliticalCategorization", new tara.lang.model.rules.Size(0, 2147483647)), component("DataMiner", new tara.lang.model.rules.Size(0, 2147483647)), component("Server", new tara.lang.model.rules.Size(1, 1)), component("Space", new tara.lang.model.rules.Size(1, 1)), component("Dashboard", new tara.lang.model.rules.Size(0, 2147483647)), component("PivotTable", new tara.lang.model.rules.Size(0, 2147483647)), component("Role", new tara.lang.model.rules.Size(0, 2147483647)), component("Timeline", new tara.lang.model.rules.Size(1, 1))).doc("sumus.Dwellings", 0, ""));
		declare("apartmentSides", java.util.Arrays.asList("AutomaticCategorization", "Concept", "MetaConcept"), "Dwellings");
		declare("buildings", java.util.Arrays.asList("StructuralCategorization", "Concept", "MetaConcept"), "Dwellings");
		declare("inputTemperatureIndicator", java.util.Arrays.asList("Indicator", "Concept", "MetaConcept"), "Dwellings");
		declare("energyIndicator", java.util.Arrays.asList("Indicator", "Concept", "MetaConcept"), "Dwellings");
		declare("volumeIndicator", java.util.Arrays.asList("Indicator", "Concept", "MetaConcept"), "Dwellings");
		declare("temperatureIndicator", java.util.Arrays.asList("Indicator", "Concept", "MetaConcept"), "Dwellings");
		declare("energyAverage", java.util.Arrays.asList("Ticket", "Concept", "MetaConcept"), "Dwellings");
		declare("volumeAverage", java.util.Arrays.asList("Ticket", "Concept", "MetaConcept"), "Dwellings");
		declare("inputTemperatureAverage", java.util.Arrays.asList("Ticket", "Concept", "MetaConcept"), "Dwellings");
		declare("dwellingWarehouse", java.util.Arrays.asList("Warehouse", "Concept", "MetaConcept"), "Dwellings");
		declare("weatherWarehouse", java.util.Arrays.asList("Warehouse", "Concept", "MetaConcept"), "Dwellings");
		def("Load").with(context("Member", "MetaConcept").has(parameter("label", tara.lang.model.Primitive.STRING, new tara.lang.model.rules.Size(1, 1), null, 0, null)).assume(isInstance()).doc("/Users/octavio/workspace/sandbox/sumus/Dwellings/model/Store.tara", 6, ""));
		def("City").with(context("Member", "MetaConcept").has(component("City.Building", new tara.lang.model.rules.Size(0, 2147483647), tara.lang.model.Tag.Instance), parameter("label", tara.lang.model.Primitive.STRING, new tara.lang.model.rules.Size(1, 1), null, 0, null)).assume(isInstance()).doc("/Users/octavio/workspace/sandbox/sumus/Dwellings/model/Store.tara", 19, ""));
		def("City.Building").with(context("Member", "MetaConcept").has(component("City.Building.Apartment", new tara.lang.model.rules.Size(0, 2147483647), tara.lang.model.Tag.Instance), parameter("label", tara.lang.model.Primitive.STRING, new tara.lang.model.rules.Size(1, 1), null, 0, null)).assume(isInstance()).doc("/Users/octavio/workspace/sandbox/sumus/Dwellings/model/Store.tara", 20, ""));
		def("City.Building.Apartment").with(context("Member", "MetaConcept").has(component("City.Building.Apartment.Room", new tara.lang.model.rules.Size(0, 2147483647), tara.lang.model.Tag.Instance), component("City.Building.Apartment.DomesticHotWater", new tara.lang.model.rules.Size(0, 1), tara.lang.model.Tag.Instance), component("City.Building.Apartment.SelfLoad", new tara.lang.model.rules.Size(0, 1), tara.lang.model.Tag.Instance), parameter("label", tara.lang.model.Primitive.STRING, new tara.lang.model.rules.Size(1, 1), null, 0, null), parameter("floor", tara.lang.model.Primitive.STRING, new tara.lang.model.rules.Size(1, 1), null, 1, null, "Terminal"), parameter("side", tara.lang.model.Primitive.WORD, new tara.lang.model.rules.Size(1, 1), null, 2, new tara.lang.model.rules.variable.WordRule(java.util.Arrays.asList("Left", "Right")), "Terminal")).assume(isInstance()).doc("/Users/octavio/workspace/sandbox/sumus/Dwellings/model/Store.tara", 21, ""));
		def("City.Building.Apartment.Room").with(context("Member", "MetaConcept").has(parameter("label", tara.lang.model.Primitive.STRING, new tara.lang.model.rules.Size(1, 1), null, 0, null), parameter("sensor", tara.lang.model.Primitive.WORD, new tara.lang.model.rules.Size(1, 1), null, 1, new tara.lang.model.rules.variable.WordRule(java.util.Arrays.asList("KUE", "BAD", "SLZ", "KIZ", "WNZ", "BHK", "HKB")), "Terminal")).assume(isInstance()).doc("/Users/octavio/workspace/sandbox/sumus/Dwellings/model/Store.tara", 24, ""));
		def("City.Building.Apartment.DomesticHotWater").with(context("Member", "MetaConcept").has(parameter("label", tara.lang.model.Primitive.STRING, new tara.lang.model.rules.Size(1, 1), null, 0, null)).assume(isInstance()).doc("/Users/octavio/workspace/sandbox/sumus/Dwellings/model/Store.tara", 25, ""));
		def("City.Building.Apartment.SelfLoad").with(context("Member", "MetaConcept").has(parameter("label", tara.lang.model.Primitive.STRING, new tara.lang.model.rules.Size(1, 1), null, 0, null)).assume(isInstance()).doc("/Users/octavio/workspace/sandbox/sumus/Dwellings/model/Store.tara", 26, ""));
		def("AndreasNormalisation").with(context("Member", "MetaConcept").has(parameter("label", tara.lang.model.Primitive.STRING, new tara.lang.model.rules.Size(1, 1), null, 0, null), parameter("A", tara.lang.model.Primitive.DOUBLE, new tara.lang.model.rules.Size(1, 1), null, 1, null, "Terminal"), parameter("B", tara.lang.model.Primitive.DOUBLE, new tara.lang.model.rules.Size(1, 1), null, 2, null, "Terminal"), parameter("C", tara.lang.model.Primitive.DOUBLE, new tara.lang.model.rules.Size(1, 1), null, 3, null, "Terminal"), parameter("D", tara.lang.model.Primitive.DOUBLE, new tara.lang.model.rules.Size(1, 1), null, 4, null, "Terminal"), parameter("formula", tara.lang.model.Primitive.FUNCTION, new tara.lang.model.rules.Size(0, 1), null, 5, new tara.lang.model.rules.variable.NativeRule("NormalisationFormula", "public double calculate()", java.util.Arrays.asList(), "Dwellings"), "Terminal")).assume(isInstance()).doc("/Users/octavio/workspace/sandbox/sumus/Dwellings/model/Store.tara", 30, ""));
		def("HeatingSystem").with(context("Fact", "MetaConcept").has(parameter("instant", tara.lang.model.Primitive.DATE, new tara.lang.model.rules.Size(1, 1), null, 0, null), parameter("aggregation", tara.lang.model.Primitive.WORD, new tara.lang.model.rules.Size(0, 1), null, 1, new tara.lang.model.rules.variable.WordRule(java.util.Arrays.asList("None", "Sum", "Max", "Min"))), parameter("aggregatedItems", tara.lang.model.Primitive.INTEGER, new tara.lang.model.rules.Size(0, 1), null, 2, null), parameter("load", new tara.lang.model.rules.Size(1, 1), null, 3, new tara.lang.model.rules.variable.ReferenceRule(java.util.Arrays.asList("City.Building.Apartment.Room", "Load", "City.Building.Apartment.DomesticHotWater", "City.Building.Apartment.SelfLoad")), "Terminal"), parameter("energy", tara.lang.model.Primitive.DOUBLE, new tara.lang.model.rules.Size(1, 1), null, 4, new tara.lang.model.rules.variable.DoubleRule(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, "Wh"), "Terminal"), parameter("volume", tara.lang.model.Primitive.DOUBLE, new tara.lang.model.rules.Size(1, 1), null, 5, new tara.lang.model.rules.variable.DoubleRule(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, "L"), "Terminal"), parameter("inputTemperature", tara.lang.model.Primitive.DOUBLE, new tara.lang.model.rules.Size(1, 1), null, 6, new tara.lang.model.rules.variable.DoubleRule(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, "°C"), "Terminal"), parameter("outputTemperature", tara.lang.model.Primitive.DOUBLE, new tara.lang.model.rules.Size(1, 1), null, 7, new tara.lang.model.rules.variable.DoubleRule(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, "°C"), "Terminal")).assume(isInstance()).doc("/Users/octavio/workspace/sandbox/sumus/Dwellings/model/Store.tara", 38, ""));
		def("Weather").with(context("Fact", "MetaConcept").has(parameter("instant", tara.lang.model.Primitive.DATE, new tara.lang.model.rules.Size(1, 1), null, 0, null), parameter("aggregation", tara.lang.model.Primitive.WORD, new tara.lang.model.rules.Size(0, 1), null, 1, new tara.lang.model.rules.variable.WordRule(java.util.Arrays.asList("None", "Sum", "Max", "Min"))), parameter("aggregatedItems", tara.lang.model.Primitive.INTEGER, new tara.lang.model.rules.Size(0, 1), null, 2, null), parameter("city", new tara.lang.model.rules.Size(1, 1), null, 3, new tara.lang.model.rules.variable.ReferenceRule(java.util.Arrays.asList("City")), "Terminal"), parameter("temperature", tara.lang.model.Primitive.DOUBLE, new tara.lang.model.rules.Size(1, 1), null, 4, new tara.lang.model.rules.variable.DoubleRule(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, "°C"), "Terminal"), parameter("pressure", tara.lang.model.Primitive.DOUBLE, new tara.lang.model.rules.Size(1, 1), null, 5, new tara.lang.model.rules.variable.DoubleRule(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, "hPa"), "Terminal"), parameter("lightIntensity", tara.lang.model.Primitive.DOUBLE, new tara.lang.model.rules.Size(1, 1), null, 6, new tara.lang.model.rules.variable.DoubleRule(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, "lux"), "Terminal"), parameter("solarRadiation", tara.lang.model.Primitive.DOUBLE, new tara.lang.model.rules.Size(1, 1), null, 7, new tara.lang.model.rules.variable.DoubleRule(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, "W/m2"), "Terminal"), parameter("humidity", tara.lang.model.Primitive.DOUBLE, new tara.lang.model.rules.Size(1, 1), null, 8, new tara.lang.model.rules.variable.DoubleRule(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, "%"), "Terminal"), parameter("windSpeed", tara.lang.model.Primitive.DOUBLE, new tara.lang.model.rules.Size(1, 1), null, 9, new tara.lang.model.rules.variable.DoubleRule(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, "km/h"), "Terminal"), parameter("windDirection", tara.lang.model.Primitive.DOUBLE, new tara.lang.model.rules.Size(1, 1), null, 10, new tara.lang.model.rules.variable.DoubleRule(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, "°"), "Terminal")).assume(isInstance()).doc("/Users/octavio/workspace/sandbox/sumus/Dwellings/model/Store.tara", 45, ""));
		def("Temperature").with(context("Fact", "MetaConcept").has(parameter("instant", tara.lang.model.Primitive.DATE, new tara.lang.model.rules.Size(1, 1), null, 0, null), parameter("aggregation", tara.lang.model.Primitive.WORD, new tara.lang.model.rules.Size(0, 1), null, 1, new tara.lang.model.rules.variable.WordRule(java.util.Arrays.asList("None", "Sum", "Max", "Min"))), parameter("aggregatedItems", tara.lang.model.Primitive.INTEGER, new tara.lang.model.rules.Size(0, 1), null, 2, null), parameter("ahead", tara.lang.model.Primitive.INTEGER, new tara.lang.model.rules.Size(1, 1), null, 3, new tara.lang.model.rules.variable.IntegerRule(-2147483648, 2147483647, "h"), "Terminal"), parameter("temperature", tara.lang.model.Primitive.DOUBLE, new tara.lang.model.rules.Size(1, 1), null, 4, new tara.lang.model.rules.variable.DoubleRule(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, "°C"), "Terminal")).assume(isInstance()).doc("/Users/octavio/workspace/sandbox/sumus/Dwellings/model/Store.tara", 55, ""));
		def("Dashboard.HeatMap").with(context("Concept", "MetaConcept").has(parameter("label", tara.lang.model.Primitive.STRING, new tara.lang.model.rules.Size(1, 1), null, 0, null), parameter("of", new tara.lang.model.rules.Size(1, 2147483647), null, 1, new tara.lang.model.rules.variable.ReferenceRule(java.util.Arrays.asList("Indicator")))).assume(isInstance()));
		def("Warehouse").with(context("Concept", "MetaConcept").has(component("Warehouse.Restrict", new tara.lang.model.rules.Size(0, 2147483647)), parameter("code", tara.lang.model.Primitive.STRING, new tara.lang.model.rules.Size(1, 1), null, 0, null), parameter("changing", tara.lang.model.Primitive.WORD, new tara.lang.model.rules.Size(1, 1), null, 1, new tara.lang.model.rules.variable.WordRule(java.util.Arrays.asList("Slow", "Fast")))).assume(isInstance()));
		def("Query").with(context("Concept", "MetaConcept").has(parameter("path", tara.lang.model.Primitive.STRING, new tara.lang.model.rules.Size(1, 1), null, 0, null), parameter("handler", tara.lang.model.Primitive.FUNCTION, new tara.lang.model.rules.Size(1, 1), null, 1, new tara.lang.model.rules.variable.NativeRule("QueryHandler", "public void handle(Map parameters, javax.servlet.http.HttpServletResponse servletResponse)", java.util.Arrays.asList("import java.util.Map;"), "Sumus"))).assume(isInstance()));
		def("Server").with(context("Concept", "MetaConcept").has(parameter("port", tara.lang.model.Primitive.INTEGER, new tara.lang.model.rules.Size(1, 1), null, 0, null), parameter("pushUrl", tara.lang.model.Primitive.STRING, new tara.lang.model.rules.Size(1, 1), null, 1, null), parameter("localPortPush", tara.lang.model.Primitive.INTEGER, new tara.lang.model.rules.Size(1, 1), null, 2, null)).assume(isInstance()));
		def("Dashboard.ColorScheme").with(context("Concept", "MetaConcept").has(parameter("colors", tara.lang.model.Primitive.INTEGER, new tara.lang.model.rules.Size(1, 2147483647), null, 0, null)).assume(isInstance()));
		def("Olap").with(context("Concept", "MetaConcept").has(component("Olap.ZoomGroup", new tara.lang.model.rules.Size(0, 1)), parameter("label", tara.lang.model.Primitive.STRING, new tara.lang.model.rules.Size(1, 1), null, 0, null), parameter("tickets", new tara.lang.model.rules.Size(1, 2147483647), null, 1, new tara.lang.model.rules.variable.ReferenceRule(java.util.Arrays.asList("Ticket")))).assume(isInstance()));
		def("Dashboard").with(context("Concept", "MetaConcept").has(component("Dashboard.ColorScheme", new tara.lang.model.rules.Size(0, 1)), component("Dashboard.HeatMap", new tara.lang.model.rules.Size(0, 2147483647)), component("Dashboard.LineChart", new tara.lang.model.rules.Size(0, 2147483647)), parameter("label", tara.lang.model.Primitive.STRING, new tara.lang.model.rules.Size(1, 1), null, 0, null), parameter("layout", tara.lang.model.Primitive.STRING, new tara.lang.model.rules.Size(0, 1), null, 1, null)).assume(isInstance()));
		def("Dashboard.LineChart").with(context("Concept", "MetaConcept").has(parameter("label", tara.lang.model.Primitive.STRING, new tara.lang.model.rules.Size(1, 1), null, 0, null), parameter("of", new tara.lang.model.rules.Size(1, 2147483647), null, 1, new tara.lang.model.rules.variable.ReferenceRule(java.util.Arrays.asList("Indicator"))), parameter("by", new tara.lang.model.rules.Size(1, 1), null, 2, new tara.lang.model.rules.variable.ReferenceRule(java.util.Arrays.asList("StructuralCategorization", "AutomaticCategorization", "Categorization", "PoliticalCategorization"))), parameter("drill", new tara.lang.model.rules.Size(0, 1), null, 3, new tara.lang.model.rules.variable.ReferenceRule(java.util.Arrays.asList("StructuralCategorization", "AutomaticCategorization", "Categorization", "PoliticalCategorization")))).assume(isInstance()));
		def("DataMiner").with(context("Concept", "MetaConcept").has(parameter("source", new tara.lang.model.rules.Size(1, 1), null, 0, new tara.lang.model.rules.variable.ReferenceRule(java.util.Arrays.asList("Warehouse"))), parameter("target", new tara.lang.model.rules.Size(1, 1), null, 1, new tara.lang.model.rules.variable.ReferenceRule(java.util.Arrays.asList("Warehouse"))), parameter("scale", tara.lang.model.Primitive.WORD, new tara.lang.model.rules.Size(1, 1), null, 2, new tara.lang.model.rules.variable.WordRule(java.util.Arrays.asList("Year", "QuarterOfYear", "Month", "Week", "Day", "SixHours", "Hour", "FifteenMinutes", "Minute", "Second"), "TimeScale")), parameter("builder", tara.lang.model.Primitive.FUNCTION, new tara.lang.model.rules.Size(1, 1), null, 3, new tara.lang.model.rules.variable.NativeRule("FactBuilder", "public List<Fact> execute()", java.util.Arrays.asList("import java.util.List;", "import sumus.Fact;"), "Sumus"))).assume(isInstance()));
		def("AutomaticCategorization.Category").with(context("Concept", "MetaConcept").has(component("AutomaticCategorization.Category", new tara.lang.model.rules.Size(0, 2147483647)), parameter("label", tara.lang.model.Primitive.STRING, new tara.lang.model.rules.Size(1, 1), null, 0, null), parameter("parent", new tara.lang.model.rules.Size(1, 1), null, 1, new tara.lang.model.rules.variable.ReferenceRule(java.util.Arrays.asList("AutomaticCategorization.Category", "Categorization.Category"))), parameter("check", tara.lang.model.Primitive.FUNCTION, new tara.lang.model.rules.Size(0, 1), null, 2, new tara.lang.model.rules.variable.NativeRule("CategoryChecker", "public boolean check(sumus.Record record)", java.util.Arrays.asList("import tara.magritte.Layer;", "import java.util.stream.Stream;"), "Sumus"))).assume(isInstance()));
		def("Warehouse.Restrict").with(context("Concept", "MetaConcept").has(parameter("to", new tara.lang.model.rules.Size(1, 1), null, 0, new tara.lang.model.rules.variable.ReferenceRule(java.util.Arrays.asList("Role")))).assume(isInstance()));
		def("Categorization").with(context("Concept", "MetaConcept").has(component("PoliticalCategorization", new tara.lang.model.rules.Size(0, 2147483647)), component("StructuralCategorization", new tara.lang.model.rules.Size(0, 2147483647)), component("Categorization", new tara.lang.model.rules.Size(0, 2147483647)), component("AutomaticCategorization", new tara.lang.model.rules.Size(0, 2147483647)), component("AutomaticCategorization.Category", new tara.lang.model.rules.Size(0, 2147483647)), component("Categorization.Category", new tara.lang.model.rules.Size(0, 2147483647)), parameter("label", tara.lang.model.Primitive.STRING, new tara.lang.model.rules.Size(1, 1), null, 0, null)).assume(isInstance()));
		def("Space").with(context("Concept", "MetaConcept").has(parameter("title", tara.lang.model.Primitive.STRING, new tara.lang.model.rules.Size(1, 1), null, 0, null), parameter("subtitle", tara.lang.model.Primitive.STRING, new tara.lang.model.rules.Size(1, 1), null, 1, null)).assume(isInstance()));
		def("Ticket").with(context("Concept", "MetaConcept").has(parameter("label", tara.lang.model.Primitive.STRING, new tara.lang.model.rules.Size(1, 1), null, 0, null), parameter("source", new tara.lang.model.rules.Size(1, 1), null, 1, new tara.lang.model.rules.variable.ReferenceRule(java.util.Arrays.asList("Warehouse"))), parameter("indicator", new tara.lang.model.rules.Size(1, 1), null, 2, new tara.lang.model.rules.variable.ReferenceRule(java.util.Arrays.asList("Indicator")))).assume(isInstance()));
		def("Olap.ZoomGroup").with(context("Concept", "MetaConcept").has(component("Olap.ZoomGroup.Zoom", new tara.lang.model.rules.Size(0, 2147483647))).assume(isInstance()));
		def("AutomaticCategorization").with(context("Concept", "MetaConcept").has(component("AutomaticCategorization.Category", new tara.lang.model.rules.Size(0, 2147483647)), component("PoliticalCategorization", new tara.lang.model.rules.Size(0, 2147483647)), component("StructuralCategorization", new tara.lang.model.rules.Size(0, 2147483647)), component("AutomaticCategorization", new tara.lang.model.rules.Size(0, 2147483647)), parameter("label", tara.lang.model.Primitive.STRING, new tara.lang.model.rules.Size(1, 1), null, 0, null), parameter("record", new tara.lang.model.rules.Size(1, 1), null, 1, new tara.lang.model.rules.variable.ReferenceRule(java.util.Arrays.asList("Sensorization", "Member", "Fact"))), parameter("attribute", tara.lang.model.Primitive.FUNCTION, new tara.lang.model.rules.Size(1, 1), null, 2, new tara.lang.model.rules.variable.NativeRule("Attribute", "public Object get(sumus.Record item)", java.util.Arrays.asList(), "Sumus")), parameter("link", tara.lang.model.Primitive.FUNCTION, new tara.lang.model.rules.Size(0, 1), null, 3, new tara.lang.model.rules.variable.NativeRule("CategoryLinker", "public String link(sumus.Record item)", java.util.Arrays.asList(), "Sumus"))).assume(isInstance()));
		def("Categorization.Category").with(context("Concept", "MetaConcept").has(component("AutomaticCategorization.Category", new tara.lang.model.rules.Size(0, 2147483647)), component("Categorization.Category", new tara.lang.model.rules.Size(0, 2147483647)), parameter("label", tara.lang.model.Primitive.STRING, new tara.lang.model.rules.Size(1, 1), null, 0, null), parameter("check", tara.lang.model.Primitive.FUNCTION, new tara.lang.model.rules.Size(1, 1), null, 1, new tara.lang.model.rules.variable.NativeRule("CategoryChecker", "public boolean check(sumus.Record record)", java.util.Arrays.asList("import tara.magritte.Layer;", "import java.util.stream.Stream;"), "Sumus")), parameter("parent", new tara.lang.model.rules.Size(1, 1), null, 2, new tara.lang.model.rules.variable.ReferenceRule(java.util.Arrays.asList("AutomaticCategorization.Category", "Categorization.Category")))).assume(isInstance()));
		def("Indicator").with(context("Concept", "MetaConcept").has(parameter("label", tara.lang.model.Primitive.STRING, new tara.lang.model.rules.Size(1, 1), null, 0, null), parameter("fact", new tara.lang.model.rules.Size(1, 1), null, 1, new tara.lang.model.rules.variable.ReferenceRule(java.util.Arrays.asList("Fact"))), parameter("attribute", tara.lang.model.Primitive.FUNCTION, new tara.lang.model.rules.Size(1, 1), null, 2, new tara.lang.model.rules.variable.NativeRule("Attribute", "public Object get(sumus.Record item)", java.util.Arrays.asList(), "Sumus")), parameter("aggregation", tara.lang.model.Primitive.WORD, new tara.lang.model.rules.Size(1, 1), null, 3, new tara.lang.model.rules.variable.WordRule(java.util.Arrays.asList("Average", "Sum", "Max", "Min", "Count"), "Aggregation")), parameter("scales", tara.lang.model.Primitive.WORD, new tara.lang.model.rules.Size(1, 2147483647), null, 4, new tara.lang.model.rules.variable.WordRule(java.util.Arrays.asList("Year", "QuarterOfYear", "Month", "Week", "Day", "SixHours", "Hour", "FifteenMinutes", "Minute", "Second"), "TimeScale")), parameter("dimensions", new tara.lang.model.rules.Size(0, 2147483647), null, 5, new tara.lang.model.rules.variable.ReferenceRule(java.util.Arrays.asList("StructuralCategorization", "AutomaticCategorization", "Categorization", "PoliticalCategorization")))).assume(isInstance()));
		def("PivotTable").with(context("Concept", "MetaConcept").has(parameter("label", tara.lang.model.Primitive.STRING, new tara.lang.model.rules.Size(1, 1), null, 0, null)).assume(isInstance()));
		def("PoliticalCategorization").with(context("Concept", "MetaConcept").has(component("PoliticalCategorization", new tara.lang.model.rules.Size(0, 2147483647)), component("StructuralCategorization", new tara.lang.model.rules.Size(0, 2147483647)), component("AutomaticCategorization", new tara.lang.model.rules.Size(0, 2147483647)), component("AutomaticCategorization.Category", new tara.lang.model.rules.Size(0, 2147483647)), parameter("label", tara.lang.model.Primitive.STRING, new tara.lang.model.rules.Size(1, 1), null, 0, null), parameter("record", new tara.lang.model.rules.Size(1, 1), null, 1, new tara.lang.model.rules.variable.ReferenceRule(java.util.Arrays.asList("Geolocated:Member"))), parameter("regions", tara.lang.model.Primitive.RESOURCE, new tara.lang.model.rules.Size(1, 1), null, 2, new tara.lang.model.rules.variable.FileRule(java.util.Arrays.asList("geojson")))).assume(isInstance()));
		def("Role").with(context("Concept", "MetaConcept").has(parameter("user", tara.lang.model.Primitive.STRING, new tara.lang.model.rules.Size(1, 1), null, 0, null), parameter("from", tara.lang.model.Primitive.DATE, new tara.lang.model.rules.Size(1, 1), null, 1, null), parameter("to", tara.lang.model.Primitive.DATE, new tara.lang.model.rules.Size(0, 1), null, 2, null)).assume(isInstance()));
		def("Timeline").with(context("Concept", "MetaConcept").has(parameter("from", tara.lang.model.Primitive.DATE, new tara.lang.model.rules.Size(1, 1), null, 0, null), parameter("to", tara.lang.model.Primitive.DATE, new tara.lang.model.rules.Size(1, 1), null, 1, null), parameter("scales", tara.lang.model.Primitive.WORD, new tara.lang.model.rules.Size(1, 2147483647), null, 2, new tara.lang.model.rules.variable.WordRule(java.util.Arrays.asList("Year", "QuarterOfYear", "Month", "Week", "Day", "SixHours", "Hour", "FifteenMinutes", "Minute", "Second"), "TimeScale"))).assume(isInstance()));
		def("StructuralCategorization").with(context("Concept", "MetaConcept").has(component("PoliticalCategorization", new tara.lang.model.rules.Size(0, 2147483647)), component("StructuralCategorization", new tara.lang.model.rules.Size(0, 2147483647)), component("AutomaticCategorization", new tara.lang.model.rules.Size(0, 2147483647)), component("AutomaticCategorization.Category", new tara.lang.model.rules.Size(0, 2147483647)), parameter("label", tara.lang.model.Primitive.STRING, new tara.lang.model.rules.Size(1, 1), null, 0, null), parameter("record", new tara.lang.model.rules.Size(1, 1), null, 1, new tara.lang.model.rules.variable.ReferenceRule(java.util.Arrays.asList("Sensorization", "Member", "Fact")))).assume(isInstance()));
		def("Command").with(context("Concept", "MetaConcept").has(parameter("path", tara.lang.model.Primitive.STRING, new tara.lang.model.rules.Size(1, 1), null, 0, null), parameter("warehouse", new tara.lang.model.rules.Size(1, 1), null, 1, new tara.lang.model.rules.variable.ReferenceRule(java.util.Arrays.asList("Warehouse"))), parameter("input", new tara.lang.model.rules.Size(1, 1), null, 2, new tara.lang.model.rules.variable.ReferenceRule(java.util.Arrays.asList("Sensorization")))).assume(isInstance()));
		def("Olap.ZoomGroup.Zoom").with(context("Concept", "MetaConcept").has(parameter("scale", tara.lang.model.Primitive.WORD, new tara.lang.model.rules.Size(1, 1), null, 0, new tara.lang.model.rules.variable.WordRule(java.util.Arrays.asList("Year", "QuarterOfYear", "Month", "Week", "Day", "SixHours", "Hour", "FifteenMinutes", "Minute", "Second"), "TimeScale")), parameter("levels", tara.lang.model.Primitive.INTEGER, new tara.lang.model.rules.Size(1, 2147483647), null, 1, null)).assume(isInstance()));
	}

	@Override
	public String languageName() {
		return "Dwellings";
	}

	@Override
	public Locale locale() {
		return Locale.ENGLISH;
	}

	@Override
	public boolean isTerminalLanguage() {
		return true;
	}

	@Override
	public String metaLanguage() {
		return "Sumus";
	}
}