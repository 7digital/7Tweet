package com.sevendigital.scala.seventweet.twitter

import java.text.{ParsePosition, SimpleDateFormat}
import collection.mutable.ListBuffer

class JsonAdapter {
	def toSearchResults(json : Map[String, Any]) = {
		this validate json

		val searchResults = findResults(json)

		val buffer = new ListBuffer[SearchResult]

		searchResults.foreach(buffer += toSearchResult(_))

		buffer toList
	}

	private def findResults(json : Map[String, Any]) = {
		 json.get("results").get.asInstanceOf[List[List[(String, String)]]];
	}

	private def validate(json : Map[String, Any]) {
		if (false == json.contains("results"))
			throw new Exception("Malformed json <" + json.toString + ">")
	}

	private def toSearchResult(result : List[(String, String)]) : SearchResult = {
		val createdDate	 	= tupleValue(result, "created_at")
		val text 			= tupleValue(result, "text")

		new SearchResult(text, parseDate(createdDate))
	}

	// TODO: Consider tuple reader
	private def tupleValue(result : List[(String, String)], name : String) = {
		val tuple = result.find(_._1 == name)

		if (tuple == None)
			throw new Exception("No tuple found with first element <" + name + ">")

		tuple.get._2
	}

	private def parseDate(dateText : String) = dateFormat parse(dateText, new ParsePosition(0))

	private lazy val dateFormat = new SimpleDateFormat("EEE, DD MMM yyyy HH:mm:ss ZZZZ")
}