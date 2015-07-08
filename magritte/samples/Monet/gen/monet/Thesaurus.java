package monet;

import magritte.Set;
import magritte.wraps.Morph;

public class Thesaurus extends Entity {

    public Set<Term> termSet() {
        return _components(Term.class);
    }

    public Term term(int index) {
        return termSet().get(index);
    }

    public Term createTerm() {
        return _create(Term.class);
    }

    public void deleteTerm(Term term) {
        _delete(term);
    }

    public static class Term extends Morph {

        public int code() {
            return _get("code").asInteger();
        }

        public void code(int code) {
            _edit().set("code", code);
        }

        public String label() {
            return _get("label").asString();
        }
        public void label(String label) {
            _edit().set("label", label);
        }

        public Set<Term> termSet() {
            return _components(Term.class);
        }

        public Term term(int index) {
            return termSet().get(index);
        }

        public Term newTerm() {
            return _create(Term.class);
        }

        public void freeTerm(Term term) {
            _delete(term);
        }


    }

}
