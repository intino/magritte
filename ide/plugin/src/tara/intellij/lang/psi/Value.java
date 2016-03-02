package tara.intellij.lang.psi;

import com.intellij.openapi.util.Iconable;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.lang.model.*;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static tara.lang.model.Primitive.*;

public interface Value extends Navigatable, Iconable, TaraPsiElement {

	@NotNull
	List<Object> values();

	static List<Object> makeUp(List<Object> values, Primitive type, PsiElement scope) {
		if (type == null) tryAsReference(values);
		if (RESOURCE.equals(type))
			return values.stream().
				map(o -> o instanceof EmptyNode ? null : new File(TaraUtil.getResourcesRoot(scope).getPath(), o.toString().substring(1, o.toString().length() - 1))).
				collect(Collectors.toList());
		if (DOUBLE.equals(type) && !isNative(scope))
			return values.stream().map(o -> o instanceof Integer ? ((Integer) o).doubleValue() : o).collect(Collectors.toList());
		if (STRING.equals(type) && !isNative(scope))
			return values.stream().map(o -> o.toString().substring(1, o.toString().length() - 1)).collect(Collectors.toList());
		if (WORD.equals(type))
			return values.stream().map(o -> o instanceof Node ? new Primitive.Reference(((Node) o).name()) : o).collect(Collectors.toList());
		return values;
	}

	static boolean isNative(PsiElement scope) {
		return scope instanceof Variable && ((Variable) scope).flags().contains(Tag.Native) ||
			scope instanceof Parameter && ((Parameter) scope).flags().contains(Tag.Native.name());

	}

	static List<Object> tryAsReference(List<Object> values) {
		if (values.get(0) instanceof Node) return values;
		return Collections.emptyList();
	}
}
