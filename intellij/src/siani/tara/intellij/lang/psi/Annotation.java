package siani.tara.intellij.lang.psi;

import com.intellij.pom.Navigatable;
import siani.tara.intellij.lang.lexer.Tag;

public interface Annotation extends Navigatable, TaraPsiElement {

	boolean is(Tag tag);

}
