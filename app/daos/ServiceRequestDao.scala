package daos

import models.{Service, ServiceRequest, Tables}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.api.mvc.Results.Created
import slick.jdbc.PostgresProfile
import slick.jdbc.PostgresProfile.api._

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class ServiceRequestDao @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)
                           (implicit executionContext: ExecutionContext)
  extends HasDatabaseConfigProvider[PostgresProfile] {

  def insert(serviceRequest: ServiceRequest) =
    db.run(Tables.ServiceRequest += serviceRequest).map { _ => Created("")}

  def list(pageNumber: Int, pageSize: Int): Future[Seq[ServiceRequest]] =
    db.run(Tables.ServiceRequest.drop(pageNumber * pageSize).take(pageSize).result)


}
