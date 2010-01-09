package com.sevendigital.scala.seventweet.http

import java.net.URI

abstract class TheInternet {
	def get(resourceAndQuery : URI) : Response
}