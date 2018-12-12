package com.khinthirisoe.listmaker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.widget.EditText

class ListDetailActivity : AppCompatActivity() {

    lateinit var addTaskButton: FloatingActionButton
    lateinit var list: TaskList
    private var listDetailFragment: ListDetailFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_detail)

        list = intent.getParcelableExtra(MainActivity.INTENT_LIST_KEY)
        title = list.name

        listDetailFragment = ListDetailFragment.newInstance(list)
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, listDetailFragment!!, getString(R.string.list_fragment_tag))
            .commit()

        addTaskButton = findViewById<FloatingActionButton>(R.id.add_task_button)
        addTaskButton.setOnClickListener {
            showCreateTaskDialog()
        }
    }

    private fun showCreateTaskDialog() {
        val taskEditText = EditText(this)
        taskEditText.inputType = InputType.TYPE_CLASS_TEXT
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.task_to_add)
        builder.setView(taskEditText)
        builder.setPositiveButton(R.string.add_task) { dialog, i ->
            val task = taskEditText.text.toString()
            listDetailFragment?.addTask(task)
            dialog.dismiss()
        }
        builder.create().show()
    }

    override fun onBackPressed() {
        val bundle = Bundle()
        bundle.putParcelable(MainActivity.INTENT_LIST_KEY, list)
        val intent = Intent()
        intent.putExtras(bundle)
        setResult(Activity.RESULT_OK, intent)
        super.onBackPressed()

    }
}
