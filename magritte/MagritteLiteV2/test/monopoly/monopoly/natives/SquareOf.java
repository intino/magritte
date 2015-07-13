package monopoly.monopoly.natives;

import monopoly.monopoly.Square;
import wata._magritte.lite.NativeCode;

public interface SquareOf extends NativeCode {

	Square squareOf(Class<? extends Square> aClass);

}
