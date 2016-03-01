package tara.compiler.core.operation.model;

import tara.compiler.core.CompilationUnit;
import tara.compiler.core.CompilerConfiguration;
import tara.compiler.core.errorcollection.CompilationFailedException;
import tara.compiler.model.Model;
import tara.compiler.model.NodeImpl;
import tara.compiler.model.ParameterImpl;
import tara.lang.model.Node;
import tara.lang.model.NodeContainer;
import tara.lang.model.Parameter;
import tara.lang.model.rules.Size;
import tara.lang.semantics.Constraint;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TableProfilingOperation extends ModelOperation {

	private final CompilerConfiguration conf;

	public TableProfilingOperation(CompilationUnit unit) {
		this.conf = unit.getConfiguration();
	}

	@Override
	public void call(Model model) throws CompilationFailedException {
		List<Node> tableNodes = findTables(model);
		profile(tableNodes);
	}

	private void profile(List<Node> tableNodes) {
		for (Node tableNode : tableNodes) {
			List<NodeImpl> nodes = frofile((NodeImpl) tableNode);
			substitute(tableNode, nodes);
		}
	}

	private void substitute(Node tableNode, List<NodeImpl> nodes) {
		nodes.stream().forEach(n -> tableNode.container().add(n, Size.MULTIPLE()));
		tableNode.container().remove(tableNode);
	}

	private List<NodeImpl> frofile(NodeImpl tableNode) {
		List<Constraint.Parameter> parameters = conf.getLanguage().constraints(tableNode.type()).stream().filter(c -> c instanceof Constraint.Parameter).map(c -> (Constraint.Parameter) c).collect(Collectors.toList());
		List<NodeImpl> nodes = new ArrayList<>();
		for (List<Object> row : tableNode.table().data()) {
			final NodeImpl node = new NodeImpl();
			node.addFlags(tableNode.flags());
			node.file(tableNode.file());
			node.line(tableNode.line());
			node.type(tableNode.type());
			node.container(tableNode.container());
			node.column(tableNode.column());
			createParameters(parameters, tableNode.table().header(), row).forEach(parameter -> {
				((ParameterImpl) parameter).owner(node);
				node.add(parameter);
			});
			nodes.add(node);
		}
		return nodes;
	}

	private List<Parameter> createParameters(List<Constraint.Parameter> parameters, List<String> header, List<Object> row) {
		List<Parameter> values = new ArrayList<>();
		for (int i = 0; i < parameters.size(); i++) {
			final ParameterImpl p = new ParameterImpl(parameters.get(i).name(), i, "", values(header, row));
			p.type(parameters.get(i).type());
			p.multiple(!parameters.get(i).size().isSingle());
			values.add(p);
		}
		return values;
	}

	private List<Object> values(List<String> parameters, List<Object> row) {
		List<Object> values = new ArrayList<>();
		int index = 0;
		for (String parameter : parameters) {
			values.add(row.subList(index, index + parameter.split(" ").length));
			index += parameter.split(" ").length;
		}
		return values;
	}

	private List<Node> findTables(NodeContainer node) {
		List<Node> nodes = new ArrayList<>();
		for (Node component : node.components()) {
			if (!(component instanceof NodeImpl) || !component.isTerminal()) continue;
			if (!component.tableName().isEmpty()) nodes.add(component);
			nodes.addAll(findTables(component));
		}
		if (node instanceof NodeImpl) ((NodeImpl) node).facets().forEach(f -> nodes.addAll(findTables(f)));
		return nodes;
	}
}
