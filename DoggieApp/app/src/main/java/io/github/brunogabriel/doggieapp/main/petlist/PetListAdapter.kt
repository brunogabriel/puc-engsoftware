package io.github.brunogabriel.doggieapp.main.petlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.brunogabriel.doggieapp.R
import io.github.brunogabriel.doggieapp.shared.extensions.loadImage
import io.github.brunogabriel.doggieapp.shared.models.Pet
import kotlinx.android.synthetic.main.holder_pet.view.*

/**
 * Created by brunogabriel on 2019-06-15.
 */
class PetListAdapter(private val pets: List<Pet>) : RecyclerView.Adapter<PetListAdapter.PetViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.holder_pet, parent, false)
        return PetViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pets.size
    }

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        holder.bind(pets[position])
    }
    inner class PetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(pet: Pet) = with(itemView) {
            if (pet.images?.isNotEmpty() == true) {
                pet_image.loadImage(pet.images[0])
            } else {
                // TODO: add if empty image
            }
            pet_name.text = pet.name
            pet_services_text.text = "13 Serviços" // TODO: vir no mock
        }
    }
}