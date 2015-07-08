package magritte.handlers;

import magritte.NativeCode;
import magritte.Node;

import static magritte.helpers.Check.check;
import static magritte.helpers.Extract.nameOf;

public abstract class NativeCodeHandler<T extends NativeCode> {
    public abstract T on(Node node);

    public static <T extends NativeCode> NativeCodeHandler<T> init(final T prototype) {
        return new NativeCodeHandler<T>() {
            @Override
            public T on(Node node) {
                try {
                    return createOn(targetFor(node, classOf(prototype)));
                } catch (CloneNotSupportedException e) {
                    return null;
                }
            }

            private T createOn(Node target) throws CloneNotSupportedException {
                T result = (T) prototype.clone();
                result._target(target);
                return result;
            }
        };

    }

    private static <T extends NativeCode> Class<?> classOf(T prototype) {
        return prototype.getClass().getSuperclass();
    }

    public static Node targetFor(Node node, Class<?> wrapClass) {
        return targetFor(node, nameOf(wrapClass));
    }

    private static Node targetFor(Node node, String wrap) {
        while (node != null) {
            if (check(node, wrap)) return node;
            node = node.owner();
        }
        return null;
    }


}
