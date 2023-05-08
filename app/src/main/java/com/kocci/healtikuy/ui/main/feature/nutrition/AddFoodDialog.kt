package com.kocci.healtikuy.ui.main.feature.nutrition

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.kocci.healtikuy.R
import com.kocci.healtikuy.core.domain.model.Nutrition
import com.kocci.healtikuy.core.util.helper.PointsManager
import com.kocci.healtikuy.databinding.DialogNutritionAddFoodBinding
import com.kocci.healtikuy.util.extension.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class AddFoodDialog : DialogFragment() {

    private var _binding: DialogNutritionAddFoodBinding? = null
    private val binding get() = _binding!!

    val viewModel: AddDialogViewModel by viewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val materialAlertDialog = MaterialAlertDialogBuilder(requireActivity())
//        val view = layoutInflater.inflate(R.layout.dialog_nutrition_add_food, null)
        _binding = DialogNutritionAddFoodBinding.inflate(requireActivity().layoutInflater)
        materialAlertDialog.apply {
            setView(binding.root)
            setTitle("Add Food")
            setMessage("Add your food like vegetables or fruits!")
        }

        return materialAlertDialog.create()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddFood.setOnClickListener {
            val foodName = binding.etFoodName.text.toString()
            val category = binding.actvFoodCategory.text.toString()
            val inputError = validateInput(foodName)
            if (inputError == null) {
                runBlocking {
                    viewModel.addFood(Nutrition(foodName, category))
                    showToast("You got ${PointsManager.NUTRITION_POINT} points!")
                    dismiss()
                }
            } else {
                showToast(inputError)
            }
        }
    }

    private fun validateInput(str: String): String? {
        if (str.isEmpty()) {
            return "Empty string."
        } else if (str.length < 3) {
            return "please input a valid name"
        }
        return null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        _binding = null
    }
}