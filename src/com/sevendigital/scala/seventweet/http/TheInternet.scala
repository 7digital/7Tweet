package com.sevendigital.scala.seventweet.http

import java.net.URI
import org.apache.commons.httpclient.NameValuePair

abstract class TheInternet {
	def get(resource : URI) : Response
	def get(resource : URI, parameters : List[NameValuePair]) : Response
}