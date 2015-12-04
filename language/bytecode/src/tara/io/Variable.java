package tara.io;

import java.util.ArrayList;
import java.util.List;

public abstract class Variable  {

    public String name;

    public static class Integer extends Variable{

        public List<java.lang.Integer> values = new ArrayList<>();

    }

    public static class Double extends Variable{

        public List<java.lang.Double> values = new ArrayList<>();

    }

    public static class Boolean extends Variable{

        public List<java.lang.Boolean> values = new ArrayList<>();

    }

    public static class String extends Variable{

        public List<java.lang.String> values = new ArrayList<>();

    }

    public static class File extends String{
    }

    public static class Reference extends String{
    }

    public static class Word extends String{
    }

    public static class Function extends String{
    }

    public static class Date extends String{
    }

    public static class Time extends String{
    }
}
