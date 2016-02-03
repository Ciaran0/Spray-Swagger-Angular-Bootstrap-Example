package me.ciaranoconnor.models

import com.wordnik.swagger.annotations.{ApiModel, ApiModelProperty}

import spray.json._
import scala.annotation.meta.field

@ApiModel(description = "A user object")
case class User
(
  @(ApiModelProperty @field)(value = "unique identifier for the user")
  id: Long,
  @(ApiModelProperty @field)(value = "name of the user")
  name: String
)

object User extends DefaultJsonProtocol {
  implicit val userFormat = jsonFormat2(User.apply)
}