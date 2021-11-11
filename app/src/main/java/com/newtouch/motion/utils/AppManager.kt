package com.newtouch.motion.utils

import com.newtouch.motion.constants.Constants
import com.newtouch.motion.event.LogoutEvent
import org.greenrobot.eventbus.EventBus
import android.content.ClipboardManager
import android.content.Context
import com.common.baselibrary.utils.PrefUtils
import com.common.baselibrary.utils.ToastUtils


/**
 * des app管理类
 *
 */
class AppManager {
    companion object{
        /**
         * 登录状态
         */
        fun isLogin():Boolean {
            return PrefUtils.getBoolean(Constants.LOGIN, false)
        }

        /**
         * 退出登录，重置用户状态
         */
        fun resetUser() {
            //发送退出登录消息
            EventBus.getDefault().post(LogoutEvent())
            PrefUtils.setBoolean(Constants.LOGIN, false)
            PrefUtils.removeKey(Constants.USER_INFO)
        }

        /**
         * 复制剪切板
         */
        fun copy(context: Context,msg:String){
            var clip = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            clip.text = msg
            ToastUtils.show("已复制")
        }
    }
}