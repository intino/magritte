package tara.magritte;

import tara.util.WordGenerator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrototypeCloner {

    private final List<Declaration> prototypes;
    private final Declaration declaration;
    private final Board board;
    private final Map<String, Declaration> cloneMap = new HashMap<>();
    private final DeclarationLoader loader = cloneMap::get;

    private PrototypeCloner(List<Declaration> prototypes, Declaration declaration, Board board) {
        this.prototypes = prototypes;
        this.declaration = declaration;
        this.board = board;
    }

    public static void clone(List<Declaration> prototypes, Declaration declaration, Board board) {
        new PrototypeCloner(prototypes, declaration, board).execute();
    }

    private void execute() {
        board.loaders.add(loader);
        prototypes.forEach(p -> clone(declaration.name() + "." + WordGenerator.generate(), p, declaration));
        board.loaders.remove(loader);
    }

    private Declaration clone(String name, Declaration prototype, Declaration owner) {
        Declaration clone = new Declaration(name);
        clone.owner(owner);
        prototype.types().forEach(clone::morphWith);
        prototype.components().forEach(c -> clone.add(clone(name + "." + c.shortName(), c, clone)));
        cloneMap.put(prototype.name, clone);
        owner.add(clone);
        return clone;
    }

}
