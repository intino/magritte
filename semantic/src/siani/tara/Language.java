package siani.tara;

import siani.tara.semantic.Allow;
import siani.tara.semantic.Assumption;
import siani.tara.semantic.Constraint;
import siani.tara.semantic.model.Context;

import java.util.Collection;
import java.util.Locale;
import java.util.Map;

public interface Language {

	String[] lexicon();

	Map<String, Context> catalog();

	Collection<Constraint> constraints(String qualifiedName);

	Collection<Assumption> assumptions(String qualifiedName);

	Collection<Allow> allows(String qualifiedName);

	Collection<String> types(String qualifiedName);

	String languageName();

	Locale locale();

	boolean isTerminalLanguage();

}
