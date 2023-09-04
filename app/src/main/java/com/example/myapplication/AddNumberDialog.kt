package com.example.myapplication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//        private fun selectGallery(){
//        val permissionList = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
//        val checkPermission = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){ result ->
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
    fun isNumberic(s : String):Boolean{
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

}