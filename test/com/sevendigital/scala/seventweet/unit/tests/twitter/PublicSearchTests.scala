package com.sevendigital.scala.seventweet.unit.tests.twitter

import org.junit.Test
import org.junit.Assert._
import org.hamcrest.core.Is._
import org.hamcrest.core.IsNot._
import org.hamcrest.core.IsEqual._
import org.mockito.Mockito._
import org.mockito.Matchers._
import java.net.URI
import com.sevendigital.scala.seventweet.twitter.PublicSearch
import com.sevendigital.scala.seventweet.http.{QueryParameters, Response, TheInternet}

class PublicSearchTests {
    @Test
	def search_queries_the_internets {
		given_any_uri_returns(SOME_MINIMAL_JSON)

		new PublicSearch(mockInternet, SEARCH_TWITTER).search("")

		verify(mockInternet, times(1)).get(
			any(classOf[URI]), // TODO: Verify the URL is like the one supplied
			any(classOf[QueryParameters])
		)
    }
	
	private def given_any_uri_returns(result : String) {
		mockInternet = mock(classOf[TheInternet])
		when(
			mockInternet.get(
				any(classOf[URI]),
				any(classOf[QueryParameters])
			)
		).
		thenReturn(Response.okay(result));
	}

	var mockInternet : TheInternet = null
	val SEARCH_TWITTER = new URI("http://search.twitter.com/search.json")
	val SOME_MINIMAL_JSON = """{"results":[{"created_at":"","text":""}]}"""
}