package me.ciaranoconnor.api

import me.ciaranoconnor.models.User
import org.scalatest.{Matchers, FreeSpec}
import spray.http.StatusCodes
import spray.httpx.SprayJsonSupport
import spray.testkit.ScalatestRouteTest


class UserHttpServiceTest extends FreeSpec with Matchers with ScalatestRouteTest with SprayJsonSupport with UserHttpService {

  def actorRefFactory = system

  "The user Route" - {
    "when listing users" - {
      "returns a JSON list" in {
        Get("/User") ~> getUsers ~> check {
          assert(contentType.mediaType.isApplication)
          //Check content type
          contentType.toString should include("application/json")
          //check response is of the right type
          val response = responseAs[List[User]]
          //check the response size and content
          response.size should equal(2)
          response.head.name should equal("Tom")
          //Check http status
          status should equal(StatusCodes.OK)
        }
      }
    }
  }

}
