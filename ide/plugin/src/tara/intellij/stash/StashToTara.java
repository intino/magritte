package tara.intellij.stash;

import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import tara.io.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

public class StashToTara {

	private StringBuilder builder = new StringBuilder();

	public static Path createTara(VirtualFile stash, File destiny) throws IOException {
		destiny.deleteOnExit();
		final String json = taraFrom(StashDeserializer.stashFrom(new File(stash.getPath())));
		Files.write(destiny.toPath(), json.getBytes());
		return destiny.toPath();
	}

	public static String taraFrom(Stash stash) {
		return new StashToTara().execute(stash);
	}

	private String execute(Stash stash) {
		writeDsl(stash);
		writeContentRules(stash.contentRules, -1, stash.concepts);
		writeComponentConceptsDefinedAsMain(stash, -1);
		writeComponents(stash.instances, -1);
		return builder.toString();
	}

	private void writeComponentConceptsDefinedAsMain(Stash stash, int level) {
		List<String> mainTypes = stash.contentRules.stream().map(r -> r.type).collect(toList());
		writeContentRules(stash.concepts.stream()
			.filter(c -> !c.name.contains("$"))
			.filter(c -> !mainTypes.contains(c.name))
			.map(c -> new Concept.Content(c.name, 0, 0)).collect(toList()), level, stash.concepts);
	}

	private void writeContentRules(List<Concept.Content> contentRules, int level, List<Concept> directory) {
		contentRules.forEach(c -> writeConcept(c, level + 1, directory));
	}

	private void writeConcept(Concept.Content contentRules, int level, List<Concept> directory) {
		newLine(level);
		writeHeader(contentRules, conceptOf(contentRules.type, directory));
		writeVariables(conceptOf(contentRules.type, directory).variables, level);
		writeContentRules(conceptOf(contentRules.type, directory), level, directory);
		writeComponents(conceptOf(contentRules.type, directory).instances, level);
		writeComponents(conceptOf(contentRules.type, directory).prototypes, level);
		if (level == 0) newLine(0);
	}

	private void writeContentRules(Concept concept, int level, List<Concept> directory) {
		concept.contentRules.stream()
			.filter(r -> !r.type.startsWith(concept.name + "$"))
			.forEach(r -> {
				newLine(level + 1);
				write("has", cardinalityOf(r), r.type);
			});
		writeContentRules(concept.contentRules.stream()
			.filter(r -> r.type.startsWith(concept.name) && !r.type.equals(concept.name)).collect(toList()), level, directory);
	}

	private void writeHeader(Concept.Content rule, Concept concept) {
		write(coreType(concept), cardinalityOf(rule), simpleName(concept.name));
		if (concept.types.size() > 1) {
			write(" > ");
			range(1, concept.types.size()).forEach(i -> write(concept.types.get(i), ";"));
		}
	}

	private String coreType(Concept concept) {
		return concept.types.get(0).startsWith("MetaFacet") ? "MetaFacet" :
			concept.types.get(0).startsWith("Facet") ? "Facet" : concept.types.get(0);
	}

	private void writeComponents(List<? extends Instance> instances, int level) {
		instances.forEach(i -> writeInstance(i, level + 1));
	}

	private void writeDsl(Stash stash) {
		write("dsl ", stash.language);
		newLine(0);
	}

	private void writeInstance(Instance instance, int level) {
		newLine(level);
		writeCore(instance, level);
		writeFacets(instance, level);
		if (level == 0) newLine(0);
	}

	private void newLine(int level) {
		addNewLine();
		addTabs(level);
	}

	private void writeCore(Instance instance, int level) {
		Facet core = instance.facets.get(0);
		write(core.name, " ", simpleName(instance.name));
		writeVariables(core.variables, level);
		writeComponents(core.instances, level);
	}

	private void writeFacets(Instance instance, int level) {
		range(1, instance.facets.size()).forEach(i -> writeFacet(instance.facets.get(i), level + 1));
	}

	private void writeFacet(Facet facet, int level) {
		newLine(level);
		write("as ", facet.name);
		writeVariables(facet.variables, level);
		writeComponents(facet.instances, level);
	}

	private void writeVariables(List<Variable> variables, int level) {
		variables.forEach(v -> {
			addNewLine();
			addTabs(level + 1);
			write(v);
		});
	}

	private void write(Variable variable) {
		write(variable.name, " = ");
		if (variable instanceof Variable.Integer) format(variable);
		if (variable instanceof Variable.Double) format(variable);
		if (variable instanceof Variable.Boolean) format(variable);
		if (variable instanceof Variable.String) formatWithQuotes(variable);
		if (variable instanceof Variable.Resource) formatWithQuotes(variable);
		if (variable instanceof Variable.Reference) format(variable);
		if (variable instanceof Variable.Word) format(variable);
		if (variable instanceof Variable.Function) format(variable);
		if (variable instanceof Variable.Date) formatWithQuotes(variable);
		if (variable instanceof Variable.Time) formatWithQuotes(variable);
	}

	private void format(Variable variable) {
		variable.values.stream().forEach(v -> write(v, " "));
	}

	private void formatWithQuotes(Variable variable) {
		variable.values.stream().forEach(v -> write("\"", v, "\" "));
	}

	private void addNewLine() {
		write("\n");
	}

	private void addTabs(int level) {
		range(0, level).forEach(i -> write("\t"));
	}

	private String simpleName(String name) {
		String shortName = name.contains(".") ? name.substring(name.lastIndexOf(".") + 1) : name;
		shortName = shortName.contains("#") ? shortName.substring(shortName.lastIndexOf("#") + 1) : shortName;
		shortName = shortName.contains("$") ? shortName.substring(shortName.lastIndexOf("$") + 1) : shortName;
		return shortName;
	}

	@NotNull
	private String cardinalityOf(Concept.Content rules) {
		return ":{" + rules.min + ".." + (rules.max == Integer.MAX_VALUE ? "*" : rules.max) + "} ";
	}

	public void write(Object... content) {
		for (Object o : content) builder.append(o);
	}

	private Concept conceptOf(String type, List<Concept> directory) {
		return directory.stream().filter(c -> c.name.equals(type)).findFirst().get();
	}
}
