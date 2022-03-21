package main

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import scala.collection.mutable._


class ParkingLot {
  var capacityOfVechicle=0
  var slotOccupied:Int=0
  var allslots:ListBuffer[Int]= ListBuffer()
  var map=scala.collection.mutable.Map[String,ListBuffer[String]]()
  var bikeCapacity:Int = 10
  var carCapacity:Int = 10
  var slotOccupiedBike:Int=0
  var slotOccupiedCar:Int=0



  def checkAvailability(capacityOfVechicle:Int,slotOccupied:Int): Boolean =
  {

    if(slotOccupied<capacityOfVechicle)
      return true
    else
      return  false
  }

  def avilableSlotNow()
  {
    println("Bike :"+(bikeCapacity-slotOccupiedBike))
    println("Car :"+(carCapacity-slotOccupiedCar))

  }

  def parkingVechicle(vehicle: Vehicle) =
  {
    if(!map.contains(vehicle.vehicleNumber)) {
      vehicle match {
        case car: Car => capacityOfVechicle = carCapacity
          slotOccupied = slotOccupiedCar
        case bike: Bike => capacityOfVechicle = bikeCapacity
          slotOccupied = slotOccupiedBike
      }
      if (checkAvailability(capacityOfVechicle, slotOccupied)) {
        var slot: Int = getslot(vehicle)
        allslots += slot
        println(" Vechicle Number:" + vehicle.vehicleNumber + " parked into slot: " + slot)
        map(vehicle.vehicleNumber) = ListBuffer(slot.toString, LocalDateTime.now().toString)

        vehicle match {
          case car: Car => slotOccupiedCar += 1
          case bike: Bike => slotOccupiedBike += 1
        }

      }
      else {
        println("Sorry ! All slots are currently full !!\n")
      }
      println("------------------------------------------------------")
    }
    else
      {
        println("Vehicle already parked into Parking")
      }
  }
  def getslot(vehicel: Vehicle) :Int=
  {
    vehicel match{
      case car:Car=> capacityOfVechicle=carCapacity
      case bike:Bike=>capacityOfVechicle=bikeCapacity
    }


    for(m<-0 until capacityOfVechicle)
      {
        if(!allslots.contains(m))
        {
          return  m
        }
      }
      return -1
  }




  def unParked(vehicle: Vehicle): Unit =
  {
    if(map.contains(vehicle.vehicleNumber))
      {
        var slot=map(vehicle.vehicleNumber)(0)
        var num=slot.toInt
        var index:Int=allslots.indexOf(num)
        allslots.remove(index)
        var exittime=LocalDateTime.now()
        map(vehicle.vehicleNumber).addOne(exittime.toString)
        var totalTime:Float=getTotalTime(vehicle)
        map(vehicle.vehicleNumber).append((totalTime*60).toString)
        var cost=totalCost(vehicle,totalTime)
        map(vehicle.vehicleNumber).append(cost.toString)
        println("Unparked vehicle :"+vehicle.vehicleNumber)
        println("    slot allocated for this is :"+map(vehicle.vehicleNumber)(0))
        println("    Exit time : "+map(vehicle.vehicleNumber)(2))
        println("    Amount Have to paid: "+map(vehicle.vehicleNumber)(3))
      }
    else
      {
        println("This vehicle is not Present into parking")
      }
    println("------------------------------------------------------")

  }
  def getTotalTime(vehicle: Vehicle): Float =
  {
    var entryTime:LocalDateTime=LocalDateTime.parse(map(vehicle.vehicleNumber)(1))
    var exitTime:LocalDateTime=LocalDateTime.parse(map(vehicle.vehicleNumber)(2))
    var totaltime=ChronoUnit.MINUTES.between(entryTime,exitTime)
    return  totaltime/60f

  }
  def totalCost(vehicle: Vehicle,totalTime:Float):Float =
  {
    if (totalTime<=0)
      return  0
    var rate=0
    vehicle match {
      case car:Car=>
        rate=20//per Hour rate
      case bike:Bike=>
        rate=10//per hour
      case _=>
        rate=40
    }
    return  rate*totalTime
  }

  def detailOfVechicle(vehicle:Vehicle): Unit =
  {
    if(map.contains(vehicle.vehicleNumber)) {
      println("Detail of Vechicle : " + vehicle.vehicleNumber)
      println("   Slot allocated to Vechicle in Parking: " + map(vehicle.vehicleNumber)(0))
      println("   Entry Time of vechicle: " + map(vehicle.vehicleNumber)(1))
      println("   Exit Time of Vechicle: "+map(vehicle.vehicleNumber)(2))
      println("   Total  time in Parking: "+map(vehicle.vehicleNumber)(3))
      println("   Total amount the vehicle has to pay :"+map(vehicle.vehicleNumber)(4))
      println("------------------------------------------------------")


    }
    else
    {
      println("Detail of this vechicle is not present in the system")
    }
  }



}
