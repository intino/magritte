package tafat.intentions;

import magritte.NativeCode;

public class Behavior {

	public interface Action extends NativeCode {
		public void tick();
	}


}
