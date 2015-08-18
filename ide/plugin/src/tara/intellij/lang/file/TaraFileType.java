package tara.intellij.lang.file;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.TaraIcons;
import tara.intellij.lang.TaraLanguage;

public class TaraFileType extends LanguageFileType {
	public static final TaraFileType INSTANCE = new TaraFileType();

	private TaraFileType() {
		super(TaraLanguage.INSTANCE);
	}

	@NotNull
	@Override
	public String getName() {
		return "Tara";
	}

	@NotNull
	@Override
	public String getDescription() {
		return "Tara file";
	}

	@NotNull
	@Override
	public String getDefaultExtension() {
		return "tara";
	}

	@Nullable
	@Override
	public javax.swing.Icon getIcon() {
		return TaraIcons.MODEL;
	}
}