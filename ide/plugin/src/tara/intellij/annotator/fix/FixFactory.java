package tara.intellij.annotator.fix;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.psi.PsiElement;
import tara.intellij.TaraRuntimeException;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FixFactory {

	private FixFactory() {
	}

	private static Map<String, Class<? extends IntentionAction>[]> fixes = new HashMap<>();

	static {
		fixes.put("type.not.exists", new Class[]{RemoveNodeFix.class});
		fixes.put("parent.model.file.found", new Class[]{ImportMetamodelFix.class, ConfigureModuleFix.class});
		fixes.put("duplicated.dsl.declaration", new Class[]{ImportMetamodelFix.class, ConfigureModuleFix.class});
		fixes.put("dsl.not.found", new Class[]{AddMetamodelReferenceFix.class});
		fixes.put("required.plate", new Class[]{AddAddressFix.class});
		fixes.put("required.terminal.variable.redefine", new Class[]{RedefineFix.class});
		fixes.put("required.parameter", new Class[]{AddRequiredParameterFix.class});
	}

	public static IntentionAction[] get(String key, PsiElement element, String... parameters) {
		Class<? extends IntentionAction>[] classes = fixes.get(key);
		if (classes == null) return IntentionAction.EMPTY_ARRAY;
		return instanceFixes(classes, element, parameters);
	}

	private static IntentionAction[] instanceFixes(Class<? extends IntentionAction>[] classes, PsiElement element, String[] parameters) {
		try {
			List<IntentionAction> actions = new ArrayList<>();
			for (Class<? extends IntentionAction> aClass : classes) {
				IntentionAction intentionAction = aClass.getDeclaredConstructors()[0].getParameters().length == 2 ?
					aClass.getConstructor(PsiElement.class, String[].class).newInstance(element, parameters) :
					aClass.getConstructor(PsiElement.class).newInstance(element);
				actions.add(intentionAction);
			}
			return actions.toArray(new IntentionAction[actions.size()]);
		} catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
			throw new TaraRuntimeException("Fix couldn't be instantiated: " + e.getMessage(), e);
		}
	}
}
