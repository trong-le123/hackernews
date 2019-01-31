package atomicrobot.com.hackernews.adapter

import android.content.Context
import android.support.v4.text.HtmlCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import atomicrobot.com.hackernews.R
import atomicrobot.com.hackernews.data.model.Comments
import butterknife.ButterKnife
import org.ocpsoft.prettytime.PrettyTime
import java.util.*

class CommentAdapter(private val context: Context, private var commentList: ArrayList<Comments?>) : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_comment_cardview, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.username.text = commentList[position]?.getBy()
        holder.postTime.text = PrettyTime().format(Date(commentList[position]?.getTime()?.toLong()!! * 1000))
        if(commentList[position]?.getText() != null) {
            holder.comment.text = HtmlCompat.fromHtml(commentList[position]!!.getText()!!, 0)
        } else {
            holder.comment.text = "No Comments"
        }
    }

    override fun getItemCount(): Int {
        return this.commentList.size
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val username: TextView = itemView.findViewById(R.id.post_user)
        val postTime: TextView = itemView.findViewById(R.id.post_time)
        val comment: TextView = itemView.findViewById(R.id.comment_body)

        init {
            ButterKnife.bind(this, itemView)

            itemView.setOnClickListener{ Toast.makeText(context, "selected", Toast.LENGTH_SHORT).show()}
        }
    }
}
