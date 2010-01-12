package com.sevendigital.scala.seventweet.twitter

import java.net.URI
import util.parsing.json.JSON
import org.apache.commons.httpclient.NameValuePair
import com.sevendigital.scala.seventweet.http.{Response, TheInternet}

class PublicSearch(val internet : TheInternet, val resource : URI) {
	def search(query : String) : Seq[SearchResult] = search(query, 15)
	def search(query : String, resultCount : Int) : Seq[SearchResult] = {
		require (internet != null, "This class requires the internets!")
		
		val result = get(
			resource,
			List(
				new NameValuePair("q", query),
				new NameValuePair("rpp", resultCount.toString)
			)
		)

		val json = JSON.parseFull(result.text).get.asInstanceOf[Map[String, String]]
		new JsonAdapter().toSearchResults(json)
	}

	private def get(resource : URI, parameters : List[NameValuePair]) : Response = {
		val result = internet.get(resource, parameters)

		require(
			result.status == 200,
			"An unexpected status code was returned, expected <200> " +
			"but was <" + result.status + ">. " +
			"Message: " + result.text
		)

		require(
			result != null,
			"The request to the internet returned null reference unexpectedly"
		)

		result
	}
}