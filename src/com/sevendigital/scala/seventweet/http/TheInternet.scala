package com.sevendigital.scala.seventweet.http

import java.net.URI
abstract class TheInternet {
	def get(resource : URI) : Response = get(resource, new QueryParameters())
	def get(resource : URI, parameters : QueryParameters) : Response
}