package tara.compiler;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import tara.compiler.codegeneration.FileSystemUtils;
import tara.compiler.core.CompilationUnit;
import tara.compiler.core.CompilerConfiguration;
import tara.compiler.core.SourceUnit;
import tara.compiler.core.operation.sourceunit.ParseOperation;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static tara.CompilationInfoExtractor.getInfoFromArgsFile;

@Ignore
public class BuildPhasesTest {


	private String retrieveFilesConnection = "git archive --remote=git@bitbucket.org:siani/tafat-platform.git develop Base/definitions/Main.tara > file.tar.gz";
	private CompilerConfiguration configuration;
	private CompilationUnit unit;
	private Map<File, Boolean> srcFiles;

	@Before
	public void setUp() throws Exception {
		configuration = new CompilerConfiguration();
		srcFiles = definitionFiles();
		getInfoFromArgsFile(new File(this.getClass().getResource("/sandbox/confFiles/sample/M3.txt").getPath()), configuration, srcFiles);
		unit = new CompilationUnit(configuration);
	}

	private Map<File, Boolean> definitionFiles() throws InterruptedException {
		try {
			final File temp = Files.createTempDirectory("_test_tara_").toFile();
			final Process exec = Runtime.getRuntime().exec(retrieveFilesConnection, new String[0], temp);
			exec.waitFor();
			BufferedReader reader =
				new BufferedReader(new InputStreamReader(exec.getErrorStream()));
			String line;
			String text = "";
			while ((line = reader.readLine()) != null)
				text += line + "\n";
			System.out.println(text);
			Map<File, Boolean> map = new HashMap<>();
			final ArrayList<File> files = new ArrayList<>();
			FileSystemUtils.getAllFiles(temp, files);
			files.stream().filter(file -> file.getName().endsWith(".tara")).forEach(file -> map.put(file, true));
			return map;
		} catch (IOException ignored) {
		}
		return Collections.emptyMap();
	}

	@Test
	public void parse() throws Exception {
		ParseOperation operation = new ParseOperation(unit);
		for (File srcFile : srcFiles.keySet()) {
			operation.call(new SourceUnit(srcFile, configuration, unit.getErrorCollector(), srcFiles.get(srcFile)));
			assertFalse(unit.getErrorCollector().hasErrors());
		}
	}
}
