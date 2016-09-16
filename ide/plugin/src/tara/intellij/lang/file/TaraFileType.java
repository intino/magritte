package tara.intellij.lang.file;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.TaraIcons;
import tara.intellij.lang.TaraLanguage;

public class TaraFileType extends LanguageFileType {
	private static TaraFileType INSTANCE;

	protected TaraFileType() {
		super(TaraLanguage.INSTANCE);
	}

	public static TaraFileType instance() {
		return INSTANCE != null ? INSTANCE : (INSTANCE = new TaraFileType());
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
		return TaraIcons.ICON_16;
	}
}