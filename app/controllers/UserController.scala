package controllers

import daos.UserDao
import models.User

import javax.inject._
import play.api.mvc._
import play.api.libs.json.Json
import utilities.Formatter

import scala.concurrent.ExecutionContext

@Singleton
class UserController @Inject()(val controllerComponents: ControllerComponents,userDao: UserDao)
                              (implicit executionContext: ExecutionContext)
                                extends BaseController {

  implicit val userJson = Formatter.userJson
  implicit val serviceRequestJson = Formatter.serviceRequestJson

  def create = Action.async(parse.json[User]) {
    request => userDao.insert(request.body)
  }

  def list(pageNumber: Int, pageSize:Int) = Action.async {
    implicit request =>
      userDao.list(pageNumber = pageNumber, pageSize = pageSize)
        .map(users => if (users.isEmpty) NoContent else Ok(Json.toJson(users)))
  }

  def listOfRequests(userId: Long, pageNumber: Int, pageSize: Int) = Action.async {
    implicit request =>
      userDao.listOfRequests(userId, pageNumber, pageSize)
        .map(requests => if (requests.isEmpty) NoContent else Ok(Json.toJson(requests)))
  }

}
