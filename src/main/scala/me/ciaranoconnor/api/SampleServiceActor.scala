package me.ciaranoconnor.api

import akka.actor.ActorLogging
import com.gettyimages.spray.swagger.SwaggerHttpService
import com.wordnik.swagger.model.ApiInfo
import spray.routing.HttpServiceActor

import scala.reflect.runtime.universe._


class SampleServiceActor extends HttpServiceActor with ActorLogging {

  override def actorRefFactory = context

  val users = new UserHttpService {
    def actorRefFactory = context
  }

  def receive = runRoute(users.routes ~ swaggerService ~
    get {
      pathPrefix("swagger") { pathEndOrSingleSlash {
        getFromResource("swagger/index.html")
        }
      } ~
        getFromResourceDirectory("swagger")
      pathPrefix("") { pathEndOrSingleSlash {
        getFromResource("app/index.html")
      }
      } ~
        getFromResourceDirectory("app")
    })

  val swaggerService = new SwaggerHttpService {
    override def apiTypes = Seq(typeOf[UserHttpService])
    override def apiVersion = "1.0"
    override def baseUrl = "/"
    override def docsPath = "api-docs"
    override def actorRefFactory = context
    override def apiInfo = Some(new ApiInfo("Spray-Swagger Sample", "A sample spray.io project", "TOC Url", "", "Apache V2", "http://www.apache.org/licenses/LICENSE-2.0"))
  }.routes

}
