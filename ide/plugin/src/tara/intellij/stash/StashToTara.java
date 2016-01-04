package tara.intellij.stash;

import com.intellij.openapi.vfs.VirtualFile;
import tara.io.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

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
		writeComponents(stash.instances, -1);
		return builder.toString();
	}

	private void writeDsl(Stash stash) {
		builder.append("dsl ").append(stash.language);
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
		builder.append(core.name).append(" ").append(simpleName(instance.name));
		writeVariables(core.variables, level);
		writeComponents(core.instances, level);
	}

	private void writeFacets(Instance instance, int level) {
		range(1, instance.facets.size()).forEach(i -> writeFacet(instance.facets.get(i), level + 1));
	}

	private void writeFacet(Facet facet, int level) {
		newLine(level);
		builder.append("as ").append(facet.name);
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
		builder.append(variable.name).append(" = ");
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
		variable.values.stream().forEach(v -> builder.append(v).append(" "));
	}

	private void formatWithQuotes(Variable variable) {
		variable.values.stream().forEach(v -> builder.append("\"").append(v).append("\" "));
	}

	private String format(Object values) {
		return values.toString().replace("[", "").replace("]", "").replace(",", " ");
	}

	private void writeComponents(List<Instance> instances, int level) {
		instances.forEach(i -> writeInstance(i, level + 1));
	}

	private void addNewLine() {
		builder.append("\n");
	}

	private void addTabs(int level) {
		range(0, level).forEach(i -> builder.append("\t"));
	}

	public String simpleName(String name) {
		String shortName = name.contains(".") ? name.substring(name.lastIndexOf(".") + 1) : name;
		shortName = shortName.contains("#") ? shortName.substring(shortName.lastIndexOf("#") + 1) : shortName;
		shortName = shortName.contains("$") ? shortName.substring(shortName.lastIndexOf("$") + 1) : shortName;
		return shortName;
	}
}
