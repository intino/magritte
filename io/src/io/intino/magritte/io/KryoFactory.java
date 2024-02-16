package io.intino.magritte.io;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.serializers.DeflateSerializer;
import com.esotericsoftware.kryo.util.DefaultInstantiatorStrategy;
import com.esotericsoftware.kryo.util.Pool;
import com.esotericsoftware.minlog.Log;
import io.intino.magritte.io.model.Concept;
import io.intino.magritte.io.model.Node;
import io.intino.magritte.io.model.Stash;
import io.intino.magritte.io.model.Variable;
import org.objenesis.strategy.StdInstantiatorStrategy;

import java.time.LocalDateTime;
import java.util.ArrayList;

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
		kryo.register(Stash.class, new DeflateSerializer(kryo.getDefaultSerializer(Stash.class)));
		kryo.register(Node.class, new DeflateSerializer(kryo.getDefaultSerializer(Node.class)));
		kryo.register(Concept.class, new DeflateSerializer(kryo.getDefaultSerializer(Concept.class)));
		kryo.register(Variable.class, new DeflateSerializer(kryo.getDefaultSerializer(Variable.class)));
		kryo.register(LocalDateTime.class, new LocalDateTimeSerializer());
		kryo.register(ArrayList.class);
		return kryo;
	}
}