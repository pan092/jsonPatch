package org.json4s.patch

/**
  * Created by pankhuri on 2/6/17.
  */
trait JsonPatchExceptions extends Exception

case class InvalidJsonPatch(msg:String) extends Exception(msg) with JsonPatchExceptions

case class IncompatibleJsonPatch(msg: String) extends Exception(msg) with JsonPatchExceptions