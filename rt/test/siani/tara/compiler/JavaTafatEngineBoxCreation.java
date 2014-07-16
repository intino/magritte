package siani.tara.compiler;

import org.junit.Assert;
import org.junit.Test;
import siani.tara.compiler.codegeneration.model.ModelProvider;
import siani.tara.compiler.core.CompilationUnit;
import siani.tara.compiler.core.CompilerConfiguration;
import siani.tara.compiler.core.operation.ModelToJavaOperation;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JavaTafatEngineBoxCreation {


	@Test
	public void testTafatEngineCoreBox() throws Exception {
		CompilationUnit mockUnit = mock(CompilationUnit.class);
		CompilerConfiguration mockConfiguration = mock(CompilerConfiguration.class);
		when(mockUnit.getConfiguration()).thenReturn(mockConfiguration);
		File value = new File("/Users/oroncal/workspace/tara/rt/res_test/Tafat/engine/out");
		when(mockConfiguration.getOutDirectory()).thenReturn(value);
		ModelToJavaOperation toJavaOperation = new ModelToJavaOperation(mockUnit);
		toJavaOperation.call(ModelProvider.getModel("/Users/oroncal/workspace/tara/rt/res_test/", "engine"));
		String text = new String(Files.readAllBytes(Paths.get(value.getAbsolutePath() + "/tafat/engine/core/Entity.java")), StandardCharsets.UTF_8);
		Assert.assertEquals(expectedTafatEngineCoreBox, text);
	}

	private String expectedTafatEngineCoreBox = "package magritte.model;\n" +
		"\n" +
		"import magritte.schema.Model;\n" +
		"import Tafat.engine.core.Entity;\n" +
		"import Tafat.engine.core.Simulation;\n" +
		"\n" +
		"import static magritte.schema.Annotation.*;\n" +
		"\n" +
		"public class TafatEngineCoreBox extends Box {\n" +
		"\n" +
		"    public static final String BEHAVIOR = \"Behavior\";\n" +
		"    public static final String SIMULATION = \"Simulation\";\n" +
		"    public static final String ASPECT = \"Aspect\";\n" +
		"    public static final String ENTITY = \"Entity\";\n" +
		"\n" +
		"    public static void load(Model model) {\n" +
		"        new TafatEngineCoreBox(model);\n" +
		"    }\n" +
		"\n" +
		"    private TafatEngineCore(Model model) {\n" +
		"        super(model);\n" +
		"    }\n" +
		"\n" +
		"    @Override\n" +
		"    protected void loadDependencies() {\n" +
		"        \n" +
		"    }\n" +
		"\n" +
		"    @Override\n" +
		"    protected void loadNodes() {\n" +
		"        def(BEHAVIOR);as(INTENTION); \n" +
		"            cast(Behavior.class);\n" +
		"        end();\n" +
		"        def(ASPECT);as(INTENTION); \n" +
		"            cast(Aspect.class);\n" +
		"        end();\n" +
		"        def(ENTITY);as(CONCEPT); is(ROOT);\n" +
		"            cast(Entity.class);\n" +
		"        end();\n" +
		"        def(SIMULATION);as(CONCEPT); is(REQUIRED);is(SINGLE);is(TERMINAL);is(ROOT);\n" +
		"            cast(Simulation.class);\n" +
		"        end();\n" +
		"\n" +
		"    }\n" +
		"\n" +
		"}\n" +
		"\n";


}
