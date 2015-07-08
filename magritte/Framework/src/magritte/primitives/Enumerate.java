package magritte.primitives;

import java.util.Arrays;

public class Enumerate {

	public static int ordinal(Enum value) {
		return value.ordinal();
	}

	public static int[] ordinal(Enum[] values) {
		int[] result = new int[values.length];
		for (int i = 0; i < result.length; i++) result[i] = values[i].ordinal();
		return result;
	}

	public static <T extends Enum> T enumerate(int value, T[] set) {
		return set[value];
	}

	public static <T extends Enum> T[] cardinal(int[] values, T[] set) {
		if (values == null) values = new int[0];
		T[] result = Arrays.copyOf(set, values.length);
		for (int i = 0; i < result.length; i++) result[i] = enumerate(values[i], set);
		return result;
	}
}
