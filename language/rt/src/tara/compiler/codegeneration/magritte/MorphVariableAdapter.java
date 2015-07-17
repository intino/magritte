package tara.compiler.codegeneration.magritte;

import org.siani.itrules.Adapter;
import org.siani.itrules.engine.FormatterStore;
import org.siani.itrules.model.Frame;
import tara.Language;
import tara.compiler.codegeneration.magritte.box.NativeFormatter;
import tara.compiler.codegeneration.magritte.morph.TypesProvider;
import tara.compiler.model.VariableReference;
import tara.language.model.EmptyNode;
import tara.language.model.Primitives;
import tara.language.model.Variable;

import java.util.Locale;

import static tara.compiler.codegeneration.magritte.NameFormatter.getQn;
import static tara.language.model.Variable.NATIVE_SEPARATOR;

public class MorphVariableAdapter extends Generator implements Adapter<Variable>, TemplateTags {


	private final Language language;
	private final String generatedLanguage;
	private int modelLevel;

	public MorphVariableAdapter(String generatedLanguage, Language language, int modelLevel) {
		this.language = language;
		this.generatedLanguage = generatedLanguage;
		this.modelLevel = modelLevel;
	}

	@Override
	public void execute(Frame frame, Variable source, FrameContext<Variable> context) {
		createVarFrame(frame, source);
	}

	private Frame createVarFrame(Frame frame, final Variable variable) {
		frame.addTypes(TypesProvider.getTypes(variable, modelLevel));
		frame.addFrame(NAME, variable.name());
		frame.addFrame(GENERATED_LANGUAGE, generatedLanguage.toLowerCase());
		frame.addFrame(CONTAINER, variable.container().qualifiedName());
		if (!variable.defaultValues().isEmpty() && !(variable.defaultValues().get(0) instanceof EmptyNode))
			frame.addFrame(VALUE, variable.defaultValues().toArray());
		if (variable.contract() != null) frame.addFrame(CONTRACT, format(variable.contract()));
		frame.addFrame(TYPE, getType(variable));
		if (variable.type().equals(Variable.WORD))
			frame.addFrame(WORDS, variable.allowedValues().toArray(new String[(variable.allowedValues().size())]));
		else if (variable.type().equals(Primitives.NATIVE)) fillNativeVariable(frame, variable);
		return frame;
	}

	private String getType(Variable variable) {
		if (variable instanceof VariableReference)
			return getQn(((VariableReference) variable).getDestiny(), generatedLanguage.toLowerCase());
		else if (variable.type().equals(Primitives.WORD))
			return new FormatterStore(Locale.ENGLISH).get("firstUpperCase").format(variable.name()).toString();
		else return variable.type();
	}

	private void fillNativeVariable(Frame frame, Variable variable) {
		final NativeFormatter adapter = new NativeFormatter(generatedLanguage, language, false);
		if (variable.defaultValues().isEmpty() || !(variable.defaultValues().get(0) instanceof Primitives.Expression))
			return;
		final Object next = variable.defaultValues().get(0);
		if (Primitives.NATIVE.equals(variable.type())) adapter.fillFrameForNativeVariable(frame, variable, next);
		else adapter.fillFrameExpressionVariable(frame, variable, next);
	}

	private String format(String contract) {
		if (contract == null) return "";
		final int i = contract.indexOf(NATIVE_SEPARATOR);
		return (i >= 0) ? contract.substring(0, i) : contract;
	}

}
