package tara.intellij.lang.psi;

import com.intellij.pom.Navigatable;
import tara.language.model.Tag;

public interface Annotation extends Navigatable, TaraPsiElement {

	boolean is(Tag tag);

}
