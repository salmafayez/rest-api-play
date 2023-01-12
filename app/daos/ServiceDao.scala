package daos

import models.{Service, Tables}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.api.mvc.Results.Created
import slick.jdbc.PostgresProfile
import slick.jdbc.PostgresProfile.api._

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class ServiceDao @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)
                        (implicit executionContext: ExecutionContext)
  extends HasDatabaseConfigProvider[PostgresProfile] {

  def insert(service: Service) =
    db.run(Tables.Service += service).map { _ => Created("")}

  def list(pageNumber: Int, pageSize: Int): Future[Seq[Service]] =
    db.run(Tables.Service.drop(pageNumber * pageSize).take(pageSize).result)




}
