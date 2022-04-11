package com.example.fitnessapp.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fitnessapp.Communicator
import com.example.fitnessapp.DBHelper
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.FragmentFoodBinding
import android.widget.Toast
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry

import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class FoodFragment : Fragment(R.layout.fragment_food) {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var comm: Communicator
    private var _binding: FragmentFoodBinding? = null
    private val binding get() = _binding!!


    private lateinit var barChart: BarChart
    private var scoreList = ArrayList<Score>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        //now draw bar chart with dynamic data
    }

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {

        _binding = FragmentFoodBinding.inflate(inflater, container, false)
        comm = requireActivity() as Communicator
        _binding = binding
        binding?.foodBtn.setOnClickListener{


            val db = DBHelper(requireActivity(), null)


            val amount = binding.foodTxt.text.toString().toInt()

            db.addCal(amount)

            Toast.makeText(requireActivity(), binding.foodTxt.text.toString() + " lisätty", Toast.LENGTH_SHORT).show()

            binding.foodId.text = binding.foodTxt.text.toString()

            binding.foodTxt.text.clear()

            //comm.passDataCom(binding.foodTxt.text.toString())

        }

        scoreList = getScoreList()

        initBarChart()

        val entries: ArrayList<BarEntry> = ArrayList()
        //you can replace this data object with  your custom object
        for (i in scoreList.indices) {
            val score = scoreList[i]
            entries.add(BarEntry(i.toFloat(), score.score.toFloat()))
        }


        val barDataSet = BarDataSet(entries, "")
        barDataSet.setColors(*ColorTemplate.COLORFUL_COLORS)

        val data = BarData(barDataSet)
        barChart.data = data


        //draw chart
        barChart.invalidate()

        // Inflate the layout for this fragment
        return binding?.root
    }

    // simulate api call
    // we are initialising it directly
    private fun getScoreList(): ArrayList<Score> {
        scoreList.add(Score("John", 56))
        scoreList.add(Score("Rey", 75))
        scoreList.add(Score("Steve", 85))
        scoreList.add(Score("Kevin", 45))
        scoreList.add(Score("Jeff", 63))

        return scoreList
    }

    data class Score(
        val name:String,
        val score: Int,
    )

    private fun initBarChart() {


//        hide grid lines
        barChart.axisLeft.setDrawGridLines(true)
        val xAxis: XAxis = barChart.xAxis
        xAxis.setDrawGridLines(true)
        xAxis.setDrawAxisLine(true)

        //remove right y-axis
        barChart.axisRight.isEnabled = true

        //remove legend
        barChart.legend.isEnabled = true


        //remove description label
        barChart.description.isEnabled = true


        //add animation
        barChart.animateY(3000)

        // to draw label on xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM_INSIDE
        xAxis.valueFormatter = MyAxisFormatter()
        xAxis.setDrawLabels(true)
        xAxis.granularity = 1f
        xAxis.labelRotationAngle = +90f

    }

    inner class MyAxisFormatter : IndexAxisValueFormatter() {

        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            val index = value.toInt()
            Log.d(TAG, "getAxisLabel: index $index")
            return if (index < scoreList.size) {
                scoreList[index].name
            } else {
                ""
            }
        }
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FoodFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}