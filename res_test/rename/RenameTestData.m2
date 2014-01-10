concept abstract Aspect @nameable @root

concept abstract Feature @nameable

concept abstract MeasureUnit @nameable
    string label
    string pluralLabel
    string abbreviation

concept abstract Entity @nameable
    ref Location location

concept abstract AreaMeasureUnit is MeasureUnit

concept abstract PowerMeasureUnit is MeasureUnit

concept abstract TemperatureMeasureUnit is MeasureUnit

concept abstract Invariable is Feature

concept abstract Variable is Feature

concept abstract Stock is Feature

concept abstract Stock2 is Feature

concept JavaMethod @action:java

concept abstract Behavior
    ref Aspect powerAspect
    has onCalculate

concept final SquareMeter is AreaMeasureUnit

concept final Gram is WeightMeasureUnit
    string label ="Gram"
    string pluralLabel = "Grams"
    string abbreviation = "g"

concept Area is Invariable
    double value @anonymous
    has AreaMeasureUnit

concept Weight is Feature
    double value
    has WeightMeasureUnit

concept Building is Entity
    has Radiator[0..n]
    has Refrigerator[0..n]
    has Refrigerator[0..n]

concept <caret>Radiator is Entity
    use Power : PowerRadiator
    use Thermal :ThermalStorage

concept final Watt is PowerMeasureUnit

concept abstract Power is Invariable
    double value @anonymous
    double value @anonymous
    has PowerMeasureUnit

concept final NominalPower is Power

concept final StandbyPower is Power

concept abstract ConsumingPower is Variable
    double value @anonymous
    has PowerMeasureUnit

concept final ActivePower is ConsumingPower

concept final ReactivePower is ConsumingPower

concept abstract Variable is Feature @nameable

concept PowerRadiator is Aspect
    has StandbyPower[1..n]
    has NominalPower
    has ActivePower
    has ReactivePower
    has PowerRadiatiorBehavior
    ref Temperature temperature
    use Behavior : onCalculate(paramenter)

