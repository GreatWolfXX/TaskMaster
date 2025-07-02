package com.greatwolf.taskmaster

import com.greatwolf.common.BuildConfigFields
import com.greatwolf.common.BuildConfigFieldsProvider

class ApplicationBuildConfigFieldsProvider : BuildConfigFieldsProvider {

    override fun get(): BuildConfigFields = BuildConfigFields(
        supabaseUrl = BuildConfig.SUPABASE_URL,
        supabaseKey = BuildConfig.SUPABASE_KEY,
    )
}