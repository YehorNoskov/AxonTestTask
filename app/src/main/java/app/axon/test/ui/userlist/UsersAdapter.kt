package app.axon.test.ui.userlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.axon.test.R
import app.axon.test.utills.ImageLoader
import kotlinx.android.synthetic.main.user_item.view.*


class UsersAdapter : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    private val items: ArrayList<UserModel> = java.util.ArrayList()
    private lateinit var clickListener: ClickListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.user_item, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = items[position]
        holder.bind(model)
    }



    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(user: UserModel){
            itemView.tvUserName.text = user.userName
            ImageLoader.load(user.userAvatarMedium, itemView.ivAvatar)
            itemView.tvUserEmail.text = user.userEmail

            itemView.linearLayout.setOnClickListener {
                clickListener.onItemClicked(user)
            }

        }
    }

    override fun getItemCount(): Int = items.size


    fun setItems(items: List<UserModel>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun appendItems(items: List<UserModel>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onItemClicked(userModel: UserModel)
    }

    fun setClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }
}