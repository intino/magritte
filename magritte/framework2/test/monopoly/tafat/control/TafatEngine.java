package monopoly.tafat.control;

import monopoly.tafat.Behavior;
import monopoly.tafat.Simulation;
import monopoly.tafat.Tafat;
import tara.magritte.Box;
import tara.magritte.Node;
import monopoly.boxes.PlayGameMain;
import monopoly.monopoly.Monopoly;
import monopoly.tafat.Action;

import java.io.IOException;
import java.time.ZoneOffset;
import java.util.List;


public class TafatEngine {

    private final List<Behavior> behaviors;

    public static void main(String[] args) {
        TafatEngine tafatEngine = new TafatEngine(PlayGameMain.box);
        PlayGameMain.box = null;
        tafatEngine.execute();
    }

    public TafatEngine(Box box) {
        loadScene(box);
        behaviors = Tafat.node.find(Behavior.class);
        behaviors.forEach(behavior -> {
            behavior.startSet().forEach((start) -> {
                start.start();
            });
        });
    }

    private void loadScene(Box box) {
        Node node = new Node();
        box.load(node);
        Monopoly.node(node);
        Tafat.node = node;
    }

    public void execute() {
        try {
            System.out.printf("press enter");
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long time = System.nanoTime();
        long steps = steps();
        for (int step = 0; step < steps; step++) {
            run();
//            System.out.println(Monopoly.model.board().positionOf(Monopoly.model.player(0).square()));
        }
        System.out.println((System.nanoTime() - time) / 1e9);
        print();
    }

    private void print() {
        Monopoly.model.board().squares().forEach(s -> System.out.println(s.count()));
    }

    private void run() {
        behaviors.forEach(this::run);
    }

    private void run(Behavior behavior) {
        behavior.actionSet().stream().filter(Action::condition).forEach(Action::action);
    }

    private long steps() {
        Simulation simulation = Tafat.model.simulation();
        return (simulation.to().toEpochSecond(ZoneOffset.UTC) - simulation.from().toEpochSecond(ZoneOffset.UTC));
    }
}
