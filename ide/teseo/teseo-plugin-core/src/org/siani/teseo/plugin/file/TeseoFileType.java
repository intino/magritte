package org.siani.teseo.plugin.file;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.siani.teseo.plugin.TeseoIcons;
import tara.intellij.lang.file.TaraFileType;

public class TeseoFileType extends TaraFileType {
	private static TaraFileType INSTANCE;


	private TeseoFileType() {
		super();
	}

	public static TeseoFileType instance() {
		return INSTANCE != null ? (TeseoFileType) INSTANCE : (TeseoFileType) (INSTANCE = new TeseoFileType());
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
		return "teseo";
	}

	@Nullable
	@Override
	public javax.swing.Icon getIcon() {
		return TeseoIcons.ICON_16;
	}


}