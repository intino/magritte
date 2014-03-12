Concept as Term
    var String code
    var String label
    new Term <multiple optional>

Concept abstract as Source <has-code root>
    Concept as Ontology <optional>
        var Uid uid

Concept Source as Thesaurus
    new Term <multiple>

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

Concept Entity as Form <has-code extensible>

    Concept polymorphic as Field <has-code extensible multiple>
        var String label
        ' Añadir para ofrecer al usuario información más detallada del campo
        Concept as Description <optional>
            var  String description

        ' Añadir para indicar que el campo es obligatorio
        Concept as Required <optional>

        ' Añadir para indicar que el campo no es editable
        Concept as ReadOnly <optional>

        ' Añadir para mostrar un mensaje asociado al campo
        ' - **WhenEmpty**. Cuando esta vacío
        ' - **WhenRequired**. Cuando es un campo requerido y está vacío
        ' - **WhenReadOnly**. Cuando el campo no es editable
        ' - **WhenInvalid**. Cuando el valor del campo no es válido
        Concept as Display <optional multiple>
            var Word when { WhenEmpty; WhenRequired; WhenReadOnly; WhenInvalid }
            var String message

        ' Añadir para incluir un campo boolean en el formulario
        Concept morph as BooleanField

        ' Añadir para incluir un campo texto en el formulario
        Concept morph as TextField
            ' Añadir para configurar la edición del campo
            Concept as Edition <optional>
                ' Añadir para permitir un histórico de valores asociado al campo
                ' **store**. Almacén donde se guardará el histórico
                Concept as AllowHistory <optional>
                    var String store

                Concept polymorphic as Rule <multiple>
                    Concept abstract morph as Length
                        var Natural value
                    Concept Length morph as MaxLength
                    Concept Length morph as MinLength
                    Concept as Mode
                        var Word mode { Uppercase; Lowercase; Sentence; Title }
                    ' Añadir para indicar un patrón de edición del campo
                    Concept morph as Pattern
                        var String regularExpression
                        Concept as Meta <multiple>
                            var Natural position
                            var String tag

        ' Añadir para incluir un campo fecha en el formulario
        Concept morph as DateField
            var Word precision
                Years
                Months
                Days
                Hours
                Minutes
                Seconds
            Concept as Edition
                Concept polymorphic as Rule
                    Concept morph as TodayIsTheLatestDate
                    Concept morph as TodayIsTheEarliestDate

        ' Añadir para incluir un campo de selección en el formulario
        Concept morph as SelectField
            var Source source
            new Thesaurus <multiple optional>
            Concept as Edition <optional>
                Concept as AllowSearch <optional>
                Concept as AllowOthers <optional>
                Concept as AllowHistory <optional>
                    var String store
                Concept polymorphic as Select <optional>
                    Concept morph as Root
                    Concept morph as Internal
                    Concept morph as Leaf
                    Concept morph as FromField
                        var Form.Field field
                    Concept morph as FromTerm
                        var Term term
                        Concept as Depth {var Natural depth}

            Concept as Filter <optional>
                var String[] tags
            Concept as View <optional>
                Concept as ShowKey
                Concept as Embedded

        Concept Form.Field as CompositeField
            new Form.Field  <multiple>
            Concept as View <optional>
                Concept as Table <optional>
                    var Form.Field[] fields
                Concept as Section <optional>
                    var Form.Field[] fields

    Concept as Georeference <optional>
    Concept Entity.View abstract as View
        var String label 
    Concept View as Tab <multiple>
    Concept View as Summary <optional>
