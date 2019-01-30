package atomicrobot.com.hackernews.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User {


    @SerializedName("about")
    @Expose
    private var about: String? = null
    @SerializedName("created")
    @Expose
    private var created: Int? = 0
    @SerializedName("id")
    @Expose
    private var id: String? = null
    @SerializedName("karma")
    @Expose
    private var karma: Int? = 0
    @SerializedName("submitted")
    @Expose
    private var submitted: List<Int>? = null

    fun getAbout(): String? {
        return about
    }

    fun setAbout(about: String) {
        this.about = about
    }

    fun getCreated(): Int? {
        return created
    }

    fun setCreated(created: Int?) {
        this.created = created
    }

    fun getId(): String? {
        return id
    }

    fun setId(id: String) {
        this.id = id
    }

    fun getKarma(): Int? {
        return karma
    }

    fun setKarma(karma: Int?) {
        this.karma = karma
    }

    fun getSubmitted(): List<Int>? {
        return submitted
    }

    fun setSubmitted(submitted: List<Int>) {
        this.submitted = submitted
    }


}