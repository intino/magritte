package io.intino.magritte.builder.core.operation.sourceunit;

import io.intino.magritte.builder.core.CompilationUnit;
import io.intino.magritte.builder.core.SourceUnit;
import io.intino.magritte.builder.core.errorcollection.CompilationFailedException;

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
