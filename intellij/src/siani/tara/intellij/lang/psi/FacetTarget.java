package siani.tara.intellij.lang.psi;

import com.intellij.pom.Navigatable;

import java.util.Collection;

public interface FacetTarget extends Navigatable, TaraPsiElement{

	String target();

	Collection<Node> includes();
}
