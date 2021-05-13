package com.example.android_internship

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_check_boxes.*
import kotlinx.android.synthetic.main.fragment_input_fields.imageViewBack


/**
 * A simple [Fragment] subclass.
 */
class CheckBoxes : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_check_boxes, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        hideOtherFragments()
        backFragment()
        listenCheckBoxes()
        sliderListener()
        radioButtonsListener()

    }

    /**
     * A method that hides other fragments from the screen
     */
    private fun hideOtherFragments() {
        fragment.view?.setBackgroundColor(Color.WHITE);
    }

    /**
     * A method in which when you click on the back button in the
     * upper left corner, you return to the main fragment
     */
    private fun backFragment() {
        imageViewBack.setOnClickListener() {
            val fragment: Fragment = MainFragment()
            changeFragment(fragment)
        }
    }

    /**
     * add listeners for all checkboxes
     */
    private fun listenCheckBoxes() {
        listenFirstCheckBox()
        listenSecondCheckBox()
        listenThirdCheckBox()
    }


    /**
     * The method that implements changing the fragment
     * to the one specified in the parameters
     */
    private fun changeFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = this.requireActivity().supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment, fragment)
        this.requireActivity().supportFragmentManager.executePendingTransactions();
        fragmentTransaction.commit()
    }


    /**
     * When you click on the 1st checkbox, the 2nd and 3rd are disabled
     */
    private fun listenFirstCheckBox() {
        checkBox1.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                checkBox2.isEnabled = false
                checkBox3.isEnabled = false
            } else {
                checkBox2.isEnabled = true
                checkBox3.isEnabled = true
            }
        })
    }

    private fun listenSecondCheckBox() {
        checkBox2.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { _, isChecked ->
            if (isChecked)
                disableOrEnableRadioButtons(radioGroup, false)
            else disableOrEnableRadioButtons(radioGroup, true)
        })
    }

    private fun listenThirdCheckBox() {
        checkBox3.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { _, isChecked ->
            slider.isEnabled = !isChecked
        })
    }


    /**
     * The method that makes the specified radio group changeable or not
     */
    private fun disableOrEnableRadioButtons(radioGroup: RadioGroup, enable_or_disable: Boolean) {
        for (i in 0 until radioGroup.childCount) {
            (radioGroup.getChildAt(i) as RadioButton).isEnabled = enable_or_disable
        }
    }

    /**
     * The value of the slider changes depending on the radio buttons
     */
    private fun radioButtonsListener() {
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radioButton -> slider.value = 1f
                R.id.radioButton2 -> slider.value = 2f
                R.id.radioButton3 -> slider.value = 3f
            }
        }
    }

    /**
     * The value of the radio buttons changes depending on the slider
     */
    private fun sliderListener() {
        slider.addOnChangeListener { _, value, _ ->
            when (value) {
                0f -> {
                    radioButton.isChecked = false
                    radioButton2.isChecked = false
                    radioButton3.isChecked = false
                }
                1f -> radioButton.isChecked = true
                2f -> radioButton2.isChecked = true
                3f -> radioButton3.isChecked = true
            }
        }
    }

}