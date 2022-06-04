package com.example.vezdekodpin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.vezdekodpin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object {
        const val PIN_CODE = "pincode"
    }

    private lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val storage = PinCodeSaver()


//        binding.saveButton.setOnClickListener {
//            if (binding.pinCodeEditor.text.toString().length < 4) {
//                Toast.makeText(this, "PASSWORD!", Toast.LENGTH_SHORT).show()
//            } else {
//                storage.makePinSaving(this, PIN_CODE, binding.pinCodeEditor.text.toString())
//                binding.pinCodeEditor.text.clear()
//            }
//        }
//
//        binding.loadButton.setOnClickListener {
//            binding.pinViewer.text = storage.takeSavedPin(this, PIN_CODE)
//        }
    }


}