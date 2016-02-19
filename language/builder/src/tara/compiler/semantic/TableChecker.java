package tara.compiler.semantic;

import com.opencsv.CSVReader;
import tara.Language;
import tara.compiler.model.NodeImpl;
import tara.compiler.model.Table;
import tara.lang.model.Node;
import tara.lang.model.Primitive;
import tara.lang.semantics.Constraint.Parameter;
import tara.lang.semantics.errorcollector.SemanticFatalException;
import tara.lang.semantics.errorcollector.SemanticNotification;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static tara.lang.semantics.errorcollector.SemanticNotification.ERROR;

public class TableChecker {

	private static final String TABLE = ".table";
	private final Language language;
	private final File resources;

	public TableChecker(Language language, File resources) {
		this.language = language;
		this.resources = resources;
	}

	public boolean check(NodeImpl node) throws SemanticFatalException {
		final Table table = node.table();
		File dataFile = findFile(table.name());
		if (!dataFile.exists())
			throw new SemanticFatalException(new SemanticNotification(ERROR, "table.not.found", node, Collections.emptyList()));
		Map<String, Primitive> expectedParameters = expectedParameters(node);
		final List<List<String>> data = readCSV(dataFile, node);
		if (expectedParameters.size() != table.header().size())
			throw new SemanticFatalException(new SemanticNotification(ERROR, "table.header.size.does.not.match", node, Collections.singletonList(join(expectedParameters))));
		if (!matchHeaderNames(table.header(), data.get(0)))
			throw new SemanticFatalException(new SemanticNotification(ERROR, "table.header.names.does.not.match", node, Collections.singletonList(join(expectedParameters))));
		table.setData(parse(data, expectedParameters, table.header()));
		return true;
	}

	private List<List<Object>> parse(List<List<String>> data, Map<String, Primitive> expectedParameters, List<String> header) {
		List<List<Object>> result = new ArrayList<>();
		List<Primitive> types = ungroup(expectedParameters, header);
		for (List<String> row : data.subList(1, data.size())) {
			final ArrayList<Object> list = new ArrayList<>();
			for (int j = 0; j < row.size(); j++)
				list.add(TypeConverter.convert(types.get(j), row.get(j)));
			result.add(list);
		}
		return result;
	}

	private boolean matchHeaderNames(List<String> parameters, List<String> header) throws SemanticFatalException {
		for (String name : ungroup(parameters))
			if (!header.contains(name)) return false;
		return true;
	}

	private List<Primitive> ungroup(Map<String, Primitive> expectedParameters, List<String> header) {
		List<Primitive> primitives = new ArrayList<>();
		int index = 0;
		for (Map.Entry<String, Primitive> entry : expectedParameters.entrySet()) {
			for (int i = 0; i < header.get(index).split(" ").length; i++) primitives.add(entry.getValue());
			index++;
		}
		return primitives;
	}

	private List<String> ungroup(List<String> parameters) {
		List<String> ungrouped = new ArrayList<>();
		for (String parameter : parameters) Collections.addAll(ungrouped, parameter.split(" "));
		return ungrouped;
	}

	private Map<String, Primitive> expectedParameters(NodeImpl node) {
		Map<String, Primitive> map = new LinkedHashMap<>();
		language.constraints(node.resolve().type()).stream().filter(c -> c instanceof Parameter).forEach(c -> map.put(((Parameter) c).name(), ((Parameter) c).type()));
		return map;
	}

	private String join(Map<String, Primitive> expectedParameters) {
		String result = "";
		for (Map.Entry<String, Primitive> entry : expectedParameters.entrySet())
			result += ", " + entry.getValue().getName() + " " + entry.getKey();
		return result.substring(2);
	}

	private List<List<String>> readCSV(File dataFile, Node node) throws SemanticFatalException {
		List<List<String>> list = new ArrayList<>();
		try {
			CSVReader reader = new CSVReader(new FileReader(dataFile.getPath()), ';');
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null)
				if (!isEmptyLine(nextLine)) list.add(new ArrayList<>(Arrays.asList(nextLine)));
		} catch (IOException e) {
			throw new SemanticFatalException(new SemanticNotification(ERROR, "file.cannot.be.read", node, Collections.emptyList()));
		}
		return list;
	}

	private boolean isEmptyLine(String[] nextLine) {
		return nextLine.length == 0 || (nextLine.length == 1 && nextLine[0].isEmpty());
	}

	private File findFile(String name) {
		return new File(resources, name + TABLE);
	}

	protected static class TypeConverter {

		public static Object convert(Primitive type, String value) {
			if (type == Primitive.BOOLEAN) return Boolean.parseBoolean(value);
			if (type == Primitive.DOUBLE) return Double.parseDouble(value);
			if (type == Primitive.INTEGER) return Integer.parseInt(value);
			else return value;
		}
	}

}
