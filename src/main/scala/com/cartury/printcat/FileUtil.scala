package com.cartury.printcat

import java.io.{File, FileInputStream}

import javax.print.attribute.{HashDocAttributeSet, HashPrintRequestAttributeSet}
import javax.print.{DocFlavor, PrintServiceLookup, SimpleDoc}

object FileUtil {
  def print(filePath : String) : Unit = print(new File(filePath))

  def print(file : File) : Unit = {
    val pras = new HashPrintRequestAttributeSet
    val flavor = DocFlavor.INPUT_STREAM.AUTOSENSE
    val services = PrintServiceLookup lookupPrintServices(flavor, pras)
    val defaultService = PrintServiceLookup.lookupDefaultPrintService
    if (defaultService != null) {
      try {
        val job = defaultService.createPrintJob
        val fis = new FileInputStream(file)
        val das = new HashDocAttributeSet
        val doc = new SimpleDoc(fis, flavor, das)
        job.print(doc, pras)
      } catch {
        case e: Throwable => e.printStackTrace
      }
    }
  }
}
