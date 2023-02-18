package me.jorgetargz.data.apollo

import com.apollographql.apollo3.ApolloClient
import me.jorgetargz.main.common.Constantes

object Apollo {

    val apolloClient = ApolloClient.Builder()
        .serverUrl(Constantes.URL)
        .build()

}