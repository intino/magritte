package tara.intellij.lang.psi;

import com.intellij.openapi.util.Iconable;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.lang.model.EmptyNode;
import tara.lang.model.Node;
import tara.lang.model.Primitive;

import java.io.File;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static tara.lang.model.Primitive.*;

public interface Value extends Navigatable, Iconable, TaraPsiElement {

	@NotNull
	List<Object> values();

	static List<Object> makeUp(List<Object> values, Primitive type, PsiElement scope) {
		if (values.get(0) instanceof Primitive.Expression) return values;
		if (type == null) tryAsReference(values);
		if (RESOURCE.equals(type))
			return values.stream().map(o -> asResource(scope, o)).collect(Collectors.toList());
		if (DOUBLE.equals(type))
			return values.stream().map(o -> o instanceof Integer ? ((Integer) o).doubleValue() : o).collect(Collectors.toList());
		if (STRING.equals(type)) return values.stream().
			filter(o -> !o.toString().isEmpty()).map(o -> o.toString().substring(1, o.toString().length() - 1)).collect(Collectors.toList());
		if (WORD.equals(type)) return values.stream().
			map(o -> o instanceof Node ? new Primitive.Reference(((Node) o).name()) : o).collect(Collectors.toList());
		return values;
	}

	static Serializable asResource(PsiElement scope, Object o) {
		final VirtualFile resourcesRoot = TaraUtil.getResourcesRoot(scope);
		return o instanceof EmptyNode ?
			null :
			resourcesRoot == null ?
				new File(o.toString().substring(1, o.toString().length() - 1)) :
				new File(resourcesRoot.getPath(), o.toString().substring(1, o.toString().length() - 1));
	}

	static List<Object> tryAsReference(List<Object> values) {
		if (values.get(0) instanceof Node) return values;
		return Collections.emptyList();
	}
}
