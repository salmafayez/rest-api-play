package utilities

import dtos.ReviewRequestDto
import models.{Property, Service, ServiceProperty, ServiceRequest, User}
import play.api.libs.json.Json

object Formatter {
  implicit val propertyJson = Json.format[Property]
  implicit val serviceJson = Json.format[Service]
  implicit val servicePropertyJson = Json.format[ServiceProperty]
  implicit val userJson = Json.format[User]
  implicit val serviceRequestJson = Json.format[ServiceRequest]

  implicit val reviewRequestJson = Json.format[ReviewRequestDto]
}
