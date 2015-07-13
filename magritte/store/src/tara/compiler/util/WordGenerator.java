package tara.compiler.util;

public class WordGenerator {
	private static String[] syllabus = {"b%#$", "c%#$", "d#$", "dr#s", "f%#$", "g%#$", "j#$", "k%#$", "l#$", "m#$", "n#$", "p%#$", "ps#", "r#$", "s#$", "t%#$", "v#$", "y#$", "z#$"};
	private static String[] vowel = {"a", "e", "i", "o", "u"};
	private static String[] middle = {"", "r", "l"};
	private static String[] ending = {"", "n", "r", "s", "l", "t", "c"};

	public static String generate() {
		return generateSyllab() + generate(1);
	}

	private static String generate(double threshold) {
		return Math.random() < threshold ? generateSyllab() + generate(threshold / 2) : "";
	}

	private static String generateSyllab() {
		return random(syllabus).replace("#", random(vowel)).replace("%", random(middle)).replace("$", random(ending)).replace("cc", "c").replace("tt", "t").replace("ss", "s");
	}

	private static String random(String[] values) {
		return values[random(values.length)];
	}

	private static int random(int length) {
		return (int) (Math.random() * length);
	}

}
