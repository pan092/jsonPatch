package org.json4s.patch

import com.jayway.jsonpath.{InvalidPathException, JsonPath}

/**
  * Created by pankhuri on 2/6/17.
  */

class JsonPatch(val jsonPatchElements: Array[JsonPatchElement]) {
  def apply(i: Int): JsonPatchElement = jsonPatchElements(i)
  def bool(): Boolean = !jsonPatchElements.isEmpty
  def returnElements() : Array[JsonPatchElement] = jsonPatchElements
}

abstract class JsonPatchElement(val path: String){
  def apply(originalJson: String): String
}

case class AddElement(override val path: String, val value: String)
  extends JsonPatchElement(path){

  def apply(originalJson: String): String = {
    val jsonContext = JsonPath.parse(originalJson)
    val orig = jsonContext.toString
    try {
      val jsonPath = JsonPath.compile(path)
      jsonContext.set(jsonPath.getPath, value)
      //TODO: Check adding a new node using set. put works.
    }
    catch{
      case invalidPath: InvalidPathException =>  InvalidJsonPatch("Path not valid")
      case e: Exception => IncompatibleJsonPatch("Operation not permitted")
    }
    jsonContext.jsonString()
  }
}

case class RemoveElement(override val path: String) extends JsonPatchElement(path){
  def apply(originalJson: String): String = {
      val jsonContext = JsonPath.parse(originalJson)
      try{
        val jsonPath = JsonPath.compile(path)
        jsonContext.delete(jsonPath.getPath)
      }
      catch{
        case invalidPath: InvalidPathException =>  InvalidJsonPatch("Path not valid")
        case e: Exception => IncompatibleJsonPatch("Operation not permitted")
      }
      jsonContext.jsonString()
    }
}

case class ReplaceElement(override val path: String, val value: String) extends JsonPatchElement(path){
  def apply(originalJson: String): String = {
    val tempString = RemoveElement(path).apply(originalJson)
    AddElement(path, value).apply(tempString)
  }
}

case class MoveElement(override val path: String, val from: String) extends JsonPatchElement(path){
  def apply(originalJson: String): String = {
    val jsonContext = JsonPath.parse(originalJson)
    try{
      val jsonFromPath = JsonPath.compile(from)
      val jsonToPath = JsonPath.compile(path)
      val value = jsonContext.read(jsonFromPath.getPath)
      val tempString = RemoveElement(jsonFromPath.getPath) .apply(originalJson)
      AddElement(jsonToPath.getPath, value).apply(tempString)
    }
    catch{
      case invalidPath: InvalidPathException =>  InvalidJsonPatch("Path not valid")
      case e: Exception => IncompatibleJsonPatch("Operation not permitted")
    }
    jsonContext.jsonString()
  }
}

case class CopyElement(override val path: String, val from: String) extends JsonPatchElement(path) {
  def apply(originalJSon: String): String = {
    val jsonContext = JsonPath.parse(originalJSon)
    try{
      val jsonFromPath = JsonPath.compile(from)
      val jsonToPath = JsonPath.compile(path)
      val value = jsonContext.read(jsonFromPath.getPath)
      AddElement(jsonToPath.getPath, value).apply(originalJSon)
    }
    catch{
      case invalidPath: InvalidPathException =>  InvalidJsonPatch("Path not valid")
      case e: Exception => IncompatibleJsonPatch("Operation not permitted")
    }
    jsonContext.jsonString()
  }
}

case class TestElement(override val path: String, val value: String) extends JsonPatchElement(path) {
  def apply(originalJson: String): String = {
      var returnValue = "false"
      val jsonContext = JsonPath.parse(originalJson)
      try{
        val jsonPath = JsonPath.compile(path)
        val readValue = jsonContext.read(jsonPath.getPath).toString
        if (readValue.equals(value)){
          returnValue = "true"
        }
      }
      catch{
        case invalidPath: InvalidPathException =>  InvalidJsonPatch("Path not valid")
        case e: Exception => IncompatibleJsonPatch("Operation not permitted")
      }
      returnValue
    }
}