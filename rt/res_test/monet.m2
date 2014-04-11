Concept Term
    var String code
    var String label
    new Term <multiple optional>

Concept abstract Source <has-code root>
    Concept Ontology <generic optional>
        var Uid uid


' Una entidad es un objeto en la unidad de negocio que representa un contenido
Concept abstract Entity  <has-code>

    ' Añadir para ofrecer al usuario información más detallada de la entidad
    Concept Description <optional>
        var String description

    ' Añadir para ofrecer al usuario una ayuda
    ' - **resource**. Nombre del fichero incluido en la distribución
    Concept Help <generic optional>
        var String resource

    Concept Operation
        var String label

    Concept abstract View

Concept:Entity Form <has-code extensible>

    Concept polymorphic Field <has-code extensible multiple>
        var String label
        ' Añadir para ofrecer al usuario información más detallada del campo
        Concept Description <optional>
            var  String description

        ' Añadir para indicar que el campo es obligatorio
        Concept Required <optional>

        ' Añadir para indicar que el campo no es editable
        Concept ReadOnly <optional>

        ' Añadir para mostrar un mensaje asociado al campo
        ' - **WhenEmpty**. Cuando esta vacío
        ' - **WhenRequired**. Cuando es un campo requerido y está vacío
        ' - **WhenReadOnly**. Cuando el campo no es editable
        ' - **WhenInvalid**. Cuando el valor del campo no es válido
        Concept Display <optional multiple>
            var Word when { WhenEmpty; WhenRequired; WhenReadOnly; WhenInvalid }
            var String message

        ' Añadir para incluir un campo boolean en el formulario
        Concept morph BooleanField

        ' Añadir para incluir un campo texto en el formulario
        Concept morph TextField
            ' Añadir para configurar la edición del campo
            Concept Edition <optional>
                ' Añadir para permitir un histórico de valores asociado al campo
                ' **store**. Almacén donde se guardará el histórico
                Concept AllowHistory <optional>
                    var String store

                Concept polymorphic Rule <multiple>
                    Concept abstract morph Length
                        var Natural value
                    Concept:Length morph MaxLength
                    Concept:Length morph MinLength
                    Concept Mode
                        var Word mode { Uppercase; Lowercase; Sentence; Title }
                    ' Añadir para indicar un patrón de edición del campo
                    Concept morph Pattern
                        var String regularExpression
                        Concept Meta <multiple>
                            var Natural position
                            var String tag

        ' Añadir para incluir un campo fecha en el formulario
        Concept morph DateField
            var Word precision
                Years
                Months
                Days
                Hours
                Minutes
                Seconds
            Concept Edition
                Concept polymorphic Rule
                    Concept morph TodayIsTheLatestDate
                    Concept morph TodayIsTheEarliestDate

        ' Añadir para incluir un campo de selección en el formulario
        Concept morph SelectField
            var Source source
            new Thesaurus <multiple optional>
            Concept Edition <optional>
                Concept AllowSearch <optional>
                Concept AllowOthers <optional>
                Concept AllowHistory <optional>
                    var String store
                Concept polymorphic Select <optional>
                    Concept morph Root
                    Concept morph Internal
                    Concept morph Leaf
                    Concept morph FromField
                        var Form.Field field
                    Concept morph FromTerm
                        var Term term
                        Concept Depth {var Natural depth}

            Concept Filter <optional>
                var String[] tags
            Concept View <optional>
                Concept ShowKey
                Concept Embedded

        Concept:Form.Field CompositeField
            new Form.Field  <multiple>
            Concept View <optional>
                Concept Table <optional>
                    var Form.Field[] fields
                Concept Section <optional>
                    var Form.Field[] fields

    Concept Georeference <optional>
    Concept:Entity.View abstract View
        var String label 
    Concept:View Tab <multiple>
    Concept:View Summary <optional>