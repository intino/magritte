package io.intino.compiler;

import io.intino.builder.CompilationInfoExtractor;
import io.intino.builder.CompilerConfiguration;
import io.intino.tara.builder.FileSourceProvider;
import io.intino.tara.builder.core.CompilationUnit;
import io.intino.tara.builder.core.SourceUnit;
import io.intino.tara.builder.core.operation.sourceunit.ParseOperation;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;

@Ignore
public class ModelGenerationOperationTest {
	private final CompilerConfiguration configuration = new CompilerConfiguration();
	private CompilationUnit unit;
	private final Map<URI, Boolean> srcFiles = new LinkedHashMap<>();

	@Before
	public void setUp() {
		srcFiles.put(new File(this.getClass().getResource("/sandbox/src/ParseTest.tara").getPath()).toURI(), true);
		CompilationInfoExtractor.getInfoFromArgsFile(new File(this.getClass().getResource("/sandbox/confFiles/sample/M3.txt").getPath()).toURI(), configuration, srcFiles);
		unit = new CompilationUnit(configuration);

	}

	@Test
	public void acceptedParsing() {
		ParseOperation operation = new ParseOperation(unit);
		for (URI srcFile : srcFiles.keySet()) {
			FileSourceProvider fileSourceProvider = new FileSourceProvider(Map.of(srcFile, true));
			operation.call(new SourceUnit(fileSourceProvider.all().findFirst().get(), configuration, unit.getErrorCollector()));
			assertFalse(unit.getErrorCollector().hasErrors());
		}
	}

	@Test
	public void acceptedGeneratedModel() {
		ParseOperation operation = new ParseOperation(unit);
		for (URI srcFile : srcFiles.keySet()) {
			FileSourceProvider fileSourceProvider = new FileSourceProvider(Map.of(srcFile, true));
			operation.call(new SourceUnit(fileSourceProvider.all().findFirst().get(), configuration, unit.getErrorCollector()));
			assertFalse(unit.getErrorCollector().hasErrors());
		}
	}
}
