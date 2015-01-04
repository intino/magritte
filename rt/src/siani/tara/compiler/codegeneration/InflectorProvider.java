package siani.tara.compiler.codegeneration;

import org.siani.itrules.formatter.Inflector;
import org.siani.itrules.formatter.inflectors.EnglishInflector;
import org.siani.itrules.formatter.inflectors.SpanishInflector;

import java.util.Locale;

public class InflectorProvider {

	public static Inflector getInflector() {
		return (Locale.getDefault().getCountry().equals("ES")) ? new SpanishInflector() : new EnglishInflector();
	}

	public static Inflector getInflector(Locale locale) {
		return (locale.getCountry().equals("ES")) ? new SpanishInflector() : new EnglishInflector();
	}
}
