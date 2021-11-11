package com.newtouch.motion.entity

/**
 * Created by Dan on 2021/10/22.
 */
class CodeEnergyEntity {
    var content: MutableList<DataBean>? = null

    class DataBean {
        var productId: String? = null
        var productName: String? = null
        var productLang: String? = null
        var productPrice: Double = 0.0
        var productOriginalPrice: Double = 0.0
        var status: Int = 0
        var storageAttachments: MutableList<DataBeans>? = null

        var cstCreate: String? = null
        var cstCreateTimestamp: Long = 0
        var createUserId: String? = null
        var authorNickname: String? = null
        var authorHeadImgUrl: String? = null
        var countBrowses: Int = 0
        var bought: Boolean = false

        class DataBeans {
            var attachmentName: String? = null
            var sortNum: Int = 0
            var attachmentKey: String? = null
            var attachmentKeyThumbnail: String? = null
        }
    }
}