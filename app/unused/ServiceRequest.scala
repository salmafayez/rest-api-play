package unused

import play.api.i18n.MessagesApi
import play.api.mvc.{Request, WrappedRequest}

class ServiceRequest[A](request: Request[A], val messagesApi: MessagesApi)
  extends WrappedRequest(request)
    with ServiceRequestHeader
