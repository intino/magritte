package magritte;

import java.util.Arrays;

public enum Tag {
	Case(1),
	Root(2),
	Main(4),
	Abstract(8),
	Facet(16),
	Prototype(32),
	Singleton(64),
	Edited(512),
	None(0);

	public final int flag;

	private Tag(int flag) {
		this.flag = flag;
	}

	public static int flags(Tag... tags) {
		int result = 0;
		for (Tag tag : tags) result += tag.flag;
		return result;
	}

	public static Tag[] tags(int flags) {
		Tag[] result = values();
		int size = 0;
		for (int i = 0; i < result.length; i++) {
			if ((flags & 1 << i) == 0) continue;
			result[size++] = result[i];
		}
		return Arrays.copyOf(result, size);
	}

	public static int mask(Tag... tags) {
		int value = 0;
		for (Tag tag : tags)
			value = value | tag.flag;
		return value;
	}

	public static enum Link {
		Class,
		Parent, Child,
		OwnedMember, RequiredMember, OptionalMember, AggregableMember, Owner,
		FanOut, FanIn
	}

}
