package controllers

import play.api.libs.json.{JsValue, Json}
import play.api.test._
import play.api.test.{PlaySpecification, FakeRequest, WithApplication}

//class ServiceControllerSpec extends PlaySpecification with Injecting with WithApplication {
//
//  implicit val controller = inject[ServiceController]
//
//  "list services" in {
//    val servicesResult = controller.list(0, 10).apply(FakeRequest(GET, "/services"))
//
//    status(servicesResult) must_==   OK
//    contentAsJson(servicesResult) must beSome[Seq[ServiceResource]].which{
//      service => service.size === 5
//    }
//
//  "add service" in {
//    val serviceAdded = controller.create.apply(FakeRequest[JsValue](method = POST, uri = "/services",
//      headers = FakeHeaders,
//      body = Json.toJson(ServiceDto("new service"))))
//
//    status(serviceAdded) must_== OK
//    contentAsJson(serviceAdded) must beSome[ServiceResource].which {
//      service => service.name === "new service"
//    }
//
//  "get service" in {
//    val service = controller.get(1).apply(FakeRequest(GET, "/services/1"))
//
//    status(service) must_== OK
//    contentAsJson(service) must beSome[ServiceResource].which {
//      service => service.name === ""
//    }
//  }
//
////  "list items" in new WithApplication {
////    route(FakeRequest(c) match {
////      case Some(response) => status(response) must equalTo(OK) contentAsJson (response) must equalTo(Json.arr())
////      case None => failure
////    }
////  }
//
//}