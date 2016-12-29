package io.intino.tara.magritte.loaders;

import io.intino.tara.magritte.NativeCode;

@SuppressWarnings("WeakerAccess")
public class NativeCodeLoader {

    @SuppressWarnings("unused")
    public static NativeCode nativeCodeOf(String className) {
        try {
            if (className == null) return null;
			return nativeCodeOf(ClassFinder.find(className));
		} catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static NativeCode nativeCodeOf(Class<?> nativeClass){
        try {
            return (NativeCode) nativeClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(""); //TODO
        }
    }


}
