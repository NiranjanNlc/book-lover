package com.example.kitabikiro

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.example.kitabikiro.model.Book
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    private  var arraylist1= ArrayList<String>()
    private lateinit var listView: ListView
    private var adapter=CustomAdapter(this,arraylist1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Generate sample data
        listView= findViewById(R.id.book_list)
        arraylist1 =generateBookData()
        println("Niranjan "+arraylist1.toString())
        // perform set on query text listener event
        // perform set on query text listener event
        searchBook.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean
            {
                Toast.makeText(this@MainActivity,"not implemented till now",Toast.LENGTH_LONG).show()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean
            {
                Toast.makeText(this@MainActivity,newText.toString(),Toast.LENGTH_LONG).show()
                adapter =CustomAdapter(this@MainActivity,ArrayList(filterBooks(newText.toString())))
                listView.adapter =adapter
                return false
            }
        })
    }
    private  fun filterBooks(quey:String):List<String>
    {
       return  arraylist1.filter {it.contains(quey)}
    }

    private fun generateBookData(): ArrayList<String> {
        var  bookNamelist = arrayOf(
            "Lion", "Tiger", "Dog",
            "Cat", "Tortoise", "Rat", "Elephant", "Fox",
            "Cow", "Donkey", "Monkey"
        )

        for (i in bookNamelist)
        {
            val animalNames = Book(i)
            // Binds all strings into an array
            arraylist1.add(i)
        }
        println(arraylist1.toString() + " NIRANJAN")
        return arraylist1;
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }
    class CustomAdapter(private val context: Activity, private val booklist: ArrayList<String>): BaseAdapter()
    {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View
        {
            val rowView = context.layoutInflater.inflate(R.layout.book_list,null,true)
            rowView.findViewById<TextView>(R.id.nameLabel).text = booklist[position]
            return  rowView
        }

        override fun getItem(position: Int): Any
        {
            return  booklist[position];
           }

        override fun getItemId(position: Int): Long
        {
               return  position.toLong()
            }

        override fun getCount(): Int
        {
            return booklist.size;
        }

    }
}
