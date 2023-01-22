package repository

import models.{Service, s}
import models.dao.ServiceDAO
import play.api.MarkerContext
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.PostgresProfile
import slick.jdbc.PostgresProfile.api._

import javax.inject.{Inject, Singleton}
import scala.concurrent.Future


@Singleton
class ServiceDaoImpl @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
                                 (implicit ec: RepositoryExecutionContext) extends ServiceDAO {

  override def create(service: Service)(implicit mc: MarkerContext): Future[Int] = {
    db.run(services += service)
  }

  override def list(pageNumber: Int, pageSize: Int)(implicit mc: MarkerContext): Future[Seq[Service]] =
    db.run(services.drop(pageNumber * pageSize).take(pageSize).result)

  override def get(id: Long)(implicit mc: MarkerContext): Future[Option[Service]] =
    db.run(services.filter(_.id === id).result.headOption)
}
