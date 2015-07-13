package monopoly.monopoly;

import monopoly.monopoly.natives.Position;
import monopoly.monopoly.natives.SquareAt;
import monopoly.monopoly.natives.SquareOf;
import siani.tara.magritte.Morph;
import siani.tara.magritte.NativeCode;
import siani.tara.magritte.Node;

import java.util.ArrayList;
import java.util.List;


public class Board extends Morph {

	protected SquareAt squareAt = new SquareAt_meme();
	protected SquareOf squareOf = new SquareOf_meme();
	protected Position positionOf = new Position_meme();

	protected ArrayList<Square> squares = new ArrayList<>(0);

	public Board(Node node) {
		super(node);
	}

	public Square squareAt(int position) {
		return squareAt.squareAt(position);
	}

	public Square squareOf(Class<? extends Square> aClass) {
		return squareOf.squareOf(aClass);
	}

	public int positionOf(Square square) {
		return positionOf.position(square);
	}

	public void squareAt(SquareAt value) {
		squareAt = value;
	}

	public void squareOf(SquareOf value) {
		squareOf = value;
	}

	public void positionOf(Position value) {
		positionOf = value;
	}

	public List<Square> squares() {
		return squares;
	}

	public Square square(int index) {
		return squares().get(index);
	}

	@Override
	protected void add(Node component) {
		if (component.is(Square.class)) squares.add(component.morph(Square.class));
	}

	@Override
	protected void set(String name, Object object) {
		// TODO como no se setean no est� hecho
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
		public Square squareOf(Class<? extends Square> aClass) {
			return $.squares().stream().filter(s -> s.is(aClass.getSimpleName())).findFirst().get();
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
			return $.squares().indexOf(square);
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
