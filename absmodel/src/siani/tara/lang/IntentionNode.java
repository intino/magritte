package siani.tara.lang;

public class IntentionNode extends DeclaredNode {

	public IntentionNode(IntentionObject object, DeclaredNode container) {
		super(object, container);
	}

	public IntentionObject getObject() {
		return (IntentionObject)object;
	}

	public String toString() {
		return "IntentionNode{" + qualifiedName + '}';
	}
}
