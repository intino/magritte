Concept Term
    var String code
    var String label
    new Term <multiple optional>

Concept abstract Source <has-code root>
    Concept Ontology <optional>
        var Uid uid

Concept:Source Thesaurus
    new Term <multiple>

' Una entidad es un objeto en la unidad de negocio que representa un contenido
Concept abstract Entity  <has-code>

    ' Añadir para ofrecer al usuario información más detallada de la entidad
    Concept Description <optional>
        var String description

    ' Añadir para ofrecer al usuario una ayuda
    ' * **resource**. Nombre del fichero incluido en la distribución
    Concept Help <optional>
        var String resource

    Concept Operation
        var String label

    Concept abstract View