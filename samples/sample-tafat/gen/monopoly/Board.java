package monopoly;

import monopoly.natives.Position;
import monopoly.natives.SquareAt;
import monopoly.natives.SquareOf;
import tafat.natives.Check;
import tara.magritte.Morph;
import tara.magritte.NativeCode;
import tara.magritte.Node;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Board extends Morph {

    protected SquareAt squareAt;
    protected SquareOf squareOf;
    protected Position positionOf;
    protected List<Square> squareList = new ArrayList<>();

    public Board(Node node) {
        super(node);
        set("squareAt", new SquareAt_meme());
        set("squareOf", new SquareOf_meme());
        set("positionOf", new Position_meme());
    }

    public Board(Morph morph, Node node) {
        super(morph, node);
        set("squareAt", ((Board)morph).squareAt);
        set("squareOf", ((Board)morph).squareOf);
        set("positionOf", ((Board)morph).positionOf);
    }

    public Square squareAt(int position) {
        return squareAt.squareAt(position);
    }

    public void squareAt(SquareAt value) {
        squareAt = value;
    }

    public Square squareOf(String type) {
        return squareOf.squareOf(type);
    }

    public void squareOf(SquareOf value) {
        squareOf = value;
    }

    public int positionOf(Square square) {
        return positionOf.position(square);
    }

    public void positionOf(Position value) {
        positionOf = value;
    }

    public List<Square> squareList() {
        return squareList;
    }

    public Square square(int index) {
        return squareList.get(index);
    }

    @Override
    protected void add(Node component) {
        if (component.is("Square")) squareList.add(component.morph(Square.class));
    }

    @Override
    protected void set(String name, Object object) {
        if (name.equalsIgnoreCase("squareAt")) squareAt = (SquareAt) link((NativeCode) object);
        else if (name.equalsIgnoreCase("squareOf")) squareOf = (SquareOf) link((NativeCode) object);
        else if (name.equalsIgnoreCase("positionOf")) positionOf = (Position) link((NativeCode) object);
    }

    @Override
    public List<Node> components() {
        Set<Node> nodes = new HashSet<>();
        squareList.stream().forEach(c -> nodes.add(c.node()));
        return new ArrayList<>(nodes);
    }

    private static class SquareAt_meme implements SquareAt, NativeCode {

        Board $;

        @Override
        public Square squareAt(int position) {
            return $.square(position < 0 ? position + 40 : position % 40);
        }

        @Override
        public void set(Morph context) {
            $ = (Board) context;
        }

        @Override
        public Class<? extends Morph> $Class() {
            return Board.class;
        }
    }

    private static class SquareOf_meme implements SquareOf, NativeCode {

        Board $;

        @Override
        public Square squareOf(String type) {
            return $.squareList().stream().filter(s -> s.is(type)).findFirst().get();
        }

        @Override
        public void set(Morph context) {
            $ = (Board) context;
        }

        @Override
        public Class<? extends Morph> $Class() {
            return Board.class;
        }
    }

    private class Position_meme implements Position, NativeCode {
        Board $;

        @Override
        public int position(Square square) {
            return $.squareList().indexOf(square);
        }

        @Override
        public void set(Morph context) {
            $ = (Board) context;
        }

        @Override
        public Class<? extends Morph> $Class() {
            return Board.class;
        }
    }
}
