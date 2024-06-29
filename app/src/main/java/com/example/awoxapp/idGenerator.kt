package com.example.awoxapp


object IdGenerator {
    private val idMap = mutableMapOf<String, Int>()

    fun generateId(tip: String): String {
        synchronized(this) {
            val currentId = idMap.getOrDefault(tip, 0)
            val nextId = currentId + 1
            idMap[tip] = nextId
            return "$tip$nextId"
        }
    }
}