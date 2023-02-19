package me.jorgetargz.data.impl

import com.apollographql.apollo3.api.Mutation
import com.apollographql.apollo3.api.Query
import com.apollographql.apollo3.exception.ApolloException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import me.jorgetargz.data.apollo.Apollo.apolloClient
import me.jorgetargz.utils.NetworkResult
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

open class BaseRepository {

    fun <T : Query.Data, R> executeGraphQLQuery(
        query: Query<T>,
        transform: (T) -> R
    ): Flow<NetworkResult<R>> = flow {

        emit(NetworkResult.Loading())

        try {
            val response = apolloClient.query(query).execute().dataAssertNoErrors
            emit(NetworkResult.Success(transform(response)))
        } catch (e: ApolloException) {
            onError(e)
        }
    }

    fun <T : Mutation.Data, R> executeGraphQLMutation(
        mutation: Mutation<T>,
        transform: (T) -> R
    ): Flow<NetworkResult<R>> = flow {

        emit(NetworkResult.Loading())

        try {
            val response = apolloClient.mutation(mutation).execute().dataAssertNoErrors
            emit(NetworkResult.Success(transform(response)))
        } catch (e: ApolloException) {
            onError(e)
        }

    }

    private suspend fun <R> FlowCollector<NetworkResult<R>>.onError(e: ApolloException) {
        val logger: Logger = LogManager.getLogger(BaseRepository::class.java)
        emit(NetworkResult.Error("Error en la respuesta del servidor"))
        logger.error(e.message, e)
    }
}
