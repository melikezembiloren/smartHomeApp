package com.example.awoxapp.login

open class TextControl {

      companion object { //nesne oluşturmaya gerek kalmadan erişilebilirler

        val patternForUpperCase = ("\\p{Lu}")
        val patternForLowerCase = ("\\p{Ll}")
        val patternForSymbols = ("[! @ # $ % ^ & * ( ) _ + = [ ] { }  | ; : '  , . / < > ? ]")
        val patternForNumbers = ("\\p{Nd}")


        fun textControlForUpperCase(text: String): Boolean {

            var result = true

            if (!text.contains(Regex(patternForUpperCase))) {

                result = false
            }

            return result

        }

        fun textControlForLowerCase(text: String): Boolean {

            var result = true

            if (!text.contains(Regex(patternForLowerCase))) {

                result = false
            }

            return result

        }

        fun textControlForSymbols(text: String): Boolean {

            var result = true

            if (!text.contains(Regex(patternForSymbols))) {

                result = false
            }

            return result

        }

        fun textControlForNumbers(text: String): Boolean {

            var result = true

            if (!text.contains(Regex(patternForNumbers))) {

                result = false
            }

            return result

        }


    }



}