package siani.tafat.intellij.metamodel.file;

import com.intellij.openapi.fileTypes.LanguageFileType;
import siani.tafat.intellij.metamodel.TafatIcons;
import siani.tafat.intellij.metamodel.TafatLanguage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TafatFileType extends LanguageFileType {
	public static final TafatFileType INSTANCE = new TafatFileType();

	private TafatFileType() {
		super(TafatLanguage.INSTANCE);
	}

	@NotNull
	@Override
	public String getName() {
		return "Tafat file";
	}

	@NotNull
	@Override
	public String getDescription() {
		return "Tafat language file";
	}

	@NotNull
	@Override
	public String getDefaultExtension() {
		return "m1";
	}

	@Nullable
	@Override
	public javax.swing.Icon getIcon() {
		return TafatIcons.ICON;
	}
}