package tara.intellij.stash;

import org.junit.Test;
import tara.io.Concept;
import tara.io.Stash;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static tara.intellij.stash.StashToTara.taraFrom;
import static tara.io.Helper.*;

public class StashToTaraTest {

	@Test
	public void format_of_a_concept() throws Exception {
		Stash stash = newStash("x", emptyList(), singletonList(new Concept.Content("Square", 1, 1)), singletonList(squareConcept()), emptyList());
		String result =
				"dsl x\n\n" +
				"Concept:{1..1} Square\n" +
				"\tside = 1.0 \n";
		assertThat(taraFrom(stash), is(result));
	}

	@Test
	public void format_of_nested_concepts() throws Exception {
		Stash stash = newStash("x", emptyList(), singletonList(new Concept.Content("Square", 1, 1)), list(squareConceptWithSide(), sideConcept()), emptyList());
		String result =
				"dsl x\n\n" +
				"Concept:{1..1} Square\n" +
				"\tside = 1.0 \n" +
				"\tConcept:{0..4} Square$Side\n" +
				"\t\tsize = 5.0 \n";
		assertThat(taraFrom(stash), is(result));
	}

	@Test
	public void format_non_nested_concepts() throws Exception {
		Stash stash = newStash("x", emptyList(), singletonList(new Concept.Content("Square", 1, 1)), list(squareConceptWithOuterSide(), outerSideConcept()), emptyList());
		String result =
				"dsl x\n\n" +
				"Concept:{1..1} Square\n" +
				"\tside = 1.0 \n" +
				"\thas:{0..4} Side\n\n" +
				"Concept:{0..0} Side\n" +
				"\tsize = 5.0 \n";
		assertThat(taraFrom(stash), is(result));
	}

	private Concept squareConcept() {
		return newConcept("Square", false, true, true, "Square.class", null, singletonList("Concept"), emptyList(), emptyList(),
				list(newDouble("side", 1.0)), emptyList());
	}

	private Concept squareConceptWithSide() {
		Concept concept = squareConcept();
		concept.contentRules.add(new Concept.Content("Square$Side", 0, 4));
		return concept;
	}

	private Concept squareConceptWithOuterSide() {
		Concept concept = squareConcept();
		concept.contentRules.add(new Concept.Content("Side", 0, 4));
		return concept;
	}

	private Concept sideConcept() {
		return newConcept("Square$Side", false, true, false, "Side.class", null, singletonList("Concept"), emptyList(), emptyList(),
				list(newDouble("size", 5.0)), emptyList());
	}

	private Concept outerSideConcept() {
		return newConcept("Side", false, true, false, "Side.class", null, singletonList("Concept"), emptyList(), emptyList(),
				list(newDouble("size", 5.0)), emptyList());
	}
}