package com.sevendigital.scala.seventweet.unit.tests

import org.junit.Test
import org.junit.Assert._
import org.hamcrest.core.Is._
import org.hamcrest.core.IsNot._
import org.hamcrest.core.IsEqual._
import io.Source
import util.parsing.json.JSON
import java.util.Calendar._
import java.util.{SimpleTimeZone, Date}

class UnitTestFixture {
	protected def given_some_twitter_search_json {
		json = parse(MUSIC_MONDAY_SEARCH_RESULT)
	}

	protected def toDate(
		year : Int,
		month : Int,
		day : Int
	) : Date = {
		val BLANK = 0
		toDateAndTimeGmt(year, month, day, BLANK, BLANK, BLANK )
	}

	protected def toDateAndTimeGmt(
		year : Int, month : Int, 	day : Int,
		hour : Int, minute : Int,	second : Int
	) : Date = {
		val calendar = getInstance(GMT)
		calendar.set(year, month, day, hour, minute, second)
		calendar.set(MILLISECOND, 0)

		calendar.getTime
	}
	
	var json : Map[String, String] = Map()

	private def parse(file : String) : Map[String, String] =
		JSON.parseFull(load(file)).get.asInstanceOf[Map[String, String]]

	private def load(file : String) = Source.fromFile(file).getLines.mkString
	private val GMT = new SimpleTimeZone(0, "GMT")
	private val MUSIC_MONDAY_SEARCH_RESULT = "test/res/search-twitter-example.json"
}