package controllers

import daos.{PropertyDao, ServicePropertyDao}
import models.{Property, Service, ServiceProperty}
import play.api.libs.json.Json
import play.api.mvc._
import utilities.Formatter

import javax.inject._
import scala.concurrent.ExecutionContext

@Singleton
class ServicePropertyController @Inject()(val controllerComponents: ControllerComponents,
                                          serviceropertyDao: ServicePropertyDao)
                                         (implicit executionContext: ExecutionContext)
  extends BaseController {

  implicit val servicePropertyJson = Formatter.servicePropertyJson

  def create = Action.async(parse.json[ServiceProperty]) {
    request => serviceropertyDao.insert(request.body)
  }

}
