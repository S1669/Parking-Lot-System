package main

abstract class Vehicle(val vehicleNumber:String) {
  val typeOfVechicle:Any=0
  
}
class Bike(vehicleNumber:String) extends Vehicle(vehicleNumber)
{

}


class Car(vehicleNumber:String) extends Vehicle(vehicleNumber)
{

}



