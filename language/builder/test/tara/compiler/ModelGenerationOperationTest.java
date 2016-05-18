package tara.compiler;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import tara.CompilationInfoExtractor;
import tara.compiler.core.CompilationUnit;
import tara.compiler.core.CompilerConfiguration;
import tara.compiler.core.SourceUnit;
import tara.compiler.core.operation.sourceunit.ParseOperation;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;

@Ignore
public class ModelGenerationOperationTest {

	private CompilerConfiguration configuration = new CompilerConfiguration();
	private CompilationUnit unit;
	private Map<File, Boolean> srcFiles = new LinkedHashMap<>();

	@Before
	public void setUp() throws Exception {
		srcFiles.put(new File(this.getClass().getResource("/sandbox/src/ParseTest.tara").getPath()), true);
		CompilationInfoExtractor.getInfoFromArgsFile(new File(this.getClass().getResource("/sandbox/confFiles/sample/M3.txt").getPath()), configuration, srcFiles);
		unit = new CompilationUnit(configuration);

	}

	@Test
	public void acceptedParsing() throws Exception {
		ParseOperation operation = new ParseOperation(unit);
		for (File srcFile : srcFiles.keySet()) {
			operation.call(new SourceUnit(srcFile, configuration, unit.getErrorCollector(), srcFiles.get(srcFile)));
			assertFalse(unit.getErrorCollector().hasErrors());
		}
	}

	@Test
	public void acceptedGeneratedModel() throws Exception {
		ParseOperation operation = new ParseOperation(unit);
		for (File srcFile : srcFiles.keySet()) {
			operation.call(new SourceUnit(srcFile, configuration, unit.getErrorCollector(), srcFiles.get(srcFile)));
			assertFalse(unit.getErrorCollector().hasErrors());
		}
	}
}
