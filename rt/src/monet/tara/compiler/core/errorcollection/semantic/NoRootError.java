package monet.tara.compiler.core.errorcollection.semantic;

public class NoRootError extends SemanticError implements SemanticError.FatalError

{
	public NoRootError() {
		super("null", null);
	}

	@Override
	public String getMessage() {
		return "There are no root concept declared. One at least needed";
	}
}