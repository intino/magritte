Concept as Term
	var Entity.Source source
	var String code
	var String label
	Term <multiple optional>

Concept abstract as Source <has-code root>
	Concept as Ontology <optional>
		var Uid uid

Source as Thesaurus
	var Term term

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
