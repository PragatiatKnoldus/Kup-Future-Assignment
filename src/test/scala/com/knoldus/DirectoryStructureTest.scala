package com.knoldus

import org.scalatest.flatspec.AnyFlatSpec

import scala.concurrent.Await
import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class DirectoryStructureTest extends AnyFlatSpec {

  val dirStruct = new DirectoryStructure

  "Condition" should "give the list of directory struct for a valid path" in {
    val result: List[String] = Await.result(dirStruct.findDirectories("src"), 1 seconds)
    val actualResult = List("src/test", "src/test/scala", "src/test/scala/com", "src/test/scala/com/knoldus", "src/test/scala/com/knoldus/DirectoryStructureTest.scala", "src/main", "src/main/scala", "src/main/scala/com", "src/main/scala/com/knoldus", "src/main/scala/com/knoldus/DirectoryStructure.scala")
    assertResult(result)(actualResult)
  }

  "Condition" should "give exception for an invalid path" in {
    val error = "Path does not exist"
    val actualError = intercept[Exception] {
      Await.result(dirStruct.findDirectories("s"), 1 seconds)
    }
    assert(actualError.getMessage == error)
  }
}