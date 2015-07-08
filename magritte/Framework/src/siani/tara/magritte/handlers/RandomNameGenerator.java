package siani.tara.magritte.handlers;

import siani.tara.magritte.NameGenerator;

public class RandomNameGenerator implements NameGenerator {
	private static String[] syllabus = {"b%#$", "c%#$", "d#$", "dr#s", "f%#$", "g%#$", "j#$", "k%#$", "l#$", "m#$", "n#$", "p%#$", "r#$", "s#$", "t%#$", "v#$", "y#$", "z#$"};
	private static String[] vowel = {"a", "e", "i", "o", "u"};
	private static String[] middle = {"", "r", "l"};
	private static String[] ending = {"", "n", "r", "s", "l", "t", "c"};

	@Override
	public String generate() {
		return (generateSyllab() + generate(1)).replace("cc", "c").replace("tt", "t").replace("ss", "s");
	}

	private String generate(double threshold) {
		return Math.random() < threshold ? generateSyllab() + generate(threshold / 2) : "";
	}

	private String generateSyllab() {
		return random(syllabus).replace("#", random(vowel)).replace("%", random(middle)).replace("$", random(ending));
	}

	private String random(String[] values) {
		return values[random(values.length)];
	}

	private static int random(int length) {
		return (int) (Math.random() * length);
	}

}
