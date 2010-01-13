package com.sevendigital.scala.seventweet.integration.tests

import java.net.URI
import org.junit.Test
import org.junit.Assert._
import org.hamcrest.core.Is._
import org.hamcrest.core.IsNot._
import org.hamcrest.core.IsEqual._
import org.mockito.Mockito._
import org.mockito.Matchers._
import com.sevendigital.scala.seventweet.twitter.PublicSearch
import com.sevendigital.scala.seventweet.http.SimpleInternet

class PublicSearchTests {
	@Test
	def searching_returns_15_results_by_default {
		val publicSearch = new PublicSearch(new SimpleInternet, SEARCH_TWITTER)
		val results = publicSearch.search("#musicmonday")

		assertThat(results.size, is(equalTo(15)))
	}

    @Test
	def searching_returns_number_of_specified_results {
		val resultCount: Int = 10
		
		val publicSearch = new PublicSearch(new SimpleInternet, SEARCH_TWITTER)
		val results = publicSearch.search("#musicmonday", resultCount)

		assertThat(results.size, is(equalTo(resultCount)))
	}

	@Test
	def can_search_for_maximum_of_100_results {
		val publicSearch = new PublicSearch(new SimpleInternet, SEARCH_TWITTER)
		val results = publicSearch.search("#musicmonday", 500)

		assertThat(
			"Expected the limit for results to be 100",
			results.size, is(equalTo(100))
		)
	}

	val SEARCH_TWITTER = new URI("http://search.twitter.com/search.json")
}