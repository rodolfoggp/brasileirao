package com.brasileirao.presentation.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brasileirao.databinding.GamesListElementBinding
import com.brasileirao.domain.model.Game
import com.bumptech.glide.Glide
import com.core.view.timeString
import com.core.view.weekDayAndDateString

class GamesListAdapter(
    val onClickAction: (Long) -> Unit,
) : RecyclerView.Adapter<GamesListAdapter.GameViewHolder>() {

    private var games: List<Game> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val binding =
            GamesListElementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val glide = Glide.with(holder.binding.root.context)
        with(holder.binding) {
            with(games[position]) {
                team1Name.text = team1Performance.team
                team2Name.text = team2Performance.team
                team1Score.text = team1Performance.score.toString()
                team2Score.text = team2Performance.score.toString()
                date.text = dateTime.weekDayAndDateString()
                time.text = dateTime.timeString()
                glide.load(team1Performance.badge).into(team1Badge)
                glide.load(team2Performance.badge).into(team2Badge)
            }
        }
    }

    override fun getItemCount() = games.size

    fun updateData(newGames: List<Game>) {
        games = newGames
        notifyDataSetChanged()
    }

    inner class GameViewHolder(val binding: GamesListElementBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(v: View?) = onClickAction(games[layoutPosition].id)
    }
}