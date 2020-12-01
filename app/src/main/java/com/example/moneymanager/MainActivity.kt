package com.example.moneymanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moneymanager.model.Tran
import com.example.moneymanager.viewmodel.TranViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),TranClicked {

    private lateinit var mViewModel: TranViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = RecyclerAdapter(this)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_id)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        mViewModel = ViewModelProvider(this).get(TranViewModel::class.java)
        mViewModel.allTransaction.observe(this,{
            adapter.setData(it)
            total_amount_id.text = adapter.totalSum().toString()
        })


        val button  = findViewById<Button>(R.id.trans_bt_id)
        button.setOnClickListener {
            val intent = Intent(this,SecondActivity::class.java)
            startActivity(intent)
        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_activity_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.delete_all_id ->{
                deleteAllNotesFromRV()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun deleteAllNotesFromRV(){
        mViewModel.deleteAllTransaction()
    }

    override fun onItemLongClicked(tran: Tran): Boolean {
        deleteAlertDialog(tran)
        return true
    }

    private fun deleteAlertDialog(tran: Tran){
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("DELETE")
        alertDialogBuilder.setMessage("Do you want to delete this Transaction ?")
        alertDialogBuilder.setIcon(R.drawable.ic_baseline_delete_24)
        alertDialogBuilder.setPositiveButton("Yes"
        ) { _, _ ->
            //Toast.makeText(this, "Positive Work", Toast.LENGTH_SHORT).show()
            //delete
            mViewModel.deleteTran(tran)
        }
        alertDialogBuilder.setNegativeButton("No"
        ){ _,_ ->

        }
        alertDialogBuilder.setNeutralButton("Cancel"
        ){ _,_ ->

        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

}