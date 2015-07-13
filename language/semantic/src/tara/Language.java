package tara;

import tara.semantic.Allow;
import tara.semantic.Assumption;
import tara.semantic.Constraint;
import tara.semantic.model.Context;
import tara.semantic.model.Documentation;

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
