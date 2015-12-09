package tara.dsl;

import tara.Language;
import tara.Resolver;
import tara.lang.semantics.*;
import tara.lang.semantics.constraints.GlobalConstraints;

import java.util.*;
import java.util.stream.Collectors;

public abstract class Tara implements Language {
	public static final String Root = "";
	protected Map<String, Context> rulesCatalog = new HashMap<>();
	protected Map<String, DeclarationContext> declarationsCatalog = new HashMap<>();
	protected List<String> lexicon = new ArrayList<>();

	protected RuleTransaction def(final String qualifiedName) {
		return context -> rulesCatalog.put(qualifiedName, context);
	}

	protected void declare(final String qualifiedName, List<String> types, String path) {
		declarationsCatalog.put(qualifiedName, new DeclarationContext(types, path));
	}

	protected Context context(String... types) {
		return new Context(types, new GlobalConstraints(rulesCatalog).all());
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
	public Map<String, DeclarationContext> declarations() {
		return Collections.unmodifiableMap(declarationsCatalog);
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

	public interface RuleTransaction {
		Context with(Context context);
	}
}
