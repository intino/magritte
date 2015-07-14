package tafat.control;

import tafat.Behavior;
import tafat.Simulation;
import tafat.Tafat;
import tara.magritte.Box;
import tara.magritte.Node;
import monopoly.Monopoly;
import tafat.Action;

import java.io.IOException;
import java.time.ZoneOffset;
import java.util.List;


public class TafatEngine {

    private final List<Behavior> behaviors;

    public TafatEngine(Box box) {
        Node node = loadScene(box);
        behaviors = node.find(Behavior.class);
        behaviors.forEach(behavior -> {
            behavior.startList().forEach((start) -> {
                start.start();
            });
        });
    }

    private Node loadScene(Box box) {
        Node node = new Node();
        box.load(node);
        Monopoly.use(node);
        Tafat.use(node);
        return node;
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
//            System.out.println(Monopoly.model.board().positionOf(Monopoly.model._player(0).square()));
        }
        System.out.println((System.nanoTime() - time) / 1e9);
        print();
    }

    private void print() {
        Monopoly.board().squareList().forEach(s -> System.out.println(s.count()));
    }

    private void run() {
        behaviors.forEach(this::run);
    }

    private void run(Behavior behavior) {
        behavior.actionList().stream().filter(Action::condition).forEach(Action::action);
    }

    private long steps() {
        Simulation simulation = Tafat.simulation();
        return (simulation.to().toEpochSecond(ZoneOffset.UTC) - simulation.from().toEpochSecond(ZoneOffset.UTC));
    }
}
