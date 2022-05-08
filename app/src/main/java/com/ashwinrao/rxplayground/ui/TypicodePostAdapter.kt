package com.ashwinrao.rxplayground.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ashwinrao.rxplayground.data.model.TypicodePost
import com.ashwinrao.rxplayground.databinding.ViewHolderPostBinding

class TypicodePostAdapter(val onPostClicked: ((Int?) -> Unit)) :
    ListAdapter<TypicodePost, TypicodePostAdapter.TypicodeVH>(TypicodePostDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypicodeVH =
        TypicodeVH(
            ViewHolderPostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: TypicodeVH, position: Int) {
        currentList[position]?.let {
            holder.binding.postTitle.text = it.title
            holder.binding.postAuthor.text = it.userId.toString()
            holder.binding.postPublishDate.text = it.id.toString()
            holder.binding.postSummary.text = it.body
        }
    }

    inner class TypicodeVH(val binding: ViewHolderPostBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.postCard.setOnClickListener {
                onPostClicked(getItem(adapterPosition).id)
            }
        }
    }
}

class TypicodePostDiff : DiffUtil.ItemCallback<TypicodePost>() {
    override fun areItemsTheSame(oldItem: TypicodePost, newItem: TypicodePost): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: TypicodePost, newItem: TypicodePost): Boolean =
        oldItem.body.equals(newItem.body) ||
                oldItem.title.equals(newItem.title) ||
                oldItem.id?.equals(newItem.id) ?: false ||
                oldItem.userId?.equals(newItem.userId) ?: false
}