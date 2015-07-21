package tara;

import tara.language.semantics.Allow;
import tara.language.semantics.Assumption;
import tara.language.semantics.Constraint;
import tara.language.semantics.Context;
import tara.language.semantics.Documentation;

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
