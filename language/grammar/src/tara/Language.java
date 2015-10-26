package tara;

import tara.lang.semantics.Allow;
import tara.lang.semantics.Assumption;
import tara.lang.semantics.Constraint;
import tara.lang.semantics.Context;
import tara.lang.semantics.Documentation;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public interface Language {

	String[] lexicon();

	Map<String, Context> catalog();

	List<Constraint> constraints(String qualifiedName);

	List<Assumption> assumptions(String qualifiedName);

	List<Allow> allows(String qualifiedName);

	Documentation doc(String qualifiedName);

	List<String> types(String qualifiedName);

	String languageName();

	Locale locale();

	boolean isTerminalLanguage();

}
