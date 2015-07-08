package siani.tara.intellij.lang.psi;

import com.intellij.pom.Navigatable;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface Annotations extends Navigatable, TaraPsiElement {

	@NotNull
	<T extends Annotation> List<T> getAnnotationList();

	String[] asStringArray();
}
