package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var itemsAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        itemsAdapter = TodoAdapter(mutableListOf())

        recycler_view_todo_list.adapter = itemsAdapter
        recycler_view_todo_list.layoutManager = LinearLayoutManager(this)

        button_add_todo_item.setOnClickListener {
            val todoName = edit_text_title.text.toString()
            if(todoName.isNotEmpty()) {
                val item = Todo(todoName)
                itemsAdapter.addTodoItem(item)
                edit_text_title.text.clear()
            }
        }
        button_remove_done_item.setOnClickListener {
            itemsAdapter.deleteTodoItems()
        }
    }
}