package unused

import play.api.mvc.{MessagesRequestHeader, PreferredMessagesProvider}

/**
 * A wrapped request for post resources.
 *
 * This is commonly used to hold request-specific information like
 * security credentials, and useful shortcut methods.
 */
trait ServiceRequestHeader
  extends MessagesRequestHeader
    with PreferredMessagesProvider
