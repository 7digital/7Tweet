package com.sevendigital.scala.seventweet.twitter

import com.sevendigital.scala.seventweet.http.TheInternet
import java.net.URI
import util.parsing.json.JSON
import org.apache.commons.httpclient.NameValuePair

class PublicSearch(val internet : TheInternet, val resource : URI) {
	def search(query : String) : Seq[SearchResult] = search(query, 15)
	def search(query : String, resultCount : Int) : Seq[SearchResult] = {

		val result = internet.get(
			resource,
			List(
				new NameValuePair("q", query),
				new NameValuePair("rpp", resultCount.toString)
			)
		)

		val json = JSON.parseFull(result.text).get.asInstanceOf[Map[String, String]]
		new JsonAdapter().toSearchResults(json)
	}
}