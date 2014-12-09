package siani.tara.intellij.project.sdk;

import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.projectRoots.*;
import com.intellij.openapi.projectRoots.impl.JavaDependentSdkType;
import com.intellij.openapi.roots.AnnotationOrderRootType;
import com.intellij.openapi.roots.JavadocOrderRootType;
import com.intellij.openapi.roots.OrderRootType;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.util.WriteExternalException;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.JarFileSystem;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.util.ArrayUtil;
import org.jdom.Element;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.MessageProvider;
import siani.tara.intellij.lang.TaraIcons;
import siani.tara.intellij.lang.TaraLanguage;

import javax.swing.*;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaraJdk extends JavaDependentSdkType implements JavaSdkType {
	private static final Icon ADD_SDK = TaraIcons.getIcon(TaraIcons.ADD_SDK);
	private static final Icon SDK_CLOSED = TaraIcons.getIcon(TaraIcons.SDK_CLOSED);

	private static final Logger LOG = Logger.getInstance("#org.jetbrains.idea.devkit.projectRoots.TaraJdk");
	@NonNls
	private static final String LIB_DIR_NAME = "lib";
	@NonNls
	private static final String SRC_DIR_NAME = "src";

	public TaraJdk() {
		super("TARA JDK");
	}

	@Nullable
	private static Sdk getInternalJavaSdk(final Sdk sdk) {
		final SdkAdditionalData data = sdk.getSdkAdditionalData();
		if (data instanceof Tdk2)
			return ((Tdk2) data).getJavaSdk();
		return null;
	}

	@Nullable
	public static String getBuildNumber(String ideaHome) {
		try {
			@NonNls final String buildTxt = "/build.txt";
			return FileUtil.loadFile(new File(ideaHome + buildTxt)).trim();
		} catch (IOException e) {
			return null;
		}
	}

	private static VirtualFile[] getIdeaLibrary(String home) {
		ArrayList<VirtualFile> result = new ArrayList<>();
		appendLibrary(home, result, "junit.jar");
		return VfsUtilCore.toVirtualFileArray(result);
	}

	private static void appendLibrary(final String libDirPath,
	                                  final ArrayList<VirtualFile> result,
	                                  @NonNls final String... forbidden) {
		Arrays.sort(forbidden);
		final String path = libDirPath + File.separator + LIB_DIR_NAME;
		final JarFileSystem jfs = JarFileSystem.getInstance();
		final File lib = new File(path);
		if (lib.isDirectory()) {
			File[] jars = lib.listFiles();
			if (jars != null)
				for (File jar : jars) {
					@NonNls String name = jar.getName();
					if (jar.isFile() && Arrays.binarySearch(forbidden, name) < 0 && (name.endsWith(".jar") || name.endsWith(".zip")))
						result.add(jfs.findFileByPath(jar.getPath() + JarFileSystem.JAR_SEPARATOR));
				}
		}
	}

	public static boolean isValidInternalJdk(Sdk taraJdk, Sdk sdk) {
		final SdkTypeId sdkType = sdk.getSdkType();
		if (sdkType instanceof JavaSdk) {
			final JavaSdkVersion version = JavaSdk.getInstance().getVersion(sdk);
			JavaSdkVersion requiredVersion = getRequiredJdkVersion(taraJdk);
			if (version != null && requiredVersion != null)
				return version.isAtLeast(requiredVersion);
		}
		return false;
	}

	@Nullable
	private static JavaSdkVersion getRequiredJdkVersion(final Sdk ideaSdk) {
		return JavaSdkVersion.JDK_1_7;
	}

	public static void setupSdkPaths(final SdkModificator sdkModificator, final String sdkHome, final Sdk internalJava) {
		//roots from internal jre
		addClasses(sdkModificator, internalJava);
		addDocs(sdkModificator, internalJava);
		addSources(sdkModificator, internalJava);
		//roots for openapi and other libs
		if (isFromTARAProject(sdkHome)) {
			final VirtualFile[] ideaLib = getIdeaLibrary(sdkHome);
			if (ideaLib != null)
				for (VirtualFile aIdeaLib : ideaLib)
					sdkModificator.addRoot(aIdeaLib, OrderRootType.CLASSES);
			addSources(new File(sdkHome), sdkModificator);
			addModel(new File(sdkHome));
		}
	}

	private static void addModel(File sdkHome) {
		TaraLanguage.addModelRoot(new File(sdkHome, "model").getAbsolutePath());
	}

	static String getDefaultTdk() {
		@NonNls String defaultTdk = "";
		try {
			defaultTdk = new File(PathManager.getSystemPath()).getCanonicalPath() + File.separator + "plugins-sandbox";
		} catch (IOException e) {
			//can't be on running instance
		}
		return defaultTdk;
	}

	private static void addSources(File file, SdkModificator sdkModificator) {
		final File src = new File(new File(file, LIB_DIR_NAME), SRC_DIR_NAME);
		if (!src.exists()) return;
		File[] srcs = src.listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				@NonNls final String path = pathname.getPath();
				//noinspection SimplifiableIfStatement
				if (path.contains("generics")) return false;
				return path.endsWith(".jar") || path.endsWith(".zip");
			}
		});
		for (int i = 0; srcs != null && i < srcs.length; i++) {
			File jarFile = srcs[i];
			if (jarFile.exists()) {
				JarFileSystem jarFileSystem = JarFileSystem.getInstance();
				String path = jarFile.getAbsolutePath().replace(File.separatorChar, '/') + JarFileSystem.JAR_SEPARATOR;
				jarFileSystem.setNoCopyJarForPath(path);
				VirtualFile vFile = jarFileSystem.findFileByPath(path);
				sdkModificator.addRoot(vFile, OrderRootType.SOURCES);
			}
		}
	}

	private static void addClasses(SdkModificator sdkModificator, final Sdk javaSdk) {
		addOrderEntries(OrderRootType.CLASSES, javaSdk, sdkModificator);
	}

	private static void addDocs(SdkModificator sdkModificator, final Sdk javaSdk) {
		if (!addOrderEntries(JavadocOrderRootType.getInstance(), javaSdk, sdkModificator) &&
			SystemInfo.isMac) {
			Sdk[] jdks = ProjectJdkTable.getInstance().getAllJdks();
			for (Sdk jdk : jdks)
				if (jdk.getSdkType() instanceof JavaSdk) {
					addOrderEntries(JavadocOrderRootType.getInstance(), jdk, sdkModificator);
					break;
				}
		}
	}

	private static void addSources(SdkModificator sdkModificator, final Sdk javaSdk) {
		if (javaSdk != null) {
			if (!addOrderEntries(OrderRootType.SOURCES, javaSdk, sdkModificator)) {
				if (SystemInfo.isMac) {
					Sdk[] jdks = ProjectJdkTable.getInstance().getAllJdks();
					for (Sdk jdk : jdks) {
						if (jdk.getSdkType() instanceof JavaSdk) {
							addOrderEntries(OrderRootType.SOURCES, jdk, sdkModificator);
							break;
						}
					}
				} else {
					final File jdkHome = new File(javaSdk.getHomePath()).getParentFile();
					@NonNls final String srcZip = "src.zip";
					final File jarFile = new File(jdkHome, srcZip);
					if (jarFile.exists()) {
						JarFileSystem jarFileSystem = JarFileSystem.getInstance();
						String path = jarFile.getAbsolutePath().replace(File.separatorChar, '/') + JarFileSystem.JAR_SEPARATOR;
						jarFileSystem.setNoCopyJarForPath(path);
						sdkModificator.addRoot(jarFileSystem.findFileByPath(path), OrderRootType.SOURCES);
					}
				}
			}
		}
	}

	private static boolean addOrderEntries(OrderRootType orderRootType, Sdk sdk, SdkModificator toModificator) {
		boolean wasSmthAdded = false;
		final String[] entries = sdk.getRootProvider().getUrls(orderRootType);
		for (String entry : entries) {
			VirtualFile virtualFile = VirtualFileManager.getInstance().findFileByUrl(entry);
			if (virtualFile != null) {
				toModificator.addRoot(virtualFile, orderRootType);
				wasSmthAdded = true;
			}
		}
		return wasSmthAdded;
	}

	public static SdkType getInstance() {
		return SdkType.findInstance(TaraJdk.class);
	}

	public static boolean isFromTARAProject(String path) {
		File home = new File(path, LIB_DIR_NAME);
		File[] tdkFile = home.listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				@NonNls final String name = pathname.getName();
				return name.equals("tdk.jar") && !pathname.isDirectory();
			}
		});
		return tdkFile != null && tdkFile.length != 0;
	}

	public Icon getIcon() {
		return SDK_CLOSED;
	}

	@NotNull
	@Override
	public String getHelpTopic() {
		return "reference.project.structure.sdk.idea";
	}

	public Icon getIconForAddAction() {
		return ADD_SDK;
	}

	public String suggestHomePath() {
		return PathManager.getHomePath().replace(File.separatorChar, '/');
	}

	public boolean isValidSdkHome(String path) {
		if (isFromTARAProject(path)) return true;
		File home = new File(path);
		return home.exists() && getBuildNumber(path) != null;
	}

	@Nullable
	public final String getVersionString(@NotNull final Sdk sdk) {
		final Sdk internalJavaSdk = getInternalJavaSdk(sdk);
		return internalJavaSdk != null ? internalJavaSdk.getVersionString() : null;
	}

	public String suggestSdkName(String currentSdkName, String sdkHome) {
		@NonNls final String productName;
		productName = "Tdk ";
		String buildNumber = getBuildNumber(sdkHome);
		return productName + (buildNumber != null ? buildNumber : "");
	}

	public boolean setupSdkPaths(final Sdk sdk, SdkModel sdkModel) {
		final Tdk2 additionalData = (Tdk2) sdk.getSdkAdditionalData();
		if (additionalData != null)
			additionalData.cleanupWatchedRoots();

		final SdkModificator sdkModificator = sdk.getSdkModificator();

		final List<String> javaSdks = new ArrayList<>();
		final Sdk[] sdks = sdkModel.getSdks();
		for (Sdk jdk : sdks)
			if (isValidInternalJdk(sdk, jdk))
				javaSdks.add(jdk.getName());
		if (javaSdks.isEmpty()) {
			JavaSdkVersion requiredVersion = getRequiredJdkVersion(sdk);
			if (requiredVersion != null)
				Messages.showErrorDialog(MessageProvider.message("no.java.sdk.for.idea.sdk.found", requiredVersion), "No Java SDK Found");
			else
				Messages.showErrorDialog(MessageProvider.message("no.idea.sdk.version.found"), "No Java SDK Found");
			return false;
		}

		final int choice = Messages.showChooseDialog("Select Java SDK to be used for " + MessageProvider.message("sdk.title"),
			"Select Internal Java Platform",
			ArrayUtil.toStringArray(javaSdks), javaSdks.get(0),
			Messages.getQuestionIcon());

		if (choice != -1) {
			final String name = javaSdks.get(choice);
			final Sdk jdk = sdkModel.findSdk(name);
			LOG.assertTrue(jdk != null);
			setupSdkPaths(sdkModificator, sdk.getHomePath(), jdk);
			sdkModificator.setSdkAdditionalData(new Tdk2(getDefaultTdk(), jdk, sdk));
			sdkModificator.setVersionString(jdk.getVersionString());
			sdkModificator.commitChanges();
			return true;
		}
		return false;
	}

	public AdditionalDataConfigurable createAdditionalDataConfigurable(final SdkModel sdkModel, SdkModificator sdkModificator) {
//		return new TaraJdkConfigurable(sdkModel, sdkModificator);
		return null;
	}

	@Nullable
	public String getBinPath(@NotNull Sdk sdk) {
		final Sdk internalJavaSdk = getInternalJavaSdk(sdk);
		return internalJavaSdk == null ? null : JavaSdk.getInstance().getBinPath(internalJavaSdk);
	}

	@Nullable
	public String getToolsPath(@NotNull Sdk sdk) {
		final Sdk jdk = getInternalJavaSdk(sdk);
		if (jdk != null && jdk.getVersionString() != null)
			return JavaSdk.getInstance().getToolsPath(jdk);
		return null;
	}

	@Nullable
	public String getVMExecutablePath(@NotNull Sdk sdk) {
		final Sdk internalJavaSdk = getInternalJavaSdk(sdk);
		return internalJavaSdk == null ? null : JavaSdk.getInstance().getVMExecutablePath(internalJavaSdk);
	}

	public void saveAdditionalData(@NotNull SdkAdditionalData additionalData, @NotNull Element additional) {
		if (additionalData instanceof Tdk2)
			try {
				((Tdk2) additionalData).writeExternal(additional);
			} catch (WriteExternalException e) {
				LOG.error(e);
			}
	}

	public SdkAdditionalData loadAdditionalData(@NotNull Sdk sdk, Element additional) {
		Tdk2 sandbox = new Tdk2(sdk);
		try {
			sandbox.readExternal(additional);
		} catch (InvalidDataException e) {
			LOG.error(e);
		}
		return sandbox;
	}

	public String getPresentableName() {
		return MessageProvider.message("sdk.title");
	}

	@Override
	public boolean isRootTypeApplicable(OrderRootType type) {
		return type == OrderRootType.CLASSES ||
			type == OrderRootType.SOURCES ||
			type == JavadocOrderRootType.getInstance() ||
			type == AnnotationOrderRootType.getInstance();
	}

	public String getDefaultDocumentationUrl(final @NotNull Sdk sdk) {
		return JavaSdk.getInstance().getDefaultDocumentationUrl(sdk);
	}
}