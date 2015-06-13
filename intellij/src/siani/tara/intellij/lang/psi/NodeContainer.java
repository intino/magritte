package siani.tara.intellij.lang.psi;

import java.util.List;

public interface NodeContainer extends TaraPsiElement {

	List<Node> getIncludes();
}
