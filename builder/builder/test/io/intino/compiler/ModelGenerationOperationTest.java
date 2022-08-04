package io.intino.compiler;

import io.intino.magritte.builder.CompilationInfoExtractor;
import io.intino.magritte.builder.core.CompilationUnit;
import io.intino.magritte.builder.core.CompilerConfiguration;
import io.intino.magritte.builder.core.SourceUnit;
import io.intino.magritte.builder.core.operation.sourceunit.ParseOperation;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;

@Ignore
public class ModelGenerationOperationTest {
	private final CompilerConfiguration configuration = new CompilerConfiguration();
	private CompilationUnit unit;
	private final Map<File, Boolean> srcFiles = new LinkedHashMap<>();

	@Before
	public void setUp() {
		srcFiles.put(new File(this.getClass().getResource("/sandbox/src/ParseTest.tara").getPath()), true);
		CompilationInfoExtractor.getInfoFromArgsFile(new File(this.getClass().getResource("/sandbox/confFiles/sample/M3.txt").getPath()), configuration, srcFiles);
		unit = new CompilationUnit(configuration);

	}

	@Test
	public void acceptedParsing() {
		ParseOperation operation = new ParseOperation(unit);
		for (File srcFile : srcFiles.keySet()) {
			operation.call(new SourceUnit(srcFile, configuration, unit.getErrorCollector(), srcFiles.get(srcFile)));
			assertFalse(unit.getErrorCollector().hasErrors());
		}
	}

	@Test
	public void acceptedGeneratedModel() {
		ParseOperation operation = new ParseOperation(unit);
		for (File srcFile : srcFiles.keySet()) {
			operation.call(new SourceUnit(srcFile, configuration, unit.getErrorCollector(), srcFiles.get(srcFile)));
			assertFalse(unit.getErrorCollector().hasErrors());
		}
	}
}
