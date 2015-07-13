package siani.tara.magritte.schema;

import siani.tara.magritte.primitives.Resource;

import java.io.Serializable;

public class Stash {

    public Root root;
    public Resource[] resources;

    public abstract static class Content implements Serializable {

        @Override
        @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
        public boolean equals(Object o) {
            return o != null && o.toString().equals(toString());
        }

        protected String valid(String name) {
            return null == name ? "?" : name;
        }
    }

    public static class Pass extends Content {
        public String uid;
        public String type;

        @Override
        public String toString() {
            return "pass[" + uid + "]";
        }
    }

    public static class Case extends Content {
        public String name;
        public String[] types;
        public Variable[] variables;
        public Case[] components;
        public Pass[] fanIn;

        @Override
        public String toString() {
            return "case[" + valid(name) + ":" + titleOf(types[0]) + "]";
        }

    }

    public static class Root extends Case {
        public Pass[] fanOut;
    }

    public static class Variable implements Serializable {
        public String name;
        public Object value;

        @Override
        public String toString() {
            return "var[" + name + "=" + value + "]";
        }

    }

    public static class Blob implements Serializable {
        public String uid;

        @Override
        public String toString() {
            return "blob[" + uid + "]";
        }
    }

    @Override
    public String toString() {
        return "stash" + "[" + titleOf(root.name) + ":" + titleOf(root.types[0]) + "]";
    }

    public static String titleOf(String name) {
        String[] keys = name.split("\\|");
        return keys[0].isEmpty() ? "?" : keys[0];
    }


}
