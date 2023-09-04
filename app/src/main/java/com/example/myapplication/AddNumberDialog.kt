package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.example.myapplication.databinding.DialogAddNumberBinding
import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat

class AddNumberDialog: DialogFragment() {
    private var _binding: DialogAddNumberBinding? = null
    private val binding get() = _binding!!
    private var fileAbsolutePath: String?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogAddNumberBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
//        binding.profileImg.setOnClickListener{
//
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    private fun selectGallery(){
//        val permissionList = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
//        val checkPermission = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){result ->
//            result.forEach{
//                if(!it.value){
//                    Toast.makeText(this,"권한 동의 필요!",Toast.LENGTH_SHORT).show()
//
//                }
//            }
//        }
//        private val readImage = registerForActivityResult(ActivityResultContracts.GetContent()){
//            ee
//        }
//
//
//    }



}