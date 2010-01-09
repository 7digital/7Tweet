package com.sevendigital.scala.seventweet.twitter

import com.sevendigital.scala.seventweet.http.TheInternet
import java.net.URI

class PublicSearch(val internet : TheInternet, val resource : URI) {
	def search(query : String) : Seq[SearchResult] = {
		val result = internet.get(resource)
		List()
	}
}