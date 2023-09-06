package com.example.myapplication

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE

import android.content.Intent
import android.graphics.BitmapFactory
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.Contact
import com.example.myapplication.databinding.ContactlistItemGridBinding
import com.example.myapplication.databinding.FragmentContactDetailBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

private lateinit var binding:FragmentContactDetailBinding

@Suppress("DEPRECATION")
class ContactDetailFragment : Fragment() {
    private var mActivity: MainActivity?= null// Context에 MainActivty를 넣기 위해 만든 함수
    private var handler: Handler = Handler()//Notification delay적용을 위한 Handler
    private var statusFiveMinBtn = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentContactDetailBinding.inflate(layoutInflater)
        binding.messageBtnDetail.setOnClickListener {
          Toast.makeText(context, "message", Toast.LENGTH_SHORT).show()


        }


        val contact = Contact(null, "디폴트 네임", "010-0000-0000", "email@email.com", false)
        var selectedContact = arguments?.getParcelable<Contact>("selectedContact")

        if(selectedContact==null){
            selectedContact = contact
            binding.linearLayoutCallBtn.visibility = View.GONE
        }

        binding.nameDetail.text = selectedContact.name
        binding.phoneNumberDetail.text = selectedContact.phone
        binding.emailDetail.text = selectedContact.email

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
//    inner class FiveMinAlarmThread: Thread() {
//        private var time = 0
//        private var name = arguments?.getParcelable<Contact>("selectedContact")?.name.toString()
//        override fun run() {
//            while(handlerThread.isAlive){
//                //HandlerThread가 살아있는동안 실행
//                Handler(handlerThread.looper).post{
//                    sleep(5000)
//                    notification(name)
//                    handlerThread.quitSafely()
//                    stopThread()
//                }
//            }
//        }
//        fun stopThread(){
//            fiveMinAlarmThread = null
//        }
//
//    }

}