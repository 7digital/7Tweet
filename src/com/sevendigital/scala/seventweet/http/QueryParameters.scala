package com.sevendigital.scala.seventweet.http

import org.apache.commons.httpclient.NameValuePair
import collection.mutable.ListBuffer

class QueryParameters(nameValuePairs : List[NameValuePair]) {
	def this() = this(List())
	
	private lazy val parameters : ListBuffer[NameValuePair] = new ListBuffer[NameValuePair]()

	nameValuePairs.foreach(parameters.append(_))

	def toArray = parameters.toArray
}