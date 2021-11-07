package com.efhem.creditscore.utils

import android.os.Bundle
import androidx.navigation.NavType
import com.efhem.creditscore.di.RemoteModule
import com.efhem.creditscore.domain.models.CreditScore
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.lang.RuntimeException

class AssetParamType : NavType<CreditScore>(isNullableAllowed = true) {
    override fun get(bundle: Bundle, key: String): CreditScore? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): CreditScore {
        return RemoteModule.provideMoshi().adapter(CreditScore::class.java).fromJson(value) ?: throw RuntimeException()
    }

    override fun put(bundle: Bundle, key: String, value: CreditScore) {
        bundle.putParcelable(key, value)
    }
}