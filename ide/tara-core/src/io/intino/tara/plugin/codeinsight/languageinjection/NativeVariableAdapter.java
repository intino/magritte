package io.intino.tara.plugin.codeinsight.languageinjection;

import com.intellij.openapi.module.Module;
import org.siani.itrules.Adapter;
import org.siani.itrules.engine.Context;
import org.siani.itrules.model.Frame;
import io.intino.tara.Language;
import io.intino.tara.plugin.lang.psi.TaraVariable;
import io.intino.tara.lang.model.Primitive;
import io.intino.tara.lang.model.Tag;
import io.intino.tara.lang.model.Variable;

class NativeVariableAdapter implements Adapter<Variable> {

	private NativeFormatter formatter;

	NativeVariableAdapter(Module module, String generatedLanguage, Language language) {
		formatter = new NativeFormatter(module, generatedLanguage, language);
	}

	@Override
	public void adapt(Variable source, Context context) {
		final Frame frame = context.frame();
		if (source.type() == null) return;
		frame.addTypes(source.type().getName());
		for (Tag tag : source.flags()) frame.addTypes(tag.name().toLowerCase());
		createFrame(frame, source);
	}

	private void createFrame(Frame frame, final Variable variable) {
		if (variable.name() == null || variable.values() == null || variable.values().isEmpty() || !(variable.values().get(0) instanceof Primitive.Expression))
			return;
		final Primitive.Expression body = (Primitive.Expression) variable.values().get(0);
		if (Primitive.FUNCTION.equals(variable.type()))
			formatter.fillFrameForNativeVariable(frame, variable, ((TaraVariable) variable).getBodyValue() != null);
		else formatter.fillFrameExpressionVariable(frame, variable, body.get(), ((TaraVariable) variable).getBodyValue() != null);
	}
}