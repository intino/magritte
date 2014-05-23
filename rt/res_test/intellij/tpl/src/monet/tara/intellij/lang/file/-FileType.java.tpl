package monet.::projectName::.intellij.lang.file;

import com.intellij.openapi.fileTypes.LanguageFileType;
import monet.::projectName::.intellij.lang.::projectProperName::Icons;
import monet.::projectName::.intellij.lang.::projectProperName::Language;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ::projectProperName::FileType extends LanguageFileType {
	public static final ::projectProperName::FileType INSTANCE = new ::projectProperName::FileType();

	private ::projectProperName::FileType() {
		super(::projectProperName::Language.INSTANCE);
	}

	\@NotNull
	\@Override
	public String getName() {
		return "::projectProperName:: file";
	}

	\@NotNull
	\@Override
	public String getDescription() {
		return "::projectProperName:: language file";
	}

	\@NotNull
	\@Override
	public String getDefaultExtension() {
		return "m1";
	}

	\@Nullable
	\@Override
	public javax.swing.Icon getIcon() {
		return ::projectProperName::Icons.getIcon(::projectProperName::Icons.DEFINITION);
	}


}