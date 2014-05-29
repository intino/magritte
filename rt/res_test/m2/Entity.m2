package goros.m2

' Una entidad es un objeto en la unidad de negocio que representa un contenido
Concept abstract Entity  <root>
    var Alias alias
    ' Añadir para ofrecer al usuario información más detallada de la entidad
    Concept Description
        var String description

    ' Añadir para ofrecer al usuario una ayuda
    ' - **resource**. Nombre del fichero incluido en la distribución
    Concept Help
        var String resource

    Concept Operation <required>
        var String label
        Concept:Event Execute

    Concept abstract View
