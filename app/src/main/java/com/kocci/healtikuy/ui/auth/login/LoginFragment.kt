package com.kocci.healtikuy.ui.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kocci.healtikuy.core.data.remote.model.Async
import com.kocci.healtikuy.core.domain.usecase.LoginForm
import com.kocci.healtikuy.databinding.FragmentLoginBinding
import com.kocci.healtikuy.util.extension.gone
import com.kocci.healtikuy.util.extension.showToast
import com.kocci.healtikuy.util.extension.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnToRegister.setOnClickListener(this)
        binding.btnLogin.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnToRegister -> {
                val gotoRegister = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
                findNavController().navigate(gotoRegister)
            }
            binding.btnLogin -> {
                val email = binding.etLoginEmail.text.toString()
                val password = binding.etLoginPassword.text.toString()
                val loginForm = LoginForm(email, password)
                viewModel.loginWithEmailAndPassword(loginForm).observe(viewLifecycleOwner) {
                    when (it) {
                        Async.Loading -> {
                            binding.progressBarLogin.visible()
                        }
                        is Async.Error -> {
                            showToast(it.msg)
                            binding.progressBarLogin.gone()
                        }
                        is Async.Success -> {
                            binding.progressBarLogin.gone()
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
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}