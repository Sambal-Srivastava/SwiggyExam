package com.app.swiggyexam.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.app.swiggyexam.data.model.MockResponseDto
import com.app.swiggyexam.databinding.RowMovieItemsBinding
import com.bumptech.glide.Glide

class MoviesAdapter(
    private val mList: List<MockResponseDto.MockData>,
    private val mContext: Context,
    private val listener: OnItemClickListener
) :
    Adapter<MoviesAdapter.ViewHolder>() {

    private var filteredUserList =
        mList.toMutableList()


    inner class ViewHolder(val rowMovieItemsBinding: RowMovieItemsBinding) :
        RecyclerView.ViewHolder(rowMovieItemsBinding.root), View.OnClickListener {
        init {
            itemView.setOnClickListener(this) // Set the click listener on the item view
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(
                    position,
                    filteredUserList.get(position).imdbId!!
                ) // Invoke the listener method
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RowMovieItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return filteredUserList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentMovie = filteredUserList[position]
        holder.rowMovieItemsBinding.tvMovieName.text = currentMovie.title
        Glide.with(mContext)
            .load(currentMovie.poster)
            .into(holder.rowMovieItemsBinding.ivMovie)
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, movieId: String)
    }
}