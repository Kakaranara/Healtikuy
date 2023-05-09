package com.kocci.healtikuy.core.util.helper

enum class Avatar(val lowerNames: String, val prices: Int) {
    POPEYE("popeye", 800),
    FINN("finn", 100),
    ICE_KING("ice_king", 200),
    BRUTUS("brutus", 200),
    HELLO_KITTY("hello_kitty", 400),
}

data class CharacterInStore(
    val name: String,
    val price: Int
)

fun generateCharacterInStore() = listOf<CharacterInStore>(
    CharacterInStore(Avatar.FINN.lowerNames, Avatar.FINN.prices),
    CharacterInStore(Avatar.ICE_KING.lowerNames, Avatar.ICE_KING.prices),
    CharacterInStore(Avatar.BRUTUS.lowerNames, Avatar.BRUTUS.prices),
    CharacterInStore(Avatar.HELLO_KITTY.lowerNames, Avatar.HELLO_KITTY.prices),
    CharacterInStore(Avatar.POPEYE.lowerNames, Avatar.POPEYE.prices),
)