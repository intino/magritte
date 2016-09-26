package pandora;

import org.siani.itrules.engine.formatters.StringFormatter;
import pandora.object.ObjectData;
import pandora.object.ObjectData;

public class Pandora {
	public static String schemaName(ObjectData self) {
		return StringFormatter.get().get("firstuppercase").format(self.schema().name()).toString();
	}
}
