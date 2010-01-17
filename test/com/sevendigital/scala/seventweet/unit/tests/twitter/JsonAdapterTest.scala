package com.sevendigital.scala.seventweet.unit.tests.twitter

import java.util.Calendar
import org.junit.Test
import org.junit.Assert._
import org.hamcrest.core.Is._
import org.hamcrest.core.IsNot._
import org.hamcrest.core.IsEqual._
import com.sevendigital.scala.seventweet.twitter.JsonAdapter
import com.sevendigital.scala.seventweet.unit.tests.UnitTestFixture

class JsonAdapterTest extends UnitTestFixture {
    @Test
	def toSearchResults_returns_the_correct_number_of_results { 
		given_some_twitter_search_json

		val result = new JsonAdapter().toSearchResults(json);

		assertThat(result.size, is(equalTo(15)))
    }

	@Test
	def toSearchResults_returns_the_correct_dates {
		val theResults = List(List(
			("created_at", "Fri, 26 Oct 1984 20:00:00 +0000"),
			("text", "")
		))

		val someJsonWithOneResult = Map("results" -> theResults)

		val expectedDate = MICHAEL_JORDAN_DEBUTS_FOR_CHICAGO_BULLS
		
		val result = new JsonAdapter().toSearchResults(someJsonWithOneResult);

		assertThat(result.first.created, is(equalTo(expectedDate)))
    }

	@Test { val expected = classOf[Exception] }
	def toSearchResults_throws_json_error_if_there_are_no_results {
		val someJsonWithNoResults = Map("CHUBBY" -> "BAT OR RAIN")
		val result = new JsonAdapter().toSearchResults(json);
	}

	@Test
	def created_date_is_parsed_accounting_for_timezone {
		val theResults = List(List(
			("created_at", "Tue, 20 Nov 1990 19:30:00 +0100"),
			("text", "")
		))

		val someJsonWithOneResult = Map("results" -> theResults)

		val expectedDate = THATCHER_FAILS_TO_WIN_PARTY_MANDATE

		val result = new JsonAdapter().toSearchResults(someJsonWithOneResult);

		assertThat(result.first.created, is(equalTo(expectedDate)))
	}

	private val MICHAEL_JORDAN_DEBUTS_FOR_CHICAGO_BULLS =
		toDateAndTimeGmt(1984, Calendar.OCTOBER, 26, 20, 0, 00);

	private val THATCHER_FAILS_TO_WIN_PARTY_MANDATE =
		toDateAndTimeGmt(1990, Calendar.NOVEMBER, 20, 18, 30, 00);
}