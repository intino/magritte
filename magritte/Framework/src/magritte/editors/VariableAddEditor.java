package magritte.editors;

import magritte.*;

import java.util.Arrays;

public class VariableAddEditor extends VariableEditor {

    public VariableAddEditor(Node node) {
        super(node);
    }

    @Override
    protected int[] array(String var, int[] values) {
        return merge(node.get(var), values);
    }

    @Override
   protected double[] array(String var, double[] values) {
        return merge(node.get(var), values);
    }

    @Override
    protected boolean[] array(String var, boolean[] values) {
        return merge(node.get(var), values);
    }

    @Override
    protected <T> T[] array(String var, T[] values) {
        return merge(node.get(var), values);
    }

    private static int[] merge(int[] head, int[] tail) {
        int[] result = Arrays.copyOf(head, head.length + tail.length);
        for (int i = head.length, j = 0; j < tail.length; i++, j++) result[i] = tail[j];
        return result;
    }
    
    private static double[] merge(double[] head, double[] tail) {
        double[] result = Arrays.copyOf(head, head.length + tail.length);
        for (int i = head.length, j = 0; j < tail.length; i++, j++) result[i] = tail[j];
        return result;
    }
    
    private static boolean[] merge(boolean[] head, boolean[] tail) {
        boolean[] result = Arrays.copyOf(head, head.length + tail.length);
        for (int i = head.length, j = 0; j < tail.length; i++, j++) result[i] = tail[j];
        return result;
    }

    private static <T> T[] merge(T[] head, T[] tail) {
        T[] result = Arrays.copyOf(head, head.length + tail.length);
        for (int i = head.length, j = 0; j < tail.length; i++, j++) result[i] = tail[j];
        return result;
    }

}
