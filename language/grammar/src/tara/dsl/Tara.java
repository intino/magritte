package tara.dsl;

import tara.Language;
import tara.language.semantics.*;
import tara.language.semantics.constraints.GlobalConstraints;

import java.util.*;
import java.util.stream.Collectors;

public abstract class Tara implements Language {
	public static final String Root = "";
	protected Map<String, Context> rulesCatalog = new HashMap<>();
	protected List<String> lexicon = new ArrayList<>();

	protected Context context(String... type) {
		return new Context(type, new GlobalConstraints(rulesCatalog).all());
	}

	protected Transaction in(final String qualifiedName) {
		return context -> {
			context.commit();
			rulesCatalog.put(qualifiedName, context);
		};
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
	public List<Allow> allows(String qualifiedName) {
		if (!rulesCatalog.containsKey(qualifiedName)) return null;
		return Collections.unmodifiableList(rulesCatalog.get(qualifiedName).allows());
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
			filter(qn -> !shortType(qn).isEmpty()).map(this::shortType).collect(Collectors.toList()));
		return lexicon.toArray(new String[lexicon.size()]);
	}

	private String shortType(String qn) {
		return !qn.contains(".") ? qn : qn.substring(qn.lastIndexOf(".") + 1);
	}

	public interface Transaction {
		void def(Context context);
	}
}
