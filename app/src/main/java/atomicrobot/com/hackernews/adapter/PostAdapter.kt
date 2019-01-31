package atomicrobot.com.hackernews.adapter

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import atomicrobot.com.hackernews.R
import atomicrobot.com.hackernews.data.model.Post
import atomicrobot.com.hackernews.view.CommentActivity
import atomicrobot.com.hackernews.view.LocalActivity
import butterknife.ButterKnife

class PostAdapter(private val context: Context, private var postList: ArrayList<Post>) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_post_cardview, parent, false)

        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return this.postList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = postList[position].getTitle()
        holder.points.text = postList[position].getScore().toString()
        holder.author.text = postList[position].getBy()
        holder.viewStory.setOnClickListener {
            if(postList[position].getUrl() != null) {
                viewStoryBrowser(postList[position].getUrl()!!)
            } else {
                val map = HashMap<String, String>()
                map["title"] = postList[position].getTitle().toString()
                map["author"] = postList[position].getBy().toString()
                map["time"] = postList[position].getTime().toString()
                map["body"] = postList[position].getText().toString()
                viewStoryLocal(map)
            }
        }
        holder.commentsButton.setOnClickListener {
            commentView(postList[position].getKids()!!)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.post_title)
        val points: TextView = itemView.findViewById(R.id.post_points)
        val author: TextView = itemView.findViewById(R.id.post_author)
        val viewStory: LinearLayout = itemView.findViewById(R.id.container_post)
        val commentsButton: TextView = itemView.findViewById(R.id.view_comments)

        init {
            ButterKnife.bind(this, itemView)

            itemView.setOnClickListener { Toast.makeText(context, "selected", Toast.LENGTH_SHORT).show() }
        }
    }

    private fun viewStoryBrowser(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        try {
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException){
            Toast.makeText(context, "There was a problem opening the link, please try again", Toast.LENGTH_SHORT).show()
        }
    }

    private fun viewStoryLocal(post: HashMap<String, String>) {
        val intent = Intent(context, LocalActivity::class.java)
        intent.putExtra("post", post)
        ContextCompat.startActivity(context, intent, null)
    }

    private fun commentView(commentIds: ArrayList<Int>) {
        val intent = Intent(context, CommentActivity::class.java)
        intent.putExtra("commentIds", commentIds)
        ContextCompat.startActivity(context, intent, null)
    }
}
