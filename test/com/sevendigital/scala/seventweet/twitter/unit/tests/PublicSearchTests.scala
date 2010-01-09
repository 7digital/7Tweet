package com.sevendigital.scala.seventweet.twitter.unit.tests

import org.junit.Test
import org.junit.Assert._
import org.hamcrest.core.Is._
import org.hamcrest.core.IsNot._
import org.hamcrest.core.IsEqual._
import org.mockito.Mockito._
import org.mockito.Matchers._
import java.net.URI
import com.sevendigital.scala.seventweet.twitter.PublicSearch
import com.sevendigital.scala.seventweet.http.{Response, TheInternet}

class PublicSearchTests {
    @Test
	def search_queries_the_internets {
		given_any_uri_returns("")

		new PublicSearch(mockInternet, SEARCH_TWITTER).search("for anything")

		verify(mockInternet, org.mockito.Mockito.times(1)).get(SEARCH_TWITTER)
    }

	private def given_any_uri_returns(result : String) {
		mockInternet = mock(classOf[TheInternet])
		when(mockInternet.get(any(classOf[URI]))).thenReturn(Response.okay(result));	
	}

	var mockInternet : TheInternet = null
	val SEARCH_TWITTER = new URI("http://search.twitter.com/search.json")
}