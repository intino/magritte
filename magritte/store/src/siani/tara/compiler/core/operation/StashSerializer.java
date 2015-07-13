package siani.tara.compiler.core.operation;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import siani.tara.builder.StashException;
import siani.tara.compiler.model.impl.Stash;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

class StashSerializer {

	public static byte[] serialize(Stash stash) {
		try {
			return doSerialize(stash);
		} catch (IOException e) {
			throw new StashException(e.getMessage());
		}
	}

	private static byte[] doSerialize(Stash stash) throws IOException {
		try (Output output = new Output(new ByteArrayOutputStream())) {
			Kryo kryo = new Kryo();
			kryo.writeClassAndObject(output, stash);
			return output.toBytes();
		}
	}


}
