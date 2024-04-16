package com.speech.assistant.datas

data class VoiceInfo(val FriendlyName: String, val Gender: String,
                     val Language: String, val Locale: String, val ShortName: String, val Status: String, val SuggestedCodec: String, val VoiceTag: VoiceTag)


data class VoiceTag(val ContentCategories: List<String>, val VoicePersonalities: List<String>)