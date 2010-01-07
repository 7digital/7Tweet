package com.sevendigital.scala.seventweet.integration.tests.how.to

import _root_.scala.util.parsing.json.JSON
import _root_.scala.io.Source
import org.junit.Test
import org.junit.Assert._
import org.hamcrest.core.Is._
import org.hamcrest.core.IsNot._
import org.hamcrest.core.IsEqual._

class ParseJson {
	@Test
	def with_scala_inbuilt {
		val jsonMap = parse(MUSIC_MONDAY_SEARCH_RESULT)

		val expectedQuery 	= "%23musicmonday+OR+%23mm"
		val actual 			= jsonMap get("query") get

		println(jsonMap)

		assertThat(actual, is(equalTo(expectedQuery)))
    }

	@Test
	def into_search_results {
		val jsonMap = parse(MUSIC_MONDAY_SEARCH_RESULT)
		val searchResults = jsonMap.get("results").get.asInstanceOf[List[List[(String, String)]]];

		val firstResult = searchResults.first
		
		val (_, profileImage) 	= firstResult.first
		val (_, createdDate) 	= firstResult(1)
		
		val fromUser 		= firstResult(2)
		val toUser 			= firstResult(3)
		val text 			= firstResult(4)
		val id 				= firstResult(5)
		val fromUserId 		= firstResult(6)
		val geo 			= firstResult(7)
		val isoLanguageCode	= firstResult(8)
		val source 			= firstResult(9)

		assertThat(profileImage, is(equalTo("http://a1.twimg.com/profile_images/591055468/PIC_2191_normal.JPG")))
		assertThat(createdDate, is(equalTo("Thu, 07 Jan 2010 14:01:55 +0000")))
	}

	private def parse(file : String) : Map[String, String] =
		JSON.parseFull(load(file)).get.asInstanceOf[Map[String, String]]

	private def load(file : String) = Source.fromFile(file).getLines.mkString

	private val MUSIC_MONDAY_SEARCH_RESULT = "test/res/search-twitter-example.json"
}