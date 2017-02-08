package io.intino.tara.plugin.lang.psi;

import com.intellij.openapi.util.Iconable;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiElement;
import io.intino.tara.lang.model.EmptyNode;
import io.intino.tara.lang.model.Node;
import io.intino.tara.lang.model.Primitive;
import io.intino.tara.plugin.lang.psi.impl.TaraUtil;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import static io.intino.tara.lang.model.Primitive.*;
import static java.util.stream.Collectors.toList;

public interface Value extends Navigatable, Iconable, TaraPsiElement {

	@NotNull
	List<Object> values();

	static List<Object> makeUp(List<Object> values, Primitive type, PsiElement scope) {
		if (values.isEmpty() || values.get(0) instanceof Primitive.Expression || values.get(0) instanceof Primitive.MethodReference) return values;
		if (type == null) tryAsReference(values);
		if (RESOURCE.equals(type)) return values.stream().map(o -> asResource(scope, o)).collect(toList());
		if (values.get(0) instanceof EmptyNode) return values;
		if (DOUBLE.equals(type))
			return values.stream().map(o -> o instanceof Integer ? ((Integer) o).doubleValue() : o).collect(toList());
		if ((INSTANT.equals(type) || TIME.equals(type) || STRING.equals(type)) && !(values.get(0) instanceof EmptyNode))
			return values.stream().
					filter(o -> !o.toString().isEmpty()).
					map(o -> o.toString().length() < 2 ? null : o.toString().substring(1, o.toString().length() - 1)).
					collect(toList());
		if (WORD.equals(type)) return values.stream().
				map(o -> o instanceof Node ? new Primitive.Reference(((Node) o).name()) : o).
				collect(toList());
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
