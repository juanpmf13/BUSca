package me.dio.busca.dados.servicos

import okhttp3.logging.HttpLoggingInterceptor

val intercepitador = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)