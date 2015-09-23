package tara.intellij.lang.psi;

public interface MultilineValue {

	boolean isMultiLine();

	void toInline();

	void toMultiline();
}
