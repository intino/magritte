package siani.tara.intellij.project.sdk;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.projectRoots.ProjectJdkTable;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.projectRoots.SdkModel;
import com.intellij.openapi.projectRoots.ValidatableSdkAdditionalData;
import com.intellij.openapi.util.DefaultJDOMExternalizer;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.JDOMExternalizable;
import com.intellij.openapi.util.WriteExternalException;
import com.intellij.openapi.vfs.LocalFileSystem;
import org.jdom.Element;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.TaraBundle;

public class Magritte implements ValidatableSdkAdditionalData, JDOMExternalizable {
	private static final Logger LOG = Logger.getInstance("#org.jetbrains.idea.devkit.projectRoots.Magritte");

	@SuppressWarnings({"WeakerAccess"})
	public String myMagritteHome;
	private final Sdk myCurrentJdk;

	private String myJavaSdkName;
	private Sdk myJavaSdk;

	private LocalFileSystem.WatchRequest myMagritteRoot = null;
	@NonNls
	private static final String SDK = "sdk";

	public Magritte(String MagritteHome, Sdk javaSdk, Sdk currentJdk) {
		myMagritteHome = MagritteHome;
		myCurrentJdk = currentJdk;
		if (myMagritteHome != null) {
			myMagritteRoot = LocalFileSystem.getInstance().addRootToWatch(myMagritteHome, true);
		}
		myJavaSdk = javaSdk;
	}

	//readExternal()
	public Magritte(Sdk currentSdk) {
		myCurrentJdk = currentSdk;
	}

	public String getMagritteHome() {
		return myMagritteHome;
	}

	public Object clone() throws CloneNotSupportedException {
		return new Magritte(myMagritteHome, getJavaSdk(), myCurrentJdk);
	}

	public void checkValid(SdkModel sdkModel) throws ConfigurationException {
		if (myMagritteHome == null || myMagritteHome.length() == 0 || getJavaSdk() == null) {
			throw new ConfigurationException(TaraBundle.message("Magritte.specification"));
		}
	}

	public void readExternal(Element element) throws InvalidDataException {
		DefaultJDOMExternalizer.readExternal(this, element);
		LOG.assertTrue(myMagritteRoot == null);
		myJavaSdkName = element.getAttributeValue(SDK);
		if (myMagritteHome != null) {
			myMagritteRoot = LocalFileSystem.getInstance().addRootToWatch(myMagritteHome, true);
		}
	}

	public void writeExternal(Element element) throws WriteExternalException {
		DefaultJDOMExternalizer.writeExternal(this, element);
		final Sdk sdk = getJavaSdk();
		if (sdk != null) {
			element.setAttribute(SDK, sdk.getName());
		}
	}

	void cleanupWatchedRoots() {
		if (myMagritteRoot != null) {
			LocalFileSystem.getInstance().removeWatchedRoot(myMagritteRoot);
		}
	}

	@Nullable
	public Sdk getJavaSdk() {
		final ProjectJdkTable jdkTable = ProjectJdkTable.getInstance();
		if (myJavaSdk == null) {
			if (myJavaSdkName != null) {
				myJavaSdk = jdkTable.findJdk(myJavaSdkName);
				myJavaSdkName = null;
			} else {
				for (Sdk jdk : jdkTable.getAllJdks()) {
					if (TaraJdk.isValidInternalJdk(myCurrentJdk, jdk)) {
						myJavaSdk = jdk;
						break;
					}
				}
			}
		}
		return myJavaSdk;
	}

	public void setJavaSdk(final Sdk javaSdk) {
		myJavaSdk = javaSdk;
	}
}