package com.example.fitnessapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.fitnessapp.databinding.ActivityMainBinding
import com.example.fitnessapp.fragments.ExerciseFragment
import com.example.fitnessapp.fragments.FoodFragment
import com.example.fitnessapp.fragments.FrontFragment
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity(), Communicator {

    private val frontFragment = FrontFragment()
    private val foodFragment = FoodFragment()
    private val exerciseFragment = ExerciseFragment()



    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(frontFragment)
        binding.bottomNavigation.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener {

            when(it.itemId){
                R.id.front -> replaceFragment(frontFragment)
                R.id.food -> replaceFragment(foodFragment)
                R.id.exercise -> replaceFragment(exerciseFragment)
            }
            true
        })

    }

    override fun passDataCom(editTextInput: String) {
        val bundle = Bundle()
        bundle.putString("input_txt", editTextInput)


        val frag2 = FoodFragment()
        frag2.arguments = bundle
    }

    private fun replaceFragment(fragment: Fragment){
        if(fragment != null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }
}

interface Communicator {
    fun passDataCom(editTextInput: String)
}



