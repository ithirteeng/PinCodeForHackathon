package com.example.vezdekodpin

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.vezdekodpin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object {
        const val PIN_CODE = "pincode"
        const val NUMBERS_AMOUNT = 4
    }

    private lateinit var binding: ActivityMainBinding
    private var currentPinCode = ""
    private var mainPinCode = ""
    private var pinSpace = arrayListOf(false, false, false, false)
    private var isPinCreated = false
    private lateinit var pinArray: ArrayList<ImageView>
    private val storage = PinCodeSaver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pinArray = arrayListOf(
            binding.firstPinView,
            binding.secondPinView,
            binding.thirdPinView,
            binding.fourthPinView
        )

        if (storage.takeSavedPin(this, PIN_CODE) == null) {
            isPinCreated = false
            binding.pinCodeText.text = this.resources.getString(R.string.pincodeCreateText)
        } else {
            isPinCreated = true
            binding.pinCodeText.text = this.resources.getString(R.string.pincodeEnterText)
        }
        buttonClickListener()
        refreshButtonCLickEvent()

    }
    private fun refreshButtonCLickEvent() {
        binding.newPasswordButton.setOnClickListener {
            for (i in 0 until NUMBERS_AMOUNT) {
                binding.buttonDelete.performClick()
            }
            for (i in 0 until NUMBERS_AMOUNT) {
                pinSpace[i] = false
            }
            isPinCreated = false
            binding.pinCodeText.text = this.resources.getString(R.string.pincodeCreateText)
            mainPinCode = ""
            currentPinCode = ""
            storage.clearPinCode(this)
        }
    }

    private fun buttonClickListener() {
        for (buttonId in binding.buttonsGroup.referencedIds) {
            val button = findViewById<View>(buttonId)
            button.setOnClickListener {
                var space = check()
                if (button == binding.buttonDelete) {
                    if (space != NUMBERS_AMOUNT) {
                        space += 1
                        pinSpace[NUMBERS_AMOUNT - space] = false
                        check()
                        currentPinCode = currentPinCode.substring(0, currentPinCode.length - 1)
                    }
                } else if (button == binding.buttonTick) {
                    if (space != 0) {
                        makeToast(resources.getString(R.string.numbersLengthError2))
                    } else {
                        if (isPinCreated) {
                            if (currentPinCode == storage.takeSavedPin(this, PIN_CODE).toString()) {
                                makeToast(resources.getString(R.string.correctPin))
                            } else {
                                makeToast(resources.getString(R.string.wrongPin))
                            }
                        } else {
                            binding.pinCodeText.text = this.resources.getString(R.string.pincodeEnterText)
                            isPinCreated = true
                            mainPinCode = currentPinCode
                            storage.savePin(this, PIN_CODE, mainPinCode)
                            makeToast(resources.getString(R.string.savedPinText))
                            for (i in 0 until NUMBERS_AMOUNT) {
                                binding.buttonDelete.performClick()
                            }
                        }
                    }
                } else {
                    if (space == 0) {
                        makeToast(resources.getString(R.string.numbersLengthError))
                    } else {
                        pinSpace[NUMBERS_AMOUNT - space] = true
                        check()
                        when (button) {
                            binding.buttonOne -> {
                                currentPinCode += binding.buttonOne.text.toString()
                            }
                            binding.buttonTwo -> {
                                currentPinCode += binding.buttonTwo.text.toString()
                            }
                            binding.buttonThree -> {
                                currentPinCode += binding.buttonThree.text.toString()
                            }
                            binding.buttonFour -> {
                                currentPinCode += binding.buttonFour.text.toString()
                            }
                            binding.buttonFive -> {
                                currentPinCode += binding.buttonFive.text.toString()
                            }
                            binding.buttonSix -> {
                                currentPinCode += binding.buttonSix.text.toString()
                            }
                            binding.buttonSeven -> {
                                currentPinCode += binding.buttonSeven.text.toString()
                            }
                            binding.buttonEight -> {
                                currentPinCode += binding.buttonEight.text.toString()
                            }
                            binding.buttonNine -> {
                                currentPinCode += binding.buttonNine.text.toString()
                            }
                            binding.buttonZero -> {
                                currentPinCode += binding.buttonZero.text.toString()
                            }
                        }

                    }
                }
                Log.e("Password", currentPinCode)
            }
        }
    }

    private fun check(): Int {
        var counter = 0
        for (i in 0 until NUMBERS_AMOUNT) {
            if (!pinSpace[i]) {
                counter++
                pinArray[i].setImageResource(R.drawable.empty_password_circle)
            } else {
                pinArray[i].setImageResource(R.drawable.filled_password_circle)
            }
        }
        return counter
    }

    private fun makeToast(string: String) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
    }


}