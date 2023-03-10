//package notused
//
//import play.api.http.HttpVerbs
//import play.api.i18n.MessagesApi
//import play.api.mvc._
//import play.api.{Logger, MarkerContext}
//
//import javax.inject.Inject
//import scala.concurrent.{ExecutionContext, Future}
//
///**
// * The action builder for the Post resource.
// *
// * This is the place to put logging, metrics, to augment
// * the request with contextual data, and manipulate the
// * result.
// */
//class ServiceActionBuilder @Inject()(messagesApi: MessagesApi,
//                                     playBodyParsers: PlayBodyParsers)(
//                                      implicit val executionContext: ExecutionContext)
//  extends ActionBuilder[ServiceRequest, AnyContent]
//    with RequestMarkerContext
//    with HttpVerbs {
//
//  override val parser: BodyParser[AnyContent] = playBodyParsers.anyContent
//
//  type PostRequestBlock[A] = ServiceRequest[A] => Future[Result]
//
//  private val logger = Logger(this.getClass)
//
//  override def invokeBlock[A](request: Request[A],
//                              block: PostRequestBlock[A]): Future[Result] = {
//    // Convert to marker context and use request in block
//    implicit val markerContext: MarkerContext = requestHeaderToMarkerContext(
//      request)
//    logger.trace(s"invokeBlock: ")
//
//    val future = block(new ServiceRequest(request, messagesApi))
//
//    future.map { result =>
//      request.method match {
//        case GET | HEAD =>
//          result.withHeaders("Cache-Control" -> s"max-age: 100")
//        case other =>
//          result
//      }
//    }
//  }
//}
