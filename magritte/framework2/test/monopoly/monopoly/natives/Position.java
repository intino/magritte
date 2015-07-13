package monopoly.monopoly.natives;

import monopoly.monopoly.Square;
import tara.magritte.NativeCode;

public interface Position extends NativeCode {

	int position(Square square);

}
