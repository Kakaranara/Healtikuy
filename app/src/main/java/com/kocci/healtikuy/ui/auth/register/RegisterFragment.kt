package com.kocci.healtikuy.ui.auth.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kocci.healtikuy.core.data.remote.model.Async
import com.kocci.healtikuy.core.domain.usecase.RegisterForm
import com.kocci.healtikuy.databinding.FragmentRegisterBinding
import com.kocci.healtikuy.util.extension.gone
import com.kocci.healtikuy.util.extension.showToast
import com.kocci.healtikuy.util.extension.visible
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RegisterFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RegisterViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnRegister.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnRegister -> {
                val email = binding.etRegisterEmail.text.toString()
                val password = binding.etRegisterPassword.text.toString()
                val username = binding.etUsername.text.toString()
                val form = RegisterForm(email, password, username)
                viewModel.registerUserWithEmailAndPassword(form)
                    .observe(viewLifecycleOwner) {
                        when (it) {
                            is Async.Error -> {
                                binding.progressRegisterIndicator.gone()
                                showToast(it.msg)
                            }
                            Async.Loading -> {
                                binding.progressRegisterIndicator.visible()
                            }
                            is Async.Success -> {
                                binding.progressRegisterIndicator.gone()
                                showToast(it.toString())
                            }
                        }
                    }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}