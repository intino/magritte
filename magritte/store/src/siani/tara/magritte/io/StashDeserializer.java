package siani.tara.magritte.io;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.io.Input;
import siani.tara.magritte.schema.Stash;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public abstract class StashDeserializer {

    public static Stash stashFrom(byte[] bytes) {
        Stash stash = new Stash();
        stash.root = doDeserialize(bytes);
        return stash;
    }

    private static Stash.Root doDeserialize(byte[] bytes)  {
        try (ByteArrayInputStream bs= new ByteArrayInputStream(bytes); Input input = new Input(bs)) {
            return ((Stash.Root) new Kryo().readClassAndObject(input));
        }
        catch (IOException | KryoException e) {
            return new Stash.Root();
        }
    }


}
