<fold text='Concept as Term{...}'>Concept as Term
    var String code
    var String label
    new Term <multiple optional></fold>

<fold text='Concept abstract as Source <has-code root>{...}'>Concept abstract as Source <has-code root>
    Concept as Ontology <optional>
        var Uid uid</fold>

<fold text='Concept Source as Thesaurus{...}'>Concept Source as Thesaurus
    new Term <multiple></fold>

<fold text='Concept as Operation{...}'>Concept as Operation
    var String label</fold>

<fold text='Concept abstract as Entity  <has-code>{...}'>
' Una entidad es un objeto en la unidad de negocio que representa un contenido
Concept abstract as Entity  <has-code>
    ' Añadir para ofrecer al usuario información más detallada de la entidad
    Concept as Description <optional>
        var String description

    ' Añadir para ofrecer al usuario una ayuda
    ' - **resource**. Nombre del fichero incluido en la distribución
    Concept as Help <optional>
        var String resource

    Concept as Operation
        var String label

    Concept abstract as View</fold>
