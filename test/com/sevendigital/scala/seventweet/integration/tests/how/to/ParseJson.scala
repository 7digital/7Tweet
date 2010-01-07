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
		val json  	= JSON parseFull load(MUSIC_MONDAY_SEARCH_RESULT);
		val result 	= json.get.asInstanceOf[Map[String, String]]

		val expectedQuery 	= "%23musicmonday+OR+%23mm"
		val actual 			= result get("query") get

		assertThat(actual, is(equalTo(expectedQuery)))
    }

	private def load(file : String) = Source.fromFile(file).getLines.mkString

	private val MUSIC_MONDAY_SEARCH_RESULT = "test/res/search-twitter-example.json"
}