package controllers

import daos.{PropertyDao, ServiceRequestDao}
import models.{Property, Service, ServiceRequest}
import play.api.libs.json.Json
import play.api.mvc._
import utilities.Formatter

import javax.inject._
import scala.concurrent.ExecutionContext

@Singleton
class ServiceRequestController @Inject()(val controllerComponents: ControllerComponents,
                                         serviceRequestDao: ServiceRequestDao)
                                       (implicit executionContext: ExecutionContext)
  extends BaseController {

  implicit val serviceRequestJson = Formatter.serviceRequestJson

  def create = Action.async(parse.json[ServiceRequest]) {
    request => serviceRequestDao.insert(request.body)
  }

  def list(pageNumber: Int, pageSize:Int) = Action.async {
    implicit request =>
      serviceRequestDao.list(pageNumber = pageNumber, pageSize = pageSize)
        .map(requests => if (requests.isEmpty) NoContent else Ok(Json.toJson(requests)))
  }

}
