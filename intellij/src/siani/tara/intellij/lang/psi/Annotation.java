package siani.tara.intellij.lang.psi;

import com.intellij.pom.Navigatable;
import siani.tara.semantic.model.Tag;

public interface Annotation extends Navigatable, TaraPsiElement {

	boolean is(Tag tag);

}
