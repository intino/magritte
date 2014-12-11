package siani.tara.intellij.lang.psi;

import com.intellij.pom.Navigatable;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface Annotations extends Navigatable, TaraPsiElement {

	@NotNull
	List<? extends Annotation> getAnnotationList();

	@NotNull
	List<? extends Annotation> getMetaAnnotations();

	@NotNull
	List<? extends Annotation> getNormalAnnotations();
}
