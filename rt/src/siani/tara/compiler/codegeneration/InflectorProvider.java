package siani.tara.compiler.codegeneration;

import org.siani.itrules.formatter.Inflector;
import org.siani.itrules.formatter.inflectors.EnglishInflector;
import org.siani.itrules.formatter.inflectors.SpanishInflector;

import java.util.Locale;

public class InflectorProvider {

	private InflectorProvider() {
	}

	public static Inflector getInflector(Locale locale) {
		return "ES".equals(locale.getCountry()) ? new SpanishInflector() : new EnglishInflector();
	}
}
