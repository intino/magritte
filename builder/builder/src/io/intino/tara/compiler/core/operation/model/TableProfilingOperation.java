package io.intino.tara.compiler.core.operation.model;

import io.intino.tara.compiler.model.Model;
import io.intino.tara.compiler.model.ParameterImpl;
import io.intino.tara.compiler.core.CompilationUnit;
import io.intino.tara.compiler.core.CompilerConfiguration;
import io.intino.tara.compiler.core.errorcollection.CompilationFailedException;
import io.intino.tara.compiler.model.NodeImpl;
import io.intino.tara.compiler.model.Table;
import io.intino.tara.lang.model.Node;
import io.intino.tara.lang.model.Parameter;
import io.intino.tara.lang.model.rules.Size;
import io.intino.tara.lang.semantics.Constraint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

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
			List<NodeImpl> nodes = profile((NodeImpl) tableNode);
			substitute(tableNode, nodes);
		}
	}

	private void substitute(Node tableNode, List<NodeImpl> nodes) {
		nodes.forEach(n -> tableNode.container().add(n, Collections.singletonList(Size.MULTIPLE())));
		tableNode.container().remove(tableNode);
	}

	private List<NodeImpl> profile(NodeImpl tableNode) {
		List<Constraint.Parameter> parameters = conf.language().constraints(tableNode.type()).stream().filter(c -> c instanceof Constraint.Parameter).map(c -> (Constraint.Parameter) c).collect(Collectors.toList());
		List<NodeImpl> nodes = new ArrayList<>();
		for (List<Object> row : tableNode.table().data()) {
			final NodeImpl node = new NodeImpl();
			node.addFlags(tableNode.flags());
			node.file(tableNode.file());
			node.line(tableNode.line());
			node.type(tableNode.type());
			node.container(tableNode.container());
			node.column(tableNode.column());
			createParameters(parameters, tableNode.table(), row).forEach(parameter -> {
				((ParameterImpl) parameter).owner(node);
				node.add(parameter);
			});
			nodes.add(node);
		}
		return nodes;
	}

	private List<Parameter> createParameters(List<Constraint.Parameter> parameters, Table table, List<Object> row) {
		List<Parameter> values = new ArrayList<>();
		for (int i = 0; i < parameters.size(); i++) {
			final ParameterImpl p = new ParameterImpl(parameters.get(i).name(), i, "", values(asList(table.parameters().get(i).split(" ")), table.header(), row));
			p.type(parameters.get(i).type());
			p.multiple(!parameters.get(i).size().isSingle());
			values.add(p);
		}
		return values;
	}

	private List<Object> values(List<String> parameters, List<String> header, List<Object> row) {
		return parameters.stream().map(parameter -> row.get(header.indexOf(parameter))).collect(Collectors.toList());
	}

	private List<Node> findTables(Node node) {
		List<Node> nodes = new ArrayList<>();
		for (Node component : node.components()) {
			if (!(component instanceof NodeImpl) || !component.isTerminal()) continue;
			if (!component.tableName().isEmpty()) nodes.add(component);
			nodes.addAll(findTables(component));
		}
		return nodes;
	}
}
