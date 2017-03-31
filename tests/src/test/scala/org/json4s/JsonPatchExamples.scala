package org.json4s

import org.specs2.mutable._
import org.json4s.patch._
/**
  * Created by pankhuri on 2/20/17.
  */
class JsonPatchExamples extends Specification{

  val originalJson =
    """
      |{
      |    "tool":
      |    {
      |        "jsonpath":
      |        {
      |            "creator":
      |            {
      |                "name": "Jayway Inc.",
      |                "location":
      |                [
      |                    "Malmo",
      |                    "San Francisco",
      |                    "Helsingborg"
      |                ]
      |            }
      |        }
      |    },
      |
      |    "book":
      |    [
      |        {
      |            "title": "Beginning JSON",
      |            "price": 49.99
      |        },
      |
      |        {
      |            "title": "JSON at Work",
      |            "price": 29.99
      |        }
      |    ]
      |}
    """.stripMargin.toString

  "Json patch add" in {
    val patch1 = AddElement("tool.jsonpath.creator.location", "India")
    val jpatch = new JsonPatch(Array(patch1))
    val modifiedJson = ApplyPatch.applyJsonPatch(originalJson, jpatch)
    val expectedJson = """{"tool":{"jsonpath":{"creator":{"name":"Jayway Inc.","location":"India"}}},"book":[{"title":"Beginning JSON","price":49.99},{"title":"JSON at Work","price":29.99}]}"""
    modifiedJson must_== expectedJson
  }

  "Json patch remove" in {
    val patch2 = RemoveElement("tool.jsonpath.creator.name")
    val jpatch = new JsonPatch(Array(patch2))
    val modifiedJson = ApplyPatch.applyJsonPatch(originalJson, jpatch)
    val expectedJson = """{"tool":{"jsonpath":{"creator":{"location":["Malmo","San Francisco","Helsingborg"]}}},"book":[{"title":"Beginning JSON","price":49.99},{"title":"JSON at Work","price":29.99}]}"""
    modifiedJson must_== expectedJson
  }

  "Json patch test" in {
    val patch2 = TestElement("tool.jsonpath.creator.location", """["Malmo","San Francisco","Helsingborg"]""")
    val jpatch = new JsonPatch(Array(patch2))
    val modifiedJson = ApplyPatch.applyJsonPatch(originalJson, jpatch)
    val expectedJson = "true"
    modifiedJson must_== expectedJson
  }

  //  "Json patch replace" in {
//    val patch2 = ReplaceElement("tool.jsonpath.creator.name", "Jayway Ltd.")
//    val jpatch = new JsonPatch(Array(patch2))
//    val modifiedJson = ApplyPatch.applyJsonPatch(originalJson, jpatch)
//    val expectedJson = """{"tool":{"jsonpath":{"creator":{"name":"Jayway Ltd.","location":["Malmo","San Francisco","Helsingborg"]}}},"book":[{"title":"Beginning JSON","price":49.99},{"title":"JSON at Work","price":29.99}]}"""
//    modifiedJson must_== expectedJson
//  }
}
