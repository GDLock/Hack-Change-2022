package com.otkritie.hackaton.screens.chatmenu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.otkritie.hackaton.R
import com.otkritie.hackaton.data.remote.model.Question

class QuestionAdapter : RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {

    var listQ = emptyList<Question>()

    class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.questions_item_layout, parent, false)
        return QuestionViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val title = holder.itemView.findViewById<TextView>(R.id.tv_title_quest)
        val avatar = holder.itemView.findViewById<TextView>(R.id.iv_avatar)
        title.text = listQ[position].title


    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}