package tara.io;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.serializers.DeflateSerializer;

import java.io.File;
import java.io.InputStream;
import java.util.logging.Logger;

public class StashDeserializer extends Deserializer {

	private static final Logger LOG = Logger.getLogger(StashDeserializer.class.getName());

	private StashDeserializer() {
	}

	public static Stash stashFrom(File file) {
		return stashFrom(bytesFrom(file));
	}

	public static Stash stashFrom(InputStream inputStream) {
		return stashFrom(bytesFrom(inputStream));
	}

	private static Stash stashFrom(byte[] bytes) {
		Stash result = null;
		try (Input input = new Input(bytes)) {
			final Kryo kryo = new Kryo();
			kryo.register(Stash.class, new DeflateSerializer(kryo.getDefaultSerializer(Stash.class)));
			kryo.register(Stash.class, 1);
			kryo.register(Instance.class, 2);
			result = kryo.readObject(input, Stash.class);
		} catch (KryoException e) {
			LOG.severe(e.getMessage());
		}
		return result;
	}

}
