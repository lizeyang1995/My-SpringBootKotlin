package com.example.MySpringBootKotlin.common

import arrow.core.*

fun <A> Try<A>.mapException(map: (exeption: Throwable) -> Throwable): Try<A> =
        fold({ map(it).failure() }, { it.success() })

fun <A> Try<A>.orThrow(): A = fold({ throw it }, ::identity)

fun <A> Option<A>.toTry(ifEmpty: () -> Throwable): Try<A> =
        fold({ ifEmpty().failure() }, { it.success() })