package main

object MainObj {
  def main(args:Array[String]): Unit =
  {
    val pl=new ParkingLot()
    val b1=new Bike("RJ06 SK 1669")
    val b2=new Bike("RJ06 Pk 1234")
    val b3=new Bike("RJ06 KK 2345")
    val c1=new Car("RJ09 GJ 1256")
    val c2=new Car("RJ09 GJ 1256")
    pl.parkingVechicle(b1)
    pl.parkingVechicle(b2)
    pl.parkingVechicle(c1)
    pl.parkingVechicle(c2)
    pl.parkingVechicle(b3)

    pl.unParked(c1)


  }
}
