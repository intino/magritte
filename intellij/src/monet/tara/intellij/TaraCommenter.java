package monet.tara.intellij;

import com.intellij.lang.Commenter;

/**
 * Created by oroncal on 10/01/14.
 */
public class TaraCommenter implements Commenter {

	public String getLineCommentPrefix() {
		return "#";
	}

	public String getBlockCommentPrefix() {
		return null;
	}

	public String getBlockCommentSuffix() {
		return "";
	}

	public String getCommentedBlockCommentPrefix() {
		return null;
	}

	public String getCommentedBlockCommentSuffix() {
		return null;
	}
}
