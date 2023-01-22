package repository

//import unused.PropertyDAO
//import models.{Property, Service}
//import play.api.MarkerContext
//import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
//import slick.jdbc.PostgresProfile
//
//import javax.inject.{Inject, Singleton}
//import scala.concurrent.Future
//
//@Singleton
//class PropertyDaoImpl @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
//                                     (implicit ec: RepositoryExecutionContext)
//  extends PropertyDAO{
//
//  override def create(property: Property)(implicit mc: MarkerContext): Future[Int] = {
//    db.run(properties += property)
//  }
//  override def list(pageNumber: Int, pageSize: Int)(implicit mc: MarkerContext): Future[Seq[Property]] =
//    db.run(properties.drop(pageNumber * pageSize).take(pageSize).result)
//
//  override def get(id: Long)(implicit mc: MarkerContext): Future[Option[Property]] =
//    db.run(property.filter(_.id === id).result.headOption)
//
//  override def listOfServices(propertyId: Long, pageNumber: Int, pageSize: Int): Future[Seq[Service]] = {
//
//    val query = services
//      .join(Tables.ServiceProperty)
//      .on(_.id === _.serviceId)
//      .join(Tables.Property)
//      .on(_._2.propertyId === _.id)
//      .filter(_._2.id === propertyId)
//      .map(_._1._1)
//      .drop(pageNumber * pageSize)
//      .take(pageSize)
//
//    db.run(query.result)
//  }
//}

