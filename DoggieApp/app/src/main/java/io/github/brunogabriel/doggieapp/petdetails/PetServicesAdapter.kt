package io.github.brunogabriel.doggieapp.petdetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.brunogabriel.doggieapp.R
import io.github.brunogabriel.doggieapp.shared.models.Service
import kotlinx.android.synthetic.main.holder_service.view.*
import java.text.NumberFormat
import java.util.*

/**
 * Created by brunogabriel on 2019-06-19.
 */
class PetServicesAdapter(private val services: List<Service>) : RecyclerView.Adapter<PetServicesAdapter.PetServiceViewHolder>() {

    private val currencyFormatter = NumberFormat.getCurrencyInstance(Locale("pt", "br"))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetServiceViewHolder {
        return PetServiceViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.holder_service, parent, false))
    }

    override fun getItemCount(): Int {
        return services.size
    }

    override fun onBindViewHolder(holder: PetServiceViewHolder, position: Int) {
        holder.bind(services[position])
    }

    inner class PetServiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(service: Service) = with(itemView) {
            service_type_text.text = service.type
            duration_text.text = "${service.date} | ${context.getString(R.string.minutes_pattern, service.duration)}"
            price_text.text = currencyFormatter.format(service.price)
            owner_text.text = service.employee

            if (service.products != null && service.products.isNotEmpty()) {
                products.visibility = View.VISIBLE
                products_text.apply {
                    visibility = View.VISIBLE
                    text = service.products.joinToString("\n")
                }
            } else {
                products.visibility = View.GONE
                products_text.visibility = View.GONE
            }

            if (service.annotations != null && service.annotations.isNotEmpty()) {
                annotations.visibility = View.VISIBLE
                annotations_text.apply {
                    visibility = View.VISIBLE
                    text = service.annotations.joinToString("\n")
                }
            } else {
                annotations.visibility = View.GONE
                annotations_text.visibility = View.GONE
            }
        }
    }
}