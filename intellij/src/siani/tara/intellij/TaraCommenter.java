package siani.tara.intellij;

import com.intellij.lang.Commenter;

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
