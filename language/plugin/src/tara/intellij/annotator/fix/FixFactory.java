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


	private static Map<String, Class<? extends IntentionAction>[]> fixes = new HashMap<>();

	static {
		fixes.put("type.not.exists", new Class[]{RemoveNodeFix.class});
		fixes.put("parent.model.file.found", new Class[]{ImportMetamodelFix.class, ConfigureModuleFix.class});
		fixes.put("duplicated.dsl.declaration", new Class[]{ImportMetamodelFix.class, ConfigureModuleFix.class});
		fixes.put("dsl.not.found", new Class[]{AddMetamodelReferenceFix.class});
	}

	public static IntentionAction[] get(String key, PsiElement element) {
		Class<? extends IntentionAction>[] classes = fixes.get(key);
		if (classes == null) return IntentionAction.EMPTY_ARRAY;
		return instanceFixes(classes, element);
	}

	private static IntentionAction[] instanceFixes(Class<? extends IntentionAction>[] classes, PsiElement element) {
		try {
			List<IntentionAction> actions = new ArrayList<>();
			for (Class<? extends IntentionAction> aClass : classes) {
				IntentionAction c = aClass.getConstructor(PsiElement.class).newInstance(element);
				actions.add(c);
			}
			return actions.toArray(new IntentionAction[actions.size()]);
		} catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
			throw new TaraRuntimeException("Fix couldnt be instantiated: " + e.getMessage());
		}
	}
}
