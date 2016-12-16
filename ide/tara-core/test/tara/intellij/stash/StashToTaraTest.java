package tara.intellij.stash;

import org.junit.Test;
import tara.io.Concept;
import tara.io.Stash;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static tara.intellij.stash.StashToTara.taraFrom;
import static tara.io.Helper.*;

public class StashToTaraTest {

	@Test
	public void format_of_a_concept() throws Exception {
		Stash stash = newStash("x", list(), list(new Concept.Content("Square", 1, 1)), list(squareConcept()), list());
		String result =
				"dsl x\n\n" +
				"Concept:{1..1} Square\n" +
				"\tside = 1.0 \n";
		assertThat(taraFrom(stash), is(result));
	}

	@Test
	public void format_of_nested_concepts() throws Exception {
		Stash stash = newStash("x", list(), list(new Concept.Content("Square", 1, 1)), list(squareConceptWithSide(), sideConcept()), list());
		String result =
				"dsl x\n\n" +
				"Concept:{1..1} Square\n" +
				"\tside = 1.0 \n" +
				"\tConcept:{0..4} Side\n" +
				"\t\tsize = 5.0 \n";
		assertThat(taraFrom(stash), is(result));
	}

	@Test
	public void format_non_nested_concepts() throws Exception {
		Stash stash = newStash("x", list(), list(new Concept.Content("Square", 1, 1)), list(squareConceptWithOuterSide(), outerSideConcept()), list());
		String result =
				"dsl x\n\n" +
				"Concept:{1..1} Square\n" +
				"\tside = 1.0 \n" +
				"\thas:{0..4} Side\n\n" +
				"Concept:{0..0} Side\n" +
				"\tsize = 5.0 \n";
		assertThat(taraFrom(stash), is(result));
	}

	@Test
	public void format_faceted_concepts() throws Exception {
		Stash stash = newStash("x", list(), list(new Concept.Content("Square", 1, 1)), list(facetedSquareConcept()), list());
		String result =
				"dsl x\n\n" +
				"Entity:{1..1} Square > as Geolocated;\n";
		assertThat(taraFrom(stash), is(result));
	}

	@Test
	public void format_extended_concepts() throws Exception {
		Stash stash = newStash("x", list(), list(new Concept.Content("Square", 1, 1)), list(squareConceptWithExtension()), list());
		String result =
				"dsl x\n\n" +
				"Entity:{1..1} Square extends Polygon\n";
		assertThat(taraFrom(stash), is(result));
	}

	@Test
	public void format_extended_and_faceted_concepts() throws Exception {
		Stash stash = newStash("x", list(), list(new Concept.Content("Square", 1, 1)), list(facetedSquareWithExtension()), list());
		String result =
				"dsl x\n\n" +
				"Entity:{1..1} Square extends Polygon > as Geolocated;\n";
		assertThat(taraFrom(stash), is(result));
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
		return newConcept("Square$Side", false, true, false, "Side.class", null, list("Concept"), list(), list(),
				list(newDouble("size", 5.0)), list());
	}

	private Concept outerSideConcept() {
		return newConcept("Side", false, true, false, "Side.class", null, list("Concept"), list(), list(),
				list(newDouble("size", 5.0)), list());
	}

	private Concept squareConcept() {
		return newConcept("Square", false, true, true, "Square.class", null, list("Concept"), list(), list(),
				list(newDouble("side", 1.0)), list());
	}

	private Concept facetedSquareConcept() {
		return newConcept("Square", false, true, true, "Square.class", null, list("Entity", "Geolocated"), list(), list(), list(), list());
	}
	private Concept squareConceptWithExtension() {
		return newConcept("Square", false, true, true, "Square.class", "Polygon", list("Entity"), list(), list(), list(), list());
	}

	private Concept facetedSquareWithExtension() {
		return newConcept("Square", false, true, true, "Square.class", "Polygon", list("Entity", "Geolocated"), list(), list(), list(), list());
	}
}