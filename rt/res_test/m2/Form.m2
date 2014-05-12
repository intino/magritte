package goros.m2

import goros.m2.Source
import goros.m1.Entity
import goros.m1.Form


Concept:Entity Form <has-code extensible:java>
    Concept base Field <has-code extension:Field multiple>
        var String label
        Concept:Event OnChange <intention>
 		' Añadir para ofrecer al usuario información más detallada del campo
        Concept Description <optional>
            var String description

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
        case BooleanField
		' Añadir para incluir un campo texto en el formulario
        case TextField
            ' Añadir para configurar la edición del campo
            Concept Edition <optional>
                ' Añadir para permitir un histórico de valores asociado al campo
                ' **store**. Almacén donde se guardará el histórico
                Concept AllowHistory <optional>
                	var Form.Field.OnChange onChange
                    var String store
                Concept Mode
					var Word mode { Uppercase; Lowercase; Sentence; Title }
				Concept base Rule <multiple>
					case MaxLength
						var Natural value
					case MinLength
						var Natural value
					' Añadir para indicar un patrón de edición del campo
					case Pattern
						var String regularExpression
						Concept Meta <multiple>
							var Natural position
							var String tag

        ' Añadir para incluir un campo fecha en el formulario
        case DateField
            var Word precision
                Years
                Months
                Days
                Hours
                Minutes
                Seconds
            Concept Edition
                Concept base Rule
                    case TodayIsTheLatestDate
                    case TodayIsTheEarliestDate

        ' Añadir para incluir un campo de selección en el formulario
        case SelectField
            var Source source
            Concept:Thesaurus <multiple optional>
            Concept Edition <optional>
                Concept AllowSearch <optional>
                Concept AllowOthers <optional>
                Concept AllowHistory <optional>
                    var String store
                Concept base Select <optional>
                    case Root
                    case Internal
                    case Leaf
                    case FromField
                        var Form.Field field
                    case FromTerm
                        var Term termRef
                        Concept Depth {var Natural depth}

            Concept Filter <optional>
                var String[] tags
            Concept View <optional>
                Concept ShowKey
                Concept Embedded

        case CompositeField
            Concept:Form.Field FromField <multiple>
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




