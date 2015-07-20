package tara.compiler.core.operation;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.DeflateSerializer;
import tara.builder.StashException;
import tara.io.Entry;
import tara.io.Stash;
import tara.io.Variable;

import java.io.IOException;

class StashSerializer {

	public static byte[] serialize(Stash stash) {
		try {
			final byte[] bytes = doSerialize(stash);
			int i = 0;
//			for (byte b : bytes) {
//				System.out.print((char) b);
//				if (i++ > 1024) break;
//			}
			return bytes;
		} catch (IOException e) {
			throw new StashException(e.getMessage());
		}
	}

	private static byte[] doSerialize(Stash stash) throws IOException {
		try (Output output = new Output(4096, -1)) {
			Kryo kryo = new Kryo();
			kryo.register(Stash.class, new DeflateSerializer(kryo.getDefaultSerializer(Stash.class)));
			kryo.register(Stash.class, 1);
			kryo.register(Entry.class, 2);
			kryo.register(Entry[].class, 3);
			kryo.register(Variable.class, 4);
			kryo.register(Variable[].class, 5);
			kryo.writeObject(output, stash);
			output.flush();
			return output.toBytes();
		}
	}


}
