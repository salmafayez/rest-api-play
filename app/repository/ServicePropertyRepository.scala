package repository

//import dtos.ServicePropertyDto
//import models.{ServiceProperty, Tables}
//import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
//import slick.jdbc.PostgresProfile
//import slick.jdbc.PostgresProfile.api._
//
//import javax.inject.Inject
//import scala.concurrent.Future
//
//trait ServicePropertyRepository{
//  def assignServiceToProperty(servicePropertyDto: ServicePropertyDto): Future[Int]
//}
//class ServicePropertyRepositoryImpl @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
//                                         (implicit ec: RepositoryExecutionContext)
//  extends ServicePropertyRepository
//  with HasDatabaseConfigProvider[PostgresProfile] {
//
//  override def assignServiceToProperty(servicePropertyDto: ServicePropertyDto): Future[Int] = {
//    val serviceProperty = ServiceProperty(0, servicePropertyDto.serviceId, servicePropertyDto.propertyId)
//    db.run(Tables.ServiceProperty += serviceProperty)
//  }
//
//}
