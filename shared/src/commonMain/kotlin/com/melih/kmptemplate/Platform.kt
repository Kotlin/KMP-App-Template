package com.melih.kmptemplate

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
