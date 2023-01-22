package modules

import com.google.inject.AbstractModule
import models.dao.{ServiceDAO, UserDAO}
import models.dao.impl.UserDAOImpl
import net.codingwell.scalaguice.ScalaModule
import repository.ServiceDaoImpl
import services.{UserService, UserServiceImpl}


class BaseModule extends AbstractModule with ScalaModule {

  override def configure(): Unit = {
    bind[UserDAO].to[UserDAOImpl]
    bind[UserService].to[UserServiceImpl]
    bind[ServiceDAO].to[ServiceDaoImpl]

  }
}
