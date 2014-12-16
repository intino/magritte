package siani.tara.intellij.lang.psi;

import com.intellij.pom.Navigatable;

public interface Annotation extends Navigatable, TaraPsiElement {

	boolean isMetaAnnotation();

	boolean is(siani.tara.lang.Annotation annotation);

}
