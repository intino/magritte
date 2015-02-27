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
import siani.tara.intellij.MessageProvider;

public class Tdk implements ValidatableSdkAdditionalData, JDOMExternalizable {
	private static final Logger LOG = Logger.getInstance("#org.jetbrains.idea.devkit.projectRoots.TDK");

	@SuppressWarnings({"WeakerAccess"})
	public String myTdkHome;
	private final Sdk myCurrentJdk;

	private String myJavaSdkName;
	private Sdk myJavaSdk;

	private LocalFileSystem.WatchRequest myTDKRoot = null;
	@NonNls
	private static final String SDK = "sdk";

	public Tdk(String myTdkHome, Sdk javaSdk, Sdk currentJdk) {
		this.myTdkHome = myTdkHome;
		myCurrentJdk = currentJdk;
		if (this.myTdkHome != null) myTDKRoot = LocalFileSystem.getInstance().addRootToWatch(this.myTdkHome, true);
		myJavaSdk = javaSdk;
	}

	//readExternal()
	public Tdk(Sdk currentSdk) {
		myCurrentJdk = currentSdk;
	}

	public String getTdkHome() {
		return myTdkHome;
	}

	public Object clone() throws CloneNotSupportedException {
		return new Tdk(myTdkHome, getJavaSdk(), myCurrentJdk);
	}

	public void checkValid(SdkModel sdkModel) throws ConfigurationException {
		if (myTdkHome == null || myTdkHome.length() == 0 || getJavaSdk() == null) {
			throw new ConfigurationException(MessageProvider.message("Magritte.specification"));
		}
	}

	public void readExternal(Element element) throws InvalidDataException {
		DefaultJDOMExternalizer.readExternal(this, element);
		LOG.assertTrue(myTDKRoot == null);
		myJavaSdkName = element.getAttributeValue(SDK);
		if (myTdkHome != null) {
			myTDKRoot = LocalFileSystem.getInstance().addRootToWatch(myTdkHome, true);
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
		if (myTDKRoot != null) {
			LocalFileSystem.getInstance().removeWatchedRoot(myTDKRoot);
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
					if (TaraJdk.isValidInternalJdk(jdk)) {
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