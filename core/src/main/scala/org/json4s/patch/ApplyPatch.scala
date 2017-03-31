package org.json4s.patch

/**
  * Created by pankhuri on 2/6/17.
  */
object ApplyPatch {

  def applyJsonPatch(inputString: String, patch:JsonPatch): String = {
    var workingString = inputString
    for (patchElem <- patch.returnElements()){
        workingString = applyJsonPatchElement(workingString, patchElem)
    }
    workingString
  }

  def applyJsonPatchElement(inputString: String, patchElement: JsonPatchElement): String = {
    patchElement.apply(inputString)
  }
}
