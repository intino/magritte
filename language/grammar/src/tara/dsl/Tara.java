package tara.dsl;

import tara.Language;
import tara.lang.semantics.*;
import tara.lang.semantics.constraints.GlobalConstraints;

import java.util.*;
import java.util.stream.Collectors;

import static tara.Resolver.shortType;

public abstract class Tara implements Language {
	static final String Root = "";
	private Map<String, Context> rulesCatalog = new HashMap<>();
	private Map<String, DeclarationContext> declarationsCatalog = new HashMap<>();
	private List<String> lexicon = new ArrayList<>();

	protected RuleTransaction def(final String qualifiedName) {
		return context -> rulesCatalog.put(qualifiedName, context);
	}

	protected void declare(final String qualifiedName, List<String> types, String path) {
		declarationsCatalog.put(qualifiedName, new DeclarationContext(types, path));
	}

	protected Context context(String... types) {
		return new Context(types, new GlobalConstraints().all());
	}

	@Override
	public Map<String, Context> catalog() {
		return rulesCatalog;
	}

	@Override
	public List<Constraint> constraints(String qualifiedName) {
		if (qualifiedName == null || !rulesCatalog.containsKey(qualifiedName)) return null;
		return Collections.unmodifiableList(rulesCatalog.get(qualifiedName).constraints());
	}

	@Override
	public List<Assumption> assumptions(String qualifiedName) {
		if (qualifiedName == null || !rulesCatalog.containsKey(qualifiedName)) return null;
		return Collections.unmodifiableList(rulesCatalog.get(qualifiedName).assumptions());
	}

	@Override
	public Map<String, DeclarationContext> instances() {
		return Collections.unmodifiableMap(declarationsCatalog);
	}

	@Override
	public Documentation doc(String qualifiedName) {
		if (qualifiedName == null || !rulesCatalog.containsKey(qualifiedName)) return null;
		return rulesCatalog.get(qualifiedName).doc();
	}

	@Override
	public List<String> types(String qualifiedName) {
		if (qualifiedName == null || !rulesCatalog.containsKey(qualifiedName)) return null;
		return Arrays.asList(rulesCatalog.get(qualifiedName).types());
	}

	@Override
	public String[] lexicon() {
		if (!lexicon.isEmpty()) return lexicon.toArray(new String[lexicon.size()]);
		return calculateLexicon();
	}

	private String[] calculateLexicon() {
		lexicon.addAll(collectTokens());
		return lexicon.toArray(new String[lexicon.size()]);
	}

	private Collection<String> collectTokens() {
		final Set<String> collect = rulesCatalog.keySet().stream().
			filter(qn -> !shortType(qn).isEmpty()).map(t -> {
			final String shortType = shortType(t);
			return shortType.contains(":") ? shortType.substring(0, shortType.indexOf(":")) : shortType;
		}).collect(Collectors.toSet());
		for (Context context : rulesCatalog.values())
			collect.addAll(context.constraints().stream().filter(c -> c instanceof Constraint.Facet).map(c -> ((Constraint.Facet) c).type()).collect(Collectors.toSet()));
		return collect;
	}

	public interface RuleTransaction {
		Context with(Context context);
	}
}
