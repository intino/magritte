package tara.magritte;


public class Reference {

	private final String qn;
    private Board board;
    private Declaration aDeclaration;

	public Reference(String qn, Board board) {
		this.qn = qn;
        this.board = board;
    }

	public Declaration node() {
		if (aDeclaration == null) aDeclaration = board.loadDeclaration(qn);
		return aDeclaration;
	}
}
