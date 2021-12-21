package com.brasileirao.presentation.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brasileirao.R
import com.brasileirao.databinding.HighlightsListElementBinding
import com.brasileirao.domain.model.Highlight

class HighlightsListAdapter : RecyclerView.Adapter<HighlightsListAdapter.HighlightViewHolder>() {

    private var highlights: List<Highlight> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighlightViewHolder {
        val binding =
            HighlightsListElementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HighlightViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HighlightViewHolder, position: Int) {
        with(holder.binding) {
            with(highlights[position]) {
                highlightText.text = description
                val stringFormat = root.context.getString(R.string.minute_highlight)
                highlightTime.text = String.format(stringFormat, minute)
            }
        }
    }

    override fun getItemCount() = highlights.size

    fun updateData(newHighlights: List<Highlight>) {
        highlights = newHighlights
        notifyDataSetChanged()
    }

    inner class HighlightViewHolder(val binding: HighlightsListElementBinding) :
        RecyclerView.ViewHolder(binding.root)
}