package daos

import models.{Property, Service, ServiceProperty, Tables}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.api.mvc.Results.Created
import slick.jdbc.PostgresProfile
import slick.jdbc.PostgresProfile.api._

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class PropertyDao @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)
                        (implicit executionContext: ExecutionContext)
  extends HasDatabaseConfigProvider[PostgresProfile] {

  def insert(property: Property) =
    db.run(Tables.Property += property)
      .map { _ => Created("") }

  def list(pageNumber: Int, pageSize: Int): Future[Seq[Property]] =
    db.run(Tables.Property
      .drop(pageNumber * pageSize)
      .take(pageSize).
      result)


  def listOfServices(propertyId: Long, pageNumber: Int, pageSize: Int): Future[Seq[Service]] ={
    val query = Tables.Service
      .join(Tables.ServiceProperty)
      .on(_.id === _.serviceId)
      .join(Tables.Property)
      .on(_._2.propertyId === _.id)
      .filter(_._2.id === propertyId)
      .map(_._1._1)
      .drop(pageNumber * pageSize)
      .take(pageSize)

    db.run(query.result)
  }

}
