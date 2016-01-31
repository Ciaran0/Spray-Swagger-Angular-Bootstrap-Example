package me.ciaranoconnor.api

import com.typesafe.config.ConfigFactory

object Config {
  private val config = ConfigFactory.load()

  object HttpConfig {
    private val httpConfig = config.getConfig("http")
    lazy val host = httpConfig.getString("host")
    lazy val port = httpConfig.getInt("port")
  }
}
