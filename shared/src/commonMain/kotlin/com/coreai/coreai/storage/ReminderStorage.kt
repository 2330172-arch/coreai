package com.coreai.coreai.storage

import com.coreai.coreai.models.Reminder

object ReminderStorage {

    private val reminders =
        mutableListOf<Reminder>()

    fun saveReminders(
        newReminders: List<Reminder>
    ) {

        reminders.clear()

        reminders.addAll(
            newReminders
        )
    }

    fun getReminders():
            List<Reminder> {

        return reminders
    }
}