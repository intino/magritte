package siani.tara.compiler.core.errorcollection.semantic;


import siani.tara.lang.Node;

public abstract class SemanticError {

	protected final String token;
	protected final Node node;
	protected final int line;

	protected SemanticError(String token, Node node) {
		this.token = token;
		this.node = node;
		if (node != null)
			this.line = node.getLine();
		else this.line = -1;
	}

	public String getToken() {
		return token;
	}

	public Node getNode() {
		return node;
	}

	public abstract String getMessage();

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SemanticError that = (SemanticError) o;
		if (token != null ? !token.equals(that.token) : that.token != null) return false;
		return !(node != null ? !node.getQualifiedName().equals(that.node.getQualifiedName()) : that.node != null);

	}

	@Override
	public int hashCode() {
		int result = token != null ? token.hashCode() : 0;
		result = 31 * result + (node != null ? node.hashCode() : 0);
		return result;
	}

	public int getLine() {
		return line;
	}

	public interface FatalError {
	}

	public interface Warning {
	}
}