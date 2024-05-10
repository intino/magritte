package io.intino.compiler;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Instant;

import static io.intino.magritte.builder.MagrittecRunner.main;

@Ignore
public class AcceptedTaraRunnersTest {
	private String home;

	@Test
	public void name() throws IOException {
		var project = "datahub-3_2c0a2b10";
		String dor = "/Users/oroncal/workspace/sandbox/intellij2024/system/compile-server/" + project + "/_temp_/";
		File file = new File(dor + "ideaTaraToCompile.txt");
		while (true) {
			if (file.exists()) {
				System.out.println(Files.readString(file.toPath()));
				return;
			}
		}
	}

	private static String temp(String filepath) {
		try {
			File file = new File(filepath);
			String home = System.getProperty("user.home");
			String text = Files.readString(file.toPath()).replace("$WORKSPACE", home + File.separator + "workspace").replace("$HOME", home);
			Path temporalFile = Files.createTempFile(file.getName(), ".txt");
			Files.writeString(temporalFile, text, StandardOpenOption.TRUNCATE_EXISTING);
			temporalFile.toFile().deleteOnExit();
			return temporalFile.toFile().getAbsolutePath();
		} catch (IOException e) {
			return null;
		}
	}

	@Before
	public void setUp() {
		home = new File("test-res").getAbsolutePath() + File.separator;
	}

	@Test
	public void cesarM2() {
		main(new String[]{temp(home + "sandbox/confFiles/cesar/M2.txt")});
	}

	@Test
	public void legioM2() {
		main(new String[]{temp(home + "sandbox/confFiles/legio/M2.txt")});
	}

	@Test
	public void legioM1() {
		main(new String[]{temp(home + "sandbox/confFiles/legio/M1.txt")});
	}

	@Test
	public void consulM2() {
		main(new String[]{temp(home + "sandbox/confFiles/cesar/consul.txt")});
	}

	@Test
	public void exampleM3() {
		main(new String[]{temp(home + "sandbox/confFiles/example/m3.txt")});
	}

	@Test
	public void exampleM1() {
		main(new String[]{temp(home + "sandbox/confFiles/example/m1.txt")});
	}

	@Test
	public void exampleM2() {
		main(new String[]{temp(home + "sandbox/confFiles/example/m2.txt")});
	}

	@Test
	public void example_m2_2() {
		main(new String[]{temp(home + "sandbox/confFiles/example/m2_2.txt")});
	}

	@Test
	public void ness_m2() {
		main(new String[]{temp(home + "sandbox/confFiles/ness/m2.txt")});
	}

	@Test
	public void ness_m1() {
		main(new String[]{temp(home + "sandbox/confFiles/ness/m1.txt")});
	}

	@Test
	public void ness_m1_2() {
		main(new String[]{temp(home + "sandbox/confFiles/ness/m1_2.txt")});
	}

	@Test
	public void ness_m2_2() {
		main(new String[]{temp(home + "sandbox/confFiles/ness/m2_2.txt")});
	}

	@Test
	public void konos_M1() {
		System.out.println(Instant.now().toString());
		main(new String[]{temp(home + "sandbox/confFiles/konos/m1-test.txt")});
		System.out.println(Instant.now().toString());
	}

	@Test
	public void konos_M2() {
		System.out.println(Instant.now().toString());
		main(new String[]{temp(home + "sandbox/confFiles/konos/m2.txt")});
		System.out.println(Instant.now().toString());
	}

}