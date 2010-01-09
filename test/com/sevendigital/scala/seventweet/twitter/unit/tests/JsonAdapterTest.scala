package com.sevendigital.scala.seventweet.twitter.unit.tests

import org.junit.Test
import org.junit.Assert._
import org.hamcrest.core.Is._
import org.hamcrest.core.IsNot._
import org.hamcrest.core.IsEqual._
import com.sevendigital.scala.seventweet.twitter.JsonAdapter
import java.util.Calendar

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
			("created_at", "Thu, 07 Jan 2010 14:01:55 +0000"),
			("text", "")
		))

		val someJsonWithOneResult = Map("results" -> theResults)

		val expectedDate = toDateAndTime(2010, Calendar.JANUARY, 7, 14, 1, 55);
		
		val result = new JsonAdapter().toSearchResults(someJsonWithOneResult);

		assertThat(result.first.created, is(equalTo(expectedDate)))
    }

	@Test { val expected = classOf[Exception] }
	def toSearchResults_throws_json_error_if_there_are_no_results {
		val someJsonWithNoResults = Map("CHUBBY" -> "BAT OR RAIN")
		val result = new JsonAdapter().toSearchResults(json);
	}
}