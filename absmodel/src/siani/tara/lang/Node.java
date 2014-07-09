package siani.tara.lang;

import java.util.List;

public abstract class Node {

	protected transient static final String ANNONYMOUS = "@annonymous";


	public abstract List<Node> getInnerNodes();

	public abstract NodeObject getObject();

	public abstract Node getContainer();

	public abstract String getQualifiedName();

}
