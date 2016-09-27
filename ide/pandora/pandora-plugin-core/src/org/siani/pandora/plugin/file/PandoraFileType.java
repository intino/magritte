package org.siani.pandora.plugin.file;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.siani.pandora.plugin.PandoraIcons;
import tara.intellij.lang.file.TaraFileType;

public class PandoraFileType extends TaraFileType {
	private static TaraFileType INSTANCE;


	private PandoraFileType() {
		super();
	}

	public static PandoraFileType instance() {
		return INSTANCE != null ? (PandoraFileType) INSTANCE : (PandoraFileType) (INSTANCE = new PandoraFileType());
	}

	@NotNull
	public String getName() {
		return "Teseo";
	}

	@NotNull
	public String getDescription() {
		return "Teseo file";
	}

	@NotNull
	public String getDefaultExtension() {
		return "pandora";
	}

	@Nullable
	@Override
	public javax.swing.Icon getIcon() {
		return PandoraIcons.ICON_16;
	}


}