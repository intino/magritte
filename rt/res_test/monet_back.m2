Concept as Term
	var String code
	var String label
	Term <multiple optional>

Concept abstract as Source <has-code root>
	Concept as Ontology <optional>
		var Uid uid

Source as Thesaurus 
	Term <multiple>

Concept as Operation
	var String label

?? Una entidad es un objeto en la unidad de negocio que representa un contenido
Concept abstract as Entity  <has-code>

	?? Añadir para ofrecer al usuario información más detallada de la entidad
	Concept as Description <optional>
		var String description

	/? 
		Añadir para ofrecer al usuario una ayuda
		- **resource**. Nombre del fichero incluido en la distribución
	?/
	Concept as Help <optional>
		var String resource

	Concept as Operation 
		var String label

	Concept abstract as View

Entity as Form <has-code>

	from <multiple>
		Concept abstract as Field <has-code>
			var String label

			?? Añadir para ofrecer al usuario información más detallada del campo
			Concept as Description <optional>
				var String description

			?? Añadir para indicar que el campo es obligatorio
			Concept as Required <optional>

			?? Añadir para indicar que el campo no es editable
			Concept as ReadOnly <optional>

			/?
				Añadir para mostrar un mensaje asociado al campo
				- **WhenEmpty**. Cuando esta vacío
				- **WhenRequired**. Cuando es un campo requerido y está vacío
				- **WhenReadOnly**. Cuando el campo no es editable
				- **WhenInvalid**. Cuando el valor del campo no es válido
			?/
			Concept as Display <optional multiple>
				var Word when { WhenEmpty; WhenRequired; WhenReadOnly; WhenInvalid; }
				var String message

		?? Añadir para incluir un campo boolean en el formulario
		Field as BooleanField

		?? Añadir para incluir un campo texto en el formulario
		Field as TextField
			?? Añadir para configurar la edición del campo
			Concept as Edition <optional>
				/?
					Añadir para permitir un histórico de valores asociado al campo
					**store**. Almacén donde se guardará el histórico
				?/
				Concept as AllowHistory <optional>
					var String store
				from <multiple>
					Concept abstract as Rule
					Rule abstract as Length
					Length as MaxLength { var Natural value; }
					Length as MinLength { var Natural value; }
					Rule as Mode
						var Word mode { Uppercase; Lowercase; Sentence; Title; }

					?? Añadir para indicar un patrón de edición del campo
					Rule as Pattern
						var String regularExpression
						Concept as Meta <multiple>
							var Natural position
							var String tag

		?? Añadir para incluir un campo fecha en el formulario
		Field as DateField
			var Word precision 
				Years
				Months
				Days
				Hours
				Minutes
				Seconds

			Concept as Edition
				from
					Concept abstract as Rule
					Rule as TodayIsTheLatestDate
					Rule as TodayIsTheEarliestDate

		?? Añadir para incluir un campo de selección en el formulario
		Field as SelectField
			var Source source
			Thesaurus <multiple optional>
			Concept as Edition <optional>
				Concept as AllowSearch <optional>
				Concept as AllowOthers <optional>
				Concept as AllowHistory <optional>
					var String store
				from <optional>
					Concept abstract as Select
					Select as Root
					Select as Internal
					Select as Leaf
					Select as FromField
						var Field field
					Select as FromTerm
						var Term term
						Concept as Depth
							var Natural depth

			Concept as Filter <optional>
				var String[] tags
			Concept as View <optional>
				Concept as ShowKey
				Concept as Embedded

		Field as CompositeField
			Field <multiple>
			Concept as View <optional>
				Concept as Table <optional>
					var Field[] fields
				Concept as Section <optional>
					var Field[] fields

	Concept as Georeference <optional>

	Entity.View abstract as View
		var String label 
	View as Tab <multiple>
	View as Summary <optional>
