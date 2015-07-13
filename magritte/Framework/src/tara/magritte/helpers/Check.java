package tara.magritte.helpers;

import tara.magritte.Node;
import tara.magritte.Set;

import java.util.HashMap;
import java.util.Map;


public abstract class Check {

    public static Map<String, Boolean> map = new HashMap<>();

    public static boolean check(final Node node, String type) {
        if (type.equals(node.title())) return true;
        Set<Node> types = node.types();
        for (Node nodeType : types)
            if (doCheck(nodeType, type)) return true;
        return false;
    }

    private static boolean doCheck(Node node, String type) {
        if (type.equals("Concept") || node == null) return false;
        String signature = node.title() + "/" + type;
        if (!map.containsKey(signature))
            map.put(signature, doCheck(node.parent(), type) || check(node, type));
        return map.get(signature);
    }

}

