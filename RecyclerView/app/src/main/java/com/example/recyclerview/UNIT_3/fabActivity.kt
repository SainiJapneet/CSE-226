package com.example.recyclerview.UNIT_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.recyclerview.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class fabActivity : AppCompatActivity() {

    lateinit var fabHome: FloatingActionButton
    lateinit var fabAccount: FloatingActionButton
    lateinit var fabAdd: FloatingActionButton
    var fabVisibility = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fab)

        fabHome = findViewById(R.id.fabHome)
        fabAccount = findViewById(R.id.fabAccount)
        fabAdd = findViewById(R.id.fabAdd)



        fabAdd.setOnClickListener {
            if(!fabVisibility){
                fabAccount.show()
                fabAccount.visibility= View.VISIBLE
                fabHome.show()
                fabHome.visibility= View.VISIBLE

                fabAdd.setImageDrawable(resources.getDrawable(R.drawable.cancel))
                fabVisibility = true
            }else{
                fabAccount.hide()
                fabAccount.visibility= View.GONE
                fabHome.hide()
                fabHome.visibility= View.GONE

                fabAdd.setImageDrawable(resources.getDrawable(R.drawable.add))
                fabVisibility = false
            }
        }

        fabHome.setOnClickListener {
            Toast.makeText(this,"Moving to home page", Toast.LENGTH_SHORT).show()
        }
        fabAccount.setOnClickListener {
            Toast.makeText(this,"Opening account panel ...", Toast.LENGTH_SHORT).show()
        }

    }
}