package tafat.intentions;

import siani.tara.magritte.NativeCode;

public class Behavior {

	public interface Action extends NativeCode {
		public void tick();
	}


}
