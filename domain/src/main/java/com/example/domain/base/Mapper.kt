package com.example.domain.base

interface Mapper<R, D> {
    fun mapFrom(type: R): D
}
