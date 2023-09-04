package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.example.myapplication.databinding.DialogAddNumberBinding

class AddNumberDialog: DialogFragment() {
    private var _binding: DialogAddNumberBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogAddNumberBinding.inflate(inflater,container,false)
        binding.profileImg.setOnClickListener{

        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun selectGallery(){
        //var writePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.)

    }


}