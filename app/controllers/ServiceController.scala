package controllers

import daos.ServiceDao
import models.Service
import play.api.libs.json.Json
import play.api.mvc._
import utilities.Formatter

import javax.inject._
import scala.concurrent.ExecutionContext

@Singleton
class ServiceController @Inject()(val controllerComponents: ControllerComponents, serviceDao: ServiceDao)
                                 (implicit executionContext: ExecutionContext)
                                extends BaseController {

  implicit val serviceJson = Formatter.serviceJson

  def create = Action.async(parse.json[Service]) {
    request => serviceDao.insert(request.body)
  }

  def list(pageNumber: Int, pageSize:Int) = Action.async {
    implicit request =>
      serviceDao.list(pageNumber = pageNumber, pageSize = pageSize)
        .map(services => if (services.isEmpty) NoContent else Ok(Json.toJson(services)))
  }



}
