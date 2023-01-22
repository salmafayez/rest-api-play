package models.dao

import models.tables.DBTableDefinitions
import play.api.db.slick.HasDatabaseConfigProvider
import slick.jdbc.{JdbcProfile, PostgresProfile}

/**
  * Trait that contains generic slick db handling code to be mixed in with DAOs
  */
trait DAOSlick extends DBTableDefinitions with HasDatabaseConfigProvider[PostgresProfile]