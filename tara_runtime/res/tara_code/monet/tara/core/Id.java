package tara_code.monet.tara.core;

public class Id {
    String code;
    String name;

    public Id(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Id(String name) {
        this("", name);
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return (name + code).hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null)
            return false;
        if (object instanceof String)
            return equals((String) object);
        if (object instanceof Id)
            return equals((Id) object);
        return false;
    }

    public boolean equals(String id) {
        return id.equalsIgnoreCase(code) || id.equalsIgnoreCase(name);
    }

    public boolean equals(Id id) {
        return code.equalsIgnoreCase(id.code) && name.equalsIgnoreCase(id.name);
    }

}
