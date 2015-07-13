package tafat.intentions;

import tara.magritte.NativeCode;

public class Behavior {

	public interface Action extends NativeCode {
		void tick();
	}


}
