package pandora;

import org.siani.itrules.engine.formatters.StringFormatter;
import pandora.object.ObjectData;
import pandora.object.ObjectData;

public class DataTypes {
	public static String formatName(ObjectData self) {
		return StringFormatter.get().get("firstuppercase").format(self.format().name()).toString();
	}
}
