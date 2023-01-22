//package notused
//
//import handlers.ServiceHandler
//import play.api.mvc.{BaseController, ControllerComponents}
//
//import javax.inject.Inject
//
///**
// * Exposes actions and handler to the PostController by wiring the injected state into the base class.
// */
//class ServiceBaseController @Inject()(scc: ServiceControllerComponents)
//  extends BaseController
//    with RequestMarkerContext {
//  override protected def controllerComponents: ControllerComponents = scc
//
//  def serviceAction: ServiceActionBuilder = scc.serviceActionBuilder
//
//  def serviceHandler: ServiceHandler = scc.serviceHandler
//}
