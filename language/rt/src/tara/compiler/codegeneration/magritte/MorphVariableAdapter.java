package tara.compiler.codegeneration.magritte;

import org.siani.itrules.Adapter;
import org.siani.itrules.model.Frame;
import tara.compiler.codegeneration.magritte.morph.TypesProvider;
import tara.compiler.model.Node;
import tara.compiler.model.NodeContainer;
import tara.compiler.model.Variable;
import tara.compiler.model.impl.VariableReference;
import tara.semantic.model.Primitives;

import static tara.compiler.codegeneration.magritte.NameFormatter.getQn;
import static tara.semantic.model.Variable.NATIVE_SEPARATOR;

public class MorphVariableAdapter extends Generator implements Adapter<Variable>, TemplateTags {


	private final String generatedLanguage;
	private int modelLevel;

	public MorphVariableAdapter(String generatedLanguage, int modelLevel) {
		this.generatedLanguage = generatedLanguage;
		this.modelLevel = modelLevel;
	}

	@Override
	public void execute(Frame frame, Variable source, FrameContext<Variable> context) {
		createVarFrame(frame, source);
	}

	private Frame createVarFrame(Frame frame, final Variable variable) {
		frame.addTypes(TypesProvider.getTypes(variable, modelLevel));
		if (isDefinition(getNodeContainer(variable), modelLevel)) frame.addTypes(DEFINITION);
		frame.addFrame(NAME, variable.name());
		frame.addFrame(GENERATED_LANGUAGE, generatedLanguage.toLowerCase());
		if (variable.contract() != null) frame.addFrame(CONTRACT, format(variable.contract()));
		frame.addFrame(TYPE, variable instanceof VariableReference ? getQn(((VariableReference) variable).getDestiny(), generatedLanguage.toLowerCase()) : getType(variable));
		if (variable.type().equals(Variable.WORD))
			frame.addFrame(WORDS, variable.allowedValues().toArray(new String[(variable.allowedValues().size())]));
		else if (variable.type().equals(Primitives.NATIVE)) fillNativeVariable(frame, variable);
		return frame;
	}

	private Node getNodeContainer(Variable variable) {
		NodeContainer container = variable.container();
		while (!(container instanceof Node))
			container = container.container();
		return (Node) container;
	}

	private void fillNativeVariable(Frame frame, Variable variable) {
		final NativeExtractor nativeExtractor = new
			NativeExtractor(variable.contract().substring(0, variable.contract().indexOf(NATIVE_SEPARATOR)),
			variable.name(), variable.contract().substring(variable.contract().indexOf(NATIVE_SEPARATOR) + 1));
		frame.addFrame("parameters", nativeExtractor.parameters());
		frame.addFrame("interfaceName", nativeExtractor.interfaceName());
		frame.addFrame("methodName", nativeExtractor.methodName());
		frame.addFrame("returnValue", nativeExtractor.returnValue());
	}

	private String format(String contract) {
		if (contract == null) return "";
		final int i = contract.indexOf(NATIVE_SEPARATOR);
		return (i >= 0) ? contract.substring(0, i) : contract;
	}

	private String getType(Variable variable) {
		if (variable.type().equalsIgnoreCase(Primitives.NATURAL)) return Primitives.INTEGER;
		else return variable.type();
	}
}
