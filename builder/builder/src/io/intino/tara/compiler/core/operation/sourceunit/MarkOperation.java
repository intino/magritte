package io.intino.tara.compiler.core.operation.sourceunit;

import io.intino.tara.compiler.core.CompilationUnit;
import io.intino.tara.compiler.core.SourceUnit;
import io.intino.tara.compiler.core.errorcollection.CompilationFailedException;

public class MarkOperation extends SourceUnitOperation {
	private CompilationUnit compilationUnit;

	public MarkOperation(CompilationUnit compilationUnit) {
		this.compilationUnit = compilationUnit;
	}

	public void call(SourceUnit source) throws CompilationFailedException {
		if (source.getPhase() < compilationUnit.getPhase()) source.gotoPhase(compilationUnit.getPhase());
		if ((source.getPhase() == compilationUnit.getPhase()) && (compilationUnit.isPhaseComplete()) && (!source.isPhaseComplete()))
			source.completePhase();
	}
}
