package goros.m2

' Una entidad es un objeto en la unidad de negocio que representa un contenido
Concept abstract Entity  <has-code root>
    
    ' Añadir para ofrecer al usuario información más detallada de la entidad
    Concept Description <optional>
        var String description

    ' Añadir para ofrecer al usuario una ayuda
    ' - **resource**. Nombre del fichero incluido en la distribución
    Concept Help <optional>
        var String resource

    Concept Operation
        var String label

    Concept abstract View