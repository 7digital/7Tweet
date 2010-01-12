package com.sevendigital.scala.seventweet.http

import java.net.URI
import org.apache.commons.httpclient.NameValuePair
import collection.mutable.ListBuffer

abstract class TheInternet {
	def get(resource : URI) : Response = get(resource, new QueryParameters())
	def get(resource : URI, parameters : QueryParameters) : Response
}

class QueryParameters(nameValuePairs : List[NameValuePair]) {
	def this() = this(List())
	private lazy val parameters : ListBuffer[NameValuePair] = new ListBuffer[NameValuePair]()

	nameValuePairs.foreach(parameters.append(_))

	def toArray = parameters.toArray
}