import com.google.gson.*;
import io.intino.alexandria.Base64;
import io.intino.alexandria.Json;
import io.intino.magritte.io.StashDeserializer;
import io.intino.magritte.io.StashSerializer;
import io.intino.magritte.io.model.Concept;
import io.intino.magritte.io.model.Node;
import io.intino.magritte.io.model.Stash;
import io.intino.magritte.io.model.Variable;
import org.junit.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
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
	@Ignore
	public void serializeStash() throws IOException {
		InputStream stream = StashTest.class.getResourceAsStream("./cuentas_stash.json");
		String json = new String(stream.readAllBytes(), StandardCharsets.UTF_8);
		Stash stash = Json.fromJson(json, Stash.class);
		byte[] serialize = StashSerializer.serialize(stash);
		Stash returnedStash = StashDeserializer.stashFrom(serialize);
		if (returnedStash == null) {
			System.err.println(Json.toJson(stash));
		}
	}

	@Test
	@Ignore
	public void serializeStashParts() throws IOException {
		InputStream stream = StashTest.class.getResourceAsStream("./gestion_cobro_201811.json");
		String json = new String(stream.readAllBytes(), StandardCharsets.UTF_8);
		Gson gson = gsonBuilder().create();
		for (int i = 0; i < 1000; i += 1) {
			Stash stash = gson.fromJson(json, Stash.class);
			stash.nodes = new ArrayList<>(stash.nodes.subList(i, i + 2));
//			stash.nodes.forEach(n -> remove(n.variables, List.of("fecha")));
			byte[] serialize = StashSerializer.serialize(stash);
			Stash returnedStash = StashDeserializer.stashFrom(serialize);
			if (returnedStash == null) {
				System.err.println(gson.toJson(stash));
				Assert.assertNotNull("Falló en " + i, returnedStash);
			}
		}
	}

	private static GsonBuilder gsonBuilder() {
		return new GsonBuilder().setNumberToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE).registerTypeAdapter(Number.class, (JsonDeserializer<Number>) (json, type1, context) -> {
			return json.getAsJsonPrimitive().getAsString().contains(".") ? json.getAsJsonPrimitive().getAsDouble() : json.getAsJsonPrimitive().getAsLong();
		}).registerTypeAdapter(Number.class, (JsonSerializer<Number>) (json, type1, jsonDeserializationContext) -> new JsonPrimitive(json));

	}

	private void removeValue(List<Variable> variables, String variable, List<String> values) {
		variables.stream().filter(v -> v.name.equals(variable)).forEach(v -> v.values.removeIf(values::contains));
	}

	private void remove(List<Variable> variables, List<String> names) {
		variables.removeIf(v -> names.contains(v.name));
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
