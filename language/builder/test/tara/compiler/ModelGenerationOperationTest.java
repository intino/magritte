package tara.compiler;

import org.junit.Before;
import org.junit.Test;
import tara.CompilationInfoExtractor;
import tara.compiler.core.CompilationUnit;
import tara.compiler.core.CompilerConfiguration;
import tara.compiler.core.SourceUnit;
import tara.compiler.core.operation.sourceunit.ParseOperation;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertFalse;

public class ModelGenerationOperationTest {

	private CompilerConfiguration configuration;
	private CompilationUnit unit;
	private List<Map<File, Boolean>> srcFiles;

	@Before
	public void setUp() throws Exception {
		configuration = new CompilerConfiguration();
		srcFiles = new ArrayList<>();
		final LinkedHashMap<File, Boolean> e = new LinkedHashMap<>();
		e.put(new File(this.getClass().getResource("/sandbox/src/ParseTest.tara").getPath()), true);
		srcFiles.add(e);
		srcFiles.add(new LinkedHashMap<>());
		srcFiles.add(new LinkedHashMap<>());
		CompilationInfoExtractor.getInfoFromArgsFile(new File(this.getClass().getResource("/sandbox/confFiles/sample/M3.txt").getPath()), configuration, srcFiles);
		unit = new CompilationUnit(configuration);

	}

	@Test
	public void acceptedParsing() throws Exception {
		ParseOperation operation = new ParseOperation(unit);
		for (File srcFile : srcFiles.get(0).keySet()) {
			operation.call(new SourceUnit(srcFile, configuration, unit.getErrorCollector(), srcFiles.get(0).get(srcFile)));
			assertFalse(unit.getErrorCollector().hasErrors());
		}
	}

	@Test
	public void acceptedGeneratedModel() throws Exception {
		ParseOperation operation = new ParseOperation(unit);
		for (File srcFile : srcFiles.get(0).keySet()) {
			operation.call(new SourceUnit(srcFile, configuration, unit.getErrorCollector(), srcFiles.get(0).get(srcFile)));
			assertFalse(unit.getErrorCollector().hasErrors());
		}
	}
}
