package com.example.catalogoproductos.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.res.TypedArrayUtils.getText
import com.example.catalogoproductos.R
import kotlinx.android.synthetic.main.dialog_product.*

class ProductDialog(context: Context, val name: String, val description: String, val callback: (String, String) -> Unit) : Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_product)

        dialog_product_name.setText(name)
        dialog_product_description.setText(description)

        dialog_product_button.setOnClickListener {
            val name = dialog_product_name.text.toString()
            val description = dialog_product_description.text.toString()

            if (name.isNotEmpty() && description.isNotBlank()) {
                callback(name, description)
                dismiss()
            } else {
                Toast.makeText(context, R.string.verification_empty_product, Toast.LENGTH_LONG).show()
            }

        }

    }
}