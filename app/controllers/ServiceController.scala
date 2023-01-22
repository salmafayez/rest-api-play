package controllers


import models.Service
import models.dao.ServiceDAO
import play.api.libs.json.{Format, JsError, Json}
import play.api.mvc.{BaseController, ControllerComponents}

import javax.inject._
import scala.concurrent.{ExecutionContext, Future}




@Singleton
class ServiceController @Inject()(val controllerComponents: ControllerComponents,
                                  serviceDao: ServiceDAO)
                                         (implicit executionContext: ExecutionContext)
  extends BaseController {

  implicit val format: Format[Service] = Json.format

  def list(pageNumber: Int, pageSize: Int) = Action.async { implicit request =>
    serviceDao.list(pageNumber, pageSize)
        .map(services => if (services.isEmpty) NoContent else Ok(Json.toJson(services)))
  }

  def create = Action.async(parse.json) { implicit request =>
    request.body.validate[Service] map {
      service => serviceDao.create(service).map(s => Ok(Json.toJson(s)))
    } recoverTotal { error =>
      Future {
        BadRequest("Detected error: " + JsError.toJson(error))
      }
    }
  }

  def get(id: Long) = Action.async{ implicit request =>
    serviceDao.get(id).map {
        case service: Some[Service] => Ok(Json.toJson(service))
        case None => NotFound
      }
  }

}
