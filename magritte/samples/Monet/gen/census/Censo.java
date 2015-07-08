package census;

import magritte.Set;
import magritte.wraps.Morph;

public class Censo extends Morph {

    public Set<Registro> setRegistro() {
        return _components(Registro.class);
    }

    public Registro registro(int index) {
        return setRegistro().get(index);
    }

    public Registro createRegistro() {
        return super._create(Registro.class);
    }

    public void deleteRegistro(Registro registro) {
        super._delete(registro);
    }

    public static class Registro extends Morph {

        public Animal entity() {
            return _get("entity").as(Animal.class);
        }

        public void entity(Animal animal) {
            _edit().set("entity", animal);
        }

    }
}
