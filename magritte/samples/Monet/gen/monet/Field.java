package monet;

import monet.natives.OnChange;
import magritte.wraps.Morph;

public class Field extends Morph {

    public String label() {
        return _definition()._get("label").asString();
    }

    public void onChange() {
        OnChange onChange = _definition()._get("onChange").asNative();
        onChange.execute();
    }

}
