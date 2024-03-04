package io.intino.magritte.framework.stresstests;

import io.intino.magritte.framework.Graph;
import io.intino.magritte.io.StashDeserializer;
import io.intino.magritte.io.model.Concept;
import io.intino.magritte.io.model.Node;
import io.intino.magritte.io.model.Stash;
import io.intino.magritte.io.model.Variable;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static io.intino.magritte.io.Helper.*;
import static io.intino.magritte.io.StashSerializer.serialize;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

public class StressTests {
	private static final Random Random = new Random();
	private static final int NumberOfCars = 1000;
	private static final int NumberOfFacts = 100000;

	@BeforeClass
	public static void beforeClass() throws Exception {
		Files.write(Paths.get("test-res/Members.stash"), serialize(membersStash()));
		Files.write(Paths.get("test-res/Facts.stash"), serialize(factsStash()));
	}

	@Test
	public void loading_stashes_without_magritte() {
		long init = System.nanoTime();
		StashDeserializer.stashFrom(new File("test-res/Facts.stash"));
		StashDeserializer.stashFrom(new File("test-res/Members.stash"));
		long finish = System.nanoTime();
		System.out.println("Loading stashes without magritte: " + ((finish - init) / 1e6) + " ms");
	}

	@Test
	public void loading_stashes_with_magritte() {
		long init = System.nanoTime();
		new Graph().loadStashes("Members", "Facts");
		long finish = System.nanoTime();
		System.out.println("Loading stashes with magritte: " + ((finish - init) / 1e6) + " ms");
	}

	private static Stash membersStash() {
		return newStash("Proteo", list(), list(), concepts(), members());
	}

	private static Stash factsStash() {
		return newStash("Members", list(), list(), list(), facts());
	}

	private static List<Concept> concepts() {
		return asList(
				newConcept("Member", false, true, false, true, "io.intino.magritte.framework.stresstests.layers.Member", null, list(), list(), list(), list(), list()),
				newConcept("Fact", false, true, false, true, "io.intino.magritte.framework.stresstests.layers.Fact", null, list(), list(), list(), list(), list()),
				newConcept("Car", false, false, false, true, "io.intino.magritte.framework.stresstests.layers.Car", null, list("Member"), list(), list(), list(), list()),
				newConcept("CarFact", false, false, false, true, "io.intino.magritte.framework.stresstests.layers.CarFact", null, list("Fact"), list(), list(), list(), list())
		);
	}

	private static List<Node> members() {
		return range(0, NumberOfCars).boxed()
				.map(i -> newNode("Members#c" + i, list("Car"), list(newVariable("plate", "c" + i)), list())).collect(toList());
	}

	private static List<Node> facts() {
		return range(0, NumberOfFacts).boxed()
				.map(i -> newNode("Facts#f" + i, list("CarFact"), factCarVariables(), list())).collect(toList());
	}

	private static List<Variable> factCarVariables() {
		return Arrays.asList(
				newVariable("car", "Members#c" + Random.nextInt(NumberOfCars)),
				newVariable("speed", Random.nextDouble()),
				newVariable("distance", Random.nextDouble())
		);
	}
}
