package io.intino.magritte.io;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.serializers.DeflateSerializer;
import com.esotericsoftware.kryo.util.DefaultInstantiatorStrategy;
import com.esotericsoftware.minlog.Log;
import org.objenesis.strategy.StdInstantiatorStrategy;

import java.io.File;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class StashDeserializer extends Deserializer {
	private static final Kryo kryo = new Kryo();

	static {
		Log.ERROR();
		kryo.setRegistrationRequired(false);
		kryo.setInstantiatorStrategy(new DefaultInstantiatorStrategy(new StdInstantiatorStrategy()));
		kryo.register(Stash.class, new DeflateSerializer(kryo.getDefaultSerializer(Stash.class)));
		kryo.register(Node.class, new DeflateSerializer(kryo.getDefaultSerializer(Node.class)));
		kryo.register(Concept.class, new DeflateSerializer(kryo.getDefaultSerializer(Concept.class)));
		kryo.register(Variable.class, new DeflateSerializer(kryo.getDefaultSerializer(Variable.class)));
		kryo.register(LocalDateTime.class, new LocalDateTimeSerializer());
		kryo.register(ArrayList.class);
	}

	private StashDeserializer() {
	}

	public static Stash stashFrom(File file) {
		return stashFrom(bytesFrom(file));
	}

	public static Stash stashFrom(InputStream inputStream) {
		return stashFrom(bytesFrom(inputStream));
	}

	public static Stash stashFrom(byte[] bytes) {
		Stash result = null;
		try (Input input = new Input(bytes)) {
			result = kryo.readObject(input, Stash.class);
		} catch (Throwable e) {
			Log.error(e.getMessage());
		}
		return result;
	}
}