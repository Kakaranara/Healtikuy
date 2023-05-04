package com.kocci.healtikuy.ui.main.feature.exercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.kocci.healtikuy.core.util.helper.TipsManager
import com.kocci.healtikuy.databinding.FragmentExerciseBinding
import com.kocci.healtikuy.ui.dialog.tips.withdialog.TipsBSheetDialogWithTabs

class ExerciseFragment : Fragment() {
    private var _binding: FragmentExerciseBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarExercise.setupWithNavController(findNavController())

        binding.btnExerciseCardio.setOnClickListener {
            val direction = ExerciseFragmentDirections.actionExerciseFragmentToCardioListFragment()
            findNavController().navigate(direction)
        }

        binding.exerciseTips.setOnClickListener {
//            TipsDialogBSheet(TipsManager.generateExerciseTips()).show(
//                childFragmentManager,
//                "tips"
//            )
            TipsBSheetDialogWithTabs(TipsManager.generateExerciseTips())
                .show(childFragmentManager, "tips")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExerciseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}