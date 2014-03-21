package monet.tara.intellij.metamodel.file;

import com.intellij.openapi.fileTypes.LanguageFileType;
import monet.tara.intellij.metamodel.TaraIcons;
import monet.tara.intellij.metamodel.TaraLanguage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TaraFileType extends LanguageFileType {
	public static final TaraFileType INSTANCE = new TaraFileType();

	private TaraFileType() {
		super(TaraLanguage.INSTANCE);
	}

	@NotNull
	@Override
	public String getName() {
		return "Tara file";
	}

	@NotNull
	@Override
	public String getDescription() {
		return "Tara language file";
	}

	@NotNull
	@Override
	public String getDefaultExtension() {
		return "m2";
	}

	@Nullable
	@Override
	public javax.swing.Icon getIcon() {
		return TaraIcons.ICON_13;
	}
}