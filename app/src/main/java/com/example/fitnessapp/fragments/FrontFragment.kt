package com.example.fitnessapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fitnessapp.Communicator
import com.example.fitnessapp.DBHelper
import com.example.fitnessapp.DBHelper2
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.FragmentFoodBinding
import com.example.fitnessapp.databinding.FragmentFrontBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class FrontFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var inputText: String? = ""
    private lateinit var comm: Communicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private var _binding: FragmentFrontBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFrontBinding.inflate(inflater, container, false)
        comm = requireActivity() as Communicator
        _binding = binding
        //inputText = arguments?.getString("input_txt")
        //binding.totalFood.text = inputText

        val db = DBHelper(requireActivity(), null)
        val db2 = DBHelper2(requireActivity(), null)

        val cursor = db.getCal()

        cursor!!.moveToFirst()
        binding.totalFood.text = cursor.getString(cursor.getColumnIndex(DBHelper.AMOUNT))
        binding.totalExercise.text = cursor.getString(cursor.getColumnIndex(DBHelper2.AMOUNT))

        while(cursor.moveToNext()) {
            binding.totalFood.text = cursor.getString(cursor.getColumnIndex(DBHelper.AMOUNT))
            binding.totalExercise.text = cursor.getString(cursor.getColumnIndex(DBHelper2.AMOUNT))
        }
        cursor.close()

        return binding?.root
    }



    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FrontFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}