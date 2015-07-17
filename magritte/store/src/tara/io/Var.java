package tara.io;

import java.io.Serializable;

public class Var implements Serializable {
    public String n;
    public Object v;

    public Var() {
    }

    public Var(String n, Object v) {
        this.n = n;
        this.v = v;
    }
}
