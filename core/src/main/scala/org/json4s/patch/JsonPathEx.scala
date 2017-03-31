package org.json4s.patch

import com.jayway.jsonpath.JsonPath

/**
  * Created by pankhuri on 2/6/17.
  */
object JsonPathEx {
  //def main(args: Array[String]) : Unit = {
  def test(): Unit = {
    println("Hey")
    val json = "{\n    \"store\": {\n        \"book\": [\n            {\n                \"category\": \"reference\",\n                \"author\": \"Nigel Rees\",\n                \"title\": \"Sayings of the Century\",\n                \"price\": 8.95\n            },\n            {\n                \"category\": \"fiction\",\n                \"author\": \"Evelyn Waugh\",\n                \"title\": \"Sword of Honour\",\n                \"price\": 12.99\n            },\n            {\n                \"category\": \"fiction\",\n                \"author\": \"Herman Melville\",\n                \"title\": \"Moby Dick\",\n                \"isbn\": \"0-553-21311-3\",\n                \"price\": 8.99\n            },\n            {\n                \"category\": \"fiction\",\n                \"author\": \"J. R. R. Tolkien\",\n                \"title\": \"The Lord of the Rings\",\n                \"isbn\": \"0-395-19395-8\",\n                \"price\": 22.99\n            }\n        ],\n        \"bicycle\": {\n            \"color\": \"red\",\n            \"price\": 19.95\n        }\n    },\n    \"expensive\": 10\n}"
    //    val parsed = JsonPath.parse(json)
    println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh")
    //    print(parsed)
    val jstring =
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
      """.stripMargin
    val jsonContext = JsonPath.parse(jstring)
    val creator = "$['tool']['jsonpath']['creator']"
    val name = "$['tool']['jsonpath']['creator']['name']"
    val agel = "$['tool']['jsonpath']['creator']['age']"
    val locations = "$['tool']['jsonpath']['creator']['location'][*]"
    val loc_array = "$['tool']['jsonpath']['creator']['location']"
    val loc_zero = "$['tool']['jsonpath']['creator']['location'][0]"
    //jsonContext.delete(name)
    //jsonContext.set(name, "Jpath")
    //println(jsonContext.set(agel, "5").json())   -Doesn't work
    //jsonContext.add(loc_array, "India")
    //jsonContext.put(creator, "age", "5")
    println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")
    //jsonContext.set(loc_zero, "5")
    //println(jsonContext.json())
    //print(jsonContext.read(locations))
//    val loc = "/a/b/c"
//    val path = JsonPath.compile(loc)
//    println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")
//    println(path.isInstanceOf[JsonPath])
    print("hey")
  }
}
