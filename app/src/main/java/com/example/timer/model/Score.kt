package com.example.timer.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by ignacy on 22.12.17.
 */
@Entity
data class Score(@PrimaryKey(autoGenerate = true) var id: Long = 0, val scramble: String?, val time: Long, val formattedTime: String?)
