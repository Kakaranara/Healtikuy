package com.kocci.healtikuy.core.util.store

enum class Avatar(val lowerNames: String) {
    POPEYE("popeye"),
    FINN("finn"),
    ICE_KING("ice_king"),
    BRUTUS("brutus"),
    HELLO_KITTY("hello_kitty"),
}

data class CharacterInStore(
    val name: String,
    val price: Int
)

fun generateCharacterInStore() = listOf<CharacterInStore>(
    CharacterInStore(Avatar.FINN.lowerNames, 100),
    CharacterInStore(Avatar.ICE_KING.lowerNames, 200),
    CharacterInStore(Avatar.BRUTUS.lowerNames, 200),
    CharacterInStore(Avatar.HELLO_KITTY.lowerNames, 400),
    CharacterInStore(Avatar.POPEYE.lowerNames, 800),
)

fun main() {
    println(Avatar.POPEYE.lowerNames)
}