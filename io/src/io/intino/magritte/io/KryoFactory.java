package io.intino.magritte.io;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.util.DefaultInstantiatorStrategy;
import com.esotericsoftware.kryo.util.Pool;
import com.esotericsoftware.minlog.Log;
import org.objenesis.strategy.StdInstantiatorStrategy;

class KryoFactory {
	private static final Pool<Kryo> kryoPool = new Pool<>(true, false, 8) {
		protected Kryo create() {
			return KryoFactory.create();
		}
	};

	public static Kryo get() {
		return kryoPool.obtain();
	}

	private static Kryo create() {
		Kryo kryo = new Kryo();
		Log.ERROR();
		kryo.setRegistrationRequired(false);
		kryo.setInstantiatorStrategy(new DefaultInstantiatorStrategy(new StdInstantiatorStrategy()));
		return kryo;
	}
}
