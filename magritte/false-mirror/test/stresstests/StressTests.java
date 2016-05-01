package stresstests;

import org.junit.Ignore;
import org.junit.Test;
import tara.io.*;
import tara.magritte.Graph;

import java.awt.font.NumericShaper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;
import static tara.io.Helper.*;

public class StressTests {

    private static Random Random = new Random();
    private static int NumberOfCars = 1000;
    private static int NumberOfFacts = 100000;

    @Test
    public void loading_stashes_without_magritte() throws Exception {
        long init = System.nanoTime();
        StashDeserializer.stashFrom(new File("test-res/Facts.stash"));
        StashDeserializer.stashFrom(new File("test-res/Members.stash"));
        long finish = System.nanoTime();
        System.out.println("Loading stashes without magritte: " + ((finish - init) / 1e6) + " ms");
    }

    //    @Test
//    public void loading_stashes_with_magritte() throws Exception {
    public static void main(String[] args) throws IOException {
        while(new Scanner(System.in).nextLine().equals("continue"));
        System.out.println("starting");
        long init = System.nanoTime();
        Graph.ModelLoad graph = Graph.load("Members").loadStashes("Facts.stash");
        long finish = System.nanoTime();
        System.out.println("Loading stashes with magritte: " + ((finish - init) / 1e6) + " ms");
    }

    @Test
    @Ignore("Only execute if Facts.stash and Members.stash are not in test-res")
    public void should_create_big_stash() throws Exception {
        Files.write(Paths.get("test-res/Members.stash"), StashSerializer.serialize(membersStash()));
        Files.write(Paths.get("test-res/Facts.stash"), StashSerializer.serialize(factsStash()));
    }

    private Stash membersStash() {
        return newStash("Proteo", list(), list(), concepts(), members());
    }

    private Stash factsStash() {
        return newStash("Proteo", list(), list(), list(), facts());
    }

    private List<Concept> concepts() {
        return asList(
                newConcept("Member", false, true, true, "stresstests.layers.Member", null, list(), list(), list(), list(), list()),
                newConcept("Fact", false, true, true, "stresstests.layers.Fact", null, list(), list(), list(), list(), list()),
                newConcept("Car", false, false, true, "stresstests.layers.Car", null, list("Member"), list(), list(), list(), list()),
                newConcept("CarFact", false, false, true, "stresstests.layers.CarFact", null, list("Fact"), list(), list(), list(), list())
        );
    }

    private List<Node> members() {
        return range(0, NumberOfCars).boxed()
                .map(i -> newNode("Members#c" + i, list("Car"), list(newString("plate", "c" + i)), list())).collect(toList());
    }

    private List<Node> facts() {
        return range(0, NumberOfFacts).boxed()
                .map(i -> newNode("Facts#f" + i, list("CarFact"), factCarVariables(), list())).collect(toList());
    }

    private List<Variable> factCarVariables() {
        return Arrays.asList(
                newReference("car", "Members#c" + Random.nextInt(NumberOfCars)),
                newDouble("speed", Random.nextDouble()),
                newDouble("distance", Random.nextDouble())
        );
    }
}
