package atomicrobot.com.hackernews.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.LinearLayout
import atomicrobot.com.hackernews.R
import atomicrobot.com.hackernews.adapter.CommentAdapter
import atomicrobot.com.hackernews.data.model.Comments
import atomicrobot.com.hackernews.data.networking.APIClient
import atomicrobot.com.hackernews.data.networking.APIConstants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentActivity : AppCompatActivity() {

    private var commentAdapter: CommentAdapter? = null
    private val comment = ArrayList<Comments?>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)

        val recyclerView = findViewById<RecyclerView>(R.id.comments)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        commentAdapter = CommentAdapter(this, comment)
        recyclerView.adapter = commentAdapter

        val bundle = intent.getIntegerArrayListExtra("commentIds")
        fetchData(bundle)
    }

    private fun fetchData(commentList: ArrayList<Int>) {
        val apiInterface = APIClient.client.create(APIConstants::class.java)
        for(i in 0 until commentList.size-1){
            val getComments = apiInterface.getCommentItem(commentList[i])
            getComments.enqueue(object: Callback<Comments> {
                override fun onResponse(call: Call<Comments>, response: Response<Comments>) {
                    comment.add(response.body())
                    commentAdapter?.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<Comments>, t: Throwable) {
                    Log.e("Response error", "error")
                }

            })
        }

    }

}