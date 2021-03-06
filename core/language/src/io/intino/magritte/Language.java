package io.intino.magritte;

import io.intino.magritte.lang.semantics.*;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public interface Language {

	String[] lexicon();

	Map<String, Context> catalog();

	Map<String, InstanceContext> instances();

	List<Constraint> constraints(String qualifiedName);

	List<Assumption> assumptions(String qualifiedName);

	Documentation doc(String qualifiedName);

	List<String> types(String qualifiedName);

	String languageName();

	Locale locale();

	boolean isTerminalLanguage();

	String metaLanguage();
}
