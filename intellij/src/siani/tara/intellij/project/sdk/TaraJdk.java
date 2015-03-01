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

	private static final Logger LOG = Logger.getInstance(TaraJdk.class.getName());

	private static final Icon ADD_SDK = TaraIcons.getIcon(TaraIcons.ADD_SDK);

	private static final Icon SDK_CLOSED = TaraIcons.getIcon(TaraIcons.SDK_CLOSED);
	@NonNls
	private static final String LIB_DIR_NAME = "lib";
	@NonNls
	private static final String SRC_DIR_NAME = "src";
	protected static final String JUNIT_JAR = "junit.jar";
	protected static final String JAR = ".jar";
	protected static final String ZIP = ".zip";
	protected static final String SRC_ZIP = "src.zip";

	public TaraJdk() {
		super("TARA JDK");
	}

	@Nullable
	public static String getBuildNumber(String ideaHome) {
		try {
			@NonNls final String buildTxt = "/build.txt";
			return FileUtil.loadFile(new File(ideaHome + buildTxt)).trim();
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
	}

	public static void setupSdkPaths(final SdkModificator sdkModificator, final String sdkHome, final Sdk internalJava) {
		addClasses(sdkModificator, internalJava);
		addDocs(sdkModificator, internalJava);
		addSources(sdkModificator, internalJava);
		if (isFromTARAProject(sdkHome)) {
			final VirtualFile[] ideaLib = getIdeaLibrary(sdkHome);
			if (ideaLib != null)
				for (VirtualFile aIdeaLib : ideaLib)
					sdkModificator.addRoot(aIdeaLib, OrderRootType.CLASSES);
			addSources(new File(sdkHome), sdkModificator);
			addModel(new File(sdkHome));
		}
	}

	@Nullable
	private static Sdk getInternalJavaSdk(final Sdk sdk) {
		final SdkAdditionalData data = sdk.getSdkAdditionalData();
		if (data instanceof Tdk)
			return ((Tdk) data).getJavaSdk();
		return null;
	}

	private static VirtualFile[] getIdeaLibrary(String home) {
		ArrayList<VirtualFile> result = new ArrayList<>();
		appendLibrary(home, result, JUNIT_JAR);
		return VfsUtilCore.toVirtualFileArray(result);
	}

	public static boolean isValidInternalJdk(Sdk sdk) {
		final SdkTypeId sdkType = sdk.getSdkType();
		if (sdkType instanceof JavaSdk) {
			final JavaSdkVersion version = JavaSdk.getInstance().getVersion(sdk);
			JavaSdkVersion requiredVersion = getRequiredJdkVersion();
			if (version != null && requiredVersion != null)
				return version.isAtLeast(requiredVersion);
		}
		return false;
	}

	public static SdkType getInstance() {
		return SdkType.findInstance(TaraJdk.class);
	}

	public static boolean isFromTARAProject(String path) {
		File home = new File(path, LIB_DIR_NAME);
		File[] tdkFile = home.listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				@NonNls final String name = pathname.getName();
				return name.equals("magritte.jar") && !pathname.isDirectory();
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
		productName = "Magritte ";
		String buildNumber = getBuildNumber(sdkHome);
		return productName + (buildNumber != null ? buildNumber : "");
	}

	public boolean setupSdkPaths(final Sdk sdk, SdkModel sdkModel) {
		final Tdk additionalData = (Tdk) sdk.getSdkAdditionalData();
		if (additionalData != null)
			additionalData.cleanupWatchedRoots();

		final SdkModificator sdkModificator = sdk.getSdkModificator();

		final List<String> javaSdks = new ArrayList<>();
		final Sdk[] sdks = sdkModel.getSdks();
		for (Sdk jdk : sdks)
			if (isValidInternalJdk(jdk))
				javaSdks.add(jdk.getName());
		if (javaSdks.isEmpty()) {
			JavaSdkVersion requiredVersion = getRequiredJdkVersion();
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
			sdkModificator.setSdkAdditionalData(new Tdk(getDefaultTdk(), jdk, sdk));
			sdkModificator.setVersionString(jdk.getVersionString());
			sdkModificator.commitChanges();
			return true;
		}
		return false;
	}

	public AdditionalDataConfigurable createAdditionalDataConfigurable(final SdkModel sdkModel, SdkModificator sdkModificator) {
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
		if (additionalData instanceof Tdk)
			try {
				((Tdk) additionalData).writeExternal(additional);
			} catch (WriteExternalException e) {
				LOG.error(e);
			}
	}

	public SdkAdditionalData loadAdditionalData(@NotNull Sdk sdk, Element additional) {
		Tdk sandbox = new Tdk(sdk);
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

	static String getDefaultTdk() {
		@NonNls String defaultTdk = "";
		try {
			defaultTdk = new File(PathManager.getSystemPath()).getCanonicalPath() + File.separator + "plugins-sandbox";
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
		return defaultTdk;
	}

	private static void appendLibrary(final String libDirPath,
	                                  final List<VirtualFile> result,
	                                  @NonNls final String... forbidden) {
		Arrays.sort(forbidden);
		final File lib = new File(libDirPath + File.separator + LIB_DIR_NAME);
		if (lib.isDirectory()) {
			File[] jars = lib.listFiles();
			if (jars != null) processJars(jars, result, forbidden);
		}
	}

	private static void processJars(File[] jars, List<VirtualFile> result, String[] forbidden) {
		final JarFileSystem jfs = JarFileSystem.getInstance();
		for (File jar : jars) {
			@NonNls String name = jar.getName();
			if (jar.isFile() && Arrays.binarySearch(forbidden, name) < 0 && (name.endsWith(JAR) || name.endsWith(ZIP)))
				result.add(jfs.findFileByPath(jar.getPath() + JarFileSystem.JAR_SEPARATOR));
		}
	}

	@Nullable
	private static JavaSdkVersion getRequiredJdkVersion() {
		return JavaSdkVersion.JDK_1_7;
	}

	private static void addModel(File sdkHome) {
		TaraLanguage.addModelRoot(new File(sdkHome, TaraLanguage.DSL).getAbsolutePath());
	}

	private static void addSources(File file, SdkModificator sdkModificator) {
		final File src = new File(new File(file, LIB_DIR_NAME), SRC_DIR_NAME);
		if (!src.exists()) return;
		File[] srcs = src.listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				@NonNls final String path = pathname.getPath();
				return !path.contains("generics") && (path.endsWith(JAR) || path.endsWith(ZIP));
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
			for (Sdk jdk : ProjectJdkTable.getInstance().getAllJdks())
				if (jdk.getSdkType() instanceof JavaSdk) {
					addOrderEntries(JavadocOrderRootType.getInstance(), jdk, sdkModificator);
					break;
				}
		}
	}

	private static void addSources(SdkModificator sdkModificator, final Sdk javaSdk) {
		if (javaSdk != null && !addOrderEntries(OrderRootType.SOURCES, javaSdk, sdkModificator)) {
			if (SystemInfo.isMac) {
				for (Sdk jdk : ProjectJdkTable.getInstance().getAllJdks())
					if (jdk.getSdkType() instanceof JavaSdk) {
						addOrderEntries(OrderRootType.SOURCES, jdk, sdkModificator);
						break;
					}
			} else {
				final File jarFile = new File(new File(javaSdk.getHomePath()).getParentFile(), SRC_ZIP);
				if (jarFile.exists()) {
					JarFileSystem jarFileSystem = JarFileSystem.getInstance();
					String path = jarFile.getAbsolutePath().replace(File.separatorChar, '/') + JarFileSystem.JAR_SEPARATOR;
					jarFileSystem.setNoCopyJarForPath(path);
					sdkModificator.addRoot(jarFileSystem.findFileByPath(path), OrderRootType.SOURCES);
				}
			}
		}

	}

	private static boolean addOrderEntries(OrderRootType orderRootType, Sdk sdk, SdkModificator sdkModificator) {
		boolean wasSmthAdded = false;
		final String[] entries = sdk.getRootProvider().getUrls(orderRootType);
		for (String entry : entries) {
			VirtualFile virtualFile = VirtualFileManager.getInstance().findFileByUrl(entry);
			if (virtualFile != null) {
				sdkModificator.addRoot(virtualFile, orderRootType);
				wasSmthAdded = true;
			}
		}
		return wasSmthAdded;
	}
}