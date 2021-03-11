package io.intino.magritte.io;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.DeflateSerializer;
import org.objenesis.strategy.StdInstantiatorStrategy;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ThreadSafeStashSerializer {
	private static final Map<String, Kryo> kryos = new ConcurrentHashMap<>();

	private ThreadSafeStashSerializer() {
	}

	public static byte[] serialize(Stash stash) {
		try {
			return doSerialize(stash);
		} catch (IOException e) {
			throw new StoreException(e.getMessage());
		}
	}

	private static byte[] doSerialize(Stash stash) throws IOException {
		String thread = Thread.currentThread().getName();
		if (kryos.size() > 1000) kryos.clear();
		if (!kryos.containsKey(thread)) kryos.put(thread, newDeserializer());
		Kryo kryo = kryos.get(thread);
		try (Output output = new Output(4096, -1)) {
			kryo.writeObject(output, stash);
			output.flush();
			output.close();
			return output.toBytes();
		}
	}

	private static Kryo newDeserializer() {
		Kryo kryo = new Kryo();
		kryo.setInstantiatorStrategy(new Kryo.DefaultInstantiatorStrategy(new StdInstantiatorStrategy()));
		kryo.register(Stash.class, new DeflateSerializer(kryo.getDefaultSerializer(Stash.class)));
		kryo.register(LocalDateTime.class, new LocalDateTimeSerializer());
		return kryo;
	}
}