package tara.templates;

import org.siani.itrules.*;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.*;

public class NativeTemplate extends Template {

	protected NativeTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new NativeTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "native"))).add(literal("/**")).add(mark("qn")).add(literal("#")).add(mark("file")).add(literal("#")).add(mark("line")).add(literal("#")).add(mark("column")).add(literal("**/\npublic static class ")).add(mark("name", "javaValidName")).add(literal("_")).add(mark("uid")).add(literal(" implements ")).add(expression().add(mark("language", "lowercase")).add(literal(".natives."))).add(mark("contract", "firstUpperCase")).add(literal(", tara.magritte.NativeCode {\n\t")).add(mark("nativeContainer")).add(literal(" $;\n\n\t@Override\n\t")).add(mark("signature")).add(literal(" {\n\t\t")).add(mark("body")).add(literal("\n\t}\n\n\t@Override\n\tpublic void $(tara.magritte.Layer context) {\n\t\t$ = (")).add(mark("nativeContainer")).add(literal(") context;\n\t}\n\n\t@Override\n\tpublic Class<? extends tara.magritte.Layer> $Class() {\n\t\treturn ")).add(mark("nativeContainer")).add(literal(".class;\n\t}\n\n}"))
		);
		return this;
	}
}