package com.newtouch.motion.entity

class HotCodeListEntity {

    var total: Int = 0
    var pageName: Int = 0
    var list: MutableList<DataBean>? = null

    class DataBean {
        var id: Int = 0
        var categoryId: Int = 0
        var name: String? = null
        var fullName: String? = null
        var login: String? = null
        var avatarUrl: String? = null
        var htmlUrl: String? = null
        var createdAt: String? = null
        var updatedAt: String? = null
        var pushedAt: String? = null
        var cloneUrl: String? = null
        var size: Int = 0
        var starCount: Int = 0
        var forksCount: Int = 0
        var topics: String ? = null
        var description: String? = null
        var language:  String? = null
        var reposId: Long = 0


    }
}
