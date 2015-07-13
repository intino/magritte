package monopoly.monopoly.natives;

import monopoly.monopoly.Square;
import siani.tara.magritte.NativeCode;

public interface SquareOf extends NativeCode {

	Square squareOf(Class<? extends Square> aClass);

}
