package me.ciaranoconnor.api

import com.wordnik.swagger.annotations._
import me.ciaranoconnor.models.User
import spray.routing.HttpService

@Api(value = "/User", description = "Operations about Users.", produces = "application/json", position = 0)
trait UserHttpService extends HttpService {
  import User._
  import spray.http.MediaTypes
  import spray.httpx.SprayJsonSupport._

  val routes = getUser

  @ApiOperation(value = "Get a user", notes = "", nickname = "getUser", httpMethod = "GET", response = classOf[User])
  @ApiImplicitParams(Array())
  @ApiResponses(Array(
    new ApiResponse(code = 200, message = "User retrieved")
  ))
  def getUser = {
    path("user") {
      respondWithMediaType(MediaTypes.`application/json`) {
        get {
          complete(User(1, "Tom"))
        }
      }
    }
  }
}

