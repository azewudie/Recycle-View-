package com.example.todoapp.ToDoAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.TodoItemLayoutBinding
import com.example.todoapp.model.TodoCateg

class ToDoAdapter(private var dataSet:ArrayList<TodoCateg>):RecyclerView.Adapter<ToDoAdapter.TodoViewHolder>(){
    fun addNewTodoCat(newTodo:TodoCateg){
        dataSet.add(newTodo)
        notifyDataSetChanged()
    }
    class TodoViewHolder(val binding:TodoItemLayoutBinding):RecyclerView.ViewHolder(binding.root){
    }

    /**
     * used to create the viewHolder type defined at the subclass level
     * @param parent ->item_layout
     * @param viewType ->used when you need Multi-ViewHolders
     */

    override fun onCreateViewHolder(parent:ViewGroup,viewType:Int):TodoViewHolder{
       return TodoViewHolder(
           TodoItemLayoutBinding.inflate(
               LayoutInflater.from(parent.context),parent,false

           )
       )

    }

    /**
     * coonect the "data" with the Holder views.
     * @param holder The viewHolder inflate in the OnCreateViewHolder
     * @param postion The position from dataset
     */
    override fun onBindViewHolder(holder:TodoViewHolder,position:Int){
        holder.binding.tvTodoTitle.text = dataSet[position].todoTitle
        holder.binding.tvTodoCat.text = dataSet[position].todoCateg
    }
    /**
     * @return the number of rows to be displayed
     */
    override fun getItemCount():Int{
        return dataSet.size
    }


}

/*
Steps for Recyclerview
1. Create the item_layout
2. create the recyclerView.Adapter subclass
3 -Create the RecyclerView . ViewHolder   subclass
4.- Setup adapter and layout manager
 */