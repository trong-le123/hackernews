package atomicrobot.com.hackernews.view

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import atomicrobot.com.hackernews.R
import atomicrobot.com.hackernews.adapter.PostAdapter
import atomicrobot.com.hackernews.data.model.Post
import atomicrobot.com.hackernews.data.networking.APIClient
import atomicrobot.com.hackernews.data.networking.APIConstants
import retrofit2.Response
import retrofit2.Call
import retrofit2.Callback

class MainActivity : AppCompatActivity() {

    private var postAdapter: PostAdapter? = null
    private val post = ArrayList<Post>()

    var shared: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        shared = getSharedPreferences("post", Context.MODE_PRIVATE)
        val recyclerView = findViewById<RecyclerView>(R.id.storyview)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        postAdapter = PostAdapter(this, post)
        recyclerView.adapter = postAdapter

        if (shared!!.all == null) {
            fetchData()
        } else {
            loadData()
        }
    }

    private fun fetchData() {
        val editor = shared!!.edit()
        val set = HashSet<String>()
        progress(true)
        val apiInterface = APIClient.client.create(APIConstants::class.java)
        val getTopStories = apiInterface.getTopStories()

        getTopStories.enqueue(object : Callback<List<Long>> {
            override fun onResponse(call: Call<List<Long>>?, response: Response<List<Long>>?) {
                for (i in 0 until 15) {
                    set.add(response!!.body()!![i].toString())
                    editor.putStringSet("postId", set)
                    editor.apply()
                    val getStoryItem = apiInterface.getStoryItem(response!!.body()!![i])
                    getStoryItem.enqueue(object : Callback<Post> {
                        override fun onResponse(call: Call<Post>, response: Response<Post>) {
                            post.add(response.body()!!)
                            progress(false)
                            postAdapter?.notifyDataSetChanged()
                        }

                        override fun onFailure(call: Call<Post>, t: Throwable) {
                            Log.e("Response error", "error")
                        }
                    })
                }
            }

            override fun onFailure(call: Call<List<Long>>?, t: Throwable?) {
                Log.e("Response error", "error")
            }
        })
    }

    private fun loadData() {
        val apiInterface = APIClient.client.create(APIConstants::class.java)
        val data = shared!!.getStringSet("postId", null)!!
        for (i in 0 until data.size) {
            Log.e("Jesus", data.elementAt(i))
            val getStoryItem = apiInterface.getStoryItem(data.elementAt(i).toLong())
            getStoryItem.enqueue(object : Callback<Post> {
                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                    post.add(response.body()!!)
                    progress(false)
                    postAdapter?.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<Post>, t: Throwable) {
                    Log.e("Response error", "error")
                }
            })
        }
    }

    private fun progress(bool: Boolean) {
        val progressBar = findViewById<ProgressBar>(R.id.progress_bar)

        if (bool) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

}

