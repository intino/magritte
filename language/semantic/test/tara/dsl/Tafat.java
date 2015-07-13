package tara.dsl;

import tara.semantic.model.Tag;
import tara.semantic.model.Tara;

import java.util.Locale;

import static tara.semantic.constraints.RuleFactory.*;

public class Tafat extends Tara {
	public Tafat() {
		in("").def(context("").allow(multiple("Agent"), multiple("Entity"), multiple("Behavior", Tag.FACET), multiple("Operation", Tag.FACET), multiple("Behavior", Tag.FACET), multiple("Operation", Tag.FACET), multiple("Formula"), multiple("InitialValue"), multiple("Value"), multiple("Action"), multiple("TimeoutCondition"), multiple("ScheduledCondition"), multiple("RateCondition"), multiple("TimeoutCondition"), multiple("ScheduledCondition"), multiple("RateCondition"), multiple("Task"), multiple("Job"), multiple("StartJob"), multiple("RecurrentJob"), multiple("EndJob"), multiple("StartJob"), multiple("RecurrentJob"), multiple("EndJob"), multiple("EquationSystem"), multiple("StateChart"), multiple("TableFunction"), multiple("PointSet"), multiple("Map"), single("Simulation"), name()));
		in("Simulation").def(context("Concept").allow(parameter("from", "date", false, 0, "", "TERMINAL"), parameter("to", "date", false, 1, "", "TERMINAL"), name()).require(_parameter("from", "date", false, 0, "", "TERMINAL"), _parameter("to", "date", false, 1, "", "TERMINAL")).assume(isTerminalInstance()));
		in("Agent").def(context("Concept").allow(name()));
		in("Entity").def(context("Concept").allow(multiple("Entity"), multiple("Entity.Feature", Tag.FEATURE), name()));
		in("Entity.Feature").def(context("Concept").allow(name()).assume(isFeature()));
		in("Behavior").def(context("Concept").allow(multiple("StateChart"), multiple("EquationSystem"), multiple("Entity"), multiple("Formula"), multiple("Action"), multiple("PointSet"), multiple("Task"), multiple("Job"), parameter("step", "integer", false, 0, "", "TERMINAL"), parameter("onStart", "native", false, 1, "Action#public void execute()"), name()).assume(isFacet()));
		in("Operation").def(context("Concept").allow(name()).assume(isFacet()));
		in("Formula").def(context("Concept").allow(parameter("priority", "integer", false, 0, ""), parameter("value", "double", false, 2, "", "TERMINAL"), parameter("step", "integer", false, 3, "", "TERMINAL"), name(), facet("InitialValue")).require(_parameter("function", "native", false, 1, "Function#public double calculate()")));
		in("InitialValue").def(context("Concept").allow(name()).assume(isFacetInstance()));
		in("Value").def(context("Concept").allow(name()).require(_parameter("function", "native", false, 0, "Function#public double calculate()")));
		in("Action").def(context("Concept").allow(name()).require(_parameter("condition", "native", false, 0, "Check#public boolean check()"), _parameter("action", "native", false, 1, "Action#public void execute()")));
		in("TimeoutCondition").def(context("Concept").allow(name()));
		in("ScheduledCondition").def(context("Concept").allow(name()));
		in("RateCondition").def(context("Concept").allow(parameter("deviation", "double", false, 1, ""), parameter("checker", "native", false, 2, "Check#public boolean check()"), name()).require(_parameter("Rate", "measure", false, 0, "Rate")));
		in("Task").def(context("Concept").allow(multiple("Task"), multiple("StartJob"), multiple("RecurrentJob"), multiple("EndJob"), single("Task.End"), single("Task.Duration"), single("Task.End"), single("Task.Duration"), parameter("days:word", new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"}, true, 0, ""), name()).require(_single("Task.Start")));
		in("Task.Start").def(context("Concept").allow(parameter("deviation", "measure", false, 1, "Time"), name()).require(_parameter("time", "date", false, 0, "")));
		in("Task.End").def(context("Concept").allow(parameter("deviation", "measure", false, 1, "Time"), name()).require(_parameter("time", "date", false, 0, "")));
		in("Task.Duration").def(context("Concept").allow(parameter("deviation", "measure", false, 1, "Time"), name()).require(_parameter("time", "measure", false, 0, "Time")));
		in("Job").def(context("Concept").allow(multiple("Job"), multiple("StartJob"), multiple("RecurrentJob"), multiple("EndJob"), multiple("Job.StartAction"), multiple("Job.RecurrentAction"), multiple("Job.EndAction"), multiple("Job.StartAction"), multiple("Job.RecurrentAction"), multiple("Job.EndAction"), single("Job.Start"), single("Job.Duration"), name()));
		in("Job.Start").def(context("Concept").allow(parameter("deviation", "measure", false, 1, "Time"), name()).require(_parameter("time", "measure", false, 0, "Time")));
		in("Job.Duration").def(context("Concept").allow(parameter("deviation", "measure", false, 1, "Time"), name()).require(_parameter("time", "measure", false, 0, "Time")));
		in("Job.StartAction").def(context("Concept").allow(name()).require(_parameter("action", "native", false, 0, "Action#public void execute()")));
		in("Job.RecurrentAction").def(context("Concept").allow(name()).require(_parameter("action", "native", false, 0, "Action#public void execute()")));
		in("Job.EndAction").def(context("Concept").allow(name()).require(_parameter("action", "native", false, 0, "Action#public void execute()")));
		in("StartJob").def(context("Concept").allow(parameter("job", new String[]{"Job"}, false, 0, "", "TERMINAL"), name()).require(_parameter("job", new String[]{"Job"}, false, 0, "", "TERMINAL"), redefine("job", "Job")).assume(isTerminalInstance()));
		in("RecurrentJob").def(context("Concept").allow(parameter("job", new String[]{"Job"}, false, 0, "", "TERMINAL"), name()).require(_parameter("job", new String[]{"Job"}, false, 0, "", "TERMINAL"), redefine("job", "Job")).assume(isTerminalInstance()));
		in("EndJob").def(context("Concept").allow(parameter("job", new String[]{"Job"}, false, 0, "", "TERMINAL"), name()).require(_parameter("job", new String[]{"Job"}, false, 0, "", "TERMINAL"), redefine("job", "Job")).assume(isTerminalInstance()));
		in("EquationSystem").def(context("Concept").allow(multiple("EquationSystem.Stock"), multiple("EquationSystem.Flow"), multiple("EquationSystem.Stock"), multiple("EquationSystem.Flow"), parameter("solver:word", new String[]{"Euler"}, false, 0, ""), parameter("step", "double", false, 1, ""), name()));
		in("EquationSystem.Stock").def(context("Concept").allow(name()).require(_parameter("function", "native", false, 0, "Function#public double calculate()")));
		in("EquationSystem.Flow").def(context("Concept").allow(name()).require(_parameter("function", "native", false, 0, "Function#public double calculate()")));
		in("StateChart").def(context("Concept").allow(multiple("StateChart.State"), multiple("StateChart.Transition"), name()));
		in("StateChart.State").def(context("Concept").allow(multiple("StateChart.State.EntryAction"), multiple("StateChart.State.ExitAction"), multiple("StateChart.State.EntryAction"), multiple("StateChart.State.ExitAction"), multiple("StateChart.State"), multiple("StateChart.Transition"), name()));
		in("StateChart.State.EntryAction").def(context("Concept").allow(name()).require(_parameter("action", "native", false, 0, "Action#public void execute()")));
		in("StateChart.State.ExitAction").def(context("Concept").allow(name()).require(_parameter("action", "native", false, 0, "Action#public void execute()")));
		in("StateChart.Transition").def(context("Concept").allow(multiple("StateChart.Transition.Condition"), multiple("StateChart.Transition.Timeout"), multiple("StateChart.Transition.After"), multiple("StateChart.Transition.Rate"), multiple("StateChart.Transition.Message"), multiple("StateChart.Transition.Condition"), multiple("StateChart.Transition.Timeout"), multiple("StateChart.Transition.After"), multiple("StateChart.Transition.Rate"), multiple("StateChart.Transition.Message"), parameter("action", "native", false, 2, "Action#public void execute()"), name()).require(_parameter("from", new String[]{"StateChart.State"}, false, 0, ""), _parameter("to", new String[]{"StateChart.State"}, false, 1, "")));
		in("StateChart.Transition.Condition").def(context("Concept").allow(name()).require(_parameter("check", "native", false, 0, "Check#public boolean check()")));
		in("StateChart.Transition.Timeout").def(context("Concept").allow(name()).require(_parameter("timeout", "native", false, 0, "Timeout#public double calculate()")));
		in("StateChart.Transition.After").def(context("Concept").allow(name()).require(_parameter("time", "measure", false, 0, "Time")));
		in("StateChart.Transition.Rate").def(context("Concept").allow(name()).require(_parameter("times", "integer", false, 0, "time"), _parameter("unit:word", new String[]{"Second", "Minute", "Hour", "Day", "Month", "Year"}, false, 1, "")));
		in("StateChart.Transition.Message").def(context("Concept").allow(name()).require(_parameter("message", "string", false, 0, "")));
		in("TableFunction").def(context("Concept").allow(single("TableFunction.NoneInterpolation"), single("TableFunction.LinearInterpolation"), single("TableFunction.PolynomialInterpolation"), single("TableFunction.SplineInterpolation"), single("TableFunction.StepInterpolation"), single("TableFunction.NoneInterpolation"), single("TableFunction.LinearInterpolation"), single("TableFunction.PolynomialInterpolation"), single("TableFunction.SplineInterpolation"), single("TableFunction.StepInterpolation"), single("TableFunction.NoneExtrapolation"), single("TableFunction.CustomExtrapolation"), single("TableFunction.NearestPointExtrapolation"), single("TableFunction.RepeatSeriesExtrapolation"), single("TableFunction.LinearExtrapolation"), single("TableFunction.PolynomialExtrapolation"), single("TableFunction.SplineExtrapolation"), single("TableFunction.StepExtrapolation"), single("TableFunction.NoneExtrapolation"), single("TableFunction.CustomExtrapolation"), single("TableFunction.NearestPointExtrapolation"), single("TableFunction.RepeatSeriesExtrapolation"), single("TableFunction.LinearExtrapolation"), single("TableFunction.PolynomialExtrapolation"), single("TableFunction.SplineExtrapolation"), single("TableFunction.StepExtrapolation"), name()).require(_parameter("pointSet", new String[]{"PointSet"}, false, 0, "")));
		in("TableFunction.NoneInterpolation").def(context("Concept").allow(name()));
		in("TableFunction.LinearInterpolation").def(context("Concept").allow(name()));
		in("TableFunction.PolynomialInterpolation").def(context("Concept").allow(name()));
		in("TableFunction.SplineInterpolation").def(context("Concept").allow(name()));
		in("TableFunction.StepInterpolation").def(context("Concept").allow(name()));
		in("TableFunction.NoneExtrapolation").def(context("Concept").allow(name()));
		in("TableFunction.CustomExtrapolation").def(context("Concept").allow(name()).require(_parameter("function", "native", false, 0, "Function#public double calculate()")));
		in("TableFunction.NearestPointExtrapolation").def(context("Concept").allow(name()));
		in("TableFunction.RepeatSeriesExtrapolation").def(context("Concept").allow(name()));
		in("TableFunction.LinearExtrapolation").def(context("Concept").allow(name()));
		in("TableFunction.PolynomialExtrapolation").def(context("Concept").allow(name()));
		in("TableFunction.SplineExtrapolation").def(context("Concept").allow(name()));
		in("TableFunction.StepExtrapolation").def(context("Concept").allow(name()));
		in("PointSet").def(context("Concept").allow(multiple("PointSet.Point"), multiple("PointSet.X"), multiple("PointSet.Y"), multiple("PointSet.Point"), single("PointSet.X"), single("PointSet.Y"), name()));
		in("PointSet.Point").def(context("Concept").allow(name()).require(_parameter("x", "double", false, 0, ""), _parameter("y", "double", false, 1, "")));
		in("PointSet.X").def(context("Concept").allow(name()).require(_parameter("values", "double", true, 0, "")));
		in("PointSet.Y").def(context("Concept").allow(name()).require(_parameter("values", "double", true, 0, "")));
		in("Map").def(context("Concept").allow(multiple("Map.Item", Tag.TERMINAL), name()).assume(isTerminalInstance()));
		in("Map.Item").def(context("Concept").allow(parameter("key", "string", false, 0, "", "TERMINAL"), parameter("value", "double", false, 1, "", "TERMINAL"), name()).assume(isTerminal()));
	}

	@Override
	public String languageName() {
		return "Tafat";
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