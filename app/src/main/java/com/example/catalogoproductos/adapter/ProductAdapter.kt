package com.example.catalogoproductos.adapter

import android.app.AlertDialog
import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.catalogoproductos.R
import com.example.catalogoproductos.data.Product
import kotlinx.android.synthetic.main.item_product.view.*

class ProductAdapter(private val products: MutableList<Product>, private val callback: (Product, String) -> Unit): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(itemView: View, val callback: (Product, String) -> Unit) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Product) {
            itemView.name_product.text = item.name
            itemView.description_product.text = item.description
            itemView.stock_product.text = item.stock.toString()
            itemView.delete_product.setOnClickListener {
                callback(item, "Delete")
            }
            itemView.edit_product.setOnClickListener {
                callback(item, "Edit")
            }
            itemView.add_stock.setOnClickListener {
                callback(item, "Plus")
            }
            itemView.remove_stock.setOnClickListener {
                callback(item, "Minus")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun addProduct (product: Product) {
        products.add(product)
        notifyDataSetChanged()
    }

    fun deleteProduct (product: Product) {
        products.remove(product)
        notifyDataSetChanged()
    }

    fun editProduct (product: Product, newName: String, newDescription: String) {
        val index = products.indexOf(product)
        products[index].name = newName
        product.description = newDescription
        notifyDataSetChanged()
    }

    fun changeStock(product: Product, number: Int) {
        val index = products.indexOf(product)
        products[index].stock = number
        notifyDataSetChanged()
    }
}