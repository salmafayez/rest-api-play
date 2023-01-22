package repository

import akka.actor.ActorSystem
import play.libs.concurrent.CustomExecutionContext

import javax.inject.Inject

class RepositoryExecutionContext @Inject()(actorSystem: ActorSystem)
  extends CustomExecutionContext(actorSystem, "repository.dispatcher")