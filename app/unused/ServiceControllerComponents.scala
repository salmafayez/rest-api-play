//package notused
//
//import handlers.ServiceHandler
//import play.api.http.FileMimeTypes
//import play.api.i18n.{Langs, MessagesApi}
//import play.api.mvc.{ControllerComponents, DefaultActionBuilder, PlayBodyParsers}
//
//import javax.inject.Inject
//
///**
// * Packages up the component dependencies for the post controller.
// *
// * This is a good way to minimize the surface area exposed to the controller, so the
// * controller only has to have one thing injected.
// */
//case class ServiceControllerComponents @Inject()(
//                                                  serviceActionBuilder: ServiceActionBuilder,
//                                                  serviceHandler: ServiceHandler,
//                                                  actionBuilder: DefaultActionBuilder,
//                                                  parsers: PlayBodyParsers,
//                                                  messagesApi: MessagesApi,
//                                                  langs: Langs,
//                                                  fileMimeTypes: FileMimeTypes,
//                                                  executionContext: scala.concurrent.ExecutionContext)
//  extends ControllerComponents
