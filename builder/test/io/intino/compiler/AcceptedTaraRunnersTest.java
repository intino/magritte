package io.intino.compiler;

import com.github.difflib.DiffUtils;
import com.github.difflib.patch.AbstractDelta;
import com.github.difflib.patch.Patch;
import io.intino.tara.builder.utils.FileSystemUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static io.intino.magritte.builder.MagrittecRunner.main;

@Ignore
public class AcceptedTaraRunnersTest {
	private String home;

	@Test
	public void name() throws IOException {
		String dir = "/Users/oroncal/Library/Caches/JetBrains/IntelliJIdea2024.1/compile-server/tafat_71aee1a0/_temp_";
		File file = new File(dir, "ideaTaraToCompile.txt");
		while (true) {
			if (file.exists() && file.length() > 0) {
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
	public void konos_M2() throws IOException {
		String dir = "/Users/oroncal/workspace/infrastructure/magritte/test-playground/konos/konos/";
		String checkDir = "/Users/oroncal/workspace/infrastructure/magritte/test-playground/konos_check/";
		FileSystemUtils.removeDir(dir + "gen");
		main(new String[]{temp(home + "sandbox/confFiles/konos/m2.txt")});
		check(new File(dir), new File(checkDir));
	}

	static Set<String> Excluded = Set.of("Sentinel.java");

	private static void check(File root, File checkRoot) throws IOException {
		ArrayList<File> list = new ArrayList<>();
		FileSystemUtils.getAllFiles(new File(root, "gen"), list);
		int count = 0;
		for (File file : list) {
			if (Excluded.contains(file.getName())) continue;
			if (!file.getName().endsWith(".java")) continue;
			List<String> actual = Files.readAllLines(file.toPath()).stream().map(l -> l.replace("  ", " ")).toList();
			List<String> expected = Files.readAllLines(expected(root, checkRoot, file)).stream().map(l -> l.replace("  ", " ").replace(")!", ") !")).toList();
			Patch<String> patch = DiffUtils.diff(actual, expected);
			for (AbstractDelta<String> delta : patch.getDeltas()) {
				String actualText = String.join("\n", delta.getSource().getLines());
				String expectedText = String.join("\n", delta.getTarget().getLines());
				if (actualText.trim().equals(expectedText.trim())) continue;
				System.out.println(delta.getType() + " on " + file.getAbsolutePath().replace(root.getAbsolutePath(), "") + " " + delta.getSource().getPosition());
				System.out.println(actualText);
				System.out.println("vs expected:");
				System.out.println(expectedText);
				System.out.println();
				System.out.println("---------------");
				count++;
			}
			if (count > 0) break;

		}
	}

	private static Path expected(File root, File checkRoot, File file) {
		return Path.of(file.getAbsolutePath().replace(root.getAbsolutePath(), checkRoot.getAbsolutePath()));
	}

	@Test
	public void konos_M1() {
		System.out.println(Instant.now().toString());
		main(new String[]{temp(home + "sandbox/confFiles/konos/m1-test.txt")});
		System.out.println(Instant.now().toString());
	}
}