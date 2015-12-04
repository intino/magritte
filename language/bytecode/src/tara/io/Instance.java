package tara.io;

import java.util.ArrayList;
import java.util.List;

public class Instance {
	public String name;
	public List<Layer> layers = new ArrayList<>();

    @Override
    public String toString() {
        return "Instance{" + name + '}';
    }
}
