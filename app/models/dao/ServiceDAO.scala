package models.dao

import com.mohiva.play.silhouette.api.LoginInfo
import models.{Service, User}
import play.api.MarkerContext

import scala.concurrent.Future

/**
 * Gives access to the user storage.
 */
trait ServiceDAO extends DAOSlick {
  def create(service: Service)(implicit mc: MarkerContext): Future[Int]
  def list(pageNumber: Int, pageSize: Int)(implicit mc: MarkerContext): Future[Seq[Service]]
  def get(id: Long)(implicit mc: MarkerContext): Future[Option[Service]]
}
