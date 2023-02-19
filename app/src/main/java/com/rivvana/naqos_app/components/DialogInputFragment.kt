package com.rivvana.naqos_app.components

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.rivvana.naqos_app.R
import com.rivvana.naqos_app.databinding.FragmentDialogInputBinding
import com.rivvana.naqos_app.payment.PembayaranActivity
import java.text.SimpleDateFormat
import java.util.*

class DialogInputFragment : DialogFragment(){
    private var _binding : FragmentDialogInputBinding? = null
    private val binding get() = _binding!!
    val arrSpinnerBook = arrayOf("Per Hari", "Per Minggu", "Per Bulan")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDialogInputBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        binding.tvHarga.text = "500"
        initSpinner()
        btnSewa()

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        binding.etDate.setOnClickListener{
            var cal = Calendar.getInstance()
            val dpd = DatePickerDialog(this.requireActivity(), DatePickerDialog.OnDateSetListener {
                    view, year, monthOfYear, dayOfMonth ->

                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "dd/MM/yyyy" // mention the format you need
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                // Display Selected date in textbox
                binding.etDate.text = "${sdf.format(cal.time)}"
//

            }, year, month, day)
            dpd.show()
        }

        return binding.root
    }

    private fun btnSewa() {
        binding.btnSewa.setOnClickListener {
            val intent = Intent(requireActivity(), PembayaranActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initSpinner() {
        val arrayAdapter = ArrayAdapter<String>(requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, arrSpinnerBook)
        binding.spinnerDate.adapter = arrayAdapter
        binding.spinnerDate.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, parent: View?, position: Int, Id: Long) {
                if (position == 0){
                    binding.tvTotalHarga.text = "Rp.30.000"
                } else if (position == 1){
                    binding.tvTotalHarga.text = "Rp.300.000"
                } else {
                    binding.tvTotalHarga.text = "Rp.1.000.000"
                }

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }

    companion object {

    }
}