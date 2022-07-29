package com.example.todolist
import android.annotation.SuppressLint
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import kotlinx.android.synthetic.main.item_todo.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

class TodoAdapter(private val items: MutableList<Todo>) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    class TodoViewHolder(item_view: View) : RecyclerView.ViewHolder(item_view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.item_todo,
            parent,
            false,
        ))
    }

    fun addTodoItem(todo: Todo){
        items.add(todo)
        notifyItemInserted(items.size - 1)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun deleteTodoItems() {
        items.removeAll { todo ->
            todo.is_completed
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(text_item_todo_name: TextView, isChecked: Boolean) {
        if(isChecked){
            text_item_todo_name.paintFlags = text_item_todo_name.paintFlags or STRIKE_THRU_TEXT_FLAG
        }
        else{
            text_item_todo_name.paintFlags = text_item_todo_name.paintFlags or STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val current_item = items[position]
        holder.itemView.apply {
            text_item_todo_name.text = current_item.item_name
            check_box_done.isChecked = current_item.is_completed
            toggleStrikeThrough(text_item_todo_name, current_item.is_completed)
            check_box_done.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(text_item_todo_name, isChecked)
                current_item.is_completed = !current_item.is_completed
            }
        }
    }

    override fun getItemCount(): Int {
        return (items.size)
    }
}