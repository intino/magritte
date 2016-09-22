package teseo.natives.list.data;



/**#/Users/oroncal/workspace/tara/ide/teseo/application/src/teseo/Teseo.tara#49#1**/
public class Type_0 implements tara.magritte.Expression<String> {
	private teseo.list.ListData self;

	@Override
	public String value() {
		return "List";
	}

	@Override
	public void self(tara.magritte.Layer context) {
		self = (teseo.list.ListData) context;
	}

	@Override
	public Class<? extends tara.magritte.Layer> selfClass() {
		return teseo.list.ListData.class;
	}
}