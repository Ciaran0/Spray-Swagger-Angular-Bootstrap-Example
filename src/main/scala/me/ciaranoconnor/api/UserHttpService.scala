package me.ciaranoconnor.api

import com.wordnik.swagger.annotations._
import me.ciaranoconnor.models.User
import org.slf4j.LoggerFactory
import spray.http.StatusCodes
import spray.httpx.SprayJsonSupport
import spray.routing.HttpService

@Api(value = "/User", description = "Operations about Users.", produces = "application/json", position = 0)
trait UserHttpService extends HttpService with SprayJsonSupport {
  import User._
  import spray.http.MediaTypes

  val routes = getUsers ~ getUser ~ addUser
  val log = LoggerFactory.getLogger(classOf[UserHttpService])


  @ApiOperation(value = "Get all users", notes = "Returns all users", nickname = "getUsers", httpMethod = "GET", response = classOf[User], responseContainer = "List")
  @ApiImplicitParams(Array())
  @ApiResponses(Array(
    new ApiResponse(code = 200, message = "User retrieved"),
    new ApiResponse(code = 204, message = "No user found")
  ))
  def getUsers = {
    path("User") {
      respondWithMediaType(MediaTypes.`application/json`) {
        get {
          pathEnd {
            complete {
              //In a real application retrievedUsers would be retrieved from a database
              val retrievedUsers  = List(User(1, "Tom"),User(2,"bob"))
              log.debug("Getting users")
              retrievedUsers match {
                case head::tail => retrievedUsers
                case Nil => StatusCodes.NoContent
              }
            }
          }
        }
      }
    }
  }
  @ApiOperation(value = "Find a user by ID", notes = "Returns a user based on ID", httpMethod = "GET", response = classOf[User])
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "userId", value = "ID of user that needs to be fetched", required = true, dataType = "integer", paramType = "path")
  ))
  @ApiResponses(Array(
    new ApiResponse(code = 404, message = "Pet not found"),
    new ApiResponse(code = 400, message = "Invalid ID supplied")
  ))
  def getUser = {
    path("User"/ IntNumber) { id => {
        respondWithMediaType(MediaTypes.`application/json`){
          get {
            complete(User(id,"Tom"))
          }
        }
      }
    }
  }

  @ApiOperation(value = "Add a new user", nickname = "addUser", httpMethod = "POST", consumes = "application/json, application/vnd.custom.user")
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "body", value = "User object", dataType = "User", required = true, paramType = "body")
  ))
  @ApiResponses(Array(
    new ApiResponse(code = 405, message = "Invalid input"),
    new ApiResponse(code = 200, message = "User added")

  ))
  def addUser = post {
    path("User" / Segment) { id => complete(id)}
  }
}

