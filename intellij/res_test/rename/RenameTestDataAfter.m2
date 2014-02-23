Concept as TermRenamed
    var String code: "code"
    var String label
    new TermRenamed <multiple optional>

Concept abstract as Source <has-code root>
    Concept as Ontology <optional>
        var Uid uid

Concept Source as Thesaurus
    new TermRenamed <multiple>

Concept as Operation
    var String label

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

    Concept abstract as View
