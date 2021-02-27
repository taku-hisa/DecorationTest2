package com.example.decorationtest2

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import com.example.decorationtest2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.apply{

            adapter =  ConcatAdapter (
                ExpandableItemAdapter(context, "1", listOf("1-1", "1-2", "1-3")),
                ExpandableItemAdapter(context, "2", listOf("2-1", "2-2", "2-3")),
                ExpandableItemAdapter(context, "3", listOf("3-1", "3-2", "3-3")),
            )

            layoutManager =
                when {
                    resources.configuration.orientation
                            == Configuration.ORIENTATION_PORTRAIT
                    -> GridLayoutManager(context, 2)
                    else
                    -> GridLayoutManager(context, 4)
                }
        }
    }
}