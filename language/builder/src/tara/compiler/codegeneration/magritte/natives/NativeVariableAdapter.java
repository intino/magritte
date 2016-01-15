package tara.compiler.codegeneration.magritte.natives;

import org.siani.itrules.Adapter;
import org.siani.itrules.model.Frame;
import tara.Language;
import tara.compiler.codegeneration.magritte.Generator;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.compiler.codegeneration.magritte.layer.TypesProvider;
import tara.lang.model.Primitive;
import tara.lang.model.Variable;

public class NativeVariableAdapter extends Generator implements Adapter<Variable>, TemplateTags {

	private final String aPackage;

	public NativeVariableAdapter(Language language, String generatedLanguage, String aPackage) {
		super(language, generatedLanguage);
		this.aPackage = aPackage;
	}

	@Override
	public void execute(Frame frame, Variable source, FrameContext<Variable> frameContext) {
		frame.addTypes(TypesProvider.getTypes(source, 2));
		createFrame(frame, source);
	}

	public void createFrame(Frame frame, final Variable variable) {
		createNativeFrame(frame, variable);
	}

	private void createNativeFrame(Frame frame, Variable variable) {
		if (!(variable.values().get(0) instanceof Primitive.Expression)) return;
		final Primitive.Expression body = (Primitive.Expression) variable.values().get(0);
		String value = body.get();
		NativeFormatter formatter = new NativeFormatter(generatedLanguage, language, aPackage, false);
		if (Primitive.FUNCTION.equals(variable.type())) formatter.fillFrameForNativeVariable(frame, variable, value);
		else formatter.fillFrameExpressionVariable(frame, variable, value);

	}
}