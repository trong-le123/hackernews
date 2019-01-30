package atomicrobot.com.hackernews.data.model


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class Post {

    @SerializedName("by")
    @Expose
    private var by: String? = null
    @SerializedName("descendants")
    @Expose
    private var descendants: Int? = 0
    @SerializedName("id")
    @Expose
    private var id: Int? = 0
    @SerializedName("kids")
    @Expose
    private var kids: ArrayList<Int>? = null
    @SerializedName("score")
    @Expose
    private var score: Int? = null
    @SerializedName("text")
    @Expose
    private var text: String? = null
    @SerializedName("time")
    @Expose
    private var time: Int? = null
    @SerializedName("title")
    @Expose
    private var title: String? = null
    @SerializedName("type")
    @Expose
    private var type: String? = null
    @SerializedName("url")
    @Expose
    private var url: String? = null

    fun getBy(): String? {
        return by
    }

    fun setBy(by: String) {
        this.by = by
    }

    fun getDescendants(): Int? {
        return descendants
    }

    fun setDescendants(descendants: Int?) {
        this.descendants = descendants
    }

    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?) {
        this.id = id
    }

    fun getKids(): ArrayList<Int> {
        return kids!!
    }

    fun setKids(kids: ArrayList<Int>) {
        this.kids = kids
    }

    fun getScore(): Int? {
        return score
    }

    fun setScore(score: Int?) {
        this.score = score
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

    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String) {
        this.title = title
    }

    fun getType(): String? {
        return type
    }

    fun setType(type: String) {
        this.type = type
    }

    fun getUrl(): String? {
        return url
    }

    fun setUrl(url: String) {
        this.url = url
    }

}