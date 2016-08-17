package tara.io.refactor;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.serializers.DeflateSerializer;
import tara.io.Deserializer;

import java.io.File;
import java.io.InputStream;
import java.util.logging.Logger;

public class RefactorsDeserializer extends Deserializer {

	private static final Logger LOG = Logger.getLogger(RefactorsDeserializer.class.getName());

	private RefactorsDeserializer() {
	}

	public static Refactors refactorFrom(File file) {
		return refactorFrom(bytesFrom(file));
	}

	public static Refactors refactorFrom(InputStream inputStream) {
		return refactorFrom(bytesFrom(inputStream));
	}

	private static Refactors refactorFrom(byte[] bytes) {
		Refactors result = null;
		try (Input input = new Input(bytes)) {
			final Kryo kryo = new Kryo();
			kryo.register(Refactors.class, new DeflateSerializer(kryo.getDefaultSerializer(Refactors.class)));
			kryo.register(Refactors.Refactor.class, 1);
			result = kryo.readObject(input, Refactors.class);
		} catch (KryoException e) {
			LOG.severe(e.getMessage());
		}
		return result;
	}

}
