package com.example.fitnessapp.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.ListView
import android.widget.Toast
import com.example.fitnessapp.Communicator
import com.example.fitnessapp.DBHelper
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.FragmentExerciseBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ExerciseFragment : Fragment(R.layout.fragment_exercise) {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var comm: Communicator
    private var _binding: FragmentExerciseBinding? = null
    private val binding get() =_binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentExerciseBinding.inflate(inflater, container, false)
        comm = requireActivity() as Communicator
        _binding = binding


     /*   val array: Array<String> = arrayOf()
        val list: MutableList<String> = array.toMutableList()
        val listView:ListView = binding.listView
        var adapter: ArrayAdapter<String>
       */

        binding?.exerciseBtn.setOnClickListener{

            val db = DBHelper(requireActivity(),null)

            val amount = binding.exercisetxt.text.toString().toInt()
            val type = binding.type.text.toString()

            db.addExer(amount, type)

            Toast.makeText(requireActivity(), binding.exercisetxt.text.toString() + "lisätty", Toast.LENGTH_SHORT).show()

            binding.exerciseId.text = "Added " + binding.exercisetxt.text.toString() + " minutes of " + binding.type.text.toString()

            binding.exercisetxt.text.clear()
            binding.type.text.clear()



        }
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_exercise, container, false)
        return binding?.root

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ExerciseFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ExerciseFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}