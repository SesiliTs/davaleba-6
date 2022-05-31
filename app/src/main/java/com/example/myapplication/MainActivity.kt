package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var database: DatabaseReference
    private lateinit var myAdapter: Adapter
    private val bonusAdapter by lazy { BonusRecyclerViewAdapter() }
    private val userList = mutableListOf<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        database = FirebaseDatabase.getInstance().getReference("user")
        setUpAdapter()
        readData()
        updateUI()
    }

    private fun setUpAdapter() {
        myAdapter = Adapter(this, userList)
        binding.dataListView.adapter = myAdapter

        with(binding.dataRecyclerView) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = bonusAdapter
        }
    }

    private fun readData() {
        database.get().addOnSuccessListener {
            it.children.forEach { dataSnapshot ->
                userList.add(dataSnapshot.getValue(User::class.java)!!)
            }
            myAdapter.addAll(userList)
            bonusAdapter.setData(userList)
        }
    }

    private fun updateUI() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                userList.clear()
                snapshot.children.forEach {
                    userList.add(it.getValue(User::class.java)!!)
                }
                myAdapter.addAll(userList)
                bonusAdapter.setData(userList)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity, "$error", Toast.LENGTH_SHORT).show()
            }
        })
    }
}