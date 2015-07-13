package siani.tara.magritte.io;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import siani.tara.magritte.schema.Stash;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class StashSerializer {

    public static byte[] serialize(Stash.Root root) {
        try {
            return doSerialize(root);
        } catch (IOException e) {
            throw new StashException(e.getMessage());
        }
    }

    private static byte[] doSerialize(Stash.Root root) throws IOException {
        try (Output output = new Output(new ByteArrayOutputStream())) {
            Kryo kryo = new Kryo();
            kryo.writeClassAndObject(output, root);
            return output.toBytes();
        }
    }




}
