package tara.compiler.codegeneration.magritte.morph;

import org.siani.itrules.Adapter;
import org.siani.itrules.model.Frame;
import tara.compiler.codegeneration.magritte.Generator;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.compiler.model.Variable;
import tara.semantic.Allow;
import tara.semantic.constraints.ReferenceParameterAllow;
import tara.semantic.model.Primitives;

public class MorphParameterAdapter extends Generator implements Adapter<Allow.Parameter>, TemplateTags {

	private final String generatedLanguage;

	public MorphParameterAdapter(String generatedLanguage) {
		this.generatedLanguage = generatedLanguage;
	}

	@Override
	public void execute(Frame frame, Allow.Parameter source, FrameContext<Allow.Parameter> context) {
		frame.addTypes(TypesProvider.getTypes(source));
		createFrame(frame, source);
	}

	public void createFrame(Frame frame, final Allow.Parameter parameter) {
		frame.addFrame(NAME, parameter.name());
		frame.addFrame(GENERATED_LANGUAGE, generatedLanguage.toLowerCase());
		frame.addFrame(TYPE, parameter instanceof ReferenceParameterAllow ? parameter.allowedValues().get(0) : getType(parameter));//TODO QN completo
		if (parameter.type().equals(Variable.WORD))
			frame.addFrame(WORDS, parameter.allowedValues().toArray(new String[(parameter.allowedValues().size())]));
	}

	private String getType(Allow.Parameter parameter) {
		if (parameter.type().equalsIgnoreCase(Primitives.NATURAL)) return Primitives.INTEGER;
		else return parameter.type();
	}
}