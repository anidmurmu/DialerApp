package com.example.ui.helper

import android.util.Patterns
import java.util.regex.Matcher
import java.util.regex.Pattern

const val CONTACT_NUMBER_LEN = 10

fun isEmailValid(email: String): Boolean {
  if (email.length < 10) {
    return false
  }
  if (email.contains("@")) {
    val emailSplit = email.split("@".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
    if (emailSplit.size > 1) {
      val address = emailSplit[1]
      if (address.contains(".")) {
        val addressSplit = address.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        if (addressSplit.size > 1) {
          val errorMails = HashSet<String>()
          val errorDomains = HashSet<String>()
          errorMails.add("gail")
          errorMails.add("gmal")
          errorMails.add("gmil")
          errorMails.add("gmai")
          errorMails.add("mail")
          errorMails.add("email")
          errorDomains.add("co")
          errorDomains.add("con")
          errorDomains.add("cn")
          errorDomains.add("c")

          if (isNotEmpty(addressSplit[0]) && isNotEmpty(addressSplit[1])) {
            return when {
              errorMails.contains(addressSplit[0]) -> false
              errorDomains.contains(addressSplit[1]) -> addressSplit.size > 2 && address.contains(".co.in") && addressSplit[2].contains("in")
              else -> Patterns.EMAIL_ADDRESS.matcher(email).matches()
            }
          }
        }
      }
    }
  }

  return false
}

fun isNotEmpty(input: String?): Boolean {
  return input != null && input.trim { it <= ' ' }.isNotEmpty()
}

fun isNameValid(name: String): Boolean {
  val regex = "^([a-zA-Z]{2,}\\s[a-zA-Z]+'?-?[a-zA-Z]{2,}\\s?([a-zA-Z]+)?)"
  val pattern: Pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE)
  val matcher: Matcher = pattern.matcher(name)
  return matcher.find()
}

fun isContactNumberValid(number: String): Boolean {
  return number.trim().length == CONTACT_NUMBER_LEN
}