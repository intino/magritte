package tara.semantic.model;

import java.util.List;

public interface NodeContainer extends Element {

	List<Node> includes();

	String type();
}
