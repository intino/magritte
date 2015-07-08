package monet;

import census.Animal;
import census.Censo;
import census.CensusModel;
import census.Perro;
import magritte.*;
import magritte.handlers.RandomNameGenerator;
import magritte.primitives.Date;
import magritte.scene.AdejeMain;
import magritte.schema.MemoryGraph;
import monet.editable.Editable;
import monet.editable.Editable_Picture;
import monet.fields.*;
import monet.fields.Number;
import org.junit.Test;

import static magritte.Node.Member.Component;
import static magritte.helpers.Extract.nameOf;
import static magritte.helpers.Selection.instancesOf;
import static magritte.schema.Box.multiple;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class AcceptedMonetModel {

	public void testName() throws Exception {
//        Graph graph = Scene.graph;
//        assertThat(graph.roots().filter(instancesOf(nameOf(Form.class))).size(), is(1));
//        assertThat(graph.roots().filter(instancesOf(nameOf(Collection.class))).size(), is(1));
//        assertThat(graph.roots().filter(instancesOf(nameOf(Thesaurus.class))).size(), is(1));

	}

	@Test
	public void graph_is_well_constructed() throws Exception {
		Graph graph = adeje();

		assertThat(graph.roots().filter(instancesOf(nameOf(Form.class))).size(), is(1));
		assertThat(graph.roots().filter(instancesOf(nameOf(Collection.class))).size(), is(1));
		assertThat(graph.roots().filter(instancesOf(nameOf(Thesaurus.class))).size(), is(1));

		assertThat("Exists zunru", graph.exists("zunru"));
		assertThat("Exists MiCenso", graph.exists("MiCenso"));
		assertThat("MiCenso = #zunru", graph.get("MiCenso") == graph.get("zunru"));

		assertThat(graph.roots().filter(instancesOf(nameOf(Thesaurus.class))).get(0).get("label"), is("Intervenciones"));

		assertThat(graph.get("intervenciones").members(Component).size(), is(2));
		assertThat(graph.get("intervenciones").members(Component).get(0).name(), is("intervenciones.vacunacion"));
		assertThat(graph.get("intervenciones").members(Component).get(1).name(), is("intervenciones.desparasitacion"));

		assertThat(graph.get("intervenciones").members(Component).get(0).fanIn().size(), is(1));
		assertThat(graph.get("intervenciones").members(Component).get(0).fanIn().get(0).toString(), is("Case[yenpil:Perro:Form:Concept]"));
		assertThat(graph.get("intervenciones").members(Component).get(1).fanIn().size(), is(1));
		assertThat(graph.get("intervenciones").members(Component).get(1).fanIn().get(0).toString(), is("Case[yenpil:Perro:Form:Concept]"));
		assertThat(graph.get("yenpil").fanOut().size(), is(2));
		assertThat(graph.get("yenpil").fanOut().get(0).toString(), is("Case[intervenciones.vacunacion:Thesaurus.Term:Concept]"));
		assertThat(graph.get("yenpil").fanOut().get(1).toString(), is("Case[intervenciones.desparasitacion:Thesaurus.Term:Concept]"));

		assertThat(graph.find(instancesOf("Field")).size(), is(14));
		assertThat(graph.find(instancesOf("Field")).get(10).owner().toString(), is("Case[?:Intervencion:Composite:Concept]"));
		assertThat(graph.find(instancesOf("Field")).get(10).owner().owner().toString(), is("Case[?:Animal.Historial:Multiple:Concept]"));
		assertThat(graph.find(instancesOf("Field")).get(10).owner().owner().owner().toString(), is("Case[yenpil:Perro:Form:Concept]"));
		assertThat(graph.find(instancesOf("Field")).get(10).root().toString(), is("Case[yenpil:Perro:Form:Concept]"));
	}

	@Test
	public void nodes_with_facets_contains_components_and_variables() throws Exception {
		Graph graph = adeje();
		Node yenpil = graph.get("yenpil");
		Set<Node> editables = yenpil.members(Component).filter(instancesOf(nameOf(Editable.class)));
		assertThat(editables.size(), is(2));

		Node picture = editables.get(0);
		assertThat(picture.type().get("label"), is("Foto"));
		assertThat(picture.type().get("Editable+label"), is("edit now"));
		assertThat(picture.type().title(), is("Animal.Foto"));
		assertThat(picture.type().key(), is("cirdras"));
		assertThat(picture.type().types().size(), is(2));
		assertThat(picture.type().types().get(0).title(), is("Picture"));
		assertThat(picture.type().types().get(1).title(), is("Editable+Picture"));
		assertThat(picture.type().types().filter(instancesOf("Editable+Picture")).size(), is(1));
		assertThat(picture.type().types().filter(instancesOf(nameOf(Editable_Picture.class))).size(), is(1));
		assertThat(picture.type().members(Component).filter(instancesOf("Editable+Picture.Format")).size(), is(1));
		assertThat(picture.type().members(Component).filter(instancesOf("Editable+Picture.Format")).get(0).get("extension"), is("jpg"));
		assertThat(picture.members(Component).size(), is(2));
		assertThat(picture.members(Component).filter(instancesOf("Editable.Permission")).size(), is(1));
		assertThat(picture.members(Component).filter(instancesOf(nameOf(Editable.Permission.class))).size(), is(1));
		assertThat(picture.members(Component).filter(instancesOf("Editable+Picture.Format")).size(), is(1));
		assertThat(picture.members(Component).filter(instancesOf(nameOf(Editable_Picture.Format.class))).size(), is(1));
		assertThat(picture.members(Component).get(0).toString(), is("Case[?:?:Editable+Picture.Format:Concept]"));
		assertThat(picture.members(Component).get(1).toString(), is("Case[?:Editable.Permission:Concept]"));
		assertThat(picture.members(Component).get(0).get("extension"), is("jpg"));
		assertThat(picture.members(Component).get(1).get("roles"), is(multiple("all")));

		assertThat("Format is cloned", picture.type().members(Component).filter(instancesOf("Editable+Picture.Format")).get(0) != picture.members(Component).get(0));


		Node multiple = editables.get(1);
		assertThat(multiple.type().get("label"), is("Historial"));
		assertThat(multiple.type().get("Editable+label"), is("edition"));
		assertThat(multiple.type().get("Editable+sorteable"), is(true));
		assertThat(multiple.type().members(Component).size(), is(2));
		assertThat(multiple.type().members(Component).get(0).toString(), is("Case[?:Editable.Permission:Concept]"));
		assertThat(multiple.type().members(Component).get(0).get("roles"), is(multiple("boss")));
		assertThat(multiple.type().members(Component).get(1).toString(), is("Case[?:Editable+Multiple.Size:Concept]"));
		assertThat(multiple.type().members(Component).get(1).get("lines"), is(4));
		assertThat(multiple.members(Component).size(), is(3));
		assertThat(multiple.members(Component).get(0).toString(), is("Case[?:Intervencion:Composite:Concept]"));
		assertThat(multiple.members(Component).get(1).toString(), is("Case[?:?:Editable.Permission:Concept]"));
		assertThat(multiple.members(Component).get(2).toString(), is("Case[?:Intervencion:Composite:Concept]"));
	}

	@Test
	public void should_be_able_to_produce_required_nodes() throws Exception {
		Graph graph = adeje();

		Node yenpil = graph.get("yenpil");
		Set<Node> members = yenpil.members(Component);
		assertThat(members.size(), is(8));
		assertThat(members.get(0).get("value"), is("1020"));
		assertThat("Field 1 is an expression", members.get(1).get("value") instanceof Expression);
		assertThat("Field 1 value is cloned from mainType", members.get(1).get("value") != members.get(1).type().get("value"));
		assertThat(members.get(7).members(Component).size(), is(3));
		assertThat(members.get(7).members(Component).get(0).toString(), is("Case[?:Intervencion:Composite:Concept]"));
		assertThat(members.get(7).members(Component).get(1).toString(), is("Case[?:?:Editable.Permission:Concept]"));
		assertThat(members.get(7).members(Component).get(2).toString(), is("Case[?:Intervencion:Composite:Concept]"));
		assertThat(members.get(7).members(Component).get(0).members(Component).size(), is(2));
		assertThat(members.get(7).members(Component).get(0).members(Component).get(1).<Reference>get("value").value(), is("intervenciones.vacunacion"));
		assertThat(members.get(7).members(Component).get(0).members(Component).get(0).<Date>get("value").timestamp(), is(1296864000000L));
		assertThat(members.get(7).members(Component).get(2).members(Component).size(), is(2));
		assertThat(members.get(7).members(Component).get(2).members(Component).get(1).<Reference>get("value").value(), is("intervenciones.desparasitacion"));
		assertThat(members.get(7).members(Component).get(2).members(Component).get(0).<Date>get("value").timestamp(), is(1333580400000L));
	}

	@Test
	public void should_be_able_to_execute_native_code() {
		MonetModel monetModel = new MonetModel(adeje());

		Form form = monetModel.form(0);
		assertThat(form._node().title(), is("yenpil"));
		assertThat(form.field(1).label(), is("Nombre"));
		assertThat(form.field(1).as(Text.class).value(), is("1020"));
		assertThat(form.toolbar().operation(0).label(), is("Cambiar chip"));

		form.toolbar().operation(0).action();
		assertThat(form.field(3).as(Text.class).value(), is("X-2100"));
	}

	@Test
	public void should_be_able_to_use_facet_morphs() {
		MonetModel monetModel = new MonetModel(adeje());

		Form form = monetModel.form(0);
		assertThat(form.fieldSet().filter(instancesOf(Picture.class)).size(), is(1));
		assertThat(form.fieldSet().filter(instancesOf(Editable.class)).size(), is(2));
		assertThat("Field 2 is Picture", form.field(2).is(Picture.class));
		assertThat("Field 2 is Editable", form.field(2).is(Editable.class));
		assertThat(form.field(2).as(Editable.class).label(), is("edit now"));
		assertThat(form.field(2).as(Editable.class).permissionSet().size(), is(1));
		assertThat(form.field(2).as(Editable.class).permission(0).roles(), is(multiple("all")));
		assertThat(form.field(2).as(Editable_Picture.class).permission(0).roles(), is(multiple("all")));
		assertThat(form.field(2).as(Editable_Picture.class).format().extension(), is("jpg"));
	}

	@Test
	public void should_be_able_to_find_all_cases_of_M2_morphs_and_execute_native_code() {
		MonetModel monetModel = new MonetModel(adeje());

		Set<Field> fields = monetModel._find(Field.class);

		assertThat(fields.size(), is(14));
		assertThat(fields.get(0).label(), is("Codigo"));
		assertThat(fields.get(1).label(), is("Nombre"));
		assertThat("Field 1 value is an expression", fields.get(1).as(Text.class)._node().get("value") instanceof Expression);
		assertThat(fields.get(1).as(Text.class).value(), is("1020"));
	}

	@Test
	public void should_be_able_to_read_and_write_nodes_with_M2_morphs() {
		MonetModel monetModel = new MonetModel(adeje());

		Collection collection = monetModel.collectionSet().get(0);

		assertThat(collection.entrySet().size(), is(1));
		assertThat(collection.entryType()._name(), is("Censo.Registro"));

		Form form = collection.entry(0).entity().as(Form.class);
		assertThat(form.fieldSet().size(), is(8));

		assertThat("Field is Serie", form.field(0).is(Serie.class));
		assertThat(form.field(0).label(), is("Codigo"));
		assertThat(form.field(0)._definition()._node().type().title(), is("Serie"));
		assertThat(form.field(0).as(Serie.class).value(), is("1020"));

		assertThat("Field is Text", form.field(1).is(Text.class));
		assertThat(form.field(1).label(), is("Nombre"));
		assertThat(form.field(1)._definition()._node().type().title(), is("Text"));
		assertThat(form.field(0).as(Text.class).value(), is("1020"));

		assertThat(form.field(2).label(), is("Foto"));
		assertThat(form.field(2)._definition()._node().type().title(), is("Picture"));
		assertThat(form.field(2).as(Picture.class).value().size(), is(2616));

		assertThat(form.field(3).label(), is("Numero de chip"));
		assertThat(form.field(3).as(Text.class).value(), is("X2300121"));

		assertThat(form.field(4).label(), is("Fecha de nacimiento"));
		assertThat(form.field(7).label(), is("Historial"));
		assertThat(form.field(7).as(Multiple.class).fieldSet().size(), is(2));
		assertThat(form.field(7).as(Multiple.class).field(0).label(), is("Intervención"));
		assertThat(form.field(7).as(Multiple.class).field(1).label(), is("Intervención"));

		assertThat(form.field(7).as(Multiple.class).field(0).as(Composite.class).fields().size(), is(2));
		assertThat(form.field(7).as(Multiple.class).field(0).as(Composite.class).field(0).label(), is("Fecha"));
		assertThat(form.field(7).as(Multiple.class).field(0).as(Composite.class).field(1).label(), is("Tipo de intervención"));

		assertThat(form.field(7).as(Multiple.class).field(1).as(Composite.class).fields().size(), is(2));
		assertThat(form.field(7).as(Multiple.class).field(1).as(Composite.class).field(0).label(), is("Fecha"));
		assertThat(form.field(7).as(Multiple.class).field(1).as(Composite.class).field(1).label(), is("Tipo de intervención"));

		assertThat(form.field(4).as(monet.fields.Date.class).value(), is(magritte.primitives.Date.date(2010, 12, 22)));
		assertThat(form.field(5).as(Check.class).value(), is(false));
		assertThat(form.field(6).as(Number.class).value(), is(4.0));

		form.field(0).as(Serie.class).value("E00");
		form.field(6).as(Number.class).value(9);
		assertThat(form.field(0).as(Serie.class).value(), is("E00"));
		assertThat(form.field(1).as(Text.class).value(), is("E00"));
		assertThat(form.field(6).as(Number.class).value(), is(9.0));

	}

	@Test
	public void should_be_able_to_aggregate_and_write_objects_with_M2_morphs() throws Exception {
		MonetModel monetModel = new MonetModel(adeje());

		assertThat(monetModel.mainTypeSet().size(), is(2));
		assertThat(monetModel.mainType(0).toString(), is("Type[Perro:Form:Concept]"));
		assertThat(monetModel.mainType(1).toString(), is("Type[Gato:Form:Concept]"));

		Collection collection = monetModel.collection(0);
		assertThat(collection.entrySet().size(), is(1));
		assertThat(collection.entryType().toString(), is("Type[Censo.Registro:Collection.Entry:Concept]"));

		Collection.Entry entry = collection.newEntry(collection.entryType()).as(Collection.Entry.class);
		assertThat(entry.entityTypes().size(), is(2));
		assertThat(entry.entityType(0).toString(), is("Type[Perro:Form:Concept]"));
		assertThat(entry.entityType(1).toString(), is("Type[Gato:Form:Concept]"));

		Entity entity = monetModel.create(entry.entityTypes().get(0)).as(Entity.class);
		entry.entity(entity);

		assertThat(collection.entrySet().size(), is(2));

		Form form = monetModel.form(1);
		assertThat(form.fieldSet().size(), is(8));
		Field field0 = form.field(0);
		Field field2 = form.field(2);
		Field field7 = form.field(7);

		Multiple multiple = field7.as(Multiple.class);
		assertThat(multiple.fieldSet().size(), is(1));
		assertThat("Field is Serie", field0.is(Serie.class));
		assertThat("Field is Picture", field2.is(Picture.class));
		assertThat("Field is Date", form.field(4).is(monet.fields.Date.class));
		assertThat("Field is Multiple", field7.is(Multiple.class));
		assertThat("Field is Composite", multiple.field(0).is(Composite.class));
		assertThat("Field is Date", multiple.field(0).as(Composite.class).field(0).is(monet.fields.Date.class));
		assertThat("Field is Select", multiple.field(0).as(Composite.class).field(1).is(Select.class));

		assertThat(field0.label(), is("Codigo"));
		assertThat(field0._definition()._node().title(), is("Animal.Codigo"));
		assertThat(field0._definition()._node().type().title(), is("Serie"));
		assertThat(field0.as(Serie.class).value(), is("default"));

		assertThat(field2.label(), is("Foto"));
		assertThat(field2._definition()._node().title(), is("Animal.Foto"));
		assertThat(field2._definition()._node().type().title(), is("Picture"));
		assertThat(field2.as(Picture.class).value().size(), is(32883));

		assertThat(multiple.field(0).as(Composite.class).fields().size(), is(2));
		assertThat(multiple.field(0).as(Composite.class).field(0).as(monet.fields.Date.class).value(), is(nullValue()));
		assertThat(multiple.field(0).as(Composite.class).field(1).as(Select.class).value(), is(nullValue()));
		assertThat(multiple.fieldSet().size(), is(1));

		multiple.createField(multiple.fieldType(0)).as(Composite.class);
		multiple.createField(multiple.fieldType(0)).as(Composite.class);
		multiple.createField(multiple.fieldType(0)).as(Composite.class);
		assertThat(multiple.fieldSet().size(), is(4));
		assertThat(multiple.field(3).as(Composite.class).fields().size(), is(2));

		multiple.field(3).as(Composite.class).field(0).as(monet.fields.Date.class).value(magritte.primitives.Date.date(2012, 12, 10));
		multiple.field(3).as(Composite.class).field(1).as(Select.class).value(monetModel.thesaurus(0).term(0));
		assertThat(multiple.field(3).as(Composite.class).field(0).as(monet.fields.Date.class).value(), is(magritte.primitives.Date.date(2012, 12, 10)));
		assertThat(multiple.field(3).as(Composite.class).field(1).as(Select.class).value(), is(monetModel.thesaurus(0).term(0)));

		assertThat(monetModel.thesaurus(0).term(0)._referees().size(), is(2));
		assertThat(monetModel.thesaurus(0).term(0)._referee(0).is(Form.class), is(true));
		assertThat(monetModel.thesaurus(0).term(0)._referee(0).is(Animal.class), is(true));
		assertThat(monetModel.thesaurus(0).term(0)._referee(0).is(Perro.class), is(true));
		assertThat(monetModel.thesaurus(0).term(0)._referee(0).toString(), is("Morph of Case[yenpil:Perro:Form:Concept]"));
		assertThat(monetModel.thesaurus(0).term(0)._referee(1).toString(), is("Morph of Case[?:Perro:Form:Concept]"));
		assertThat(monetModel.thesaurus(0).term(0)._referee(0).as(Form.class).toString(), is("Form of Case[yenpil:Perro:Form:Concept]"));
		assertThat(monetModel.thesaurus(0).term(0)._referee(0).as(Animal.class).toString(), is("Animal of Case[yenpil:Perro:Form:Concept]"));
	}

	@Test
	public void should_be_able_to_aggregate_and_write_objects_with_M1_morphs() throws Exception {
		CensusModel censusModel = new CensusModel(adeje());

		Perro perro = censusModel._createPerro();
		perro._name(new RandomNameGenerator().generate());
		Censo censo = censusModel.censo();
		Censo.Registro registro = censo.createRegistro();
		registro.entity(perro);

		assertThat(censo.setRegistro().size(), is(2));
		assertThat(censo.setRegistro().get(1).entity().codigo().value(), is("default"));
		assertThat(censo.setRegistro().get(1).entity().esPeligroso().value(), is(false));
		assertThat(censo.setRegistro().get(1).entity().nombre().value(), is("default"));

		censo.setRegistro().get(1).entity().codigo().value("A1010");
		assertThat(censo.setRegistro().get(1).entity().codigo().value(), is("A1010"));
		assertThat(censo.setRegistro().get(1).entity().nombre().value(), is("A1010"));

	}


	private Graph adeje() {
		MemoryGraph model = new MemoryGraph();
		AdejeMain.box.load(model);
		return model;
	}

}
