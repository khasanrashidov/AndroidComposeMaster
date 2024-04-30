package com.example.dicarfieldinjection

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
    lateinit var engine: Engine

    fun installEngine(engine: Engine) {
        this.engine = engine
    }

    fun startCar(): String {
        return engine.start()
    }
}
