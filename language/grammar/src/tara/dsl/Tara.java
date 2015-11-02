package tara.dsl;

import tara.Language;
import tara.Resolver;
import tara.lang.semantics.Assumption;
import tara.lang.semantics.Constraint;
import tara.lang.semantics.Context;
import tara.lang.semantics.Documentation;
import tara.lang.semantics.constraints.GlobalConstraints;

import java.util.*;
import java.util.stream.Collectors;

public abstract class Tara implements Language {
	public static final String Root = "";
	protected Map<String, Context> rulesCatalog = new HashMap<>();
	protected List<String> lexicon = new ArrayList<>();

	protected Transaction in(final String qualifiedName) {
		return context -> rulesCatalog.put(qualifiedName, context);
	}

	protected Context context(String... type) {
		return new Context(type, new GlobalConstraints(rulesCatalog).all());
	}

	@Override
	public Map<String, Context> catalog() {
		return rulesCatalog;
	}

	@Override
	public List<Constraint> constraints(String qualifiedName) {
		if (!rulesCatalog.containsKey(qualifiedName)) return null;
		return Collections.unmodifiableList(rulesCatalog.get(qualifiedName).constraints());
	}

	@Override
	public List<Assumption> assumptions(String qualifiedName) {
		if (!rulesCatalog.containsKey(qualifiedName)) return null;
		return Collections.unmodifiableList(rulesCatalog.get(qualifiedName).assumptions());
	}

	@Override
	public Documentation doc(String qualifiedName) {
		if (!rulesCatalog.containsKey(qualifiedName)) return null;
		return rulesCatalog.get(qualifiedName).doc();
	}

	@Override
	public List<String> types(String qualifiedName) {
		if (!rulesCatalog.containsKey(qualifiedName)) return null;
		return Arrays.asList(rulesCatalog.get(qualifiedName).types());
	}

	@Override
	public String[] lexicon() {
		if (!lexicon.isEmpty()) return lexicon.toArray(new String[lexicon.size()]);
		return calculateLexicon();
	}

	private String[] calculateLexicon() {
		lexicon.addAll(rulesCatalog.keySet().stream().
			filter(qn -> !Resolver.shortType(qn).isEmpty()).map(Resolver::shortType).collect(Collectors.toList()));
		return lexicon.toArray(new String[lexicon.size()]);
	}

	public interface Transaction {
		void def(Context context);
	}
}
