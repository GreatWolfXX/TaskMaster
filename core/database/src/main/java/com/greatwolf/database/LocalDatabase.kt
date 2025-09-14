package com.greatwolf.database

import app.cash.sqldelight.db.SqlDriver
import com.greatwolf.Database
import com.greatwolf.models.Profile

class LocalDatabase(
    databaseDriver: SqlDriver
) {
    private val database = Database(databaseDriver)
    private val query = database.profileTableQueries

    fun getProfileById(id: String): Profile? {
        val profileEntity = query.getProfileById(id)
            .executeAsOneOrNull()
        return profileEntity?.let { entity ->
            Profile(
                id = entity.user_id,
                fullName = entity.full_name,
                userName = entity.user_name,
                dataOfBirth = entity.data_of_birth,
                email = entity.email,
                phone = entity.phone,
                imageUrl = entity.image_url
            )
        }
    }

    fun updateProfileById(profile: Profile) {
        query.updateProfileById(
            user_id = profile.id,
            full_name = profile.fullName,
            user_name = profile.userName,
            data_of_birth = profile.dataOfBirth,
            email = profile.email,
            phone = profile.phone
        )
    }

    fun deleteProfileById(id: String) {
        query.deleteProfileById(id)
    }
}