package tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.openapi.module.Module;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.TaraStringValue;
import tara.intellij.lang.psi.Valued;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.project.module.ModuleProvider;
import tara.lang.model.Parameter;
import tara.lang.model.Primitive;
import tara.lang.model.Variable;

import java.io.File;
import java.util.Collections;
import java.util.List;

import static tara.intellij.MessageProvider.message;
import static tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.TYPE.WARNING;

public class ResourceAnnotator extends TaraAnnotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		if (!Valued.class.isInstance(element) || ((Valued) element).getValue() == null) return;
		Valued valued = (Valued) element;
		this.holder = holder;
		if (Primitive.RESOURCE.equals(typeOf(valued)))
			check(valued.getValue().getStringValueList(), resources(element));
	}

	private Primitive typeOf(Valued valued) {
		if (valued instanceof Variable) return ((Variable) valued).type();
		return ((Parameter) valued).inferredType();
	}

	private void check(List<TaraStringValue> values, File resources) {
		if (!resources.exists()) return;
		values.stream().
			filter(v -> !new File(resources.getPath(), v.getValue()).exists()).
			forEach(v -> annotateAndFix(Collections.singletonMap(v, new AnnotateAndFix(WARNING, message("warning.resource.not.found")))));
	}

	private File resources(PsiElement element) {
		final Module moduleOf = ModuleProvider.getModuleOf(element);
		return new File(TaraUtil.getResourcesRoot(moduleOf));
	}

}
