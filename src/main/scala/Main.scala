import java.time.LocalDateTime

import MyPostgresProfile.api._
import play.api.libs.json.{JsValue, Json}
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

  val json: JsValue = Json.parse("""
{
  "name" : "Watership Down",
  "location" : {
    "lat" : 51.235685,
    "long" : -1.309197
  },
  "residents" : [ {
    "name" : "Fiver",
    "age" : 4,
    "role" : null
  }, {
    "name" : "Bigwig",
    "age" : 6,
    "role" : "Owsla"
  } ]
}
""")

  val action = table += JsonRow(0L, Some(json), Some(LocalDateTime.now()))

  Await.result(db.run(action), 2 seconds)

  db.close()
}

case class JsonRow(id: Long, json: Option[JsValue], created: Option[LocalDateTime])

class JsonTable(tag: Tag) extends Table[JsonRow](tag, Some("slick_pg_test"), "jsontable") {
  def id: Rep[Long] = column[Long]("id", O.PrimaryKey, O.AutoInc)

  def json: Rep[Option[JsValue]] = column[Option[JsValue]]("json")

  def created: Rep[Option[LocalDateTime]] = column[Option[LocalDateTime]]("created")

  def * : ProvenShape[JsonRow] = (id :: json :: created :: HNil).mappedWith(Generic[JsonRow])
}
