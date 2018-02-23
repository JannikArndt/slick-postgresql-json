import java.time.LocalDateTime

import MyPostgresProfile.api._
import shapeless.{Generic, HNil}
import slick.lifted.ProvenShape
import slickless._

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.language.postfixOps

object Main extends App {
  val table = TableQuery[JsonTable]
  val db: Database = Database.forConfig("local")

  val action = table += JsonRow(0L, Some("Foo"), Some(LocalDateTime.now()))

  Await.result(db.run(action), 2 seconds)

  db.close()
}

case class JsonRow(id: Long, json: Option[String], created: Option[LocalDateTime])

class JsonTable(tag: Tag) extends Table[JsonRow](tag, Some("slick_pg_test"), "jsontable") {
  def id: Rep[Long] = column[Long]("id", O.PrimaryKey, O.AutoInc)

  def json: Rep[Option[String]] = column[Option[String]]("json")

  def created: Rep[Option[LocalDateTime]] = column[Option[LocalDateTime]]("created")

  def * : ProvenShape[JsonRow] = (id :: json :: created :: HNil).mappedWith(Generic[JsonRow])
}
