package eu.tutorials.workout07

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import eu.tutorials.workout07.databinding.ItemExerciseStatusBinding

class exerciseStatusAdapter(val items:ArrayList<exerciseModel>)
    :RecyclerView.Adapter<exerciseStatusAdapter.ViewHolder>()
{
        inner class ViewHolder(binding: ItemExerciseStatusBinding)
            :RecyclerView.ViewHolder(binding.root)
        {
                val tvItem=binding.tvItem
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemExerciseStatusBinding
            .inflate(LayoutInflater.from(parent.context),parent,false))
        // this will return our own view holder which we have created above as inner class
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model:exerciseModel=items[position]
        // having the whole arraylist stored in this model variable
        holder.tvItem.text=model.getid().toString()
       //setting the text of tvitem which is inside our holder to the
        // current exercise number
        when{
            model.getisSelected()->
            {
                holder.tvItem.background= ContextCompat.getDrawable(holder.itemView.context,
                    R.drawable.item_circular_recyclerview_color_selected_background)
                holder.tvItem.setTextColor(Color.parseColor("#212121"))
            }
            model.getisCompleted()->
            {
                holder.tvItem.background= ContextCompat.getDrawable(holder.itemView.context,
                    R.drawable.item_circular_color_accent_bg)
                holder.tvItem.setTextColor(Color.parseColor("#FFFFFF"))
            }
            else->{
                holder.tvItem.background= ContextCompat.getDrawable(holder.itemView.context,
                    R.drawable.item_circular_recyclerview_color_grey_background)
                holder.tvItem.setTextColor(Color.parseColor("#212121"))
            }
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }
}