package com.example.dicarhilt

import javax.inject.Inject


interface Engine {
    fun start(): String
}

class GasEngine : Engine {
    override fun start() = "Gas engine starts with vroom."
}

class ElectricEngine : Engine {
    override fun start() = "Electric engine starts silently."
}

class Car @Inject constructor(private val engine: Engine) {
    fun startCar(): String = engine.start()
}
