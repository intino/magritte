package io.intino.magritte.builder.compiler.codegeneration.magritte;

import io.intino.tara.model.ElementContainer;
import io.intino.tara.model.Facet;
import io.intino.tara.model.Mogram;
import io.intino.tara.model.MogramReference;
import io.intino.tara.processors.model.Model;
import io.intino.tara.processors.model.MogramImpl;

import java.util.UUID;

import static io.intino.tara.builder.utils.Format.*;
import static io.intino.tara.model.Level.M1;

public class NameFormatter {
	public static final char DOT = '.';

	private NameFormatter() {
	}

	public static String getQn(Mogram mogram, String workingPackage) {
		return workingPackage.toLowerCase() + DOT + qualifiedName().format(layerQn(mogram)).toString();
	}

	public static String layerQn(Mogram mogram) {
		return mogram instanceof MogramReference r ?
				layerQualifiedName(r) :
				layerQualifiedName(mogram);
	}

	private static String layerQualifiedName(MogramReference r) {
		return null;
	}

	public static String layerQualifiedName(Mogram mogram) {
		ElementContainer container = mogram.container();
		String containerQn = container instanceof Model ? "" : layerQualifiedName((MogramImpl) container);
		String name = mogram.level().equals(M1) || mogram.isAnonymous() ? mogram.name() : firstUpperCase().format(mogram.name()).toString();
		return (containerQn.isEmpty() ? "" : containerQn + "$") + (name == null ? newUUID(mogram) : name);
	}

//	public String layerQn() {
//		String containerQn = container instanceof Model ? "" : ((MogramImpl) container).layerQn();
//		String name = is(Instance) || isAnonymous() ? name() : firstUpperCase().format(name()).toString();
//		if (name != null && is(Decorable)) name = "Abstract" + name;
//		return (containerQn.isEmpty() ? "" : containerQn + "$") + (name == null ? newUUID() : name);
//	}

	public static String newUUID(Mogram mogram) {
		String uid = UUID.randomUUID().toString();
		return mogram.level().equals(M1) ? uid : firstUpperCase().format(uid).toString();
	}

	public static String getQn(Facet facet, String workingPackage) {
		return workingPackage.toLowerCase() + DOT + javaValidName().format(facet.type());
	}

	public static String cleanQn(String qualifiedName) {
		return qualifiedName.replace(Mogram.ANONYMOUS, "")
				.replace("[", "")
				.replace("]", "")
				.replace(":", "")
				.replace("$", ".");
	}
}