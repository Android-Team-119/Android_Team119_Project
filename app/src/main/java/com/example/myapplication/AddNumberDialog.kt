package com.example.myapplication

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.example.myapplication.databinding.DialogAddNumberBinding
import java.lang.NumberFormatException
import java.util.regex.Pattern


class AddNumberDialog: DialogFragment() {
    private var _binding: DialogAddNumberBinding? = null
    private val binding get() = _binding!!
    private val email =
        "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
    private val pickImageActivityResult =//갤러리에서 선택한 사진 적용
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            with(binding){
                if (result.resultCode == Activity.RESULT_OK) {
                    val data: Intent? = result.data
                    if (data != null) {
                        val selectedImageUri: Uri? = data.data
                        if (selectedImageUri != null) {
                            binding.profileImg.setImageURI(selectedImageUri)
                        } else {
                            Toast.makeText(activity, "사진을 가져오는데 실패했습니다.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

        }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogAddNumberBinding.inflate(inflater,container,false)
        val view = binding.root
        binding.profileImg.setOnClickListener{
        }
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }
    private fun initView(){
        with(binding){
            checkEditBox()
            onPressSaveBtn()
            onPressCancelBtn()
            onPressImgView()

        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun isNumberic(s : String):Boolean{
        return try{
            s.toDouble()
            true
        }catch(e:NumberFormatException){
            false
        }
    }
    private fun onPressSaveBtn(){
        with(binding){
            btnSave.setOnClickListener{//Save버튼 눌렀을 시 실행
                if(inputName.text!!.isEmpty()){
                    inputNameLayout.error ="이름을 입력해주세요"
                }else if(inputNumber.text!!.isEmpty()){
                    inputNumberLayout.error="전화번호를 입력해주세요"
                }else if(inputEmail.text!!.isEmpty()){
                    inputEmailLayout.error="이메일을 입력해주세요"
                }
            }
        }
    }
    private fun onPressCancelBtn(){
        with(binding){
            btnCancel.setOnClickListener{//Cancel버튼 눌렀을 시 Dialog 종료
                dismiss()//Dialog 종료 함수
            }
        }
    }
    private fun checkEditBox(){
        //유효성
//    이름 : 빈칸만 없게
//    이메일 : 이메일 정규식 private val email =
//"^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
//    전화번호 : 빈칸만 없게, 숫자 확인,
        with(binding){
            inputName.addTextChangedListener(object: TextWatcher{
                override fun beforeTextChanged(a: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(a: Editable?) {
                    if(a.toString().isEmpty()){
                        inputNameLayout.error ="이름을 입력해주세요"
                    }else{
                        inputNameLayout.error = null
                    }
                }
            }
            )
            inputNumber.addTextChangedListener(object: TextWatcher{
                override fun beforeTextChanged(a: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(a: Editable?) {
                    if(a.toString().isEmpty()){
                        inputNumberLayout.error ="전화번호를 입력해주세요"
                    }else if(!isNumberic(a.toString())){
                        inputNumberLayout.error="숫자만 입력해주세요"
                    }else{
                        inputNumberLayout.error= null
                    }
                }
            }
            )
            inputEmail.addTextChangedListener(object: TextWatcher{
                override fun beforeTextChanged(a: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(a: Editable?) {
                    val p = Pattern.matches(email,a.toString())
                    if(a.toString().isEmpty()){
                        inputEmailLayout.error ="전화번호를 입력해주세요"
                    }else if(!p){
                        inputEmailLayout.error="이메일 형식을 맞춰주세요"
                    }else{
                        inputEmailLayout.error= null
                    }
                }
            }
            )
        }
    }
    private fun onPressImgView(){
        with(binding){
            profileImg.setOnClickListener {
                when {
                    // 갤러리 접근 권한이 있는 경우
                    ContextCompat.checkSelfPermission(
                        requireActivity(),
                        android.Manifest.permission.READ_EXTERNAL_STORAGE
                    ) == PackageManager.PERMISSION_GRANTED -> {
                        navigateGallery()
                    }
                    // 갤러리 접근 권한이 없는 경우
                    shouldShowRequestPermissionRationale(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    -> {
                        showPermissionContextPopup()
                    }
                    // 권한 요청(requestPermissions) -> 갤러리 접근(onRequestPermissionResult)
                    else -> requestPermissions(
                        arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                        1000
                    )
                }
            }
        }
        }
    private fun navigateGallery() {//갤러리에서 사진 보는 함수
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        pickImageActivityResult.launch(intent)
    }
    // 권한 요청 팝업
    private fun showPermissionContextPopup() {//권한부여 Dialog 생성
        AlertDialog.Builder(activity)
            .setTitle("권한을 부여해주세요")
            .setMessage("권한을 부여해주세요")
            .setPositiveButton("권한 부여") { _, _ ->
                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1000)
            }
            .setNegativeButton("취소") { _, _ -> }
            .create()
            .show()
    }

    }

