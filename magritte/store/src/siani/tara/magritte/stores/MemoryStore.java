package siani.tara.magritte.stores;

import siani.tara.magritte.Store;
import siani.tara.magritte.Source;
import siani.tara.magritte.Reference;

import java.util.HashMap;
import java.util.Map;

public class MemoryStore implements Store {
    private Map<Reference, byte[]> map = new HashMap<>();

    @Override
    public boolean exists(Reference reference) {
        return map.containsKey(reference);
    }

    @Override
    public Source sourceOf(Reference reference) {
        return new Source() {
            @Override
            public Reference uid() {
                return reference;
            }

            @Override
            public byte[] data() {
                return map.get(reference);
            }
        };
    }

    @Override
    public void save(Reference reference, byte[] data) {
        map.put(reference, data);
    }
}
