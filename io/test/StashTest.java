import io.intino.magritte.io.StashDeserializer;
import io.intino.magritte.io.StashSerializer;
import io.intino.magritte.io.model.Concept;
import io.intino.magritte.io.model.Node;
import io.intino.magritte.io.model.Stash;
import io.intino.magritte.io.model.Variable;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Stream;

import static io.intino.magritte.io.Helper.*;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

public class StashTest {
	private static final int NumberOfCars = 1000;
	private static final int NumberOfFacts = 100000;
	private static final java.util.Random Random = new Random();
	static File dir = new File("stashes");

	@BeforeClass
	public static void beforeClass() {
		dir.mkdirs();
	}

	@AfterClass
	public static void afterClass() {
		for (File file : Objects.requireNonNull(dir.listFiles())) file.delete();
		dir.delete();
	}

	@Test
	public void name() {
		File file = new File("/Users/oroncal/Downloads/Cuentas.stash");
		Stash stash = StashDeserializer.stashFrom(file);

	}

	@Test
	public void create_and_read_stash() {
		Stream.of(membersStash(), factsStash()).parallel().forEach(s -> {
			try {
				Files.write(Paths.get(s.path + ".stash"), StashSerializer.serialize(s));
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		});
	}


	private static Stash membersStash() {
		Stash members = newStash("Proteo", list(), list(), concepts(), members());
		members.path = "members";
		return members;
	}

	private static Stash factsStash() {
		Stash facts = newStash("Members", list(), list(), list(), facts());
		facts.path = "facts";
		return facts;
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
