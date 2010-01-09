package com.sevendigital.scala.seventweet.http

class Response(val status : Int, val text : String)

object Response {
	def okay(text : String) = new Response(200, text)
}