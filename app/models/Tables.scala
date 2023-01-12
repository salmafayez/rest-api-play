package models

import slick.jdbc.PostgresProfile.api._


object Tables {
  val Property = TableQuery[PropertyTable]
  val Service = TableQuery[ServiceTable]
  val ServiceProperty = TableQuery[ServicePropertyTable]
  val ServiceRequest = TableQuery[ServiceRequestTable]
  val User = TableQuery[UserTable]
}
