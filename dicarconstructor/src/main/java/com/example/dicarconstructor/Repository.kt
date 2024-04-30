package com.example.dicarconstructor

interface Engine {
    fun start(): String
}

class GasEngine : Engine {
    override fun start() = "Gas engine starts with vroom."
}

class ElectricEngine : Engine {
    override fun start() = "Electric engine starts silently."
}

class Car(private val engine: Engine) {
    fun startCar(): String {
        return engine.start()
    }
}
