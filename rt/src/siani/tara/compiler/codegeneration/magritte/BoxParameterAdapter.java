package siani.tara.compiler.codegeneration.magritte;

import org.siani.itrules.framebuilder.Adapter;
import org.siani.itrules.framebuilder.BuilderContext;
import org.siani.itrules.model.Frame;
import siani.tara.compiler.model.Parameter;

import java.util.ArrayList;
import java.util.List;

public class BoxParameterAdapter implements Adapter<Parameter> {


	@Override
	public void adapt(Frame frame, Parameter parameter, BuilderContext builderContext) {
//		frame.add(getTypes(variable));
	}


	protected String[] getTypes(Parameter parameter) {
		List<String> list = new ArrayList<>();
		list.add(parameter.getClass().getSimpleName());
//		list.add(VARIABLE);
//		if (parameter instanceof VariableReference) list.add("reference");
//		list.add(parameter.getType());
//		if (parameter.isTerminal()) list.add(TERMINAL);
//		if (parameter.isMultiple()) list.add(MULTIPLE);
//		for (Annotation annotation : parameter.getAnnotations()) list.add(annotation.getName());
		return list.toArray(new String[list.size()]);
	}
}
