package me.jorgetargz.data.apollo

import com.apollographql.apollo3.ApolloClient

object Apollo {

    const val URL = "http://localhost:8080/graphql"

    val apolloClient = ApolloClient.Builder()
        .serverUrl(URL)
        .build()

}