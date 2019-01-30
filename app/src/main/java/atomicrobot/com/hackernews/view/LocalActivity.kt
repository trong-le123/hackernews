package atomicrobot.com.hackernews.view

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.widget.TextView
import atomicrobot.com.hackernews.R

class LocalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local_story)
        val bundle = intent.getSerializableExtra("post") as HashMap<String, String>
        setData(bundle)
    }

    private fun setData(data: HashMap<String, String>) {
        val user = findViewById<TextView>(R.id.post_user)
        user.text = data["author"]
        val time = findViewById<TextView>(R.id.post_time)
        time.text = data["time"]
        val title = findViewById<TextView>(R.id.story_title)
        title.text = data["title"]
        val storyBody = findViewById<TextView>(R.id.story_body)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            storyBody.text = Html.fromHtml(data["body"], Html.FROM_HTML_MODE_LEGACY).toString()
        } else {
            storyBody.text = Html.fromHtml(data["body"]).toString()
        }
    }
}

