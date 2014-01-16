concept abstract Aspect @nameable @root

concept abstract Feature @nameable

<fold text='concept PowerRadiator is Aspect {...}'>concept PowerRadiator is Aspect
    has StandbyPower[1..n]
    has NominalPower
    has ActivePower
    has ReactivePower
    has PowerRadiatiorBehavior
    ref Temperature temperature
    use Behavior : onCalculate(paramenter)</fold>


<fold text='concept abstract MeasureUnit @nameable {...}'>concept abstract MeasureUnit @nameable
    string label
    string pluralLabel
    string abbreviation</fold>


