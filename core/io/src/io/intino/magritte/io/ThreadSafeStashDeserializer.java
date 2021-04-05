package io.intino.magritte.io;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.serializers.DeflateSerializer;
import com.esotericsoftware.minlog.Log;
import org.objenesis.strategy.StdInstantiatorStrategy;

import java.io.File;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ThreadSafeStashDeserializer extends Deserializer {
	private static final Map<String, Kryo> kryos = new ConcurrentHashMap<>();

	static {
		Log.ERROR();
	}

	private ThreadSafeStashDeserializer() {
	}

	public static Stash stashFrom(File file) {
		return stashFrom(bytesFrom(file));
	}

	public static Stash stashFrom(InputStream inputStream) {
		return stashFrom(bytesFrom(inputStream));
	}

	public static Stash stashFrom(byte[] bytes) {
		String thread = Thread.currentThread().getName();
		if (!kryos.containsKey(thread)) {
			if (kryos.size() > 1000) kryos.clear();
			kryos.put(thread, newDeserializer());
		}
		Kryo kryo = kryos.get(thread);
		Stash result = null;
		try (Input input = new Input(bytes)) {
			result = kryo.readObject(input, Stash.class);
		} catch (Throwable e) {
			Log.error(e.getMessage());
		}
		return result;
	}

	private static Kryo newDeserializer() {
		Kryo kryo = new Kryo();
		kryo.setInstantiatorStrategy(new Kryo.DefaultInstantiatorStrategy(new StdInstantiatorStrategy()));
		kryo.register(Stash.class, new DeflateSerializer(kryo.getDefaultSerializer(Stash.class)));
		kryo.register(LocalDateTime.class, new LocalDateTimeSerializer());
		return kryo;
	}
}