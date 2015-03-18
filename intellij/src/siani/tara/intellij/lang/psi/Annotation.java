package siani.tara.intellij.lang.psi;

import com.intellij.pom.Navigatable;

public interface Annotation extends Navigatable, TaraPsiElement {

	boolean isMetaAnnotation();

	boolean is(siani.tara.intellij.lang.lexer.Annotation annotation);

}
