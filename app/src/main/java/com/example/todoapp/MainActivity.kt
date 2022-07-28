package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.todoapp.ToDoAdapter.ToDoAdapter
import com.example.todoapp.databinding.ActivityMainBinding
import java.lang.Object as Object

/**
 * The entery point of your application
 * Defines an Entire Screen in your App
 * It can only display a single xml ref
 */
private const val TAG = "MainActivity "
class MainActivity : AppCompatActivity(), View.OnClickListener {
    /**
     * Loading the xml references for this Activity
     * Creating/Init references [Views, DatabaseManger, Network, Dependencies]
     * OnCreate  is called only once per life time
     */
    // using find view by id
    private lateinit var listOfTodos: RecyclerView
    private lateinit var  adapter: ToDoAdapter

    // using viewBinding
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Toast.makeText(this,"Create",Toast.LENGTH_LONG).show()
        if(!::listOfTodos.isInitialized){
            listOfTodos = findViewById(R.id.rv_display_todo)
        }
        initViews()

    }

    override  fun  onCreateOptionsMenu(menu: Menu?):Boolean{
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.catagory_menu,menu)
        val createCategory = menu?.findItem(R.id.activity_category)
        createCategory?.setOnMenuItemClickListener {
            navigateToCategoryActivity()
            true
        }
        return true
    }
    private fun navigateToCategoryActivity() {
        // steps to "create" a new ACTIVITY
        val intentCategory: Intent = Intent()
        intentCategory.setClass(this
        ,ActivityCategory::class.java)
        intentCategory.putExtra("Data","Your name")

        startActivityForResult(intentCategory,1234)

        // sticky Intent!!(no the default behaviour)
//        intentCategory.setFlags(Intent.Flag_s)

    }

    private fun initViews() {
        // Interface Ref.
        binding.btnSaveTodo.setOnClickListener (this)

        // Anonymous IMPI
        binding.btnSaveTodo.setOnClickListener(
            object: View.OnClickListener{
                override fun onClick(po: View?){

                }
        }
        )
        // lambdas

        binding.btnSaveTodo.setOnClickListener {

        }

        // method references
        binding.btnSaveTodo.setOnClickListener(::doSomething)

        val linearManager = LinearLayoutManager(this)
        val gridManager = GridLayoutManager(this,3)
        val staggeredManager = StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.HORIZONTAL)


        binding.rvDisplayTodo.layoutManager = staggeredManager
        adapter = ToDoAdapter(ArrayList())
        binding.rvDisplayTodo.adapter =adapter

    }

    private fun doSomething(view: View?) {
     //todo get the edittext and evaluate the empty contents
        // todo update the recyclerview Adapter
        val input = binding.etInputTodo.text.toString()
        if(input.isNotEmpty())
            updateRecyclerView()
        else
            Toast.makeText(this,"please enter the text",Toast.LENGTH_LONG).show()

    }

    private fun updateRecyclerView(newTodo:String) {
        adapter.addNewTodoCat(newTodo?.toString())

    }


    /**
     * Your view hierarchy is Visible but not touchable intractable
     * Used to preload some data into the views.x
     *
     */

//    override fun onStart() {
//        super.onStart()
//        Toast.makeText(this,"Start",Toast.LENGTH_LONG).show()
//        Log.d(TAG, "onStart: ")
//    }

    /**
     * Your application is running
     */
//    override fun onResume() {
//        super.onResume()
//        Toast.makeText(this,"Resume",Toast.LENGTH_LONG).show()
//        Log.d(TAG, "onResume: ")
//    }

    /**
     * It will be invoked when
     * user navigates away
     * popup is showed
     * Multitask
     * the view hierarchy is visible but not touchable
     * save the current state.
     */
//    override fun onPause() {
//        super.onPause()
//        Toast.makeText(this,"Pause",Toast.LENGTH_LONG).show()
//        Log.d(TAG, "onPause: ")
//    }

    /**
     * The view hierarchy is no longer visible
     * App is still in memory
     * Dispose/close references
     * save the data references
     */

//    override fun onStop() {
//        super.onStop()
//        Log.d(TAG, "onStop: ")
//
//    }

    /**
     * App is about to be dispose from memory
     * once per life cycle
     * is not guarantee that this will be called.
     */


//    override fun onDestroy() {
//        super.onDestroy()
//        Toast.makeText(this,"Destroy",Toast.LENGTH_LONG).show()
//
//        Log.d(TAG, "onDestroy: ")
//    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }

    override fun onActivityResult(requestCode: Int,
                                  resultCode: Int,
                                  data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode ==1234 && resultCode == RESULT_OK && data !=null){
            val cewCategory = data?.getStringExtra("NewCategory")
            Log.d(TAG,"onActivityResult:$cewCategory")
        }
    }
}