package com.khinthirisoe.listmaker

import android.content.Context
import android.preference.PreferenceManager
import android.util.Log

class ListDataManager(val context: Context) {

    fun saveList(list: TaskList) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context).edit()
        for (item in list.tasks) {
            Log.d("message", item)
        }
        sharedPreferences.putStringSet(list.name, list.tasks.toHashSet()).apply()
    }

    fun readList(): ArrayList<TaskList> {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val sharedPreferenceContents = sharedPreferences.all
        val taskList = ArrayList<TaskList>()
        for (preferenceItem in sharedPreferenceContents) {
            val itemsHashSet = preferenceItem.value as HashSet<String>
            val list = TaskList(preferenceItem.key, ArrayList(itemsHashSet))
            taskList.add(list)
        }
        return taskList
    }

}