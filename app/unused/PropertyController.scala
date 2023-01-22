package controllers

//import models.{Property, Service}
//import play.api.libs.json.Json
//import play.api.mvc._
//import repository.PropertyDao
//import utilities.Formatter
//
//import javax.inject._
//import scala.concurrent.ExecutionContext
//
//@Singleton
//class PropertyController @Inject()(val controllerComponents: ControllerComponents,
//                                   propertyDao: PropertyDao)
//                                  (implicit executionContext: ExecutionContext)
//  extends BaseController {
//
//  implicit val propertyJson = Formatter.propertyJson
//  implicit val serviceJson = Formatter.serviceJson
//
//  def create = Action.async(parse.json[Property]) {
//    request => propertyDao.create(request.body)
//  }
//
//  def list(pageNumber: Int, pageSize:Int) = Action.async {
//    implicit request =>
//      propertyDao.list(pageNumber = pageNumber, pageSize = pageSize)
//        .map(properties => if (properties.isEmpty) NoContent else Ok(Json.toJson(properties)))
//  }
//
//  def listOfServices(propertyId: Long, pageNumber: Int, pageSize: Int) = Action.async {
//    implicit request =>
//      propertyDao.listOfServices(propertyId, pageNumber, pageSize)
//        .map(services => if (services.isEmpty) NoContent else Ok(Json.toJson(services)))
//  }
//
//}
