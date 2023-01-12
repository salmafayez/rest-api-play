package daos

import models.{Property, Service, ServiceProperty, Tables}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.api.mvc.Results.Created
import slick.jdbc.PostgresProfile
import slick.jdbc.PostgresProfile.api._

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class ServicePropertyDao @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)
                            (implicit executionContext: ExecutionContext)
  extends HasDatabaseConfigProvider[PostgresProfile] {

  def insert(serviceProperty: ServiceProperty) =
    db.run(Tables.ServiceProperty += serviceProperty)
      .map { _ => Created("") }

}
