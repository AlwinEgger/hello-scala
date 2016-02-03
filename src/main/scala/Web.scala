//#imports
import com.twitter.finagle.{Http, Service}
import com.twitter.finagle.http
import com.twitter.util.{Await, Future}
import scala.util.Properties
import scala.collection.JavaConversions._
//#imports

object Web extends App {

val environmentVars = System.getenv
  for ((k,v) <- environmentVars) println(s"key: $k, value: $v")
 
  val properties = System.getProperties
  for ((k,v) <- properties) println(s"key: $k, value: $v")


//#service
  val host = Option(System.getenv("process.env.VCAP_APP_HOST")).getOrElse("localhost")
  val port = Option(System.getenv("PORT")).getOrElse("8080").toInt
  //Properties.envOrElse("PORT", "8080").toInt
  println("We got the following values" + host + ":" + port)
  val service = new Service[http.Request, http.Response] {
    def apply(req: http.Request): Future[http.Response] =
      Future.value(
        http.Response(req.version, http.Status.Ok) //.setContentString("Hello World")
      )
  }
//#service
//#builder
  val server = Http.serve(host + ":" + port, service)
  Await.ready(server)
//#builder
}
