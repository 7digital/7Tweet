package com.sevendigital.scala.seventweet.twitter

import java.net.URI
import util.parsing.json.JSON
import org.apache.http.HttpStatus
import com.sevendigital.scala.seventweet.http.{QueryParameters, Response, TheInternet}
import org.apache.commons.httpclient.{HttpException, NameValuePair}

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
		val response = internet.get(resource, new QueryParameters(parameters))

		this ensure response

		response
	}

	private def ensure(response : Response) {
		require(
			response != null,
			"The request to the internet returned null reference unexpectedly."
		)

		if (response.status != HttpStatus.SC_OK)
			throw new HttpException(
				"An unexpected status code was returned, expected <200> " +
				"but was <" + response.status + ">. " +
				"Message: " + response.text
			)

		require(
			false == response.text.isEmpty,
			"Did not expect no text to be returned."
		)
	}
}