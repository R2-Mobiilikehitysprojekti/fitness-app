package com.example.fitnessapp.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fitnessapp.Communicator
import com.example.fitnessapp.DBHelper
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.FragmentFoodBinding
import com.example.fitnessapp.databinding.FragmentFrontBinding
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.util.*

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

    @SuppressLint("Range")
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

        val cursor = db.getCal()

        if (cursor != null && cursor.moveToFirst() ) {
            cursor.moveToFirst()

            binding.totalFood.text = cursor.getString(cursor.getColumnIndex(DBHelper.TOTAL))


            while (cursor.moveToNext()) {
                binding.totalFood.text = cursor.getString(cursor.getColumnIndex(DBHelper.TOTAL))

            }

            cursor.close()
        }
        val cursor2 = db.getExer()

        if (cursor2 != null && cursor2.moveToFirst() ) {
            cursor2!!.moveToFirst()
            binding.totalExercise.text = cursor2.getString(cursor2.getColumnIndex(DBHelper.TOTAL2))
            while ((cursor2.moveToNext())) {
                binding.totalExercise.text =
                    cursor2.getString(cursor2.getColumnIndex(DBHelper.TOTAL2))

            }
            cursor2.close()
        }


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