package com.knoldus

import java.nio.file.FileSystems
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class DirectoryStructure {

  def findDirectories(path: String): Future[List[String]] = {
    def findFiles(path: String): List[String] = {
      val dir = FileSystems.getDefault.getPath(path)
      if (dir.toFile.isDirectory && dir.toFile.exists) {
        val subDir = dir.toFile.listFiles.toList
        subDir.flatMap { file =>
          if (file.isDirectory) file.toString +: findFiles(file.toString)
          else List(file.toString)
        }
      }
      else throw new Exception("Path does not exist")
    }

    Future(findFiles(path))
  }
}