package siani.tara.magritte.schema;

import siani.tara.magritte.Node;
import siani.tara.magritte.Reference;

import static siani.tara.magritte.Tag.*;

public abstract class GateReference implements Reference {

    public abstract String label();
    public abstract String spot();
    public abstract String tail();

    public static GateReference of(Node node) {
        return of(node,"");
    }

    public static GateReference of(Node node, String append) {
        return new GateReference() {

            @Override
            public String value() {
                return node.key() + append();
            }

            private String append() {
                return append.isEmpty() ? "" : "#" + append;
            }

            @Override
            public String label() {
                return node.type().title();
            }

            @Override
            public String spot() {
                Node spot = node;
                while (!spot.is(Root)) spot = spot.owner();
                return spot.type().key();
            }

            @Override
            public String tail() {
                return append.isEmpty() && typeHasKey() ? ":" + label() : "";
            }

            private boolean typeHasKey() {
                return !node.type().title().equals(node.type().key());
            }
        };
    }

    public static GateReference of(String uid) {
        return new GateReference() {
            @Override
            public String label() {
                return uid.contains(":") ? uid.substring(uid.indexOf(":")+1) : "";
            }

            @Override
            public String spot() {
                return uid.split("/")[2];
            }

            @Override
            public String tail() {
                String label = label();
                return label.isEmpty() ? "" : ":" + label;
            }

            @Override
            public String value() {
                return uid.split("/")[3].split(":")[0];
            }

        };
    }

    @Override
    public String toString() {
        return "//" + spot() + "/" + value() + tail();
    }

    @Override
    public boolean equals(Object o) {
        return o != null && o instanceof GateReference && equals((GateReference) o);
    }
    public boolean equals(GateReference uid) {
        return signature().equals(uid.signature());
    }

    private String signature() {
        return spot() + "." + value();
    }


    @Override
    public int hashCode() {
        return signature().hashCode();
    }
}
