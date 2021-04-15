package com.example.catalogoproductos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catalogoproductos.adapter.ProductAdapter
import com.example.catalogoproductos.data.Product
import com.example.catalogoproductos.dialogs.ProductDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // lateinit asegura a android que se inicializara adelante
    private lateinit var adapter: ProductAdapter
    lateinit var products: MutableList<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Call init functions
        this.callButtons()

        this.setupList()

    }

    private fun callButtons() {
        main_button_add.setOnClickListener {
            val dialog = ProductDialog(this, "", "") { name, description ->
                addProduct(name, description)
            }
            dialog.show()
        }
    }

    private fun setupList(){
        products = mutableListOf<Product>()

        adapter = ProductAdapter(products) { item, action ->
            if (action == "Delete") {
                val builder = AlertDialog.Builder(this)
                builder.setTitle(getString(R.string.title_delete))
                builder.setMessage(getString(R.string.subtitle_delete))
                builder.setPositiveButton(R.string.agree) { _, _ ->
                    this.deleteProduct(item)
                }
                builder.setNegativeButton(getString(R.string.cancel), null)
                builder.setCancelable(false)
                builder.show()
            } else if (action == "Edit") {
                this.editProductDialog(item)
            } else if (action == "Plus") {
                this.changeStock(item, 1)
            } else if (action == "Minus") {
                this.changeStock(item, -1)
            }

        }
        main_rv_products.adapter = adapter
        main_rv_products.layoutManager = LinearLayoutManager(this)
    }

    private fun addProduct (name: String, description: String) {
        val product = Product(name, description, 0)
        adapter.addProduct(product)
    }

    private fun deleteProduct (product: Product) {
        adapter.deleteProduct(product)
    }

    private fun editProductDialog (product: Product) {
        val dialog = ProductDialog(this, product.name, product.description) { name, description ->
            adapter.editProduct(product, name, description)
        }
        dialog.show()
    }

    private fun changeStock(product: Product, number: Int) {
        if (product.stock+number >= 0) {
            adapter.changeStock(product, product.stock+number)
        }
    }

}