package com.example.myapplication

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentResolver
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.provider.ContactsContract
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.data.Contact
import com.example.myapplication.databinding.ContactlistItemGridBinding
import com.example.myapplication.data.ContactManager
import com.example.myapplication.databinding.FragmentContactDetailBinding
import com.example.myapplication.viewpaperadapter.ViewPagerFragmentAdapter
import kotlin.properties.Delegates

@Suppress("DEPRECATION")
class ContactDetailFragment : Fragment(), MainActivity.onBackPressedLisener {
     private var handler: Handler = Handler()//Notification delay적용을 위한 Handler
    private var mActivity: MainActivity?= null
    lateinit var requestLauncher: ActivityResultLauncher<Intent>
    private lateinit var binding:FragmentContactDetailBinding
    var dataUpdateListener: DataUpdateListener?= null

    private fun addData(contact:Contact){
        Log.d("losttest!!#$@#!#", "$contact")
        dataUpdateListener?.onDataUpdated(contact)
    }
    private fun updateDate(contact:Contact,position: Int){
        Log.d("update","$contact"+"$position")
        dataUpdateListener?.updateContact(contact,position)
        Log.d("dataUpdateListener","$dataUpdateListener")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var isMyPage = false
        // Inflate the layout for this fragment
        var selectedContact = arguments?.getParcelable<Contact>("selectedContact")
        var selectedPositition = arguments?.getInt("position")

        binding = FragmentContactDetailBinding.inflate(layoutInflater)
        binding.messageBtnDetail.setOnClickListener {

        }
        // 뒤로가기 버튼 클릭 리스너
        binding.pagebackBtn.setOnClickListener {
            onbackPressed()
        }

        // 퍼미션 허용했는지 확인
        val status = ContextCompat.checkSelfPermission(requireContext(), "android.permission.READ_CONTACTS")
        if (status == PackageManager.PERMISSION_GRANTED) {
            Log.d("test", "permission granted")
        } else {
            // 퍼미션 요청 다이얼로그 표시
            ActivityCompat.requestPermissions(requireActivity(), arrayOf<String>("android.permission.READ_CONTACTS"), 100)
            Log.d("test", "permission denied")
        }

            // 다이얼로그(Dialog)에서 수정된 정보를 Fragment로 전달하는 콜백 설정

        // ActivityResultLauncher 초기화, 결과 콜백 정의
        requestLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == AppCompatActivity.RESULT_OK) {
                Log.d("test", "Uri : ${it.data!!.data!!}")
                var contentResolver:ContentResolver = requireContext().contentResolver
                val cursor = contentResolver.query(
                    it.data!!.data!!,
                    arrayOf<String>(
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                        ContactsContract.CommonDataKinds.Phone.NUMBER,
                        // ContactsContract.Contacts.PHOTO_URI,
                        // ContactsContract.Contacts._ID,
                    ),
                    null,
                    null,
                    null
                )
                Log.d("test", "cursor size : ${cursor?.count}")


                if (cursor!!.moveToFirst()) {
                    val name = cursor.getString(0)
                    val phone = cursor.getString(1)
                    // val photoURI = cursor.getString(2)
                    // val id = cursor.getString(3)
                    Toast.makeText(requireContext(), "$name, $phone", Toast.LENGTH_SHORT).show()
                    var contect = Contact(null, name, phone, "없음", false)
                   addData(contect)
                }
            }
        }

        binding.phoneBookDetail.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI)
            requestLauncher.launch(intent)
        }

        val contact = Contact(null, "디폴트 네임", "010-0000-0000", "email@email.com", false)


        if(selectedContact==null){
            val user = ContactManager.user
            isMyPage = true
            selectedContact = contact
            binding.linearLayoutCallBtn.visibility = View.GONE
            binding.linearLayoutAlertBtn.visibility = View.GONE
            binding.nameDetail.text = user.name
            binding.phoneNumberDetail.text = user.phone
            binding.emailDetail.text = user.email
            binding.profileImageDetail.setImageURI(user.image)

        }else {
            isMyPage = false
            binding.nameDetail.text = selectedContact.name
            binding.phoneNumberDetail.text = selectedContact.phone
            binding.emailDetail.text = selectedContact.email
            binding.profileImageDetail.setImageURI(selectedContact.image)
            binding.linearLayoutPhoneBook.visibility = View.GONE
            binding.pagebackBtn.visibility=View.VISIBLE
        }

        // 수정 버튼 클릭 리스너
        binding.updateBtn.setOnClickListener {
            if (!isMyPage) {
                val fragmentManager = requireActivity().supportFragmentManager
                val test = selectedContact
                val position = selectedPositition

                val updateNumberDialog = UpdateNumberDialog.newInstance(test!!, position!!, "DetailPage")
                updateNumberDialog.testContact = object : UpdateNumberDialog.EditContact {
                    override fun editContact(contact: Contact, position: Int) {
                        // 수정된 정보를 받아서 처리
                        updateContactInfo(contact, position)
//                    ContactManager.editContact(position,contact)
                    }
                    override fun editContact(contact: Contact) {

                    }
                }
                updateNumberDialog.show(fragmentManager, "UpdateNumberDialog")
                Toast.makeText(context, "message", Toast.LENGTH_SHORT).show()

            }
            else {
                val test = ContactManager.user
                val fragmentManager = requireActivity().supportFragmentManager
                val updateNumberDialog = UpdateNumberDialog.newInstance(test,"MyPage")
                updateNumberDialog.testContact = object : UpdateNumberDialog.EditContact {
                    override fun editContact(contact: Contact, position: Int) {

                    }
                    override fun editContact(contact: Contact) {
                        binding.nameDetail.text = contact.name
                        binding.phoneNumberDetail.text = contact.phone
                        binding.emailDetail.text = contact.email
                        binding.profileImageDetail.setImageURI(contact.image)

                        ContactManager.updateUser(
                            Contact(contact.image,contact.name,contact.phone,contact.email,false)
                        )
                    }

                }
                updateNumberDialog.show(fragmentManager, "UpdateNumberDialog")
            }
        }


        binding.fiveMinBtn.setOnClickListener{
            //5분 버튼이 눌렸을 때
            handler.postDelayed({
                //notification 작동. delay 5초 구현(시연을 위해 5초로 설정)
                notification(selectedContact.name)
            },5000)
        }

        binding.thirtyMinBtn.setOnClickListener{
            //30분 버튼이 눌렸을 때
            handler.postDelayed({
                //notification 작동. delay 30분 구현
                notification(selectedContact.name)
            },1800000)
        }
        binding.oneHourBtn.setOnClickListener{
            //1시간 버튼이 눌렸을 때
            handler.postDelayed({
                //notification 작동. delay 30분 구현
                notification(selectedContact.name)
            },3600000)
        }
        binding.offBtn.setOnClickListener{
            //off버튼이 눌렸을 때 handler안 콜백과 메세지 초기화
            handler.removeCallbacksAndMessages(null)
        }


        binding.callBtnDetail.setOnClickListener {
            Toast.makeText(context, "call", Toast.LENGTH_SHORT).show()
            val tell = "tel:" + selectedContact.phone.split("-").joinToString("")
            val callIntent = Intent(Intent.ACTION_DIAL, Uri.parse(tell))
            startActivity(callIntent)
        }

        return binding.root
    }

    fun updateContactInfo(updatedContact: Contact,position: Int) {
        binding.nameDetail.text = updatedContact.name
        binding.phoneNumberDetail.text = updatedContact.phone
        binding.emailDetail.text = updatedContact.email

        updateDate(updatedContact,position)
    }



    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is MainActivity){
            mActivity = context
        }
    }


    fun notification(name:String){
        val manager = mActivity?.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val builder: NotificationCompat.Builder

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            //26버전 이상
            val channelId="channel"
            val channelName="Number Channel"
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply{
                description = "My Channel One Description"
                setShowBadge(true)
                val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                val audioAttributes = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build()
                setSound(uri, audioAttributes)
                enableVibration(true)
            }
            manager.createNotificationChannel(channel)
            builder = NotificationCompat.Builder(mActivity!!, channelId)

        }else{
            builder = NotificationCompat.Builder(mActivity!!)
        }

//        val bitmap = BitmapFactory.decodeResource(resources,R.drawable.contactdetailfragment_phonecall)
        val intent = Intent(mActivity,MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(mActivity,0,intent,PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
        builder.run{
            setSmallIcon(R.mipmap.ic_launcher)
            setWhen(System.currentTimeMillis())
            setContentTitle(name+"에게 전화해야해요!")
            setContentText(name+"에게 전화할 시간이 되었어요!")
//            setLargeIcon(bitmap)
            addAction(R.mipmap.ic_launcher,"Action",pendingIntent)
        }
        manager.notify(11,builder.build())
    }



    // 다이얼로그에서 퍼미션 허용했는지 확인
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.d("test", "permission granted")
        } else {
            Log.d("test", "permission denied")
        }
    }

    override fun onbackPressed() {
        requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
    }



}