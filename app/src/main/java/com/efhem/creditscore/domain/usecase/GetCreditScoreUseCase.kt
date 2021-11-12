package com.efhem.creditscore.domain.usecase

import com.efhem.creditscore.domain.executor.PostExecutionThread
import com.efhem.creditscore.domain.models.CreditScoreEntity
import com.efhem.creditscore.domain.repository.CreditScoreRepository
import com.efhem.creditscore.domain.usecase.base.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCreditScoreUseCase @Inject constructor(
    private val repository: CreditScoreRepository,
    private val postExecutionThread: PostExecutionThread,
) : FlowUseCase<Unit, CreditScoreEntity>() {

    override val dispatcher: CoroutineDispatcher get() = postExecutionThread.io

    override fun execute(params: Unit?): Flow<CreditScoreEntity> {
        return flow {
            emit(repository.getCreditScore())
        }
    }

}