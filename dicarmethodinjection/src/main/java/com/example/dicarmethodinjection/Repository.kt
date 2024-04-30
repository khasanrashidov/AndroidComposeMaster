package com.example.dicarmethodinjection


interface Engine {
    fun start(): String
}

class GasEngine : Engine {
    override fun start() = "Gas engine starts with vroom."
}

class ElectricEngine : Engine {
    override fun start() = "Electric engine starts silently."
}

class Car {
    fun startCar(engine: Engine): String {
        return engine.start()
    }
}
