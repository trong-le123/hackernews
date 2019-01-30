package atomicrobot.com.hackernews.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Comments {

    @SerializedName("by")
    @Expose
    private var by: String? = null
    @SerializedName("id")
    @Expose
    private var id: Int? = 0
    @SerializedName("kids")
    @Expose
    private var kids: List<Int>? = null
    @SerializedName("parent")
    @Expose
    private var parent: Int? = 0
    @SerializedName("text")
    @Expose
    private var text: String? = null
    @SerializedName("time")
    @Expose
    private var time: Int? = 0
    @SerializedName("type")
    private var type: String? = null

    fun getBy(): String? {
        return by
    }

    fun setBy(by: String) {
        this.by = by
    }

    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?) {
        this.id = id
    }

    fun getKids(): List<Int>? {
        return kids
    }

    fun setKids(kids: List<Int>) {
        this.kids = kids
    }

    fun getParent(): Int? {
        return parent
    }

    fun setParent(parent: Int?) {
        this.parent = parent
    }

    fun getText(): String? {
        return text
    }

    fun setText(text: String) {
        this.text = text
    }

    fun getTime(): Int? {
        return time
    }

    fun setTime(time: Int?) {
        this.time = time
    }

    fun getType(): String? {
        return type
    }

    fun setType(type: String) {
        this.type = type
    }
}