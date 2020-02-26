package io.intino.magritte.io;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.serializers.DeflateSerializer;
import com.esotericsoftware.minlog.Log;
import org.objenesis.strategy.StdInstantiatorStrategy;

import java.io.File;
import java.io.InputStream;
import java.time.LocalDateTime;

public class StashDeserializer extends Deserializer {


	static {
		Log.ERROR();
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
			final Kryo kryo = new Kryo();
			kryo.setInstantiatorStrategy(new Kryo.DefaultInstantiatorStrategy(new StdInstantiatorStrategy()));
			kryo.register(Stash.class, new DeflateSerializer(kryo.getDefaultSerializer(Stash.class)));
			kryo.register(LocalDateTime.class, new LocalDateTimeSerializer());
			result = kryo.readObject(input, Stash.class);
		} catch (Throwable e) {
			Log.error(e.getMessage());
		}
		return result;
	}
}
