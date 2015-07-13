package bazar;

import siani.tara.magritte.wraps.Morph;

public class Customer extends Person {

	@Override
	public <T extends Morph> T _create(Class<T> morphClass) {
		return super._create(morphClass);
	}

}
