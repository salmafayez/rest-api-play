package controllers

//import models.{Property, Service, ServiceRequest}
//import play.api.libs.json.Json
//import play.api.mvc._
//import repository.ServiceRepository
//import utilities.Formatter

import javax.inject._
import scala.concurrent.ExecutionContext

//@Singleton
//class RequestController @Inject()(val controllerComponents: ControllerComponents,
//                                  serviceRequestDao: ServiceRepository)
//                                 (implicit executionContext: ExecutionContext)
//  extends BaseController {
//
//  implicit val serviceRequestJson = Formatter.serviceRequestJson
//  implicit val reviewRequestJson = Formatter.reviewRequestJson

//  def create = Action.async(parse.json[notused.ServiceRequest]) {
//    request => serviceRequestDao.insert(request.body)
//  }

//  def list(pageNumber: Int, pageSize:Int) = Action.async {
//    implicit request =>
//      serviceRequestDao.list(pageNumber = pageNumber, pageSize = pageSize)
//        .map(requests => if (requests.isEmpty) NoContent else Ok(Json.toJson(requests)))
//  }

//  def review(requestId: Long) = Action.async{
//    implicit request =>
//  }
//}
