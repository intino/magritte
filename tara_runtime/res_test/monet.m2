Concept as Term
	var Entity.Source source
	var String code
	var String label
	Term <multiple optional>

Concept abstract as Source <has-code root>
	Concept as Ontology <optional>
		var Uid uid

Concept as Operation
	var String label

Source as Thesaurus
	var Term term
	Operation as term1
		Term as term2
		Term as term3

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

Entity as Form <has-code extensible>
	from <multiple>
		Concept abstract as Field <has-code extensible>
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
